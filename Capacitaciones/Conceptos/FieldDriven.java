package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;



@Autonomous(name = "Field oriented drive")
public class FieldOriented extends LinearOpMode {
    RobotAna robot = new RobotAna();
    boolean fieldOriented = false;
    double FIELD_NORTH;
    
    @Override
    public void runOpMode() {
        robot.initHardware(hardwareMap);
        robot.iniciarAcelerometro(hardwareMap, telemetry);
        waitForStart();
        double FIELD_NORTH = robot.getHeading();
        boolean drivingModeClick = false;
        while(opModeIsActive()) {
            if(!gamepad1.dpad_down && drivingModeClick)
                fieldOriented = !fieldOriented;
            drivingModeClick = gamepad1.dpad_down;
            controlChasis(fieldOriented);
            telemetry.update();
        }
    }
    
    private void controlChasis(boolean fieldOriented) {
        double drive = -gamepad1.left_stick_y;
        double lateral = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        telemetry.addData("Drive mode:", fieldOriented ? "Field orientd": "Robot oriented");
        if(fieldOriented) {
            double heading = robot.getHeading();
            double degreesToRotate = FIELD_NORTH - heading;
            lateral = lateral * Math.cos(Math.toRadians(degreesToRotate)) - drive * Math.sin(Math.toRadians(degreesToRotate));
            drive = lateral * Math.sin(Math.toRadians(degreesToRotate)) + drive * Math.cos(Math.toRadians(degreesToRotate));
            telemetry.addData("North:", FIELD_NORTH);
            telemetry.addData("Giro:", heading);
            telemetry.addData("Lateral:", lateral);
            telemetry.addData("Drive:", drive);
        }
        setChasisPower(drive, lateral, turn);
    }
    
    public void setChasisPower(double drive, double lateral, double turn) {
        double frPower = drive - lateral - turn;
        double flPower = drive + lateral + turn;
        double blPower = drive - lateral + turn;
        double brPower = drive + lateral - turn;
        double powerMultiplier = 0.25;
        robot.FR.setPower(frPower * powerMultiplier);
        robot.FL.setPower(flPower * powerMultiplier);
        robot.BR.setPower(brPower * powerMultiplier);
        robot.BL.setPower(blPower * powerMultiplier);
    }
    
}