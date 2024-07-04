package testConjuntistas;
import conjuntistas.dinamicas.ArbolAVL;

public class TestAVL {
    public static void main(String[] args) {
        testing();
    }
    public static void testing(){
        ArbolAVL arbol=new ArbolAVL();
        arbol.insertar(5);
        arbol.insertar(4);
        arbol.insertar(8);
        arbol.insertar(6);
        arbol.insertar(7);
        System.out.println(arbol.toString());
    }
}
