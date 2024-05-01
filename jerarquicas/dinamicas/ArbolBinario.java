package jerarquicas.dinamicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

public class ArbolBinario {
    private NodoArbol raiz;
    public ArbolBinario(){//constructor
        this.raiz=null;
    }
    public void vaciar(){//vacia el arbol
        this.raiz=null;
    }
    public Boolean esVacia(){//revisa si esta vacio el arbol
        return this.raiz==null;
    }
    public Boolean insertar(Object nuevo,Object padre,char lado){
       Boolean exito=true;
       if(this.esVacia()){
           this.raiz=new NodoArbol(nuevo, null, null);
       }else{
           NodoArbol aux=obtenerNodo(this.raiz,padre);//busca el nodo padre (aux=nodo padre)
           if(lado=='I' && aux.getIzquierdo()==null){//si no hay nodo en la izquierda del padre lo inserta
                //enlaza el nodo padre con su nuevo nodo a la izquierda (este nuevo nodo no tiene hijos)
                aux.setIzquierdo(new NodoArbol(nuevo, null, null));
           }else if(lado == 'D' && aux.getDerecho()==null){//si no hay nodo en la derecha del padre lo inserta
                //enlaza el nodo padre con su nuevo nodo a la derecha (este nuevo nodo no tiene hijos)
                aux.setIzquierdo(new NodoArbol(nuevo, null, null));
           }else{
                exito=false;//posicion ocupada,retorna falso
           }
       }
       return exito;
    }
    //busca el un nodo y lo retorna
    public NodoArbol obtenerNodo(NodoArbol aux,Object padre){
         NodoArbol ret=null;
         if(aux!=null){//repite hasta terminar de buscar por todo el arbol(por todas las hojas)
            if(aux.getElem()==padre){//caso base
                 ret=aux;
            }else{//casos recursivos
       
                ret=obtenerNodo(aux.getIzquierdo(), padre);//revisa nodo izquierdo
                if(ret==null){//si reviso el nodo izquierdo y no encontro el nodo,revisa el derecho
                    ret=obtenerNodo(aux.getDerecho(), padre);
                } 
            }
            
         }
         return ret;
    }
    //retorna el elemento del nodo padre del elemento ingresado
    public Object padre(Object elem){
        Object ret;
        NodoArbol aux=this.raiz;//puntero para moverse por el arbol desde la raiz
        ret=padreRec(aux,elem,aux.getElem());
        return ret;
    }
    //nos retorna el objecto del nodo padre
    public Object padreRec(NodoArbol padre,Object hijo,Object padreAux){
        //si no encuentra el elemento o el elemento se encuentra en la raiz retorna null
        Object ret=null;
         if(padre!=null){//puede repite hasta terminar de buscar por todo el arbol(por todas las hojas)
            if(padre.getElem()==hijo){//caso base
                 ret=padreAux;
            }else{//casos recursivos
       
                ret=padreRec(padre.getIzquierdo(),hijo,padre.getElem());//revisa nodo izquierdo
                if(ret==null){//si reviso el nodo izquierdo y no encontro el nodo,revisa el derecho
                    ret=padreRec(padre.getDerecho(),hijo,padre.getElem());
                }
            }
         }   
        return ret;
    }
    //retorna el nivel donde se encuentre el nodo,si no esta en el arbol retorna -1
    public int nivel(Object elem){
         return nivelRec(elem,this.raiz,0);
    }
    public int nivelRec(Object elem,NodoArbol aux,int lvl){
        int niv=-1;
        if(aux!=null){
            if(aux.getElem()==elem){//caso base
                niv=lvl;
            }else{
                niv=nivelRec(elem,aux.getIzquierdo(),lvl+1);//revisa nodo izquierdo
                if(niv==-1){//si reviso el nodo izquierdo y no encontro el nodo,revisa el derecho
                    niv=nivelRec(elem,aux.getDerecho(),lvl+1);
                } 
            } 
        }
        return niv;
    }
    public Lista listarPreorden(){
        Lista listaElem=new Lista();
        preOrdenRec(this.raiz,listaElem);
        return listaElem;
    }
    public void preOrdenRec(NodoArbol aux,Lista list){
        if(aux!=null){//repite hasta recorrer todo el arbol en preOrden
            list.insertar(aux.getElem(), list.longitud()+1);//inserta en la lista nueva
            preOrdenRec(aux.getIzquierdo(),list);//se mueve por la izquierda hasta el ultimo nodo(null)
            preOrdenRec(aux.getDerecho(), list);//se mueve por la derecha hasta el ultimo nodo(null)
        }
    }
    public Lista listarInorden(){
        Lista listaElem=new Lista();
        inordenRec(this.raiz,listaElem);
        return listaElem;
    }
    public void inordenRec(NodoArbol aux,Lista list){
        if(aux!=null){
            inordenRec(aux.getIzquierdo(),list);//se mueve por la izquierda hasta el ultimo nodo(null)
            list.insertar(aux.getElem(), list.longitud()+1);//inserta en la lista nueva
            inordenRec(aux.getDerecho(), list);//se mueve por la derecha hasta el ultimo nodo(null)
        }
    }
    public Lista listarPosOrden(){
        Lista listaElem=new Lista();
        posOrdenRec(this.raiz,listaElem);
        return listaElem;
    }
    public void posOrdenRec(NodoArbol aux,Lista list){
        if(aux!=null){
            posOrdenRec(aux.getIzquierdo(),list);//se mueve por la izquierda hasta el ultimo nodo(null)
            posOrdenRec(aux.getDerecho(), list);//se mueve por la derecha hasta el ultimo nodo(null)
            list.insertar(aux.getElem(), list.longitud()+1);//inserta en la lista nueva
        }
    }
    public Cola listarNivel(){
        Cola colaElem=new Cola();//COLA QUE SE RETORNA
        
        if(this.raiz!=null){//si el arbol no esta vacio
            Cola colaAux=new Cola();//cola auxiliar
            colaAux.poner(this.raiz);//frente de la cola=raiz del arbol
            NodoArbol nodoActual;//nodo puntero(aux)
            while(!colaAux.esVacia()){//mientras la cola no este vacia
                 //asigna al nodo actual el frente de colaAux
                 nodoActual= (NodoArbol) colaAux.obtenerFrente();
                 colaAux.sacar();//cambia de frente (frente.getEnlace())
                 colaElem.poner(nodoActual.getElem());//coloca en la cola un elemento del arbol
                 //coloca por nivel de izq a derecha
                 if(nodoActual.getIzquierdo()!=null){//si tiene hijo izq el nodo lo coloca en la cola
                    colaElem.poner(nodoActual.getIzquierdo());
                 }
                 if(nodoActual.getDerecho()!=null){//si tiene hijo der el nodo lo coloca en la cola
                    colaElem.poner(nodoActual.getDerecho());
                 }
            }
        }
        return colaElem;
    }
    public String toString(){
        String txt="Arbol Vacio";
        if(!this.esVacia()){
            txt=toStringRec(this.raiz);
        }
        return txt;
    }
    public String toStringRec(NodoArbol aux){
        String cad="";
        if(aux!=null){
            cad="("+aux.getElem()+")";//concatena el elemento del nodo
            NodoArbol izq,der;//nodos auxiliares(izquierdo y derecho)
            izq=aux.getIzquierdo();
            der=aux.getDerecho();
            if(izq!=null){//si hay nodo hijo izquierdo
                cad=cad+"HI:"+izq.getElem()+" ";//concatena hijo izq
            }else{
                cad=cad+"HI:--";//si no hay nodo hijo izquierdo
            }
            if(der!=null){//si hay nodo hijo derecho
                 cad=cad+"HD:"+der.getElem()+"-\n";//concatena hijo der
            }else{
                 cad=cad+"HD:-- -\n";//si no hay nodo hijo derecho
            }
            cad=cad+toStringRec(izq);//caso recursivo hijo izquierdo
            cad=cad+toStringRec(der);//caso recursivo hijo derecho
        }
        return cad;
    }
    public ArbolBinario clone(){
          ArbolBinario clon=new ArbolBinario();
          clon.raiz=cloneRec(this.raiz);
          return clon;
    }
    public NodoArbol cloneRec(NodoArbol aux){
        NodoArbol ret;
        if(aux!=null){//mientras el nodo no sea nulo(caso recursivo)
            NodoArbol izq=cloneRec(aux.getIzquierdo());//consigo el subArbol izquierdo
            NodoArbol der=cloneRec(aux.getDerecho());//consigo el subArbol derecho
            ret=new NodoArbol(aux.getElem(), izq, der);//crea el nodo clon con el elemento y sus hijos izq y der
        }else{//caso base
            ret=null;
        }
        return ret;
    }
}    
