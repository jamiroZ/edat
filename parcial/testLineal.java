package parcial;
//importa TDA pila,cola,lista
import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

public class testLineal {
    public static void main(String[] args) {
        testL();
    }
    public static void testL(){
        Cola c=new Cola();
        cargarCola(c);
        System.out.println("la cola tiene "+cuentaSecuencias(c)+" secuencias capicua");
        
        
    }
    public static int cuentaSecuencias(Cola c){
        int cant=0;
        Cola clon=c.clone();
        Lista l=new Lista();
        while(!clon.esVacia()){
                if((char) clon.obtenerFrente()!='$'){
                        l.insertar(clon.obtenerFrente(), l.longitud()+1);
                        System.out.println(l.toString());
                }
                if((char) clon.obtenerFrente()=='$'){
                    if(esCapicua(l,1,l.longitud())){
                        cant=cant+1;
                    }
                }
                clon.sacar();
        }
        
        return cant;
    }
    public static Boolean esCapicua(Lista l,int i,int j){
        Boolean capi=true;
        if(i!=j){
            if(l.recuperar(i)==l.recuperar(j)){
                l.eliminar(j);
                l.eliminar(i);
                esCapicua(l, i+1, j-1);
            }else{
                l.vaciar();
                capi=false;
            }
        }
        return capi;
    }
    public static void cargarCola(Cola c){
            c.poner('A');
            c.poner('B');
            c.poner('B');
            c.poner('A');
            c.poner('$');
            c.poner('B');
            c.poner('$');
            c.poner('A');
            c.poner('C');
            c.poner('T');
            c.poner('C');
            c.poner('A');
    }
}
