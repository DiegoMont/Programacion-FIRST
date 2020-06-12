public class Subclase extends Super {
  public static void metodoPublic(){
    System.out.println("Sabor a mi\n\nSi negaras mi presencia en tu vivir,");
    metodoPrivate();
  }

  protected static void metodoProtected(){
    super.metodoDefault();
    System.out.println("De mi vida doy lo bueno,\nsoy tan pobre, que otra cosa puedo dar?\n");
    metodoDefault();
  }

  private static void metodoPrivate(){
    System.out.println("bastaria con abrazarte y conversar\nTanta vida yo te di");
    Vecina.metodoProtected();
  }

  static void metodoDefault(){
    System.out.println("Pasaran mas de mil años, muchos mas\nYo no se si tenga amor la eternidad");
    Vecina.metodoDefault();
    Super.metodoDefault();
  }
}
