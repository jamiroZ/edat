package testLineal;

import lineales.dinamicas.Lista;

//uso para los metodos de los parciales q usa el TDA Lista
public class Test2Lista {
	static String sOk = "OK!", sErr = "ERROR";
	public static void main(String[]args){
	   pruebaLista();
	   System.out.println("==================================================================================== ");
	}
    public static void pruebaLista(){
       Lista l=new Lista();
       cargarLista(l);
       System.out.println(l.toString());
       Lista l2=l.obtenerMultiplos(2);
       System.out.println(l2.toString());
    }
    public static void cargarLista(Lista l){
        int TAMAÑO=10;
        int i;
        for(i=1; i <= TAMAÑO; i++){
            l.insertar(i, i);
        }
   }
}

