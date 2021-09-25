package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Robot {

    protected OpMode programa;
    
    public OmniChasis chasis;
    
    public NaveDelOlvido(OpMode programa){
        this.programa = programa;
        chasis = new Chasis();
    }
    
    public void initializeMechanisms(){
        HardwareMap hwMap = programa.hardwareMap;
        chasis.initializeHardware(hwMap);
        programa.telemetry.addData("Status", "Ready to rumbleee!!!");
    }
}
