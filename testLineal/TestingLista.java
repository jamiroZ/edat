package testLineal;

import lineales.dinamicas.Lista;


public class TestingLista {
    static String sOk = "OK!", sErr = "ERROR";
    public static void main(String[] args) {
        testLista();
    }
    public static void testLista(){
        System.out.println("COMIENZO TEST LISTA");
		Lista q1 = new Lista();
		System.out.println("Lista vacia: \t\t\t\t\t\t\t--> " + q1.toString());
		boolean exito = true;
		int num = 1;
		while (num <= 10) {
		    System.out.print("Pone " + num + "  : \t" + q1.insertar(num,num));
			num++;
			System.out.println("\t--> " + q1.toString());
		}
        System.out.print("eliminar posicion 5 true : \t\t\t\t\t" + ((q1.eliminar(5) == true) ? sOk : sErr));
		System.out.println("\t--> " + q1.toString());
		System.out.println("Recupera elemento de la posicion 2 \t\t\t" + q1.recuperar(2));
		System.out.print("elimina posicion 9 true: \t\t\t\t\t" + ((q1.eliminar(9) == true) ? sOk : sErr));
		System.out.println("\t--> " + q1.toString());
		System.out.println("Recupera posicion del elemento 7 recupera: \t\t\t" + q1.localizar(7));
		System.out.print("Pone 23 en la posicion 3 true: \t\t\t\t\t" + ((q1.insertar(23,3) == true) ? sOk : sErr));
		System.out.println("\t--> " + q1.toString());
		System.out.print("Pone 24 en la posicion 10 true: \t\t\t\t\t" + ((q1.insertar(24,10) == true) ? sOk : sErr));
		System.out.println("\t--> " + q1.toString());

	    Lista q2 = q1.clone();
		System.out.println("Copia espera [1 2 <23> 3 4  6 7 8 9 <24>]: \t\t\t--> " + q2.toString());

		System.out.println("pone elemento  0 en la posicion 11: \t" + q1.insertar(0,11));
		System.out.print("Pone elemento 43 en la posicion 12: \t" + q1.insertar(43,12));
		System.out.println("\t--> " + q1.toString());
    }
}
