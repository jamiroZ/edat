package testConjuntistas;
import conjuntistas.ArbolHeap;
public class TestHeap {
    public static void main(String[]args){
        testingHeap();
    }
    public static void testingHeap(){
        ArbolHeap arbol=new ArbolHeap();
        arbol.insertar(1);
        arbol.insertar(10);
        arbol.insertar(2);
        arbol.insertar(20);
        arbol.insertar(30);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(50);

        System.out.println(arbol.mostrarArray());
        System.out.println("---------------------");
        if(arbol.eliminarCima()){
            System.out.println("se elimino la cima nuevo arbol:");
            System.out.println(arbol.mostrarArray());
        }
    }
   
}
