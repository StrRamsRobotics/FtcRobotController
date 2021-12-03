package org.firstinspires.ftc.teamcode.math;

public class Vector2 {

    private double x;
    private double y;
    private double magnitude;
    private double angle;

    public Vector2(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.magnitude = pythagorean(x, y);
        this.angle = calcAngle(this);
    }

    public Vector2()
    {
        this.x = 0.0;
        this.y = 0.0;
        this.angle = 0.0;
        this.magnitude = 0.0;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getMagnitude()
    {
        return this.magnitude;
    }

    public void setMagnitude(double magnitude)
    {
        this.magnitude = magnitude;
    }

    public static double pythagorean(double x, double y)
    {
        return Math.sqrt(x*x + y*y);
    }

    // you need to have an x, y and mag set up to find a right angle
    public static double calcAngle(Vector2 a)
    {
        return Math.asin(a.getY()/a.getMagnitude());
    }

    // this will find a new direction when the two vectors are added
    public static Vector2 add(Vector2 a, Vector2 b)
    {
        return new Vector2(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public static Vector2 sub(Vector2 a, Vector2 b)
    {
        return new Vector2(a.getX() + b.getX(), a.getY() + b.getY());
    }

}
