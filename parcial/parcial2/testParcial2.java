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
           ArbolGenerico arbol2=new ArbolGenerico();
           cargarArbol2(arbol2);
           cargarArbol(arbol1);
           //LISTA 1 (TRUE)
           Lista list=new Lista();
           list.insertar('A',1);
           list.insertar('J',2);
           list.insertar('Z',3);
           //LISTA 2 (TRUE)
           Lista list2=new Lista();
           list2.insertar('A', 1);
           list2.insertar('H',2);
           //LISTA 3 (FALSE)
           Lista list3=new Lista();
           list3.insertar('A',1);
           list3.insertar('Z', 2);
           System.out.println(" ");
           System.out.println(arbol1.toString());
           arbol1.jerarquizar('Y');
           System.out.println(" ");
           System.out.println(arbol1.toString());
           //VE SI EL ARBOL TIENE MAYOR GRADO QUE N
           System.out.println("El arbol tiene menor grado que 5"+arbol1.gradoEsMenor(7));
           //PRUEBA DE CAMINOS EN ARBOL
           System.out.println("LA LISTA | "+list.toString() +" | ESTA "+arbol1.verificarCamino(list)+" EN EL ARBOL");
           System.out.println("LA LISTA | "+list2.toString() +" | ESTA "+arbol1.verificarCamino(list2)+" EN EL ARBOL");
           System.out.println("LA LISTA | "+list3.toString() +" | ESTA "+arbol1.verificarCamino(list3)+" EN EL ARBOL");
           //PRUEBO EL OTRO ARBOL GENERICO
           System.out.println(arbol2.toString());
           System.out.println(" ");
           System.out.println(arbol2.listarEntreNiveles(1, 2).toString());
           System.out.println(" ");
           //LISTA:20,54,11(true)
           Lista l1=new Lista();
           l1.insertar(20,1);
           l1.insertar(54,2);
           l1.insertar(11,3);
           System.out.println("LA LISTA -"+l1.toString()+" : "+arbol2.verificarCamino2(l1));
           System.out.println(" ");
           //LISTA:54,27,17(false)
           Lista l2=new Lista();
           l2.insertar(54,1);
           l2.insertar(27,2);
           l2.insertar(17,3);
           System.out.println("LA LISTA -"+l2.toString()+" : "+arbol2.verificarCamino2(l2));
           System.out.println(" ");
           //LISTA:20,13(false)
           Lista l3=new Lista();
           l3.insertar(20,1);
           l3.insertar(13,2);
           System.out.println("LA LISTA -"+l3.toString()+" : "+arbol2.verificarCamino2(l3));
           System.out.println(" ");
           ArbolBB A=new ArbolBB();
           A.insertar(20);
           A.insertar(12);
           A.insertar(7);
           A.insertar(3);
           A.insertar(10);
           A.insertar(14);
           A.insertar(17);
           A.insertar(28);
           A.insertar(25);
           A.insertar(34);
           A.insertar(31);
           System.out.println(A.toString());
           System.out.println(A.mejorCandidato(100));
           A.eliminarHojaSubArbol(12);
           System.out.println(A.toString());
           /* ArbolBB B=new ArbolBB();
           ArbolBB C=new ArbolBB();
           //CARGO ARBOL BINARIO DE BUSQUEDA
           //A
           
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
            System.out.println(" ");//ESPACIO
            System.out.println("eliminar Anterior A 15: "+ C.eliminarElemAnterior(15));
            System.out.println("eliminar Anterior A 20: "+ C.eliminarElemAnterior(20));
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
           /*System.out.println(A.clonarParteInvertida(100).toString());
           
           System.out.println(" ");//ESPACIO
           System.out.println("listar mayor "+A.listarMayorIgual(13).toString());
           System.out.println("    ");//ESPACIO
           System.out.println("listar menor "+A.listarMenorIgual(56).toString());
           System.out.println("  ");//ESPACIO
           System.out.println("ELIMINAR HOJAS ENTRE RANGOS ");
           A.eliminarHojasEnRango(5, 100);
           System.out.println(A.toString());*/
      }
        public static void cargarArbol(ArbolGenerico arbol){
           arbol.insertar('A', null);
           arbol.insertar('H', 'A');
           arbol.insertar('R', 'A');
           arbol.insertar('P','A');
           arbol.insertar('J','A');
           arbol.insertar('Z', 'J');
           arbol.insertar('Y', 'R');
           arbol.insertar('X', 'R');
           arbol.insertar('E', 'R');
           arbol.insertar('J', 'H');
        }
        public static void cargarArbol2(ArbolGenerico arbol){
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
