package team_member.los_jefes;
import java.util.Date;

public class TeamMember {

  //Variables de instancia o propiedades para guardar la informacion de nuestro objeto
    public  String nombre;
    public  String apellido;
    public  String matricula;
    public  int semestre;
    public  String plan;
    public  String areaDelEquipo;
    public  String tarea;
    public  String equipoFTC;
    public  int anioEnElEquipo;
    public  Date cumpleanios;

    //Constructor
    public TeamMember(String nombre, String apellido,
    String areaDelEquipo, String equipoFTC) {
        System.out.println("Este es el constructor de TeamMember");
        this.nombre = nombre;
        this.apellido = apellido;
        this.areaDelEquipo = areaDelEquipo;
        this.equipoFTC = equipoFTC;
    }

    //Método que permitira a una persona tomar prestada una herramienta
    public void tomarHerramienta(/*Herramienta herramienta*/){
      /*Codigo pendiente*/
      System.out.println(nombre + " esta tomando una herramienta");
    }
}
