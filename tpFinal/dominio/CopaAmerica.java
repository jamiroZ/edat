package tpFinal.dominio;


import tpFinal.estructuras.conjuntistas.MapeoAMuchos;
import tpFinal.estructuras.grafoEtiquetado.Grafo;


public class CopaAmerica{
    public static void main(String[] args) {
        testingCopaAmerica();
    }
    public static void testingCopaAmerica(){
        //TDA
        //CLAVE DE PARTIDO Y INSTANCIA DE PARTIDO
        Partido argCanada1=new Partido("arg","canada", "grupo", null, " ", 2, 0);
        Partido argCanada2=new Partido("arg","canada", "semi", null, " ", 3, 1);
        ClaveP clave=new ClaveP("arg", "canada");
        //PRUEBA DE ESTRUCTURAS
        //HASH ABIERTO :MAPEAOAMUCHOS
        MapeoAMuchos hash=new MapeoAMuchos();
        hash.insertar(clave);
        hash.asociar(clave, argCanada1);
        hash.asociar(clave, argCanada2); 
        System.out.println(hash.mostrarPartidosConClave(clave));
        //GRAFO ETIQUETADO 
        Grafo grafo=new Grafo();
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarArco("A", "C", 10);
        System.out.println(grafo.toString());
        /*
        hash.insertar("ohal");
        hash.insertar("pan");
        hash.insertar("queso");
        hash.asociar("hola", "xxxxxx");
        hash.asociar("hola", "aaaaaa");
        hash.asociar("hola", "qqqqqq");
        //hash.eliminar("e");
         System.out.println(hash.pertenece("hola"));
         System.out.println("hola"+hash.obtenerValor("hola").toString());
         System.out.println(hash.obtenerConjuntoRango().toString());
         System.out.println(hash.desasociar("hola", "aaaaaa"));
         System.out.println("hola"+hash.obtenerValor("hola").toString());
         System.out.println(hash.mostrarPartidosConClave("hola"));
        //System.out.println(hash.pertenece("e"));
        System.out.println(hash.toString()); 
        */
    }
}