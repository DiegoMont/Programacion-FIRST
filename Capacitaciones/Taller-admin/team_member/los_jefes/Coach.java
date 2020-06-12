package team_member.los_jefes;
public class Coach extends Mentor {

  public Coach(String nombre,String apellido, String areaDelEquipo, String equipoFTC){
    super(nombre, apellido, areaDelEquipo, equipoFTC);
  }
  //COmentario
  public Mentor ascender(TeamMember estudiante){
    Mentor mentorNuevo = new Mentor(estudiante.nombre, estudiante.apellido, estudiante.areaDelEquipo, estudiante.equipoFTC);
    return mentorNuevo;
  }
}
