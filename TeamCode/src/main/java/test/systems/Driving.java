package test.systems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import test.utils.Time;

public class Driving extends System {

    private final double FortiFiveInRads = -Math.PI/4, sin45 = Math.sin(FortiFiveInRads), cos45 = Math.cos(FortiFiveInRads);
    private DcMotor flm, frm, blm, brm;
    private double flv, frv, blv, brv;
    private double x1, x2, y1, y2, sx;
    private boolean strafe = false;
    private boolean slomo = false, pressed = false;


    public Driving(HardwareMap hw, Controller controller){
        super(hw, controller);
    }

    public void init() {
        flm = hw.dcMotor.get("flm");
        frm = hw.dcMotor.get("frm");
        blm = hw.dcMotor.get("blm");
        brm = hw.dcMotor.get("brm");

        flm.setDirection(DcMotorSimple.Direction.FORWARD);
        frm.setDirection(DcMotorSimple.Direction.REVERSE);
        blm.setDirection(DcMotorSimple.Direction.FORWARD);
        brm.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void update() {
        // strafing
        y1 = UserInput.left.left_stick_y;
        x1 = -UserInput.left.left_stick_x;

        // lerp stuff
        flv = lerp(flv, 0.0, .15);
        frv = lerp(frv, 0.0, .15);
        blv = lerp(blv, 0.0, .15);
        brv = lerp(brv, 0.0, .15);

        double speedy = Math.round(y1 * 10);
        
        if(speedy == 0) return;

        // rotate the movement vector by 45 degrees counter clockwise
        y2 = (x1*sin45 + y1*cos45) * Time.delta;
        x2 = (x1*cos45 - y1*sin45) * Time.delta;

        // apply movement to each motor
        /*
        x2 is applied to the



        double y = -gamepad1.left_stick_y; // Remember, this is reversed!
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio, but only when
        // at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        motorFrontLeft.setPower(frontLeftPower);
        motorBackLeft.setPower(backLeftPower);
        motorFrontRight.setPower(frontRightPower);
        motorBackRight.setPower(backRightPower);

         */

        flv += y2; frv += x2; blv += x2; brv += y2;

        // set power
        flm.setPower(flv);
        frm.setPower(frv);
        blm.setPower(blv);
        brm.setPower(brv);

    }

    public double clamp(double num, double min, double max){
        if (num < min) return min;
        if (num > max) return max;
        return num;
    }

    public double lerp(double a, double b, double c){
        return a + (b-a) * c;
    }
}
