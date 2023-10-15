package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Core.SystemsManager;

@TeleOp(name = "ArcadeDrive1p", group = "Arcade")
public class ArcadeDrive1p extends SystemsManager {
    @Override
    public void loop() {
        telemetry.addData("STATUS: ", "Running");
        updateClaw(1);
        updateSlide(1);
        updateMotorArcade(1);
    }
}
