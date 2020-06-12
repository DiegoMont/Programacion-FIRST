public class Subclase extends ClaseSuper{
  public static void metodo(){
    //Protected tambien nos deja acceder a las variables que tienen las clases super
    System.out.println(ClaseSuper.variableProtegida);

    //Con el modificador default solo podemos acceder a los valores de una clase super
    System.out.println(ClaseSuper.variable);
  }
}
