package testConjuntistas;
import conjuntistas.dinamicas.ArbolBB;
public class TestABB {
      public static void main(String []args){
          testingAbb();
      }
      public static void testingAbb(){
           ArbolBB arbol=new ArbolBB();
           System.out.println("cargo 'E' :"+arbol.insertar('E').toString());
           System.out.println("cargo 'B' :"+arbol.insertar('B').toString());
           System.out.println("cargo 'A' :"+arbol.insertar('A').toString());
           System.out.println("cargo 'D' :"+arbol.insertar('D').toString());
           System.out.println("cargo 'C' :"+arbol.insertar('C').toString());
           System.out.println("cargo 'F' :"+arbol.insertar('F').toString());
           System.out.println("cargo 'G' :"+arbol.insertar('G').toString());
           System.out.println("cargo 'H' :"+arbol.insertar('H').toString());
           System.out.println(arbol.toString());
           if(arbol.pertenece('H')){
              System.out.println("'H' esta en el arbol");
           }
           if(!arbol.pertenece('Z')){
            System.out.println("'Z' no esta en el arbol");
           }
           System.out.println("eliminar 'E' "+arbol.eliminar('E'));
           System.out.println(arbol.toString());
           System.out.println("Maximo del arbol:"+arbol.maximoElem());
           System.out.println("Minimo del arbol"+arbol.minimoElem());
           System.out.println(arbol.listar().toString());
      }
}

