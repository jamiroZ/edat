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
        if(arbol.pertenece('H')){
            System.out.println("el objeto 'H' pertence al arbol");
        }else{
            System.out.println("el objeto 'H' NO pertence al arbol");
        }
        System.out.println("ancestros de 'D': "+arbol.ancestros('D').toString());
        System.out.println("Inorden: "+arbol.listarInorden().toString());//muestra el recorrido en inOrden
        System.out.println("Preorden: "+arbol.listarPreorden().toString());//muestra el recorrido en preOrden
        System.out.println("Posorden: "+arbol.listarPosOrden().toString());//muestra el recorrido en posOrden
    }
    public static void cargarArbol(ArbolGenerico arbol){
        arbol.insertar('A',null);
        arbol.insertar('B','A');
        arbol.insertar('C','A');
        arbol.insertar('D','A');
        arbol.insertar('F', 'B');
        arbol.insertar('H', 'D');
    }
}

