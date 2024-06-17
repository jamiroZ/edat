package testConjuntistas;
import conjuntistas.dinamicas.ArbolAVL;

public class TestAVL {
    public static void main(String[] args) {
        testing();
    }
    public static void testing(){
        ArbolAVL arbol=new ArbolAVL();
        arbol.insertar(8);
        arbol.insertar(12);
        arbol.insertar(10);
        arbol.insertar(9);
        System.out.println(arbol.toString());
    }
}
