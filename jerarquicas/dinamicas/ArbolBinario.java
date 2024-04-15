package jerarquicas.dinamicas;

import lineales.dinamicas.Lista;

public class ArbolBinario {
    private NodoArbol raiz;
    public ArbolBinario(){
        this.raiz=null;
    }
    public void vaciar(){
        this.raiz=null;
    }
    public Boolean esVacia(){
        return this.raiz==null;
    }
    public Boolean insertar(Object nuevo,Object padre,char lado){
       Boolean exito=true;
       if(this.esVacia()){
           this.raiz=new NodoArbol(nuevo, null, null);
       }else{
           NodoArbol aux=obtenerNodo(this.raiz,padre);
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
}    
