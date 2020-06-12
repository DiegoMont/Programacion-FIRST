package paquete;
public class ClaseHermana {
  public static void otroMetodo(){
    //Protected nos deja acceder al valor si ambas clases están dentro del mismo paquete
    System.out.println(ClaseSuper.variableProtegida);
  }
}
