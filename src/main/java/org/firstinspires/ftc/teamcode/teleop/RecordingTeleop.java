package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.autonomous.RecordingThread;
import org.firstinspires.ftc.teamcode.record.BlackBox;
import org.firstinspires.ftc.teamcode.record.PersistentFileOutputStream;

public class RecordingTeleop extends Teleop {

    private String recordingName;
    private PersistentFileOutputStream outputStream;
    private BlackBox.Recorder recorder;
    private boolean isRecordingMode = false;
    private ElapsedTime recordTimer;
    private RecordingThread recordingThread;
    public RecordingTeleop(String name) {
        this.recordingName = name;
    }

    @Override
    public void init() {
        super.init();

        try {
            outputStream = new PersistentFileOutputStream(this.recordingName);

            recorder = new BlackBox.Recorder(this.hardwareMap, outputStream.get());
        } catch (Exception e) {
            this.log("Failed", e.getMessage());
            //this.requestOpModeStop();

            return;
        }
    }

    @Override
    public void start() {
        super.start();
        grabberFront.setPosition(FRONT_GRABBER_VERTICAL_UP);
        grabberSide.setPosition(0.7);
        platformLeft.setPosition(LEFT_PLATFORM_GRABBER_UP);
        platformRight.setPosition(RIGHT_PLATFORM_GRABBER_UP);
        this.recordingThread = new RecordingThread(this.recorder, this.hardwareMap);
    }
    @Override
    public void loop() {
        super.loop();

        if (driver.x && (isRecordingMode == false)) {
            isRecordingMode = true;
            if (isRecordingMode) {
                if (recordTimer == null) {
                    recordTimer = new ElapsedTime();
                    //this.log("Starting recording!", "-");
                    recordTimer.reset();
                } else {
                    // TODO: pause timer
                }
            }
        }

        // Record the values
        if (this.isRecordingMode) {
            this.log("Currently Recording Values!!!!", "");

            this.recordingThread.run();
           // new RecordingThread(recorder, recordTimer, hardwareMap)
            /*try {
                this.recorder.recordAllDevices(recordTimer.time());
            } catch (Exception e) {
                e.printStackTrace();
                this.requestOpModeStop();
            }*/
        }
    }

    @Override
    public void stop() {
        super.stop();

        try {
            this.outputStream.get().close();
            this.log("Closed Output Stream", ":)");
        } catch (Exception e) {
            e.printStackTrace();
            this.log("FAILED to end recording", "!");
        }
    }

   /*@TeleOp(name = "RecordRed", group = "Recording")
    public static class RecordRed extends RecordingTeleop {
        public RecordRed() { super("RedNoStone"); }
    }

    @TeleOp(name = "RecordBlue", group = "Recording")
    public static class RecordBlue extends RecordingTeleop {
        public RecordBlue() { super("BlueNoStone"); }
    }*/

    // the recordings
    @TeleOp(name = "RecordRedStoneLeft", group = "Recording")
    public static class RecordRedStoneLeft extends RecordingTeleop {
        public RecordRedStoneLeft() { super("RedStoneLeft"); }
    }

    @TeleOp(name = "RecordRedStoneCenter", group = "Recording")
    public static class RecordRedStoneCenter extends RecordingTeleop {
        public RecordRedStoneCenter() { super("RedStoneCenter"); }
    }

    @TeleOp(name = "RecordRedStoneRight", group = "Recording")
    public static class RecordRedStoneRight extends RecordingTeleop {
        public RecordRedStoneRight() { super("RedStoneRight"); }
    }


    @TeleOp(name = "RecordBluePark", group = "Recording")
    public static class BluePark extends RecordingTeleop {
        public BluePark() {
            super("BluePark");
        }
    }

    @TeleOp(name = "RecordRedPark", group = "Recording")
    public static class RedPark extends RecordingTeleop {
        public RedPark() {
            super("RedPark");
        }
    }

    @TeleOp(name = "RecordBlueFoundationPark", group = "Recording")
    public static class BlueFoundationPark extends RecordingTeleop {
        public BlueFoundationPark() {
            super("BlueFoundationPark");
        }
    }

    @TeleOp(name = "RecordRedFoundationPark", group = "Recording")
    public static class RedFoundationPark extends RecordingTeleop {
        public RedFoundationPark() {
            super("RedFoundationPark");
        }
    }

    @TeleOp(name = "RecordRedBlockFoundationPark", group = "Recording")
    public static class RedBlockFoundationPark extends RecordingTeleop {
        public RedBlockFoundationPark() {
            super("RedBlockFoundationPark");
        }
    }

    @TeleOp(name = "RecordBlueBlockFoundationPark", group = "Recording")
    public static class BlueBlockFoundationPark extends RecordingTeleop {
        public BlueBlockFoundationPark() {
            super("BlueBlockFoundationPark");
        }
    }

}

