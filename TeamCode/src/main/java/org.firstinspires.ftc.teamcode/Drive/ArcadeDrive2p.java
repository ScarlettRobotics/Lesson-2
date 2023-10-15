package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Core.SystemsManager;

@TeleOp(name = "ArcadeDrive2p", group = "Arcade")
public class ArcadeDrive2p extends SystemsManager {
    @Override
    public void loop() {
        telemetry.addData("STATUS: ", "Running");
        updateClaw(2);
        updateSlide(2);
        updateMotorArcade(1);
    }
}
