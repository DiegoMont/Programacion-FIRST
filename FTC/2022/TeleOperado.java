package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp")
public class Teleoperado extends LinearOpMode {
    DcMotor frontLeft, frontRight, backLeft, backRight;

    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");  
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();
        while(opModeIsActive()) {
            double drive = -gamepad1.left_stick_y;
            double lateral = gamepad1.right_stick_x;
            double turn = gamepad1.left_stick_x;
            double powerMultiplier = getPowerMultiplier(gamepad1);
            double frontLeftPower = Range.clip(drive + lateral + turn, -1, 1) * powerMultiplier;
            double frontRightPower = Range.clip(drive - lateral - turn, -1, 1) * powerMultiplier;
            double backLeftPower = Range.clip(drive - lateral + turn, -1, 1) * powerMultiplier;
            double backRightPower = Range.clip(drive + lateral - turn, -1, 1) * powerMultiplier;
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
            
            telemetry.update();
        }
    }

    private double getPowerMultiplier(Gamepad gamepad){
        if(gamepad.right_bumper)
            return 1;
        if(gamepad.left_bumper)
            return 0.5;
        return 0.8;
    }
}