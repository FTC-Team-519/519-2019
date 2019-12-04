package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

public abstract class BaseTeleop extends OpMode {

    // Motors
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;

    // Positioning
    private float x;
    private float y;
    private float z;

    // Gamepads
    public Gamepad driver;
    public Gamepad gunner;

    public int using = 0;
    public void setupHardware() {
        frontLeft = hardwareMap.dcMotor.get("front_left");
        backLeft = hardwareMap.dcMotor.get("back_left");
        frontRight = hardwareMap.dcMotor.get("front_right");
        backRight = hardwareMap.dcMotor.get("back_right");

        //backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        driver = gamepad1;
        gunner = gamepad2;
    }

    public void updateDriveMotors() {
        x = driver.left_stick_x;
        y = -driver.left_stick_y;
        z = driver.right_stick_x;

        /*if (using == 0) {
            this.log("Using", "frontLeft");
            frontLeft.setPower(y + x + z);
        } else if (using == 1) {
            this.log("Using", "backLeft");
            backLeft.setPower(y - x + z);
        } else if (using == 2) {
            this.log("Using", "frontRight");
            frontRight.setPower(y - x - z);
        } else if (using == 3) {
            this.log("Using", "backRight");
            backRight.setPower(y + x - z);
        }*/

        frontLeft.setPower(y + x + z);
        backLeft.setPower(y - x + z);
        frontRight.setPower(y - x - z);
        backRight.setPower(y + x - z);
    }

    public void log(String title, String what) {
        telemetry.addData(title, what);
    }

    public abstract void init();
    public abstract void loop();
    public abstract void init_loop();
    public abstract void start();

    public BaseTeleop() {

    }
}
