package testJerarquicos;
import jerarquicas.dinamicas.ArbolGenerico;
public class TestingArbolGen {
    public static void main(String[]args){
        testArbolGen();
    }
    public static void testArbolGen(){
        ArbolGenerico arbol=new ArbolGenerico();
        //cargo arbol
        cargarArbol(arbol);
        System.out.println(arbol.toString());
    }
    public static void cargarArbol(ArbolGenerico arbol){
        arbol.insertar('A',null);
        arbol.insertar('B','A');
        arbol.insertar('C','A');
        arbol.insertar('D','A');
        arbol.insertar('F', 'D');
        arbol.insertar('H', 'D');
    }
}

