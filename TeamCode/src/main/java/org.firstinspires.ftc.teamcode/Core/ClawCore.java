package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ClawCore {
    /* Initialization */
    /** Initialization is done within ClawCore for ease of access */
    protected Servo leftClaw;
    protected Servo rightClaw;
    private boolean clawIsOpen = false;

    public ClawCore (HardwareMap hardwareMap) {
        leftClaw = hardwareMap.get(Servo.class, "claw_left");
        rightClaw = hardwareMap.get(Servo.class, "claw_right");
    }

    /** toggle()
     *  Sets the claw to be in either open or closed position.
     *  The state of the claw is stored as a private field within the object instance.
     *  This ensures that the claw will still open and close even if it has been bumped or stressed to a different point.
     */
    public void toggle() {
        if (clawIsOpen) {
            close();
        } else {
            open();
        }
    }

    /** Opens the claw to a pre-set width, then updates clawIsOpen. */
    public void open() {
        rightClaw.setPosition(0.595);
        leftClaw.setPosition(0.73);
        clawIsOpen = true;
    }

    /** Closes the claw to a pre-set width, then updates clawIsOpen. */
    public void close() {
        rightClaw.setPosition(0.70);
        leftClaw.setPosition(0.61);
        clawIsOpen = false;
    }

    /** Telemetry in contained in each class for ease of access. */
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("\nCURRENT CLASS", "ClawCore.java");
        telemetry.addData("Claw Right POS:", rightClaw.getPosition());
        telemetry.addData("Claw Left POS:", leftClaw.getPosition());
        telemetry.addData("clawIsOpen", clawIsOpen);
    }
}