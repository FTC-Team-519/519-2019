package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.BaseOpMode;

@TeleOp(name = "BlockRecognition", group = "Utility")
public class BlockRecognition extends BaseOpMode {
    private Vuforia vuforia;
    public void init() {
        super.setupHardware();
        vuforia = new Vuforia(this.hardwareMap);
    }

    public void init_loop() {

    }

    public void start() {
        super.start();

        vuforia.sampleStonePosition(this.telemetry);
        this.telemetry.addData("Sampled", "!");
    }

    public void loop() {
        this.log("YelowThirdOne", vuforia.stoneThread.yellowThirdOne + "");
        this.log("YellowThirdTwo", vuforia.stoneThread.yellowThirdTwo + "");
        this.log("YellowThirdThree", vuforia.stoneThread.yellowThirdThree + "");
    }
}
