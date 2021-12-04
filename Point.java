package thebravessnicker;

import java.io.Serializable;

public class Point implements Serializable {
    private static final long serialVersionUID = 1L;
    public double x = 0.0;
    public double y = 0.0;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}