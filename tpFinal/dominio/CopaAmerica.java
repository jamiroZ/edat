package tpFinal.dominio;
import tpFinal.estructuras.conjuntistas.MapeoAMuchos;
public class CopaAmerica{
    public static void main(String[] args) {
        testingCopaAmerica();
    }
    public static void testingCopaAmerica(){
        MapeoAMuchos hash=new MapeoAMuchos();
        hash.insertar("a");
        hash.insertar("e");
        //hash.eliminar("e");
        System.out.println(hash.pertenece("a"));
        System.out.println(hash.pertenece("e"));
        System.out.println(hash.toString());
    }
}