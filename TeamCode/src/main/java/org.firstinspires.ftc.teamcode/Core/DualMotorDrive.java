package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/*** Manages the drivetrain of the robot.
 * "Dual" refers to the two wheels in the drivetrain. */
public class DualMotorDrive {
    //// CLASS VARIABLES
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    //// CONSTANT VARIABLES
    private final int ENCODER_VALUES_PER_ROTATION = 1400;
    // TODO ADJUST VALUE
    private final double INCHES_PER_ROTATION = 12.36;

    /** Init */

    //Initializes 2 DcMotor Objects for the 2 wheels and sets movement directions
    public DualMotorDrive (HardwareMap hardwareMap) {
        // Map DcMotor variables to hardwareMap
        leftMotor = hardwareMap.get(DcMotor.class, "left_motor");
        rightMotor = hardwareMap.get(DcMotor.class, "right_motor");

        // Set motor movement directions
        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setTargetPosition(0);
        rightMotor.setTargetPosition(0);
    }


    /** setMoveVelocity
     * Uses RUN_USING_ENCODER to move all motors by an inputted velocity
     *
     * @param leftVelocity - power sent to the left motor
     * @param rightVelocity - power sent to the right motor
     */
    public void setMoveVelocity(double leftVelocity, double rightVelocity) {
        if (leftMotor.getMode() == DcMotor.RunMode.RUN_TO_POSITION) {
            if (leftVelocity == 0 && rightVelocity == 0) {
                return;
            }
        }

        if (leftMotor.getMode() != DcMotor.RunMode.RUN_USING_ENCODER) {
            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        leftMotor.setPower(leftVelocity);
        rightMotor.setPower(rightVelocity);
    }

    /** Converts inches to encoder values using constants
     * MAY BE UNRELIABLE, AS FRICTION IS UNACCOUNTED FOR */
    private int inchesToEncoderValues(double inches) {
        return Math.toIntExact(Math.round(inches * ENCODER_VALUES_PER_ROTATION / INCHES_PER_ROTATION));
    }

    /** Uses RUN_TO_POSITION to move the motors by a distance. */
    public void moveInches(double leftInches, double rightInches) {
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setTargetPosition(inchesToEncoderValues(leftInches));
        rightMotor.setTargetPosition(inchesToEncoderValues(rightInches));

        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /** If the motors are in RUN_TO_POSITION, motors progress to their target position */
    public void update() {
        if (leftMotor.getMode() == DcMotor.RunMode.RUN_TO_POSITION) {
            leftMotor.setTargetPosition(leftMotor.getTargetPosition());
            leftMotor.setPower(1);
            rightMotor.setTargetPosition(rightMotor.getTargetPosition());
            rightMotor.setPower(1);
        }
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("\nCurrent class", "TriMotorDrive.java");
        telemetry.addData("runMode", leftMotor.getMode());
        if (leftMotor.getMode() == DcMotor.RunMode.RUN_USING_ENCODER) {
            telemetry.addData("Left Power",
                    "%4.2f", leftMotor.getPower());
            telemetry.addData("Right Power",
                    "%4.2f", rightMotor.getPower());
        }
        if (leftMotor.getMode() == DcMotor.RunMode.RUN_TO_POSITION) {
            telemetry.addData("Left Position & Target",
                    "%d %d", leftMotor.getCurrentPosition(), leftMotor.getTargetPosition());
            telemetry.addData("Right Position & Target",
                    "%d %d", rightMotor.getCurrentPosition(), rightMotor.getTargetPosition());
        }
    }
}
