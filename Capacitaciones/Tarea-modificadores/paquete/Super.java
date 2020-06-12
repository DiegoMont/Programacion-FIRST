public class Super {
  public static void metodoPublic(){
    System.out.println("Tanto tiempo disfrutamos de este amor,\nnuestras almas se acercaron tanto asi,");
    Subclase.metodoPrivate();
    metodoPrivate();
  }

  protected static void metodoProtected(){
    System.out.println("No pretendo ser tu dueño\nNo soy nada, yo no tengo vanidad");
    Subclase.metodoProtected();
  }

  private static void metodoPrivate(){
    System.out.println("que yo guardo tu sabor\npero tu llevas tambien");
    Subclase.metodoPublic();
    Vecina.metodoprivate();
  }

  static void metodoDefault(){
    System.out.println("");
  }
}
