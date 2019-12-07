package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class BaseOpMode extends OpMode {

    // Motors
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;
    public DcMotor linAc;
    public DcMotor lift;

    //
    public static final int FRONT_LEFT  = 0;
    public static final int FRONT_RIGHT = 1;
    public static final int BACK_LEFT   = 2;
    public static final int BACK_RIGHT  = 3;

    // Drive motor powers
    private static double[] motorPowers = new double[4];

    // Servos


    // Teleop Positioning
    public float x;
    public float y;
    public float z;

    public float gunnerRightX;
    public float gunnerRightY;

    public float gunnerLeftX;
    public float gunnerLeftY;

    // Gamepads
    public Gamepad driver;
    public Gamepad gunner;

    public int using = 0;
    public void setupHardware() {
        // Drive
        frontLeft = hardwareMap.dcMotor.get("front_left");
        backLeft = hardwareMap.dcMotor.get("back_left");
        frontRight = hardwareMap.dcMotor.get("front_right");
        backRight = hardwareMap.dcMotor.get("back_right");

        //backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);


        // Other motors
        lift = hardwareMap.dcMotor.get("lift");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      //  backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       // frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       // frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        driver = gamepad1;
        gunner = gamepad2;
    }

    private void normalizeCombinedPowers() {
        double maxAbsPower = 0.0d;

        for (double motorPower : motorPowers) {
            double tmpAbsPower = Math.abs(motorPower);
            if (tmpAbsPower > maxAbsPower) {
                maxAbsPower = tmpAbsPower;
            }
        }

        if (maxAbsPower > 1.0d) {
            for (int i = 0; i < motorPowers.length; ++i) {
                motorPowers[i] = motorPowers[i] / maxAbsPower;
            }
        }
    }

    public void updateMotorPowers() {
        motorPowers[FRONT_RIGHT] = y - x - z;
        motorPowers[FRONT_LEFT] = (y + x + z);
        motorPowers[BACK_RIGHT] = (y + x - z);
        motorPowers[BACK_LEFT] = y - x + z;
        normalizeCombinedPowers();
    }

    //
    private float shapeInput(float input) {
        float shapedValue = 0.0f;
        if (input != 0.0f) {
            if (input < 0.0f) {
                shapedValue = input * -input;
            } else {
                shapedValue = input * input;
            }
        }

        return shapedValue;
    }

    // Update the positional values
    private void updateXYZ() {
        x = this.shapeInput(driver.left_stick_x);
        y = this.shapeInput(-driver.left_stick_y);
        z = this.shapeInput(driver.right_stick_x);

        gunnerLeftX = this.shapeInput(gunner.left_stick_x);
        gunnerLeftY = this.shapeInput(-gunner.left_stick_y);

        gunnerRightX = this.shapeInput(gunner.right_stick_x);
        gunnerRightY = this.shapeInput(-gunner.right_stick_y);
   }

    // Only used during teleop to update motors based on gamepad
    public void updateDriveMotors() {
        this.updateXYZ();
        this.updateMotorPowers();
        frontLeft.setPower(motorPowers[FRONT_LEFT]);
        backLeft.setPower(motorPowers[BACK_LEFT]);
        frontRight.setPower(motorPowers[FRONT_RIGHT]);
        backRight.setPower(motorPowers[BACK_RIGHT]);
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

        /*frontLeft.setPower(y + x + z);
        backLeft.setPower(y - x + z);
        frontRight.setPower(y - x - z);
        backRight.setPower(y + x - z);*/
    }

    public void log(String title, String what) {
        telemetry.addData(title, what);
    }

    public abstract void init();
    public abstract void loop();
    public abstract void init_loop();

    // OVERRIDE IN SUBCLASSES BUT CALL SUPER
    public void start() {
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public BaseOpMode() {

    }
}
