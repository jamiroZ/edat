package testLineal;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;

public class TestCadenas {
    public static void main(String[] args) {
        testCadena();
    }
    public static void testCadena(){
        Cola c1,c2;
        c1=new Cola();
        cargarCola(c1);//carga la cola c1
        System.out.println(c1.toString());
        c2=generar(c1);
        System.out.println(c2.toString());

    }
    public static Cola generar(Cola c1){
        Cola c2=new Cola();
        if(!c1.esVacia()){
            Cola clon=c1.clone(); //clona cola c1 para no modificar la original
            Lista list=new Lista();//
            Pila aux=new Pila();
            int i=1;//contador
            while(!clon.esVacia()){
               if( (char) clon.obtenerFrente() !='#'){
                   list.insertar(clon.obtenerFrente(), i);
                   c2.poner(clon.obtenerFrente());
                   aux.apilar(clon.obtenerFrente());
                   i++;
               }else if((char) clon.obtenerFrente() =='#'){
                  i=1;
                  while(!aux.esVacia()){
                      c2.poner((char) aux.obtenerTope());
                      aux.desapilar();
                  }
                  int j=1;
                  while(!list.estaVacia()){
                      c2.poner( list.recuperar(j));
                      list.eliminar(j);
                      j++;
                  }
                  c2.poner('#');
                 
               }
               clon.sacar();
            }
        }
        return c2;
    }
    public static void cargarCola(Cola c1){
            c1.poner('A');
            c1.poner('B');
            c1.poner('#');
            c1.poner('C');
            c1.poner('#');
            c1.poner('B');
            c1.poner('A');
    }
}
