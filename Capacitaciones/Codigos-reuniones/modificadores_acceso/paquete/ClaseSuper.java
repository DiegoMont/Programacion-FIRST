package paquete;
public class ClaseSuper {
  public static int variablePublica;
  private static int variablePrivada;
  protected static int variableProtegida;
  static int variable;

public static int getVariablePrivada(){
  //Solamente podemos acceder a una variable privada dentro de la misma clase
  return variablePrivada;
}

public static void setVariablePrivada(int valorNuevo){
  variablePrivada = valorNuevo;
}
}
