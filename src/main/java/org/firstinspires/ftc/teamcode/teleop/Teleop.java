package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.BaseOpMode;

@TeleOp(name = "Teleop2019", group = "Testing")
public class Teleop extends BaseOpMode {

    public Teleop() {
        
    }

    public void init() {
        super.setupHardware();
    }

    public void init_loop() {

    }

    @Override
    public void start() {
        super.start();
    }

    // Lift
    private float upLiftSpeed = 1f;
    private float downLiftSpeed = .9f;

    // LinAc
    private float outLinAcSpeed = .6f;
    private float inLinAcSpeed = -.5f;

    // Grabber
    private float leftOpenFull = .1f;
    private float leftOpenHalf = .3f;
    private float leftCloseFull = 1f;
    private float leftCloseHalf = .4f;

    private float rightOpenFull = .9f;
    private float rightOpenHalf = .3f;
    private float rightCloseFull = 0f;
    private float rightCloseHalf = .4f;


    public void updateLift() {
        this.log("Lift Position", lift.getCurrentPosition() + "");
        this.log("Lift Speed", "Up: " + upLiftSpeed + " | Down: " + downLiftSpeed);

        // Make the lift go slower on down, due to gravity influence
        lift.setPower((gunnerRightY < 0) ? gunnerRightY*downLiftSpeed : gunnerRightY*upLiftSpeed);
}

    public double clamp(double val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    public float remap (float value, float from1, float to1, float from2, float to2) {
        return (value - from1) / (to1 - from1) * (to2 - from2) + from2;
    }

    double position = .53;
    public void updateGrabber() {
        this.log("Grabber Turn-Servo Reading", grabberTurn.getPosition() + "");

        if (gunnerLeftX > 0.1) {
            position += .01;
        } else if (gunnerLeftX < -.1){
            position -= .01;
        }

        if (gunner.left_stick_button) {
            position = .5;
        }

        position = clamp(position, .2f, .87f);
        grabberTurn.setPosition(position);
    }

    public void loop() {
        /*if (driver.a) { using = 0; }
        if (driver.b) { using = 1; }
        if (driver.x) { using = 2; }
        if (driver.y) { using = 3; }*/
        // Lift

        this.log("LinAc", linAc.getCurrentPosition() + "");
        if (gunner.y) {
            // LinAc out
            //linAc.setTargetPosition();
            linAc.setPower(outLinAcSpeed);
        } else if (gunner.a) {
            linAc.setPower(inLinAcSpeed);
        } else {
            linAc.setPower(0);
        }

        if (gunner.dpad_left) {
            upLiftSpeed = .8f;
            downLiftSpeed = .7f;
        }

        if (gunner.dpad_right) {
            upLiftSpeed = 1f;
            downLiftSpeed = .9f;
        }

        this.updateLift();
        this.updateGrabber();

        if (gunner.left_bumper) {
            grabberRight.setPosition(rightOpenFull);
            grabberLeft.setPosition(leftOpenFull);
        }

        if (gunner.right_trigger >= .9) {
            grabberRight.setPosition(rightCloseFull);
            grabberLeft.setPosition(leftCloseFull);
        }

        this.log("right trigger", gunner.right_trigger + "");
        /*if (gunner.right_trigger) {
            grabberRight.setPosition(rightCloseFull);
            grabberLeft.setPosition(leftCloseFull);
        }*/

        this.updateDriveMotors();

        this.log("Grabber Left", grabberLeft.getPosition() + "");
        this.log("Grabber Right", grabberRight.getPosition() + "");
    }
}
