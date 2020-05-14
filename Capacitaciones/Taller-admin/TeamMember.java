import java.util.Date;

public class TeamMember {

  //Variables de instancia o propiedades para guardar la informacion de nuestro objeto
    String nombre;
    String apellido;
    int semestre;
    String plan;
    String areaDelEquipo;
    String tarea;
    String equipoFTC;
    int anioEnElEquipo;
    Date cumpleanios;

    //Constructor
    public TeamMember(String nombre, String apellido,
    String areaDelEquipo, String equipoFTC) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.areaDelEquipo = areaDelEquipo;
        this.equipoFTC = equipoFTC;
    }

    //Método que permitira a una persona tomar prestada una herramienta
    public void tomarHerramienta(/*Herramienta herramienta*/){
      /*Codigo pendiente*/
    }
}
