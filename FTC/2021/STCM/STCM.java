package org.firstinspires.ftc.teamcode.stcm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class STCM {
    private LinearOpMode opmode;
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    
    public STCM(LinearOpMode opmode){
        this.opmode = opmode;
    }
    
    public void logStatus(){
        opmode.telemetry.addData("LD", leftDrive.getPower());
        opmode.telemetry.addData("RD", rightDrive.getPower());
    }
    
    public void initializeMechanisms(){
        leftDrive = opmode.hardwareMap.get(DcMotor.class, "chasisIzquierdo");
        rightDrive = opmode.hardwareMap.get(DcMotor.class, "chasisDerecho");
    }
    
    public void moveTank(double leftPower, double rightPower, double multiplier){
        leftDrive.setPower(multiplier * leftPower);
        rightDrive.setPower(multiplier * rightPower);
    }
    
    public void moveDrone(double drive, double turn, double multiplier){
        double leftPower = drive + turn;
        double rightPower = drive - turn;
        double max = Math.max(Math.abs(leftPower), Math.abs(rightPower));
        if(max > 1){
            leftPower /= max;
            rightPower /= max;
        }
        moveTank(leftPower, rightPower, multiplier);
    }
}