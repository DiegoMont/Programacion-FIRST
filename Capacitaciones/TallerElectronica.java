package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Taller")
public class TallerElectronica extends LinearOpMode {
    DcMotor motor1, motor2;

    @Override
    public void runOpMode() {
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        
        waitForStart();
        final double POWER_CONTROL = 0.5;
        while(opModeIsActive()) {
            double motor1Power = gamepad1.left_stick_x;
            double motor2Power;
            if(gamepad1.b)
                motor2Power = 1;
            else if(gamepad1.a)
                motor2Power = -1;
            else
                motor2Power = 0;
            
            motor1.setPower(motor1Power * POWER_CONTROL);
            motor2.setPower(motor2Power * POWER_CONTROL);
            telemetry.update();
        }
    }
    
}
