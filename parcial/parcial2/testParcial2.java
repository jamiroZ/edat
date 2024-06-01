package parcial.parcial2;
import conjuntistas.dinamicas.ArbolBB;
import jerarquicas.dinamicas.ArbolGenerico;
import lineales.dinamicas.Lista;
public class testParcial2 {
       public static void main(String[] aStrings){
           //listar entre niveles
           testingParcial();
           //verificar camino
       }
       public static void testingParcial(){
           ArbolGenerico arbol1=new ArbolGenerico();
           ArbolBB A=new ArbolBB();
           ArbolBB B=new ArbolBB();
           //CARGO ARBOL BINARIO DE BUSQUEDA
           //A
           A.insertar(56);
           A.insertar(13);
           A.insertar(78);
           A.insertar(7);
           A.insertar(24);
           A.insertar(15);
           A.insertar(100);
           //B
            B.insertar(13);
            B.insertar(24);
            B.insertar(15);
            B.insertar(7);
           Lista list,list2,list3;
           //CARGO LAS LISTAS DE COMPARCION
           list=new Lista();
           list.insertar(20, 1);
           list.insertar(54, 2);
           list.insertar(27, 3);
           list2=new Lista();
           list2.insertar(20, 1);
           list2.insertar(54, 2);
           list2.insertar(27, 3);
           list2.insertar(17, 4);
           cargarArbol(arbol1);
           list3=new Lista();
           list3.insertar(20, 1);
           list3.insertar(17, 2);
           System.out.println(arbol1.toString());
           //PRUEBO METODO VERIFICARCAMINO con 3 listas
           System.out.println("la lista:20,54,27 se encuentra en el arbol: "+arbol1.verificarCamino(list));
           System.out.println("la lista:20,13,12,45 se encuentra en el arbol:"+arbol1.verificarCamino(list2));
           System.out.println("la lista:20,17 se encuentra en el arbol:"+arbol1.verificarCamino(list3));
           //PRUEBO METODO LISTAR ENTRE NIVELES
           System.out.println(arbol1.listarEntreNiveles(1, 2).toString());
           if(arbol1.eliminar(17)){
               System.out.println(arbol1.toString());
           }
           System.out.println(arbol1.altura());
           System.out.println("  ");

           //ARBOL BINARIO DE BUSQUEDA
           System.out.println("ARBOL A");
           System.out.println(A.toString());
           System.out.println(" ");
           System.out.println("ARBOL B");
           System.out.println(B.toString());
           System.out.println(" ");

           if(A.eliminarMinimo()){
                System.out.println("ELIMINA 56 DEL ARBOL A");
                System.out.println(A.toString());
           }
      }

        
        public static void cargarArbol(ArbolGenerico arbol){
           arbol.insertar(20, null);
           arbol.insertar(13, 20);
           arbol.insertar(54, 20);
           arbol.insertar(15, 13);
           arbol.insertar(12, 13);
           arbol.insertar(11, 54);
           arbol.insertar(27, 54);
           arbol.insertar(4, 54);
           arbol.insertar(17, 27);
        }
}
