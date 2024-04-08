package testLineal;
//import lineales.estaticas.Pila;
import java.util.Scanner;

//importa la clase Alumno para su implementacion
import clasesParticulares.Alumno;
//import lineales.estaticas.Pila;
import lineales.dinamicas.Pila;
/**
 *
 * @author Catedra EDAT - FAI - UNCOMA
 * Ultima modificación: 22/04/2021
 *
 */
public class TestingPila {
    /*
     * NOTA
     * se modifico el test de tal forma que ingresando 'int' o 'txt' el algoritmo
     * 'TESTPILA' realiza el test sin confundir o mezclar los valores
     * usando 3 arreglos
     * ENTEROS:0..9
     * TEXTO:a,...,k
     * CAPICUA:1,2,..,2,1 (este para la verificacion del metodo esCapicua)
     */
    static String sOk = "OK!", sErr = "ERROR";
    public static void main(String[]args) {
        
          testPila();
    }

    public static void testPila() {
        String capicua="";
        Scanner sc = new Scanner(System.in);
        String entrada="",rep="";
        //ambos arreglos tienen longitud=11
        //si es dinamica debe acomodar los 11, si es estatica hasta 10
        int[]enteros={1,2,3,4,5,6,7,8,9,10,11};//arreglo ordenado de manera creciente
        int[]capicuaArray={0,1,2,3,4,5,4,3,2,1,0};//arreglo de enteros capicua
        String[]txt={"a","b","c","d","e","f","g","h","i","j","k"};//arreglo de string alfabetico
        //creo y instancio arreglo Alumnos con alumnos
        Alumno[]alumnos=new Alumno[10];
        //el metodo mas que nada es para ejemplificar el uso del tda alumnos solo carga el legajo
        cargarAlumnos(alumnos);
        Object []aux=new Object[enteros.length];
        System.out.println("COMIENZO TEST PILA");
        Pila p1 = new Pila();
        System.out.println("ingrese 'txt' , 'int' o 'Alumno' para elegir el tipo de Objecto que componen la Pila: ");
        entrada=sc.nextLine();
        //carga un arreglo auxiliar para asi ir modificando y asignando en la Pila 
        if(entrada.equalsIgnoreCase("txt")){//carga un arreglo de String
           for(int i=0; i< aux.length ;i++){
                aux[i]= txt[i];
           }
        }else if(entrada.equalsIgnoreCase("int")){//carga un arreglo de enteros
            //si se ingreso 'int' pregunta si la pila que quiere esta ordenada de forma creciente o capicua
            System.err.println("ingrese 'c' si es capicua si no ingrese cualquiera otra letra");
            capicua=sc.nextLine();

            if(capicua.equalsIgnoreCase("c")){//si es capicua carga el arreglo de enteros capicua
                for(int i=0; i< aux.length ;i++){
                aux[i]=capicuaArray[i];
                }
            }else{

                for(int i=0; i< aux.length ;i++){//si No es capicua carga un arreglo de enteros en forma creciente
                    aux[i]=enteros[i];
                }
            }    
        }else if(entrada.equalsIgnoreCase("Alumno")){//carga el arreglo con alumnos
              for(int i=0; i<aux.length; i++){
                 aux[i]=alumnos[i];
              }
        }
        System.out.println("\t\t\t\t\t\t\t\t--> " + p1.toString());
        //probamos que la Pila funciona con objetos de tipo string y y despues vaciamos la pila para usar el test con enteros
        System.out.print("Apila "+aux[0]+" espera TRUE y ["+aux[0] +"]:\t\t\t\t" + ((p1.apilar(aux[0])) ? sOk : sErr));
        System.out.println("\t--> " + p1.toString());
        System.out.print("Apila "+aux[1]+" espera TRUE y ["+aux[0]+","+aux[1]+"]:\t\t\t\t" + ((p1.apilar(aux[1])) ? sOk : sErr));
        System.out.println("\t--> " + p1.toString());
        System.out.print("Apila "+aux[2]+" espera TRUE y ["+aux[0]+","+aux[1]+","+aux[2]+"]:\t\t\t\t" + ((p1.apilar(aux[2])) ? sOk : sErr));
        System.out.println("\t--> " + p1.toString());
        System.out.print("Apila "+aux[3]+" espera TRUE y ["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+"]:\t\t\t" + ((p1.apilar(aux[3])) ? sOk : sErr));
        System.out.println("\t--> " + p1.toString());
        System.out.print("Apila "+aux[4]+" espera TRUE y ["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+"]:\t\t\t" + ((p1.apilar(aux[4])) ? sOk : sErr));
        System.out.println("\t--> " + p1.toString());
        System.out.print("Apila "+aux[5]+" espera TRUE y ["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+","+aux[5]+"]:\t\t\t" + ((p1.apilar(aux[5])) ? sOk : sErr));
        System.out.println("\t--> " + p1.toString());
        System.out.print("Apila "+aux[6]+" espera TRUE y ["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+","+aux[5]+","+aux[6]+"]:\t\t\t" + ((p1.apilar(aux[6])) ? sOk : sErr));
        System.out.println("\t--> " + p1.toString());
        System.out.print("Apila "+aux[7]+" espera TRUE y ["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+","+aux[5]+","+aux[6]+","+aux[7]+"]:\t\t" + ((p1.apilar(aux[7])) ? sOk : sErr));
        System.out.println("\t--> " + p1.toString());
        System.out.print("Apila  "+aux[8]+" espera TRUE y ["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+","+aux[5]+","+aux[6]+","+aux[7]+","+aux[8]+"]:\t\t" + ((p1.apilar(aux[8])) ? sOk : sErr));
        System.out.println("\t--> " + p1.toString());
        System.out.print("Apila "+aux[9]+" espera TRUE y ["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+","+aux[5]+","+aux[6]+","+aux[7]+","+aux[8]+","+aux[9]+"]:\t\t" + ((p1.apilar(aux[9])) ? sOk : sErr));
        System.out.println("\t--> " + p1.toString());
        System.out.print("Apila "+aux[10]+" espera false en estatica true en dinamica:\t" + p1.apilar(aux[10]));
        System.out.println("\t--> " + p1.toString());
        //llamada al metodo que verifica si es capicua la Pila(solo si se eligio la Pila con objectos de tipo enteros)

        if(p1.obtenerTope() instanceof String){
            if (((String) p1.obtenerTope()).equalsIgnoreCase("k")) {
                 System.out.println("si pudo apilar el "+aux[10]+" , lo saca para continuar");
                 p1.desapilar();
            }
        }else if(p1.obtenerTope() instanceof Integer) {
            if((int)p1.obtenerTope()==11){
                System.out.println("si pudo apilar el "+aux[10]+" , lo saca para continuar");
                p1.desapilar();
            }        
        }else if(p1.obtenerTope() instanceof Alumno){
            if((Alumno)p1.obtenerTope()==aux[10]){
                System.out.println("si pudo apilar el "+aux[10]+" , lo saca para continuar");
                p1.desapilar();
            }
        }   
           
        /*
        *un método que, dada una pila llena con dígitos (0..9), verique si la secuencia
        *forma un número capicúa o no. Nota: Utilizar una pila auxiliar para facilitar la operación
        */
        System.out.print("espera \t["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+","+aux[5]+","+aux[6]+","+aux[7]+","+aux[8]+","+aux[9]+"]");
        System.out.println("\t\t\t\t\t--> " + p1.toString());
        if(p1.obtenerTope() instanceof String){
             System.out.println("Recupera tope espera "+aux[9]+" recupera: " + (String) p1.obtenerTope() + "\t\t\t" + ((((String) p1.obtenerTope()).equalsIgnoreCase("j")) ? sOk : sErr));
        }else{
             System.out.println("Recupera tope espera "+aux[9]+" recupera: " + (int) p1.obtenerTope() + "\t\t\t" + (((int) p1.obtenerTope() == 10 ) ? sOk : sErr));
        }
       

        System.out.println("Desapila: \t\t\t\t\t\t" + ((p1.desapilar()) ? sOk : sErr));
        System.out.println("espera \t["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+","+aux[5]+","+aux[6]+","+aux[7]+","+aux[8]+","+aux[9]+"] recupera \t\t\t\t--> " + p1.toString());
        System.out.println("Desapila: \t\t\t\t\t\t" + ((p1.desapilar()) ? sOk : sErr));
        System.out.println("espera \t["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+","+aux[5]+","+aux[6]+","+aux[7]+","+aux[8]+"] recupera \t\t\t\t--> " + p1.toString());
        if(p1.obtenerTope() instanceof String){
            System.out.println("Apila z: \t\t\t\t\t\t" + ((p1.apilar("z")) ? sOk : sErr));
        }else{
            System.out.println("Apila 6: \t\t\t\t\t\t" + ((p1.apilar(6)) ? sOk : sErr));
        }
        
        System.out.println("espera \t["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+","+aux[5]+","+aux[6]+","+aux[7]+","+aux[8]+","+aux[9]+"] recupera \t\t\t\t--> " + p1.toString());
        if(p1.obtenerTope() instanceof String){
            System.out.println("Recupera tope, espera "+aux[9]+" recupera " + (String) p1.obtenerTope() + "\t\t\t" + ((((String) p1.obtenerTope()).equalsIgnoreCase("z")) ? sOk : sErr));
        }else{
            System.out.println("Recupera tope, espera "+aux[9]+" recupera " + (int) p1.obtenerTope() + "\t\t\t" + (((int) p1.obtenerTope() == 6) ? sOk : sErr));  
        }

        Pila p2 = p1.clone();
        System.out.println("Copia espera ["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+","+aux[5]+","+aux[6]+","+aux[7]+","+aux[8]+","+aux[9]+"]: \t\t\t\t--> " + p2.toString());

        while (!p1.esVacia()) {
            System.out.print("Desapila espera true: \t\t\t\t\t" + ((p1.desapilar()) ? sOk : sErr));
            System.out.println("\t--> " + p1.toString());
        }
        System.out.print("Se vacio la pila p1");
        System.out.println("\t\t\t\t\t\t--> " + p1.toString());
        System.out.println("Desapila en pila vacia espera false: \t\t\t" + ((p1.desapilar()) ? sErr : sOk));
        System.out.println("Verifica tope en pila vacia espera null: \t\t" + ((p1.obtenerTope() == null) ? sOk : sErr));

        System.out.println("Verifica copia ["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+","+aux[5]+","+aux[6]+","+aux[7]+","+aux[8]+"] : \t\t\t\t-->" + p2.toString());
        System.out.println("Apila "+aux[6]+" : \t\t\t\t\t\t" + ((p2.apilar(aux[6])) ? sOk : sErr));
        System.out.println("Verifica copia ["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+","+aux[5]+","+aux[6]+","+aux[7]+","+aux[8]+","+aux[9]+"] : \t\t\t\t-->" + p2.toString());
        System.out.println("Apila "+aux[7]+" espera false en estatica true en dinamica:\t" + p2.apilar(aux[7]));
        System.out.println("Apila "+aux[8]+" espera false en estatica true en dinamica:\t" + p2.apilar(aux[8]));
        if(p2.obtenerTope() instanceof String){
                if( ((String) p2.obtenerTope()).equalsIgnoreCase("k")){
                    System.out.println("si apilo el 8 y el 9, los saca para continuar");
                    p2.desapilar();
                    p2.desapilar();
                }
        } else if(p2.obtenerTope() instanceof Integer){
            if ((int) p2.obtenerTope() == 9 ) {
               System.out.println("si apilo el 8 y el 9, los saca para continuar");
               p2.desapilar();
               p2.desapilar();
            }
        }

        System.out.println("Verifica copia modificada ["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+","+aux[5]+","+aux[6]+","+aux[7]+","+aux[8]+","+aux[9]+"] : \t\t\t--> " + p2.toString());

        System.out.println("Desapila: \t\t\t\t\t\t" + ((p2.desapilar()) ? sOk : sErr));
        System.out.println("Desapila: \t\t\t\t\t\t" + ((p2.desapilar()) ? sOk : sErr));
        System.out.println("Desapila: \t\t\t\t\t\t" + ((p2.desapilar()) ? sOk : sErr));
        System.out.println("Desapila: \t\t\t\t\t\t" + ((p2.desapilar()) ? sOk : sErr));
        System.out.println("Verifica copia modificada, espera ["+aux[0]+","+aux[1]+","+aux[2]+","+aux[3]+","+aux[4]+","+aux[5]+"] : \t\t\t--> " + p2.toString());
        p2.vaciar();
        System.out.println("Vacia copia espera pila vacia: \t\t\t\t\t--> " + p2.toString());
    } 
    public static boolean esCapicua(Pila pila1){
        boolean esCap = true;
        Pila pila2 = pila1.clone();
        Pila pilaAux = new Pila();
        String cadena1 = pila1.toString();
        String cadena2 ;
        while(pila1.esVacia()!=true){
            pilaAux.apilar(pila2.obtenerTope());
            pila2.desapilar();
        }

        cadena2 = pilaAux.toString();
        if(cadena1.equals(cadena2)){
            esCap = true;
        }
        else{
            esCap = false;
        }
        return esCap;
    }
    public static void cargarAlumnos(Alumno[]alumnos){
        for(int i=0;i<alumnos.length;i++){
             alumnos[i]=new Alumno(i+"");
        }
    }
}
