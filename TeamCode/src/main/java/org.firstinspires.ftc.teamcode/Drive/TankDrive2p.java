package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "TankDrive2p", group = "Teleop")
public class TankDrive2p extends OpMode {
    /* Initialization */
    // Drivetrain init
    protected DcMotor leftMotor;
    protected DcMotor rightMotor;
    // Claw init
    protected Servo leftClaw;
    protected Servo rightClaw;
    // Slide init
    protected DcMotor slideMotor;

    @Override
    public void init() {
        // hardwareMap drivetrain motors
        leftMotor = hardwareMap.get(DcMotor.class, "motorLeft");
        rightMotor = hardwareMap.get(DcMotor.class, "motorRight");
        // Set drivetrain RunModes
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // hardwareMap claw servos
        leftClaw = hardwareMap.get(Servo.class, "clawLeft");
        rightClaw = hardwareMap.get(Servo.class, "clawRight");

        // hardwareMap slide motor
        slideMotor = hardwareMap.get(DcMotor.class, "motorSlide");
        // Set slide RunMode
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Telemetry
        telemetry.addData("STATUS", "Initialized"); // the FTC equivalent to println()
        telemetry.addData("FTC Team #", "22531");
    }

    @Override
    public void loop() {
        // Update drive system
        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(gamepad1.right_stick_y);

        // Update claw
        if (gamepad2.a) {
            leftClaw.setPosition(0.595);
            rightClaw.setPosition(0.73);
        }
        if (gamepad2.b) {
            leftClaw.setPosition(0.70);
            rightClaw.setPosition(0.61);
        }

        // Update slide
        slideMotor.setPower(gamepad2.right_stick_y);

        // Telemetry
        telemetry(telemetry);
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("\nCurrent section", "Drivetrain");
        telemetry.addData("runMode", leftMotor.getMode());
        telemetry.addData("Left Power",
                "%4.2f", leftMotor.getPower());
        telemetry.addData("Right Power",
                "%4.2f", rightMotor.getPower());

        telemetry.addData("\nCurrent section", "Claw");
        telemetry.addData("Left claw position", leftClaw.getPosition());
        telemetry.addData("Right claw position", rightClaw.getPosition());

        telemetry.addData("\nCurrent section", "Slide");
        telemetry.addData("Slide Y", slideMotor.getCurrentPosition());
    }
}
