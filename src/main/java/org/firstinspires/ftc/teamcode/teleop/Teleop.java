package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.BaseOpMode;

@TeleOp(name = "Teleop2019", group = "Competition")
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
    private float liftStallPower = .05f;

    // LinAc
    private float outLinAcSpeed = .6f;
    private float inLinAcSpeed = -.5f;

    boolean foundationIsDown = false;

    public void updateLift() {
        this.log("Lift Left Position", liftLeft.getCurrentPosition() + "");
        this.log("Lift Right Position", liftRight.getCurrentPosition() + "");
        this.log("Lift Speed", "Up: " + upLiftSpeed + " | Down: " + downLiftSpeed);

        boolean goingDown = (gunnerRightY < 0);
        boolean stalling = gunner.left_bumper;
/*
        if (stalling) {
            if (!goingDown) { // includes if neutral (0)
                this.log("Stalling", "-");
                liftLeft.setPower(liftStallPower);
                liftRight.setPower(liftStallPower);
                return;
            }
        }

        // Make the lift go slower on down, due to gravity influence
        if (lift.getCurrentPosition() <= -1) {
            if (gunnerRightY < 0) {
                this.log("At LiftEncoder Min", "!");
                lift.setPower(0);
                return;
            }
        } else if (lift.getCurrentPosition() >= 3220) {
            if (gunnerRightY > 0) {
                this.log("At LiftEncoder Max", "!");
                lift.setPower(0);
                return;
            }
        }

        if (stalling) {
            this.log("MOVING AT SPECIAL SPEED", gunnerRightY*(downLiftSpeed*.15) + "");
            lift.setPower(gunnerRightY*(downLiftSpeed*.15));
        } else {
            lift.setPower(goingDown ? gunnerRightY*downLiftSpeed : gunnerRightY*upLiftSpeed);
        }
*/
    }


    public double clamp(double val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    public float remap (float value, float from1, float to1, float from2, float to2) {
        return (value - from1) / (to1 - from1) * (to2 - from2) + from2;
    }

    double position = .53;
    public void updateGrabber() {
        //this.log("Grabber Turn-Servo Reading", grabberTurn.getPosition() + "");
        if (gunner.right_bumper) {
            grabberFront.setPosition(FRONT_GRABBER_DOWN);
        }

        if (gunner.left_bumper) {
            grabberFront.setPosition(FRONT_GRABBER_UP);
        }
    }

    double pos = 0;
    public void loop() {
        /*if (driver.a) { using = 0; }
        if (driver.b) { using = 1; }
        if (driver.x) { using = 2; }
        if (driver.y) { using = 3; }*/
        // Lift

        //this.log("LinAc", linAc.getCurrentPosition() + "");
        /*
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
            this.log("LinAc", "At a limit, not moving (" + linAc.getCurrentPosition() + ")");
            linAc.setPower(0);
        }
         */

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

       /* if (driver.left_trigger >= .9) {
            grabberRight.setPosition(rightOpenFull);
            grabberLeft.setPosition(leftOpenFull);
        }*/

        /*if (driver.right_trigger >= .9) { //leftbumper=full open, rightbumper=full close, righttrigger (full press) = half close, fastslow = dpad
            grabberRight.setPosition(rightCloseFull);
            grabberLeft.setPosition(leftCloseFull);
        } else if (driver.right_trigger >= .15) {
            grabberRight.setPosition(rightCloseHalf);
            grabberLeft.setPosition(leftCloseHalf);
        }*/

        /*
        if (driver.left_bumper) {
            grabberRight.setPosition(rightOpenFull);
            grabberLeft.setPosition(leftOpenFull);
        }

        if (driver.right_bumper) {
            grabberRight.setPosition(rightCloseFull);
            grabberLeft.setPosition(leftCloseFull);
        }

        if (driver.right_trigger >= .9) {
            grabberRight.setPosition(rightCloseHalf);
            grabberLeft.setPosition(leftCloseHalf);
        }
         */

        this.updateDriveMotors();


        /*
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

         */

        if (gunner.x) {
            grabberSpeed = grabberSlow;
        }

        if (gunner.b) {
            grabberSpeed = grabberFast;
        }

        if (driver.dpad_up) {
            this.motorSpeed = .4f;
        }

        if (driver.dpad_down) {
            this.motorSpeed = .9f;
        }

        if (driver.a) {
            this.motorSpeed = -Math.abs(this.motorSpeed);
        }

        if (driver.y) {
            this.motorSpeed = Math.abs(this.motorSpeed);
        }


        if (driver.b) {
            this.platformLeft.setPosition(LEFT_PLATFORM_GRABBER_DOWN);
            this.platformRight.setPosition(RIGHT_PLATFORM_GRABBER_DOWN);
        }

        if (driver.x) {
            this.platformLeft.setPosition(LEFT_PLATFORM_GRABBER_UP);
            this.platformRight.setPosition(RIGHT_PLATFORM_GRABBER_UP);
        }


       /* if (driver.b) {
            if (foundationIsDown) {
                foundationIsDown = false;
                this.foundationGrabber.setPosition(this.foundationUp);
            } else {
                foundationIsDown = true;
                this.foundationGrabber.setPosition(this.foundationDown);
            }
        }*/

        /*if (driver.dpad_left) {
            pos -= .03;
            pos = clamp(pos, 0, 1);
            this.foundationGrabber.setPosition(pos);
        }

        if (driver.dpad_right) {
            pos += .03;
            pos = clamp(pos, 0, 1);
            this.foundationGrabber.setPosition(pos);
        }

        this.log("FoundationGrabber pos", this.foundationGrabber.getPosition() + "");*/
        //this.log("Grabber Left", grabberLeft.getPosition() + "");
       // this.log("Grabber Right", grabberRight.getPosition() + "");
    }
}
