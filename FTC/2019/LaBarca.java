/*Copyright 2019

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
public class LaBarca {

  //Inicializar variables para motores y sensores del robot
  public DcMotor leftDrive = null;
  public DcMotor rightDrive = null;
  public DistanceSensor distanceSensor = null;
  private LinearOpMode programa;

  public LaBarca(LineaOpMode programa){
    this.programa = programa;
  }

  //Metodo para buscar motores y servomotores del Expansion y asignarlos a las variables
  public void getHardware(HardwareMap hwMap){
      leftDrive = hwMap.get(DcMotor.class, "m.izquierdo");
      rightDrive = hwMap.get(DcMotor.class, "m.derecho");
  }

  public void resetEncoders(){
    leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    defaultRunmode();
  }

  public void defaultRunmode(){
    rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
  }

  /* Metodos para programacion autonoma */

  //Este metodo movera al robot en linea recta la distancia que se especifique
  public void moverDistanciaRecta(double distancia){
    if(!programa.opModeIsActive()) return;
      //Convertir rotaciones a ticks del encoder del Core Hex
      //9cm de llanta con engrane de 72 y uno de 125 en el motor
      int counts = (int) Math.round(2304d * distancia / 125d / Math.PI);

      //Establecer la posicion actual del encoder como nuestro cero
      leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      //Establecer a que posicion y velocidad se debe mover el robot
      leftDrive.setTargetPosition(counts);
      rightDrive.setTargetPosition(counts);
      leftDrive.setPower(1);
      rightDrive.setPower(1);

      //Cambiar el modo del motor para comenzar movimiento automatico
      while(programa.opModeIsActive()){
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(!(leftDrive.isBusy() && rightDrive.isBusy())){
          leftDrive.setPower(0);
          rightDrive.setPower(0);
          break;
        }
      }

      defaultRunmode();
  }

  public void setGiroDeNoventaGrados(int direccion){
    final int TICKS_TO_TURN = 184;
    int leftTarget = leftDrive.getCurrentPosition() + (direccion * TICKS_TO_TURN);
    int rightTarget = rightDrive.getCurrentPosition() - (direccion * TICKS_TO_TURN);
    leftDrive.setTargetPosition(leftTarget);
    rightDrive.setTargetPosition(rightTarget);
    leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
  }

}
