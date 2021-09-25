package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;


public class Chasis implements Mechanism{
    
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    public Chasis(){
        
    }
    
    public void initializeHardware(HardwareMap hardwareMap){
        frontLeft = hardwareMap.get(DcMotor.class, "");
        frontRight = hardwareMap.get(DcMotor.class, "");
        backLeft = hardwareMap.get(DcMotor.class, "");
        backRight = hardwareMap.get(DcMotor.class, "");
        powerMultiplier = 1;
    }
    
    public void defaultRunmode(){
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    
    public void frenar(){
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    
    public void resetEncoders(){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        defaultRunmode();
    }
    
    public void move(double drive, double lateral, double turn, double powerMultiplier){
        double[] powers = getNormalizedPowers(drive, lateral, turn);
        this.frontLeft.setPower(powers[0] * powerMultiplier);
        this.frontRight.setPower(powers[1] * powerMultiplier);
        this.backLeft.setPower(powers[2] * powerMultiplier);
        this.backRight.setPower(powers[3] * powerMultiplier);
    }

    private double[] getNormalizedPowers(double drive, double lateral, double turn){
        double frontLeftPower = drive + lateral + turn;
        double frontRightPower = drive - lateral - turn;
        double backLeftPower = drive - lateral + turn;
        double backRightPower = drive + lateral - turn;

        double biggest = Math.max(Math.abs(frontLeftPower), Math.max(Math.abs(frontRightPower), Math.max(Math.abs(backRightPower), Math.abs(backLeftPower))));
        if(biggest > 1) {
          frontLeftPower /= biggest;
          frontRightPower /= biggest;
          backLeftPower /= biggest;
          backRightPower /= biggest;
        }
        double[] powers = {
            frontLeftPower,
            frontRightPower, 
            backLeftPower,
            backRightPower
        };
        return powers;
    }
}