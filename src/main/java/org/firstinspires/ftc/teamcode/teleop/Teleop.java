package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

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
    private float grabberFast = .01f;
    private float grabberSlow = .005f;
    private float grabberSpeed = grabberFast;

    // LinAc
    private float outLinAcSpeed = .6f;
    private float inLinAcSpeed = -.5f;


    public void updateLift() {
        this.log("Lift Position", lift.getCurrentPosition() + "");
        this.log("Lift Speed", "Up: " + upLiftSpeed + " | Down: " + downLiftSpeed);

        // Make the lift go slower on down, due to gravity influence
        if (lift.getCurrentPosition() <= -1) {
            if (gunnerRightY < 0) {
                lift.setPower(0);
                return;
            }
        } else if (lift.getCurrentPosition() >= 3220) {
            if (gunnerRightY > 0) {
                lift.setPower(0);
                return;
            }
        }

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
            position += grabberSpeed;
        } else if (gunnerLeftX < -.1){
            position -= grabberSpeed;
        }

        if (gunner.left_stick_button) {
            position = .53;
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
            if (linAc.getCurrentPosition() <= 3700) {
                linAc.setPower(outLinAcSpeed);
            }
        } else if (gunner.a) {
            if (linAc.getCurrentPosition() >= 0) {
                linAc.setPower(inLinAcSpeed);
            }
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

        if (gunner.left_trigger >= .9) {
            grabberRight.setPosition(rightOpenFull);
            grabberLeft.setPosition(leftOpenFull);
        }

        if (gunner.right_trigger >= .9) {
            grabberRight.setPosition(rightCloseFull);
            grabberLeft.setPosition(leftCloseFull);
        }

        if (gunner.right_bumper) {
            grabberRight.setPosition(rightCloseHalf);
            grabberLeft.setPosition(leftCloseHalf);
        }

        this.updateDriveMotors();


        if (gunner.start) {
            this.log("Resetting horizontal", "-");
            linAc.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            linAc.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        if (gunner.back) {
            this.log("Resetting lift", "-"); // 3700 linac max out, 0 in
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        if (gunner.x) {
            grabberSpeed = grabberSlow;
        }

        if (gunner.b) {
            grabberSpeed = grabberFast;
        }
        //this.log("Grabber Left", grabberLeft.getPosition() + "");
       // this.log("Grabber Right", grabberRight.getPosition() + "");
    }
}
