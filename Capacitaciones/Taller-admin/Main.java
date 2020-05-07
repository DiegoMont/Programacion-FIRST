import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //Variables para guardar a mentores
        /*
        Mentor mentor;
        Mentor mentor1;
        Mentor mentor2;
        */

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
            System.out.print("Elige una opcion: ");

            //Leer input del usuario
            int input = sc.nextInt();
            switch(input){
                case 1:
                    System.out.print("Ingresa el nombre del miembro del equipo: ");
                    String nombre = sc.next();
                    //mentor = new Mentor("Diego");
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
            }
        }
    }
}
