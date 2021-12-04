package thebravessnicker;

import robocode.Droid;
import robocode.MessageEvent;
import robocode.TeamRobot;
import java.awt.geom.*;
import java.awt.Color;
import java.io.IOException;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class TheBravesSnickersDroid extends TeamRobot implements Droid {

    double battleFieldOffset = 120.0;
    double maxBattleFieldHeightForRobot = -1 * this.battleFieldOffset;
    double maxBattleFieldWidthForRobot  = -1 * this.battleFieldOffset;

    public void run() {
        this.maxBattleFieldHeightForRobot += getBattleFieldHeight();
        this.maxBattleFieldWidthForRobot += getBattleFieldWidth();

        while (true) {
            if (getGunHeat() == 0.0) setFire(3);
            setAhead(20);
            setTurnLeft(25);
            execute();
        }
    }

    public void onMessageReceived(MessageEvent e) {
        if (e.getMessage() instanceof Point) {
            Point p = (Point) e.getMessage();
            double dx = p.x - this.getX();
            double dy = p.y - this.getY();
            double theta = Math.toDegrees(Math.atan2(dx, dy));
            turnGunRight(normalRelativeAngleDegrees(theta - getGunHeading()));
            fire(3);
        } else if (e.getMessage() instanceof RobotColors) {
            RobotColors color = (RobotColors) e.getMessage();
            setBodyColor(color.bodyColor);
            setGunColor(color.gunColor);
            setRadarColor(color.radarColor);
            setScanColor(color.scanColor);
            setBulletColor(color.bulletColor);
        }
    }
}