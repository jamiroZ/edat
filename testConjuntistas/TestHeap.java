package testConjuntistas;
import conjuntistas.ArbolHeap;
public class TestHeap {
    public static void main(String[]args){
        testingHeap();
    }
    public static void testingHeap(){
        ArbolHeap arbol=new ArbolHeap();
        arbol.insertar(4);
        arbol.insertar(9);
        arbol.insertar(10);
        arbol.insertar(40);
        arbol.insertar(90);
        arbol.insertar(100);
        arbol.insertar(7);
        arbol.insertar(0);
        arbol.insertar(33);
        System.out.println(arbol.mostrarArray());
        System.out.println("---------------------");
        if(arbol.eliminarCima()){
            System.out.println("se elimino la cima nuevo arbol:");
            System.out.println(arbol.mostrarArray());
        }
    }
   
}
