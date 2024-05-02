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
           if(aux!=null){
                 if(lado=='I' && aux.getIzquierdo()==null){//si no hay nodo en la izquierda del padre lo inserta
                      //enlaza el nodo padre con su nuevo nodo a la izquierda (este nuevo nodo no tiene hijos)
                      aux.setIzquierdo(new NodoArbol(nuevo, null, null));
                 }else if(lado =='D' && aux.getDerecho()==null){//si no hay nodo en la derecha del padre lo inserta
                      //enlaza el nodo padre con su nuevo nodo a la derecha (este nuevo nodo no tiene hijos)
                      aux.setDerecho(new NodoArbol(nuevo, null, null));
                 }else{
                      exito=false;//posicion ocupada,retorna falso
                 }
           }else{
             exito=false;
           }

       }
       return exito;
    }
    //busca el un nodo y lo retorna
    public NodoArbol obtenerNodo(NodoArbol aux,Object padre){
         NodoArbol ret=null;
         if(aux!=null){//repite hasta terminar de buscar por todo el arbol(por todas las hojas)
            if(aux.getElem().equals(padre)){//caso base
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
            cad=cad+"("+aux.getElem()+")";//concatena el elemento del nodo
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
    //verifica si existe un recorrido desde la raiz a alguna hoja que coincida con la lista patron
    public Boolean verificarPatron(Lista patron){
         Boolean verificado=false;
         if(! this.esVacia() && !patron.estaVacia()){
           verificado= patronRec(this.raiz,patron,1,true);
         }
         return verificado;
    }
    private Boolean patronRec(NodoArbol n, Lista p,int i,Boolean v){
         Boolean c=false;//booleano final 
         if( n !=null && v && !c){
               System.out.println(n.getElem());
               //si el objeto es igual y no es la hoja repite ( caso recursivo)
               if(n.getElem().equals(p.recuperar(i)) && i< p.longitud()){
                   c=patronRec(n.getIzquierdo(), p, i+1,true);//recorre subArbolIzq 
                   if(!c){//no se encontro el patron por lado izquierdo revisa derecho
                      c=patronRec(n.getDerecho(), p, i+1,true);//recorre subArbolDer
                   }
               }else if(n.getElem().equals(p.recuperar(i)) && v){//llego a la hoja(caso base 1)
                    v=false;
                    c=true;
               }else{//si recorrio todo el arbol y no esta el patron retorna falso (caso base 2)
                   c=false;
               }
               
               
         }
         return c;
    }
    public Lista frontera(){//devuelve una lista de las hojas de izq a der
       Lista hojas=new Lista();
       if(!this.esVacia()){
          fronteraRec(this.raiz,hojas);
       }
       return hojas;
    }
    public void fronteraRec(NodoArbol n,Lista hojas){
       if(n!=null){
           if(n.getDerecho()==null && n.getIzquierdo()==null){
                    hojas.insertar(n.getElem(), hojas.longitud()+1);
           }
           fronteraRec(n.getIzquierdo(),hojas);
           fronteraRec(n.getDerecho(),hojas);
       }
    }
    public ArbolBinario clonInvertido(){
        ArbolBinario clon=new ArbolBinario();
        clon.raiz=invertidoRec(this.raiz);
        return clon;
    }
    public NodoArbol invertidoRec(NodoArbol n){
        NodoArbol ret;
        if(n!=null){
            NodoArbol izq=invertidoRec(n.getIzquierdo());//me muevo por subArbol izq
            NodoArbol der=invertidoRec(n.getDerecho());//me muevo por subArbol der
            ret=new NodoArbol(n.getElem(),der, izq);//crean nodo y lo inlaza pero cambia lo hijos d lugar
        }else{
            ret=null;//caso base (hoja.getDer()==null o hoja.getIzq()==null)
        }
        return ret;
    }
    public void completarHijos(){
        completarHijosRec(this.raiz);
    }
    public void completarHijosRec(NodoArbol n){
        if(n!= null){
            if(n.getIzquierdo()== null && n.getDerecho()!= null){
                 n.setIzquierdo(new NodoArbol(n.getDerecho().getElem(),null,null));
            }else if(n.getIzquierdo()!= null && n.getDerecho()== null){
                n.setDerecho(new NodoArbol(n.getIzquierdo().getElem(),null,null));
            }
            completarHijosRec(n.getIzquierdo());//recorre subArbol izquierdo
            completarHijosRec(n.getDerecho());//recorre subArbol derecho
        }
    }
}
