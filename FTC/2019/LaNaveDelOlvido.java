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
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
public class LaNaveDelOlvido {

  //Inicializar variables para motores y sensores del robot
  public DcMotor leftDrive = null;
  public DcMotor rightDrive = null;
  public DistanceSensor distanceSensor = null;

  //Metodo para buscar motores y servomotores del Expansion y asignarlos a las variables
  public void getHardware(HardwareMap hwMap){
      leftDrive = hwMap.get(DcMotor.class, "m.izquierdo");
      rightDrive = hwMap.get(DcMotor.class, "m.derecho");
  }

  /* Metodos para programacion autonoma */

  //Este metodo movera al robot en linea recta dependiendo de las rotaciones que reciba en su parametro
  public void moverDistanciaRecta(int rotaciones){

      //Convertir rotaciones a ticks del encoder del Core Hex
      int counts = rotaciones * 288;

      //Establecer la posicion actual del encoder como nuestro cero
      leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      //Establecer a que posicion y velocidad se debe mover el robot
      leftDrive.setTargetPosition(counts);
      rightDrive.setTargetPosition(counts);
      leftDrive.setPower(1);
      rightDrive.setPower(1);

      //Cambiar el modo del motor para comenzar movimiento automatico
      leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
  }

}
