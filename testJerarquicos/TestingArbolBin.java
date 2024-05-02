package testJerarquicos;

import jerarquicas.dinamicas.ArbolBinario;
import lineales.dinamicas.Lista;

public class TestingArbolBin {
    public static void main(String[] args) {
        testArbolBin();
    }
    public static void testArbolBin(){
        Lista patron=new Lista();
        Lista hojas=new Lista();
        ArbolBinario arbolInv=new ArbolBinario();
        ArbolBinario arbol=new ArbolBinario();
        cargarLista(patron);
        cargarArbol(arbol);
        System.out.println(patron.toString());
        System.out.println(arbol.toString());
        if(arbol.verificarPatron(patron)){
           System.out.println("hay un recorrido con ese patron");
        }
        //
        hojas=arbol.frontera();
        System.out.println(hojas.toString());
        //
        arbolInv=arbol.clonInvertido();
        System.out.println(arbolInv.toString());

    }
    public static void cargarArbol(ArbolBinario arbol){
             arbol.insertar('A',null,' ');
             arbol.insertar('B','A','I');
             arbol.insertar('C','B','D');
             arbol.insertar('D','C','I');
             arbol.insertar('E','A','D');
             arbol.insertar('F','E','I');
             arbol.insertar('G','E','D');
             arbol.insertar('H','F','I');
             arbol.insertar('I','H','D');
    }
    public static void cargarLista(Lista patron){//
            patron.insertar('A', 1);
            patron.insertar('B', 2);
            patron.insertar('C', 3);
            patron.insertar('D', 4);
    }
}
