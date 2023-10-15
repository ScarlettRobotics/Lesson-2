package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class SystemsManager extends OpMode {
    // Initialize claw and slide classes
    protected DualMotorDrive drive;
    protected ClawCore claw;
    protected SlideCore slide;

    protected boolean pBumperLeft;
    protected boolean pBumperRight;

    // Stores previous states of listed buttons
    private boolean pgamepad_dpad_up = false;
    private boolean pgamepad_dpad_down = false;

    @Override
    public void init() {
        // Define classes
        drive = new DualMotorDrive(hardwareMap);
        claw = new ClawCore(hardwareMap);
        slide = new SlideCore(hardwareMap);
        // Telemetry
        telemetry.addData("STATUS: ", "Initialized"); // the FTC equivalent to println()
        telemetry.addData("FTC Team #", "22531");
    }


    /* Moves the slide based on gamepad presses */
    protected void updateSlide(final int controllerNum) {
        // controllerNum determines the gamepad that controls the robot
        switch (controllerNum) {
            case 1:
                slide.slideManual(gamepad1.right_trigger - gamepad1.left_trigger);
                break;
            case 2:
                slide.slideManual(gamepad2.right_trigger - gamepad2.left_trigger);
                break;
        }
        slide.telemetry(telemetry);
    }

    /* Updates claw state based on gamepad presses. */
    protected void updateClaw(final int controllerNum) {
        // controllerNum determines the gamepad that controls the robot
        switch (controllerNum) {
            case 1:
                // Open/close claw if A/B is pressed (respectively)
                if (gamepad1.left_bumper) {
                    claw.open();
                } else if (gamepad1.right_bumper) {
                    claw.close();
                }
                break;
            case 2:
                // Open/close claw if A/B is pressed (respectively)
                if (gamepad2.left_bumper) {
                    claw.open();
                } else if (gamepad2.right_bumper) {
                    claw.close();
                }
                break;
        }
        claw.telemetry(telemetry);
    }

    protected void updateMotor(final int controllerNum) {
        double left, right;

        // controllerNum determines the gamepad that controls the robot
        switch(controllerNum) {
            case 1:
                // Move left/right wheels based on left/right stick movement
                left = gamepad1.left_stick_y;
                right = gamepad1.right_stick_y;
                // Snap turn
                if (!pBumperLeft && gamepad1.left_bumper) {
                    drive.moveInches(10.2, -10.2);
                }
                if (!pBumperRight && gamepad1.right_bumper) {
                    drive.moveInches(-10.3, 10.3);
                }
                pBumperLeft = gamepad1.left_bumper;
                pBumperRight = gamepad1.right_bumper;
                break;
            case 2:
                // Move left/right wheels based on left/right stick movement
                left = gamepad2.left_stick_y;
                right = gamepad2.right_stick_y;
                // Snap turn

                break;
            default:
                left = 0;
                right = 0;
        }
        drive.setMoveVelocity(left, right);
        drive.update();
        drive.telemetry(telemetry);
    }

    /* Garbage quick fix code that adds arcade drive to the drivetrain */
    protected void updateMotorArcade(final int controllerNum) {
        // turn is positive if intention is to turn right
        double forward, turn;

        // controllerNum determines the gamepad that controls the robot
        switch(controllerNum) {
            case 1:
                // Move left/right wheels based on left/right stick movement
                forward = gamepad1.left_stick_y;
                turn = gamepad1.right_stick_x;
                break;
            case 2:
                // Move left/right wheels based on left/right stick movement
                forward = gamepad2.left_stick_y;
                turn = gamepad2.right_stick_x;
                break;
            default:
                forward = 0;
                turn = 0;
        }
        drive.setMoveVelocity(forward - turn, forward + turn);
        drive.update();
        drive.telemetry(telemetry);
    }
}
