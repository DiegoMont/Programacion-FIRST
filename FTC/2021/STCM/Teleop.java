package org.firstinspires.ftc.teamcode.stcm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Teleoperado STCM")
public class Teleop extends LinearOpMode {
    private STCM robot;
    private boolean isTankModeActive = false;
    
    @Override
    public void runOpMode(){
        robot = new STCM(this);
        robot.initializeMechanisms();
        waitForStart();
        while(opModeIsActive()){
            controlChasis();
            telemetry.update();
        }
        
    }
    
    private boolean driveModeClick;
    private void controlChasis(){
        double powerMultiplier;
        if(gamepad1.right_trigger > 0.5 && gamepad1.left_trigger > 0.5)
            powerMultiplier = 1;
        else if(gamepad1.right_trigger > 0.5)
            powerMultiplier = 0.5;
        else
            powerMultiplier = 0.8;

        if(isTankModeActive){
            double leftPower = -gamepad1.left_stick_y;
            double rightPower = -gamepad1.right_stick_y;
            robot.moveTank(leftPower, rightPower, powerMultiplier);
        } else {
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            robot.moveDrone(drive, turn, powerMultiplier);
        }
    }
}