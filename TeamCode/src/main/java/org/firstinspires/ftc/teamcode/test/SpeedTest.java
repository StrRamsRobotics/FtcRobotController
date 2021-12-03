package org.firstinspires.ftc.teamcode.test;

import static java.lang.Thread.sleep;

public class SpeedTest {

    private static double milliSec = (double)1000000L;

    private static double delta, endTime, currentTime;

    public static void main(String[] args)
    {

        boolean run = true;
        currentTime = System.nanoTime()/milliSec;

        double x = 0;
        double y = 0;

        double xSpeed = 30;
        double ySpeed = 30;

        delta = 0.016;

        while (run)
        {
            x += xSpeed * delta;
            y += ySpeed * delta;

            x = lerp(x, 0.0, 0.3 * delta);
            y = lerp(y, 0.0, 0.3 * delta);
            System.out.format("%,.2f\n%,.2f\n\n", x, y);

            try {
                Thread.sleep(16);
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }

    public static double lerp(double start, double stop, double rate)
    {
        return start+(stop-start)*rate;
    }

}
