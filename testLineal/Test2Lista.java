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
       System.out.println(l.toString()+" longitud:"+l.longitud());
       l.eliminarApariciones(1);
       System.out.println(l.toString()+" longitud:"+l.longitud());
       //Lista l2=l.obtenerMultiplos(2);
       //System.out.println(l2.toString());
    }
    public static void cargarLista(Lista l){
            l.insertar(4, 1);
            l.insertar(1, 2);
            l.insertar(3, 3);
            /*l.insertar(3, 4);
            l.insertar(1, 5);
            l.insertar(3, 6);
            l.insertar(1, 7);*/
        
   }
}

