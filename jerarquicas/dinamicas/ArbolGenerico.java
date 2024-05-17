package jerarquicas.dinamicas;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
public class ArbolGenerico{
       private NodoGen raiz;
       public ArbolGenerico(){//CONSTRUCTOR VACIO
            this.raiz=null;
       }
       public Boolean insertar(Object elem,Object padre){
            Boolean inserto=true;
            if(this.esVacia()){//si el arbol esta vacio lo añade como raiz
                 this.raiz=new NodoGen(elem, null, null);
            }else{
                 NodoGen n=obtenerNodo(this.raiz,padre);//obtiene el nodo padre
                 if(n==null){//no existe el padre retorna false
                      inserto=false;
                 }else{//si existe el padre 
                      //crea un nuevo nodo que tiene como hermano al nodoHijoIzq
                      NodoGen m=new NodoGen(elem, null, n.getHijoIzq());
                      n.setHijoIZq(m);//enlaza el nodo padre al nuevo nodo
                 }
            }
            return inserto;
       }
       public Boolean pertenece(Object elem){//retorna true si el elemento pertenece al arbol
            Boolean pertenece=false;
            if(!this.esVacia()){
                NodoGen n=obtenerNodo(this.raiz,elem);//si el elemento esta en el arbol retorna el nodo
                if(n!=null){
                    pertenece=true;
                }
            }
            return pertenece;
       }
       public NodoGen obtenerNodo(NodoGen n,Object elem){//retorna el nodo buscado
            NodoGen ret=null;
            if (n != null){
                if (n.getElem() == elem){
                    ret = n;
                } else {
                    NodoGen hijo = n.getHijoIzq(); //guardo el primer hijo
                    boolean flag = false;
    
                    while (!flag && hijo != null){ //si no ha sido encontrado y no se terminaron sus hijos
                        ret = obtenerNodo(hijo, elem);
                        if (ret != null){ //Nodo encontrado, salgo de la repetitiva
                            flag = true;
                        } else {
                            hijo = hijo.getHermanoDer(); //me muevo a su hermano
                        }
                    } 
                    
                }
            }
            return ret;
       }
       public Lista ancestros(Object elem){
            Lista listAncestros=new Lista();
            if(!this.esVacia() && obtenerNodo(this.raiz,elem)!=null){// si no esta vacio el arbol y el elemento existe en este 
                ancestrosRec(listAncestros,this.raiz,elem);
                listAncestros.eliminar(1);//elimina el objeto que se busco para crear la lista
            }
            return listAncestros;
       }
       private void ancestrosRec(Lista list,NodoGen n,Object elem){
            if(n!=null){
                 if(n.getElem().equals(elem)){//caso base
                    list.insertar(n.getElem(), list.longitud()+1);
                 }else{//se mueve por el hijo izq
                    ancestrosRec(list,n.getHijoIzq(),elem);
                    if(!list.estaVacia()){//si se encontro el objeto ya se coloco en la lista y empieza a llenar con los ancestros
                       list.insertar(n.getElem(), list.longitud()+1);
                    }
                 }
                 //si no encontro por los hijos izq se mueve al hermano derecho y repite proceso
                  if(list.estaVacia()){
                    ancestrosRec(list, n.getHermanoDer(), elem);
                  }
            }
       }
       //RETORNA EL OBJECTO DEL NODO PADRE DONDE SE ENCUENTRE EL OBJETO
       public Object padre(Object elem){
           Object pa;
           pa=padreRec(this.raiz,elem);
           return pa;
       }
       private Object padreRec(NodoGen n,Object elem){
           Object papa=null;
           if(n!=null){
                if(n.getHijoIzq()!=null){
                    if(n.getHijoIzq().getElem().equals(elem)){
                         papa=n.getElem();
                    }else{
                         papa=padreRec(n.getHijoIzq(), elem);
                    }
                }
                if(papa==null){
                    papa=padreRec(n.getHermanoDer(),elem);
                }
           }
           return papa;
       }
       //retorna la altura maxima desde la raiz a una hija 
       public int altura(){//devuelve el camino mas largo desde la raiz a una hoja
             int alt=-1;//arbol vacio
             //la hoja tiene altura 0
             if(!this.esVacia()){
                alt=alturaRec(this.raiz,0);
             }
             return alt;
       }
       private int alturaRec(NodoGen n,int i){
            int cont=1;//la raiz vale 1
            if(n!=null){
                if(n.getHijoIzq()!=null){
                    cont= cont + 1;
                    cont= cont+alturaRec(n.getHijoIzq(),i);
                }else{
                    cont=0;//hoja
                }
                //termino de recorrer hijos izq guarda el valor y recorre otro subArbol para comparar
                if(cont>i && n.getHijoIzq()==null){
                   i=cont;
                   alturaRec(n.getHermanoDer(), i);//se mueve al hermano derecho
                }
            }
            return cont;
       }
       public int nivel(Object elem){
           int niv=-1;
           if(!this.esVacia() && obtenerNodo(this.raiz, elem)!=null){
               if(this.raiz.getElem().equals(elem)){//si el elemento esta en la raiz retorna o
                   niv=0;
               }else{//sino busca en el arbol
                   niv=nivelRec(this.raiz,elem);
               }
             
           }
           return niv;
       }
       private int nivelRec(NodoGen n,Object elem){
           int i=0;
           if(n!=null){
                if(n.getElem().equals(elem)){
                    i=1;
                }else{
                    i= i + 1 +nivelRec(n.getHijoIzq(), elem);
                }
                //i NO incrementa pues se mueve en el mismo nivel
                if(n.getHijoIzq()==null){
                    i= i + nivelRec(n.getHermanoDer(), elem);
                }
           }
           return i;
       }
       public void vaciar(){//VACIA EL ARBOL
           this.raiz=null;
       }
       public Boolean esVacia(){//RETORNA TRUE SI ESTA VACIO EL ARBOL
           return (this.raiz==null);
       }
       public String toString(){
        //crea un string del arbol actual
        String ret; 
        if (this.raiz == null){
            ret = "Arbol Vacio";
        } else {
            ret = toStringAux(this.raiz);
        }
        return ret;
    }

