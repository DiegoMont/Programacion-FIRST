package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.utils.FPSCounter;

@TeleOp(name="Teleoperado")
public class Teleoperado extends OpMode{
    private Robot robot;
    private FPSCounter fps;
    
    @Override
    public void init(){
        robot = new Robot(this);
        fps = new FPSCounter();
        robot.initializeMechanisms();
        robot.chasis.frenar();
        telemetry.update();
    }
    
    @Override
    public void init_loop(){}
    
    @Override
    public void start(){
        fps.startTimer();
    }
    
    @Override
    public void loop(){
        robot.chasis.move(leftPower, rightPower);
        telemetry.addData("FPS", fps.getUpdatedFPS());
        telemetry.update();
    }
    
    @Override
    public void stop(){}
}