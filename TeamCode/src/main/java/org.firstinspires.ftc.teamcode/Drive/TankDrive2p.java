package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/** While Java files normally start with "class Main", FTC files are slightly modified.
 * @TeleOp indicates that your program will appear in the driver section in the driver hub.
 *      This is different from @Autonomous, which will cause the class to appear in the autonomous section in the driver hub.
 * "name" is what the program will be shown as. This MUST match the file name.
 * "group" doesn't affect much. All it does it create a small line between different groups in the driver hub.
 * Here's why this program is named "TankDrive2p":
 *      "TankDrive" comes from each joystick being assigned to a wheel's movement, similar to tank controls.
 *      ArcadeDrive is another commonly used driving system.
 *          One joystick is assigned to moving front/back, and the other left/right.
 *      "2p" refers to the need for 2 people to run the robot.
 * The OpMode class is extended from the FTC package.
 *      If IntelliJ gives errors, use the shortcut Alt+Enter to automatically add the required package.
 */
@TeleOp(name = "TankDrive2p", group = "Teleop")
public class TankDrive2p extends OpMode {
    /** OpMode contains built-in classes that already work with the driver hub.
     * The classes "DcMotor" and "Servo" respectively correspond with to the robot's connected motors and servos.
     * You will need to set up the driver hub before converting software to hardware.
     */
    /* Initialization */
    // Drivetrain init
    protected DcMotor motorLeft;
    protected DcMotor motorRight;
    // Claw init
    protected Servo clawLeft;
    protected Servo clawRight;
    // Slide init
    protected DcMotor motorSlide;

    /** @Override is required before using "init()".
     * This is where you will initialize all your declared variables.
     * "init()" is ran when you click "INIT" on the driver hub.
     *      Because of this, avoid any robot movement in this section.
     */
    @Override
    public void init() {
        /** "hardwareMap" connects each variable to an actual motor or servo on the robot.
         * The first parameter declares the ObjectClass that's getting paired with a variable (e.g. DcMotor, Servo).
         * The second parameter identifies ObjectClass' name that was already set up in the driver hub.
         */
        // hardwareMap drivetrain motors
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        /** Before a DcMotor can be used, the RunMode must be set. Two are most common:
         * RUN_USING_ENCODER uses a double ranging from -1 to 1 to spin the motor in a specific direction.
         * RUN_TO_POSITION uses encoder values (positions of rotation) to move to specific positions.
         * For a list of additional RunModes, refer to https://ftctechnh.github.io/ftc_app/doc/javadoc/com/qualcomm/robotcore/hardware/DcMotor.RunMode.html
         */
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

        /** telemetry.addData() is equivalent to System.out.println().
         * If two strings are inputted, the terminal output will appear as "{string1}: {string2}".
         */
        // Telemetry
        telemetry.addData("STATUS", "Initialized"); // the FTC equivalent to println()
        telemetry.addData("FTC Team #", "22531");
    }

    /** @Override is required before using "init()".
     * This is where the robot's driving code will run indefinitely.
     * "loop()" is ran when you click the triangle play button on the driver hub.
     *      Due to this, insert all robot movement code here.
     */
    @Override
    public void loop() {
        /** setPower() receives a double from -1 to 1. Larger values are more powerful, and sign changes direction.
         * "gamepad1" and "gamepad2" links controller inputs to robot movement.
         *      For a list of all inputs, refer to https://ftctechnh.github.io/ftc_app/doc/javadoc/com/qualcomm/robotcore/hardware/Gamepad.html
         */
        // Update drive system
        motorLeft.setPower(gamepad1.left_stick_y);
        motorRight.setPower(gamepad1.right_stick_y);

        /** setPosition() receives a double from 0 to 1 corresponding to the minimum and maximum position.
         * 2 significant digits are usually enough for servo tasks.
         *      3 significant digits can be used for more precise tasks.
         */
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

    /** telemetry() is usually placed in a different function for ease of access. */
    public void telemetry(Telemetry telemetry) {
        /** telemetry.addData() is equivalent to System.out.println().
         * If two strings are inputted, the terminal output will appear as "{string1}: {string2}".
         */
        telemetry.addData("\nCurrent section", "Drivetrain");
        /** .getMode() returns the RunMode of the DcMotor variable. */
        telemetry.addData("runMode", motorLeft.getMode());
        /** .getPower() returns the current power of the DcMotor variable. */
        telemetry.addData("Left Power",
                "%4.2f", motorLeft.getPower());
        telemetry.addData("Right Power",
                "%4.2f", motorRight.getPower());

        telemetry.addData("\nCurrent section", "Claw");
        /** .getPosition() returns the current position of the Servo variable. */
        telemetry.addData("Left claw position", clawLeft.getPosition());
        telemetry.addData("Right claw position", clawRight.getPosition());

        telemetry.addData("\nCurrent section", "Slide");
        /** .getCurrentPosition() returns the encoder value of the DcMotor variable. */
        telemetry.addData("Slide Y", motorSlide.getCurrentPosition());
    }
}
