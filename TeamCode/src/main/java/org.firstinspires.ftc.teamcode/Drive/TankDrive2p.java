package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "TankDrive2p", group = "Teleop")
public class TankDrive2p extends OpMode {
    /* Initialization */
    // Drivetrain init
    protected DcMotor motorLeft;
    protected DcMotor motorRight;
    // Claw init
    protected Servo clawLeft;
    protected Servo clawRight;
    // Slide init
    protected DcMotor motorSlide;

    @Override
    public void init() {
        // hardwareMap drivetrain motors
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        // Set drivetrain RunModes
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // hardwareMap claw servos
        clawLeft = hardwareMap.get(Servo.class, "clawLeft");
        clawRight = hardwareMap.get(Servo.class, "clawRight");

        // hardwareMap slide motor
        motorSlide = hardwareMap.get(DcMotor.class, "motorSlide");
        // Set slide RunMode
        motorSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Telemetry
        telemetry.addData("STATUS", "Initialized"); // the FTC equivalent to println()
        telemetry.addData("FTC Team #", "22531");
    }

    @Override
    public void loop() {
        // Update drive system
        motorLeft.setPower(gamepad1.left_stick_y);
        motorRight.setPower(gamepad1.right_stick_y);

        // Update claw
        if (gamepad2.a) {
            clawLeft.setPosition(0.595);
            clawRight.setPosition(0.73);
        }
        if (gamepad2.b) {
            clawLeft.setPosition(0.70);
            clawRight.setPosition(0.61);
        }

        // Update slide
        motorSlide.setPower(gamepad2.right_stick_y);

        // Telemetry
        telemetry(telemetry);
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("\nCurrent section", "Drivetrain");
        telemetry.addData("runMode", motorLeft.getMode());
        telemetry.addData("Left Power",
                "%4.2f", motorLeft.getPower());
        telemetry.addData("Right Power",
                "%4.2f", motorRight.getPower());

        telemetry.addData("\nCurrent section", "Claw");
        telemetry.addData("Left claw position", clawLeft.getPosition());
        telemetry.addData("Right claw position", clawRight.getPosition());

        telemetry.addData("\nCurrent section", "Slide");
        telemetry.addData("Slide Y", motorSlide.getCurrentPosition());
    }
}
