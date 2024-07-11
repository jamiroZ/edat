package tpFinal.dominio;


import tpFinal.estructuras.conjuntistas.ArbolAVL;
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
        //CUIDADES
        Ciudad ciudad=new Ciudad("neuquen",true,false);
        //EQUIPOS
        Equipo eq1,eq2,eq3,eq4,eq5,eq6,eq7,eq8;
        eq1=new Equipo("Argentina");
        eq2=new Equipo("chile");
        eq3=new Equipo("bolivia");
        eq4=new Equipo("EEUU");
        eq5=new Equipo("ecuador");
        eq6=new Equipo("canada");
        eq7=new Equipo("costa rica");
        //PRUEBA DE ESTRUCTURAS
        //AVL
        ArbolAVL arbol=new ArbolAVL();
        arbol.insertar((Comparable) eq1);
       
        System.out.println(arbol.toString());
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
        grafo.insertarVertice("D");
        grafo.insertarVertice("E");
        System.out.println( grafo.insertarVertice("E"));//ya existe entonces false
        grafo.insertarArco("A", "C", 10);
        grafo.insertarArco("A", "B", 13);
        //grafo.insertarArco("A","E",100);
        //grafo.insertarArco("B", "E", 13);
        //grafo.insertarArco("E", "D", 13);
        //System.out.println(grafo.eliminarArco("A","B"));
        System.out.println("CIUDADES:");
        System.out.println(grafo.listarEnProfundidad().toString());
        System.out.println(grafo.existeArco(ciudad,"B"));
        System.out.println(grafo.existeCamino("B","C"));
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