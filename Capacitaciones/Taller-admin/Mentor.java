import java.util.Date;

public class Mentor {

  //Propiedades o variables de instancia para guardar la informacion de nuestros objetos
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
    public Mentor(String nombre, String apellido, String areaDelEquipo, String equipoFTC) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.areaDelEquipo = areaDelEquipo;
        this.equipoFTC = equipoFTC;
    }

    //Metodo que permitira a una persona tomar una herramienta prestada
    public void tomarHerramienta(/*Herramienta herramienta*/){
      /*Codigo pendiente*/
    }

    //Metodo para que un mentor le pueda asignar una tarea a una persona
    public void asignarTarea(TeamMember estudiante, String tarea){
        estudiante.tarea = tarea;
    }
}