    private String toStringAux(NodoGen n){ 
        //metodo aux toString, concatena el nodo actual y sus hijos, luego recorre recursivamente a sus hijos
        String ret = "";

        if (n != null){
            ret = ret + n.getElem()+ ")  ->  ";
            NodoGen hijo = n.getHijoIzq(); //guardo hijo ext izq

            if (hijo == null){ //si no tiene primer hijo entonces es hoja
                ret = ret + "[Hoja]";
            } else {
                while (hijo != null){  //si el hijo existe
                    ret = ret + hijo.getElem(); //concateno a su hijo
                    hijo = hijo.getHermanoDer(); //me muevo a su otro hijo
                    if (hijo != null){
                        ret = ret + ", ";
                    }
                }
            }
            hijo = n.getHijoIzq(); //vuelvo al primer hijo
             //comienza recorrido de los hijos de n recursivamente
            while (hijo != null){
                ret = ret + "\n"+toStringAux(hijo);
                hijo = hijo.getHermanoDer();
            }
        }
        return ret;
    }
    //LISTA Inorden
    public Lista listarInorden(){
        Lista salida=new Lista();
        listarInordenRec(this.raiz,salida);
        return salida;
    }
    private void listarInordenRec(NodoGen n,Lista list){
        if(n!=null){
            if(n.getHijoIzq()!=null){//llamado recursivo por el primer hijo de n
                  listarInordenRec(n.getHijoIzq(),list);
            }
            //visita el nodo n
            list.insertar(n.getElem(),list.longitud()+1);
            if(n.getHijoIzq()!=null){//llamado recursivo con los otros hijos de n
               NodoGen hijo=n.getHijoIzq().getHermanoDer();//me muevo a los hermanos del hijo izq
               while(hijo!=null){
                   listarInordenRec(hijo,list);
                   hijo=hijo.getHermanoDer();
               }
            }
        }
    }
    //lista preOrden
    public Lista listarPreorden(){
        Lista list=new Lista();
             preOrdenRec(this.raiz,list);
        return list;
    }
    private void preOrdenRec(NodoGen n ,Lista list){
        if(n!=null){
            //visita el nodo n
            list.insertar(n.getElem(),list.longitud()+1);
            //llamados recursivos con los hijos de n
            NodoGen hijo=n.getHijoIzq();
            while(hijo!=null){
                preOrdenRec(hijo, list);
                hijo=hijo.getHermanoDer();
            }
      } 
    }
    public Lista listarPosOrden(){
        Lista list=new Lista();
        if(!this.esVacia()){
             posOrdenRec(this.raiz,list);
        }
        return list;
    }
    private void posOrdenRec(NodoGen n ,Lista list){
        if(n!=null){
            //visita los hijos 
            if(n.getHijoIzq()!=null){
                posOrdenRec(n.getHijoIzq(), list);
            }
            list.insertar(n.getElem(), list.longitud()+1);
            //visita el hermano derecho
            posOrdenRec(n.getHermanoDer(), list);

        } 
    }
    public Lista listarPorNivel(){
        Lista list=new Lista();
        if(!this.esVacia()){
             Cola q=new Cola();
             q.poner(this.raiz);
             NodoGen nodoActual,hijo;
             while(!q.esVacia()){
                   nodoActual= (NodoGen) q.obtenerFrente();
                   q.sacar();
                   list.insertar(nodoActual.getElem(),list.longitud()+1);
                   hijo=nodoActual.getHijoIzq();
                   while(hijo!=null){
                        q.poner(hijo);
                        hijo=hijo.getHermanoDer();
                   }
             }
        }
        return list;
    }
    public ArbolGenerico clone(){
        ArbolGenerico clon=new ArbolGenerico();
        clon.raiz=cloneRec( this.raiz);
        return clon;
    }
    private NodoGen cloneRec( NodoGen n){
        NodoGen ret=null;
        if(n!=null){
            NodoGen hijoIzq, hermano;//punteros
            //recorrido recursivo por abajo(hijos)
            hijoIzq=cloneRec(n.getHijoIzq());
            //recorrido recursivo por derecha(hermanos)
            hermano=cloneRec(n.getHermanoDer());
            //crea el nodo con el elemento a clonar y su hijo izq y hermano der si posee
            ret=new NodoGen(n.getElem(),hijoIzq,hermano);
        }else{
            ret=null;
        }
        return ret;
    }
}
