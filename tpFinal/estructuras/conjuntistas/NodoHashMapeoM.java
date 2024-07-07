package tpFinal.estructuras.conjuntistas;
import tpFinal.estructuras.lista.Lista;
public class NodoHashMapeoM {
    private Object dominio;
    private Lista rango;
    private NodoHashMapeoM enlace;
    public NodoHashMapeoM(Object dominio,NodoHashMapeoM ant ){
          this.dominio=dominio;
          this.rango=new Lista();//rango del dominio
          this.enlace=ant;//enlaza con el anterior
          
    }
    public NodoHashMapeoM getEnlace(){//enlace entre Dominio y rango
         return this.enlace;
    }
    public void setEnlace(NodoHashMapeoM n){
        this.enlace=n;
    }
    public Object getDominio(){//retorna dominio
        return this.dominio;
    }
    public void setDominio(Object dom){//modificar dominio
         this.dominio=dom;
    }
    public Lista getRango(){//retorna la lista de elementos en el rango correspondientes al mismo dominio
        return this.rango;
    }
    public void setRango(Object elem){//inserta un elemento en el rango
        if(this.rango.localizar(elem)==-1){//si no existe lo inserta
               this.rango.insertar(elem,rango.longitud()+1);
        }
    }
    
}
