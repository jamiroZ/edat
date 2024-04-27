package testLineal;
import lineales.dinamicas.Cola;
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
            Cola c3=new Cola();//copia los caracteres en el mismo orden(ab ->ab)
            Pila aux=new Pila();//copia los caracteres al revez(ab->ba)
            Boolean copiado=false;//
            while(!clon.esVacia()){
                   if( (char) clon.obtenerFrente() !='#' ){
                       c3.poner(clon.obtenerFrente());//clona ab(3)
                       c2.poner(clon.obtenerFrente());//clona ab (1)
                       aux.apilar(clon.obtenerFrente());//clona de ab a ba(2)
                   }else {
                       copiado=true;// termino de copiar y alcanzo el'#'
                   }
                   clon.sacar();
                   if( copiado || clon.esVacia()){
                       while(!aux.esVacia()){
                           c2.poner((char) aux.obtenerTope());
                           aux.desapilar();
                       }
                       while(!c3.esVacia()){
                           c2.poner( (char) c3.obtenerFrente());
                           c3.sacar();
                       }
                       if(!clon.esVacia()){
                           c2.poner('#');
                       }
                       copiado=false;
                  }
          
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
