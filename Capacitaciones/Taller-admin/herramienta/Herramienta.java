package herramienta;
public class Herramienta {
  public String nombre, descripcion;
  public boolean esElectrica;

  public Herramienta(String nombre, String descripcion, boolean electrica){
    this.nombre = nombre;
    this.descripcion = descripcion;
    esElectrica = electrica;
  }
}
