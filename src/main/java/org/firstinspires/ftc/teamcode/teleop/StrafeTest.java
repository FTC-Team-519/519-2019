package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "StrafeTest2019", group = "Testing")
public class StrafeTest extends BaseOpMode {


    boolean UpBeingPressed;
    boolean DownBeingPressed;


    double[] corrections = new double[4];
    double speedRatio = 1.0d;

    int currMotorAdjust;

    public StrafeTest() {

    }

    public void init() {
        super.setupHardware();
        this.log("Initialized StrafeTest", "!");

        speedRatio = 1.0d;

        for (int i = 0; i < 4; i++)
            corrections[i] = 1.0;

        currMotorAdjust = 0;
        UpBeingPressed = false;
        DownBeingPressed = false;
    }

    public void init_loop() {

    }

    public void start() {

    }

    public void loop() {
        if (gamepad1.x) currMotorAdjust = 0;
        if (gamepad1.y) currMotorAdjust = 1;
        if (gamepad1.a) currMotorAdjust = 2;
        if (gamepad1.b) currMotorAdjust = 3;

        if (gamepad1.right_bumper) {
            speedRatio += 0.001d;
        }

        if (gamepad1.left_bumper) {
            speedRatio -= 0.001d;
        }

        if (gamepad1.dpad_up && !UpBeingPressed) {
            corrections[currMotorAdjust] += 0.025;
            UpBeingPressed = true;
        }
        else if (UpBeingPressed && !gamepad1.dpad_up) {
            UpBeingPressed = false;
        }

        if (gamepad1.dpad_down && !DownBeingPressed) {
            corrections[currMotorAdjust] -= 0.025;
            DownBeingPressed = true;
        }
        else if (DownBeingPressed && !gamepad1.dpad_down) {
            DownBeingPressed = false;
        }

        if(gamepad1.dpad_left) {
            strafeLeft(speedRatio, corrections[1], corrections[3], corrections[0], corrections[2]);
        }
        else if (gamepad1.dpad_right) {
            strafeRight(speedRatio, corrections[1], corrections[3], corrections[0], corrections[2]);
        }
        else {
            stopMoving();
        }

        telemetry.addData("frontleft: ", corrections[0]);
        telemetry.addData("frontright: ", corrections[1]);
        telemetry.addData("backleft: ", corrections[2]);
        telemetry.addData("backright: ", corrections[3]);
        telemetry.addData("speedRatio: ", speedRatio);

    }

    public void strafeLeft (double power, double fr, double br, double fl, double bl) {

        frontRight.setPower(fr*power);
        backRight.setPower(br*(-power));
        frontLeft.setPower(fl*(-power));
        backLeft.setPower(bl*power);
    }
    public void strafeRight (double power, double fr, double br, double fl, double bl) {
        frontRight.setPower(fr*(-power));
        backRight.setPower(br*power);
        frontLeft.setPower(fl*power);
        backLeft.setPower(bl*(-power));
    }

    public void stopMoving(){
        frontLeft.setPower(0.0d);
        frontRight.setPower(0.0d);
        backLeft.setPower(0.0d);
        backRight.setPower(0.0d);
    }
}
