package client;

import java.io.Serializable;

public final class Shape implements Serializable {

    private static final long serialVersionUID = 1L;

    private double x;
    private double y;

    public Shape() {
        x = 0.0;
        y = 0.0;
    }

    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}