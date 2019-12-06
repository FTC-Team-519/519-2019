package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Teleop2019", group = "Testing")
public class Teleop extends BaseOpMode {

    public Teleop() {
        
    }

    public void init() {
        super.setupHardware();
    }

    public void init_loop() {

    }

    public void start() {

    }

    public void loop() {
        /*if (driver.a) { using = 0; }
        if (driver.b) { using = 1; }
        if (driver.x) { using = 2; }
        if (driver.y) { using = 3; }*/

        this.updateDriveMotors();
    }
}
