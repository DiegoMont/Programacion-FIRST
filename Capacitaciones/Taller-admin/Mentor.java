import java.util.Date;

public class Mentor extends TeamMember {

  //Propiedades o variables de instancia para guardar la informacion de nuestros objetos

    //Constructor
    public Mentor(String nombre, String apellido, String areaDelEquipo, String equipoFTC) {
        super(nombre, apellido, areaDelEquipo, equipoFTC);
    }

    //Metodo que permitira a una persona tomar una herramienta prestada

    //Metodo para que un mentor le pueda asignar una tarea a una persona
    public void asignarTarea(TeamMember estudiante, String tarea){
        estudiante.tarea = tarea;
    }
}
