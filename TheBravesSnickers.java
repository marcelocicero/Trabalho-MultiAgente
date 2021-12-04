package thebravessnicker;

import robocode.*;
import robocode.util.Utils;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.Color;
import java.io.IOException;

public class TheBravesSnickers extends TeamRobot {

    private GFGun gun = new GFGun ();
    private WaveSurfer body = new WaveSurfer();

    private static int GF_SIZE = 25;
    private static int GF_CENTER = (GF_SIZE - 1) / 2;

    private static int[] guessFactors = new int[GF_SIZE] ;

    private Point2D targetLocation;
    private ArrayList<EnemyWave> enemyWaves = new ArrayList<>();

    private static double[] dangerFactors = new double[GF_SIZE];

    private int hitWall = 0;

    public void run() {
        setColors(Color.BLACK, Color.ORANGE, Color.CYAN);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForRobotTurn(true);

        RobotColors color = new RobotColors();
        color.bodyColor = Color.white;
        color.gunColor = Color.white;
        color.radarColor = Color.black;
        color.scanColor = Color.red;
        color.bulletColor = Color.white;

        setBodyColor(color.bodyColor);
        setGunColor(color.gunColor);
        setRadarColor(color.radarColor);
        setScanColor(color.scanColor);
        setBulletColor(color.bulletColor);

        try {
            broadcastMessage(color);
        } catch (IOException ignored) {}

        int counter = 0;
        while (true) {
            if (getRadarTurnRemaining() == 0.0)
                setTurnRadarRightRadians(Double.POSITIVE_INFINITY);

            if (counter < 32)
                setAhead(100);
            else
                setBack(100);

            counter = (counter + 1) % 64;
            body.updateWaves();
            body.surf();
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        if (isTeammate(e.getName())) return;

        body.onScannedRobot(e);
        gun.onScannedRobot(e);
        double angleToEnemy = getHeadingRadians() + e.getBearingRadians();
        double turnToEnemy = Utils.normalRelativeAngle(angleToEnemy - getRadarHeadingRadians());
        double extraTurn = Math.atan(36.0 / e.getDistance()) * (turnToEnemy >= 0 ? 1 : -1);
        setTurnRadarRightRadians(turnToEnemy + extraTurn);

        
        double enemyBearing = this.getHeading() + e.getBearing();
        double enemyX = getX() + e.getDistance() * Math.sin(Math.toRadians(enemyBearing));
        double enemyY = getY() + e.getDistance() * Math.cos(Math.toRadians(enemyBearing));

        Point message = new Point(enemyX, enemyY);
        try { 
            broadcastMessage(message);
        } catch (IOException ex) {
            out.println("Unable to send order: ");
            ex.printStackTrace(out);
        }

    }

    public void onHitByBullet(HitByBulletEvent e) {
        double speed = Rules.getBulletSpeed(e.getBullet().getPower());
        Point2D location = new Point2D.Double(e.getBullet().getX(), e.getBullet().getY());
        EnemyWave hitWave = null;

        for (EnemyWave ew: enemyWaves) {
            boolean isClose = Math.abs(ew.distanceFromMe()) < 50;
            boolean hasSameSpeed = (ew.bulletSpeed - speed) < 0.001;
            if (isClose && hasSameSpeed) {
                hitWave = ew;
                break;
            }
        }

        if (hitWave != null) {
            body.updateFactors(hitWave, location);
            enemyWaves.remove(enemyWaves.lastIndexOf(hitWave));
        }
    }

   public void onHitWall(HitWallEvent e) {
       if (hitWall == 0) {
           setBack(50);
       } else if (hitWall == 1) {
           setTurnLeft(90);
           setAhead(50);
       } else {
           setAhead(50);
           hitWall = 0;
       }

       hitWall++;
   }

    public Point2D getMyLocation() {
        return new Point2D.Double(getX(), getY());
    }

    public double maxEscapeAngle(double power) { 
        return Math.asin(8 / Rules.getBulletSpeed(power)) ; 
    } 

    public double getAbsoluteBearing(ScannedRobotEvent e) { 
        return e.getBearingRadians() + getHeadingRadians(); 
    } 

    public double getAbsoluteBearing(Point2D source, Point2D target) { 
        return Math.atan2(target.getX() - source.getX(), target.getY() - source.getY());
    }

    public int getDirection(ScannedRobotEvent e) { 
        double bearing = getAbsoluteBearing(e) ; 
        double lateral = Math.sin(e.getHeadingRadians() - bearing);
        return (lateral * e.getVelocity()) < 0 ? -1 : 1; 
    }

    public double decideFirePower(ScannedRobotEvent e) {
        double firePower = getOthers() == 1 ? 2.0 : 3.0;

        if (e.getDistance() > 400) {
            firePower = 1.0;
        } else if (e.getDistance() < 200) {
            firePower = 3.0;
        }

        if (getEnergy() < 1) {
            firePower = 0.1;
        } else if (getEnergy() < 10) {
            firePower = 1.0;
        }

        return Math.min(e.getEnergy() / 4, firePower);
    }

    public Point2D project(Point2D source, double angle, double length) {
        return new Point2D.Double(
            source.getX() + Math.sin(angle) * length,
            source.getY() + Math.cos(angle) * length
        );
    }

    public void setupTeammates() {

    }

    /* Custom Gun */
    private class GFGun {
        
        public void onScannedRobot(ScannedRobotEvent e) {
            int direction = getDirection(e);
            double bearing = getAbsoluteBearing(e);
            double firePower = decideFirePower(e);
            double factor = getFactorFromIndex(getBestIndex());
            double angleOffset = (direction * factor * maxEscapeAngle(firePower));
            double gunTurn = Utils.normalRelativeAngle(bearing - getGunHeadingRadians() + angleOffset);
            targetLocation = project(new Point2D.Double(getX(), getY()), bearing, e.getDistance());
            setTurnGunRightRadians(gunTurn);
            if (setFireBullet(firePower) != null) {
                GFBullet gfBullet = new GFBullet(e, firePower);
                addCustomEvent(gfBullet);
            }
        }

        private int getBestIndex() { 
            int bestIndex = GF_CENTER; 
            for (int i = 0; i < GF_SIZE; i++)   { 
                if (guessFactors[i] > guessFactors[bestIndex]) bestIndex = i; 
            } 
            return bestIndex;
        }

        private double getFactorFromIndex(int index) { 
            return (double) (index - GF_CENTER) / GF_CENTER; 
        }
    } 

    /* Custom Bullet */
    private class GFBullet extends Condition {
        
        private Point2D gunLocation;
        private double bulletSpeed;
        private double bearing;
        private double lateralDirection;
        private double t0;

        public GFBullet(ScannedRobotEvent e, double power) {
            this.bearing = getAbsoluteBearing(e);
            this.lateralDirection = getDirection(e);
            this.bulletSpeed = Rules.getBulletSpeed(power);
            this.gunLocation = new Point2D.Double(getX(), getY());
            this.t0 = getTime();
        }

        public boolean test() {
            double distanceTraveled = (getTime() - t0) * bulletSpeed;
            double limit = gunLocation.distance(targetLocation) - 18;
            if (distanceTraveled > limit) {
                updateFactor();
                removeCustomEvent(this);
            }
            return false;
        }

        private void updateFactor() {
            double currentBearing = getAbsoluteBearing(gunLocation, targetLocation);
            double angle = Utils.normalRelativeAngle(currentBearing - bearing);
            double normalizedAngle = angle / lateralDirection * GF_SIZE;
            int index = (int) Math.round(normalizedAngle + GF_CENTER);
            int safeIndex = Math.max(0, Math.min(index, GF_SIZE-1));
            guessFactors[safeIndex]++;
        }
    }

    /* Defense */
    private class WaveSurfer {
        
        private ScannedRobotEvent lastScan = null;

        public void onScannedRobot(ScannedRobotEvent e) {
            if (lastScan != null) {
                double bulletPower = lastScan.getEnergy() - e.getEnergy();
                if (0.1 <= bulletPower && bulletPower <= 3.0) {
                    enemyWaves.add(new EnemyWave(lastScan, bulletPower));
                }
            }
            lastScan = e;
        }

        public void updateFactors(EnemyWave wave, Point2D location) {
            int index = wave.getFactorIndex(location);
            for (int i = 0; i < GF_SIZE; i++) {
                dangerFactors[i] += (1.0 / Math.pow(index - i, 2) + 1);
            }
        }

        public void updateWaves() {
            for (int i = 0; i < enemyWaves.size(); i++) {
                if (enemyWaves.get(i).test()) {
                    enemyWaves.remove(i);
                    i--;
                }
            }
        }

        public void surf() {
            EnemyWave closestWave = getClosestWave();
            if (closestWave != null) {
                goTo(closestWave.getSafestSpot());
            }
        }

        public EnemyWave getClosestWave() {
            double minDistance = Double.POSITIVE_INFINITY;
            EnemyWave closestWave = null;
            for (EnemyWave wave : enemyWaves) {
                double distance = wave.distanceFromMe();
                boolean isClosestWave = (distance < minDistance && distance > wave.getBulletSpeed());
                if (isClosestWave) {
                    closestWave = wave;
                    minDistance = distance;
                }
            }
            return closestWave;
        }

        public void goTo(Point2D spot) {
            int x = (int) spot.getX() - (int) getX();
            int y = (int) spot.getY() - (int) getY();
            double turn = Math.atan2(x, y);
            setTurnRightRadians(Math.tan(turn - getHeadingRadians()));
            setAhead(Math.hypot(x, y) * Math.cos(turn));
        }
    }

    private class EnemyWave {

        private Point2D gunLocation;
        private double bulletSpeed;
        private double bearing;
        private double lateralDirection;
        private double t0;

        public double getBulletSpeed() {
            return bulletSpeed;
        }

        public EnemyWave(ScannedRobotEvent e, double power) {
            this.bulletSpeed = Rules.getBulletSpeed(power);
            this.lateralDirection = getDirection(e);
            this.bearing = getAbsoluteBearing(e) + Math.PI;
            this.gunLocation = (Point2D.Double) targetLocation.clone();
            this.t0 = getTime() - 1;
        }

        public double distanceFromMe() {
            double radius = (getTime() - t0) * bulletSpeed;
            return gunLocation.distance(getMyLocation()) - radius;
        }

        public int getFactorIndex(Point2D location) {
            double offset = Utils.normalRelativeAngle(getAbsoluteBearing(gunLocation, location) - bearing);
            double factor = offset / maxEscapeAngle(bulletSpeed) * lateralDirection;
            int index = (int) Math.round(factor * GF_CENTER + GF_CENTER);
            return Math.max(0, Math.min(index, GF_SIZE - 1));
        }

        public Point2D getSafestSpot() {
            int safestIndex = GF_CENTER;
            for (int i = 0; i < GF_SIZE; i++) {
                if (dangerFactors[i] < dangerFactors[safestIndex]) {
                    safestIndex = i;
                }
            }

            double offset = (double) (safestIndex - GF_CENTER) / GF_CENTER;
            double angle = lateralDirection * offset * Math.asin(8 / bulletSpeed);
            double distance = gunLocation.distance(getMyLocation()) - 18;
            return project(gunLocation, angle + bearing, distance);
        }

        public boolean test() {
            return distanceFromMe() < -50;
        }
    }


}