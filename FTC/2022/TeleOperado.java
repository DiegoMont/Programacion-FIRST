package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp")
public class TeleOperado extends LinearOpMode {
    final double UPDATE_PERIOD_MS = 300;
    final double MOTOR_POWER_INCREMENT = 0.02;

    DcMotor frontLeft, frontRight, backLeft, backRight;

    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");  
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        timer = new ElapsedTime();
        double lastUpdateTime = timer.milliseconds();
        
        waitForStart();
        while(opModeIsActive()) {
            double currentTime = timer.milliseconds();
            if (currentTime - lastUpdateTime > UPDATE_PERIOD){
                double drive = -gamepad1.left_stick_y;
                double lateral = gamepad1.right_stick_x;
                double turn = gamepad1.left_stick_x;
                double powerMultiplier = getPowerMultiplier(gamepad1.right_bumper, gamepad1.left_bumper);
                double frontLeftTarget = Range.clip(drive + lateral + turn, -1, 1) * powerMultiplier;
                double frontRightTarget = Range.clip(drive - lateral - turn, -1, 1) * powerMultiplier;
                double backLeftTarget = Range.clip(drive - lateral + turn, -1, 1) * powerMultiplier;
                double backRightTarget = Range.clip(drive + lateral - turn, -1, 1) * powerMultiplier;
                double frontLeftPower = getIncreasedPower(frontLeft.getPower(), frontLeftTarget);
                double frontRightPower = getIncreasedPower(frontRight.getPower(), frontRightTarget);
                double backLeftPower = getIncreasedPower(backLeft.getPower(), backLeftTarget);
                double backRightPower = getIncreasedPower(backRight.getPower(), backRightTarget);
                frontLeft.setPower(frontLeftPower);
                frontRight.setPower(frontRightPower);
                backLeft.setPower(backLeftPower);
                backRight.setPower(backRightPower);
                lastUpdateTime = currentTime;
            }
            
            telemetry.update();
        }
    }

    double getIncreasedPower(double current, double target) {
        double diff = target - current;
        double absDiff = Math.abs(diff);
        double sign = diff / absDiff;
        double valueToIncrement = sign * Math.min(absDiff, MOTOR_POWER_INCREMENT);
        return current + valueToIncrement;
    }

    double getPowerMultiplier(boolean accelerateBtn, boolean breakBtn){
        if(accelerateBtn)
            return 1;
        if(breakBtn)
            return 0.5;
        return 0.8;
    }
}