import java.util.ArrayList;
public class Main {
    public static void main(String[] args){
        
        //int[] numerosPrimos = {2, 3, 5, 7};
        ArrayList<Integer> numerosPrimos;
        numerosPrimos = new ArrayList <>();
        
        //Añadir numeros primos a la lista
        for(int i = 2; i < 101; i++) {
            if(esPrimo(i)){
                numerosPrimos.add(i);
            }
        }
        
        //Imprimir todos los numeros primos
        for(int numero: numerosPrimos)
        System.out.println(numero);

    }
    
    public static boolean esPrimo(int numero) {
        //numero = 2
        for(int i = 2; i < numero; i++) {
            if (numero % i==0) {
                return false;
            }
        }
        
        return true;
    }
}