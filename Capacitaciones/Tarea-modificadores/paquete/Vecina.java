public class Vecina {
  public static void metodoPublic(){
    metodoPrivate();
  }

  protected static void metodoProtected(){
    System.out.println("que por fuerza tienes ya\nSabor a mi\n");
    Super.metodoProtected();
  }

  private static void metodoPrivate(){
    System.out.println("Sabor a mi\n\nAlvaro Carrillo");
  }

  static void metodoDefault(){
    System.out.println("pero alla, tal como aqui\nen la boca llevaras");
  }
}
