package tpFinal.estructuras.conjuntistas;

import tpFinal.estructuras.lineales.Lista;

public class NodoHashMapeoM {
    private Object dominio;
    private Lista rango;
    private NodoHashMapeoM enlace;
    public NodoHashMapeoM(Object dominio,NodoHashMapeoM ant ){
          this.dominio=dominio;
          this.rango=new Lista();//rango del dominio
          this.enlace=ant;//enlaza con el anterior
          
    }
    public NodoHashMapeoM getEnlace(){//enlace entre Dominios
         return this.enlace;
    }
    public void setEnlace(NodoHashMapeoM n){//desenlaze
        this.enlace=n;
    }
    public Object getDominio(){//retorna object dominio
        return this.dominio;
    }
    public void setDominio(Object dom){//modificar object dominio
         this.dominio=dom;
    }
    public Lista getRango(){//retorna la lista de elementos en el rango correspondientes al mismo dominio
        return this.rango;
    }
    public Boolean agregarRango(Object elem){//inserta un elemento en el rango
        Boolean flag=false;
        if(this.rango.localizar(elem)==-1){//si no existe lo inserta
               this.rango.insertar(elem,rango.longitud()+1);
               flag=true;
        }
        return flag;
    }
    public void eliminarElemEnRango(){

    }
  
}
