package testLineal;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;

public class TestCadenas {
    public static void main(String[] args) {
        testCadena();
    }
    public static void testCadena(){
        Cola c1,c2,c3;
        c1=new Cola();
        c3=new Cola();
        cargarCola2(c3);//carga una cola una expresion matematica
        cargarCola(c1);//carga la cola c1
        System.out.println(c1.toString());
        c2=generar(c1);
        System.out.println(c2.toString());
        if(verificarBalanceo(c3)){
            System.out.println("la cola esta valanceada");
        }else{
            System.out.println("la cola NO esta valanceada");
        }
    }
    /* */
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
                       //se coloca las cadenas de caracteres en forma (1)(2)(3)
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
    /* */
    public static Boolean verificarBalanceo(Cola c){
         Boolean verificado=true;
         if(!c.esVacia()){
             Cola clon=c.clone();
             Pila p=new Pila();
             Cola col=new Cola();
             int i=1;
             String txt=" ";
             while(!clon.esVacia()){
                   char elem= (char) clon.obtenerFrente();//castea el objecto a uno de tipo char
                   if(elem=='{' || elem=='('|| elem=='[' || elem==']' || elem==')'|| elem=='}'){
                        p.apilar(elem);
                   }
                   clon.sacar();//se mueve en la cola
             }
             System.out.println(p.toString());
             System.out.println(p.obtenerTope());
             verificado=compararCaracteres(p);
         }
         return verificado;
    }
    public static Boolean compararCaracteres(Pila p){
        Boolean v=true;
        String txt="{[()]}";
        int i=txt.length()-1;
        while(!p.esVacia() && v){
            System.out.println(txt.charAt(i)+" "+p.obtenerTope());
            if(txt.charAt(i) != (char) p.obtenerTope()){
                  v=false;
            }
            i--;
            p.desapilar();
        }
        return v;
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
    public static void cargarCola2(Cola c3){
        c3.poner('{');
        c3.poner('4');
        //c3.poner('[');
        c3.poner('(');
        c3.poner('3');
        c3.poner('*');
        c3.poner('2');
        c3.poner(')');
        c3.poner('-');
        c3.poner('6');
        //c3.poner(']');
        c3.poner('+');
        c3.poner('2');
        c3.poner('}');
        //cola:{4[(3*2)-6]+2}
    }
}

