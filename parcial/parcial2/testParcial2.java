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
           ArbolBB C=new ArbolBB();
           //CARGO ARBOL BINARIO DE BUSQUEDA
           //A
           A.insertar(56);
           A.insertar(13);
           A.insertar(78);
           A.insertar(7);
           A.insertar(24);
           A.insertar(15);
           A.insertar(100);
           A.insertar(2);
           A.insertar(120);
           A.insertar(80);
           A.insertar(89);
           A.insertar(130);
           A.insertar(110);
           A.insertar(112);
           A.insertar(93);
           //B
            B.insertar(13); 
            B.insertar(7);
            B.insertar(24);
            B.insertar(15);
            //C
            
            C.insertar(50);
            C.insertar(35);
            C.insertar(27);
            C.insertar(30);
            C.insertar(33);
            C.insertar(20);
            C.insertar(15);
            C.insertar(20);
            C.insertar(18);
            C.insertar(27);
            System.out.println(C.toString());
           Lista list,list2,list3;
           //CARGO LAS LISTAS DE COMPARCION
           list=new Lista();
           list.insertar(56, 1);
           list.insertar(78, 2);

           list2=new Lista();
           list2.insertar(56, 1);
           list2.insertar(78,2);
           list2.insertar(100, 3);
           

           list3=new Lista();
           list3.insertar(56, 1);
           list3.insertar(13, 2);
           list3.insertar(10, 3);
           
           cargarArbol(arbol1);
           System.out.println(arbol1.toString());
          
           //PRUEBO METODO VERIFICARCAMINO con 3 listas 
           System.out.println("la lista:20,54,27 se encuentra en el arbol: "+arbol1.verificarCamino(list));
           System.out.println("la lista:20,54,27,17 se encuentra en el arbol:"+arbol1.verificarCamino(list2));
           System.out.println("la lista:20,17 se encuentra en el arbol:"+arbol1.verificarCamino(list3));
           
           //PRUEBO METODO VERIFICARCAMINO2 CON 3 LISTAS
           System.out.println("la lista:20,54,27 se encuentra en el arbol: "+ arbol1.verificarCamino2(list));
           System.out.println("la lista:20,54,27,17 se encuentra en el arbol:"+ arbol1.verificarCamino2(list2));
           System.out.println("la lista:20,17 se encuentra en el arbol:"+ arbol1.verificarCamino2(list3));
           //PRUEBO METODO LISTAR ENTRE NIVELES
           System.out.println("entre niveles:"+arbol1.listarEntreNiveles(0, 4).toString());
           if(arbol1.eliminar(7)){
               System.out.println(arbol1.toString());
           }
           System.out.println( " ");
           //PRUEBO LISTAR HASTA NIVEL 
           System.out.println(arbol1.listarHastaNivel(3).toString());
           System.out.println("  ");
           //PRUEBO REPETIR HEI
           arbol1.repetirHEI(13);//tiene 14 repetido NO HACE NADA
           arbol1.repetirHEI(78);//tiene a 100 no repetido debe copiar y pegar
           System.out.println(arbol1.toString());

           //ARBOL BINARIO DE BUSQUEDA
           System.out.println("ARBOL A");
           System.out.println(A.toString());
           System.out.println(" ");
           System.out.println("ARBOL B");
           System.out.println(B.toString());
           System.out.println(" ");
           /*if(A.eliminarMinimo()){
                System.out.println("ELIMINA 56 DEL ARBOL A");
                System.out.println(A.toString());
           }*/
           System.out.println(A.clonarParteInvertida(100).toString());
           //
           System.out.println(" ");//ESPACIO
           System.out.println("listar mayor "+A.listarMayorIgual(13).toString());
           System.out.println("    ");//ESPACIO
           System.out.println("listar menor "+A.listarMenorIgual(56).toString());
           System.out.println("  ");//ESPACIO
           System.out.println("ELIMINAR HOJAS ENTRE RANGOS ");
           A.eliminarHojasEnRango(5, 100);
           System.out.println(A.toString());
      }
        public static void cargarArbol(ArbolGenerico arbol){
           arbol.insertar(56, null);
           arbol.insertar(13, 56);
           arbol.insertar(78, 56);
           arbol.insertar(9,13);
           arbol.insertar(24,13);
           arbol.insertar(10, 13);
           arbol.insertar(24, 13);
           arbol.insertar(15, 78);
           arbol.insertar(100, 78);
        }
}
