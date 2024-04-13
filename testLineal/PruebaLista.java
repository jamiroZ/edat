package testLineal;
//metodos que usa este PruebaLista
/*PRUEBA
recibe una lista L1 cargada con dígitos (números enteros de 0 a 9) y verifica si los
elementos que contiene tienen la forma cadena0cadena0cadena*
(donde cadena*
es cadena invertida).
Ej: si L1=[9,6,5,0,9,6,5,0,5,6,9], cadena=965, luego cadena*=569, entonces la lista L1 cumple con la
condición deseada.
 */
/*CONCATENAR
concatenar : recibe dos listas L1 y L2 y devuelve una lista nueva con los elementos de L1 y L2
concatenados. Ej: si L1=[2,4,6] y L2=[5,1,6,7] debe devolver [2,4,6,5,1,6,7]
 */
/*INVERTIR
invertir : recibe una lista L y devuelve una lista nueva con los elementos de L invertidos. Ej: si
L1=[2,4,6] debe devolver [6,4,2]
 */

import java.util.Scanner;

import lineales.dinamicas.Lista;
public class PruebaLista {
    public static void main(String[] args) {
        pruebaLista();
    }
    public static void pruebaLista(){
         Scanner sc=new Scanner(System.in);
         Lista l1,l2,l3,l2Invertida,concatenacion;
         String opcion=" ";
         char repetir= 'y';
         l1=new Lista();
         l2=new Lista();
         l3=new Lista();
         concatenacion=new Lista();
         l2Invertida=new Lista();
         //este metodo carga con un tamaño fijo listas(0...9)
         cargarLista(l1);
         cargarLista(l2);
         cargarListaCadena(l3);//carga la lista de forma cadena0cadena0cadena(0...9)
         System.out.println("MUESTRO LAS LISTAS CREADAS");
         System.out.println("L1: "+l1.toString());
         System.out.println("L2: "+l2.toString());
         System.out.println("L3: "+l3.toString());
         
         while(repetir=='y'){
            System.out.println("INGRESE LA LETRA CORRESPONDIENTE PARA PROBAR LOS METODOS:");
            System.out.println("Ingrese 'concatenar':");
            System.out.println("Ingrese 'comprobar' :");
            System.out.println("Ingrese 'invertir' :");
            opcion=sc.nextLine();
            switch(opcion){
                case "concatenar":
                     concatenacion=concatenarLista(l1,l2);
                     System.out.println("concatenacion de l1 y l2 :");
                     System.out.println(concatenacion.toString());
                break;
                case "comprobar" :
                    // comprobar(l1);
                break;
                case "invertir" :
                     l2Invertida=invertir(l2);
                     System.out.println("cadena l2 invertida:");
                     System.out.println(l2Invertida.toString());
                break;
            }
            System.out.println("desea volver a probar los metodos?");
            System.out.println("ingrese 'y' si es asi o cualquier otra letra si no es asi");
            repetir=sc.next().charAt(0);
            if(repetir=='y'){
                opcion=" ";
            }
         }

    }
    public static void cargarLista(Lista l){
         int TAMAÑO=10;
         int i;
         for(i=1; i <= TAMAÑO; i++){
             l.insertar(i, i);
         }
    }
    public static void cargarListaCadena(Lista l){
            l.insertar(5, 1);
            l.insertar(5, 2);
            l.insertar(5, 3);
            l.insertar(0, 4);
            l.insertar(4, 5);
            l.insertar(0, 6);
            l.insertar(6, 7);
            l.insertar(7, 8);
            l.insertar(0, 9);
            l.insertar(9, 10);
    }
    public static Lista concatenarLista(Lista l1,Lista l2){
        //  l3 se comporta primero como l1 y despues se agregar los elementos de l2
        Lista concatenacion=l1.clone();
        int i=1;
        int longitudC=concatenacion.longitud()+1;//longitud de la concatenacion
        int longitudL2=l2.longitud();//longitud de la segunda Lista que se concatena
        while(i<= longitudL2){//copia elementos de la lista 2
            concatenacion.insertar(l2.recuperar(i),longitudC);
            longitudC++;//incrementa la longitud de la concatenacion
            i++;// pasa a la siguiente posicion de la lista 2
        }
        return concatenacion;
    }
    public static Lista invertir(Lista l){
        Lista invertida=new Lista();
        int i=1,longitud;
        for(longitud=l.longitud();longitud>0; longitud--){
            invertida.insertar(l.recuperar(longitud), i);
            i++;
        }
            
        
        return invertida;
    }
}
