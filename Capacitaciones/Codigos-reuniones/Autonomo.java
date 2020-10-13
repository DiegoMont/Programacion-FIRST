package org.firstinspires.ftc.teamcode.capacitaciones;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Auto")
public class Autonomo extends LinearOpMode{
  
  private Robotito robot;

  public void runOpMode(){
    robot = new Robotito(this);
    robot.getHardware(hardwareMap);
    waitForStart();
    robot.avanzar(-50);
    robot.avanzar(100);
  }
  
}