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

    public void updateLift() {
        this.log("Lift Position", lift.getCurrentPosition() + "");
        this.log("Lift Speed", "Up: " + upLiftSpeed + " | Down: " + downLiftSpeed);

        // Make the lift go slower on down, due to gravity influence
        lift.setPower((gunnerRightY < 0) ? gunnerRightY*downLiftSpeed : gunnerRightY*upLiftSpeed);
    }

    public void loop() {
        /*if (driver.a) { using = 0; }
        if (driver.b) { using = 1; }
        if (driver.x) { using = 2; }
        if (driver.y) { using = 3; }*/
        // Lift

        if (gunner.dpad_left) {
            upLiftSpeed = .8f;
            downLiftSpeed = .7f;
        }

        if (gunner.dpad_right) {
            upLiftSpeed = 1f;
            downLiftSpeed = .9f;
        }

        this.updateLift();
        this.updateDriveMotors();

    }
}
