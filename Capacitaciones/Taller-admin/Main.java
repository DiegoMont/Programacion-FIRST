import java.util.Scanner;

import team_member.los_jefes.TeamMember;
import team_member.los_jefes.Coach;
import team_member.los_jefes.Mentor;
import herramienta.Herramienta;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //Variables para guardar a mentores
        Mentor[] mentores = new Mentor[10];
        Herramienta[] herramientas = new Herramienta[20];
        herramientas[0] = new Herramienta("Flexo", "Color naranja", false);
        herramientas[1] = new Herramienta("Taladro", "Taladro color rojo", true);

        mentores[0] = new Mentor("Diego", "Montano", "Programacion", "Robbit");
        mentores[1] = new Coach("Victor", "Gomez", "Coach", "En todos");
        mentores[1].tomarHerramienta();


        //Ver que si exista mentor
        System.out.println("Nombre: " + mentores[0].nombre);
        System.out.println("Apellido: " + mentores[0].apellido);
        System.out.println("Area: " + mentores[0].areaDelEquipo);
        System.out.println("FTC: " + mentores[0].equipoFTC);

        //Declarar arreglo para guardar a todos los TeamMembers
        TeamMember[] teamMembers;
        teamMembers = new TeamMember[30];
        //Añadir estudiantes
        teamMembers[0] = new TeamMember("Ernesto", "Solis",
        "Ingenieria", "Hardlus");
        teamMembers[1] = new TeamMember("Victoria", "de Leon",
        "Ingenieria", "Hardlus");
        teamMembers[2] = new TeamMember("Tony", "Cedillo",
        "AxM, Ingenieria", "Naubots");

        //Variables para guardar herramientas
        /*
        Herramienta herramienta1;
        Herramienta herramienta2;
        Herramienta herramienta3;
        */
        while(true){

            //Imprimir opciones del menu
            System.out.println("1. Agregar miembro del equipo.");
            System.out.println("2. Agregar herramienta");
            System.out.println("3. Prestar herramienta");
            System.out.println("4. Devolver herramienta");
            System.out.println("5. Exit\n");
            System.out.println("6. Imprimir numero de integrantes");
            System.out.print("Elige una opcion: ");

            //Leer input del usuario
            int input = sc.nextInt();
            switch(input){
                case 1:
                    System.out.print("Ingresa el nombre del miembro del equipo: ");
                    String nombre = sc.next();
                    String apellido = sc.next();
                    String area = sc.next();
                    String equipoFTC = sc.next();
                    //mentor = new Mentor("Diego");
                    teamMembers[3] = new TeamMember(nombre, apellido,
                    area, equipoFTC);
                    System.out.println("El nuevo miembro es " +
                    teamMembers[3].nombre);
                    break;
                case 2:
                    System.out.print("Ingresa el nombre de la herramienta: ");
                    String herramienta = sc.next();
                    //Llamar al constructor de herramienta
                    break;
                case 3:
                    System.out.print("Ingresa la herramienta: ");
                    String nombreHerramienta = sc.next();
                    break;
                case 4: break;
                case 5: return;
                case 6: break;
            }
        }
    }
}
