/*En la temporada 2020 de FRC Infinite Recharge uno de las misiones
que los equipos tenían que hacer consistía en que el robot interactuara
con el Control Panel, que consistia en un disco giratorio que estaba
dividido en secciones. Cada sección se identificaba con un color. Los
colores que conteníá el disco con los valores RGB que un sensor de
color lee para cada color son:
  - Amarillo (82, 143, 31)
  - Rojo (133, 87, 33)
  - Verde (41, 148, 66)
  - Azul (31, 107, 115)
Cómo podemos identificar correctamente los colores del Control Panel
durante un match, si sabemos que debido a las condiciones de la
iluminación en los regionales, los valores que lea el sensor durante
cada match no van a ser iguales a los que leímos en el taller.
*/
import java.util.Arrays;
import java.util.Random;

public class SensorLuz {
  public static void main(String[] args) {
    //En esta variable guardamos los valores que el sensor de color
    //lee actualmente
    int[] lecturasSensor = {red(), green(), blue()};
    //En esta variable hay que guardar el color que el sensor esté viendo
    String colorIdentificado = "";

    //El código va aquí



    //Finalmente imprimimos los datos y el color identificado
    System.out.println(Arrays.toString(lecturasSensor));
    System.out.println("El sensor de luz ve el color: " + colorIdentificado);
  }

  //No se debe modificar ninguno de los siguientes métodos
  public static int red(){
    Random random = new Random();
    int valor = random.nextInt();
    return (valor < 0 ? valor*-1: valor)%256;
  }

  public static int green(){
    Random random = new Random();
    int valor = random.nextInt();
    return (valor < 0 ? valor*-1: valor)%256;
  }

  public static int blue(){
    Random random = new Random();
    int valor = random.nextInt();
    return (valor < 0 ? valor*-1: valor)%256;
  }
}
