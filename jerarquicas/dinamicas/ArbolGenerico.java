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
       public Boolean insertarPorPosicion(Object elem,Object posPadre){
            Boolean exito=false;

            return exito;
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
                    System.out.println(n.getElem());
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
        if(!this.esVacia()){//arbol no vacio
             Cola q=new Cola();//
             q.poner(this.raiz);//
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
    //EJERCICIOS DEL SEGUNDO PARCIAL
    public Boolean verificarCamino(Lista list){
        Lista camino=list.clone();//usamos un clon para no modificar la lista original
        return verificarCaminoAux(this.raiz ,camino,1);
    }
    private Boolean verificarCaminoAux(NodoGen n,Lista camino,int pos){
        Boolean existe=false;
        if(n!=null ){
              if(n.getElem().equals(camino.recuperar(pos)) && pos < camino.longitud()){//caso recursivo hijo izq
                  System.out.println("clon"+camino.recuperar(pos));
                  pos++;
                  existe=verificarCaminoAux(n.getHijoIzq(),camino,pos);
              }else if(pos==camino.longitud() &&  n.getElem().equals(camino.recuperar(pos))){//caso base final de la lista
                  existe=true;
              }
              if(!existe){
                existe=verificarCaminoAux(n.getHermanoDer(),camino,pos);//caso recursivo hermanoDerecho
              }

        }
        return existe;
    }
    public Lista listarEntreNiveles(int niv1, int niv2){
        Lista niveles=new Lista();
        if(!this.esVacia()){//el arbol no esta vacio 
                listarEntreNivRec(this.raiz,niveles,niv1, niv2,0);
            
        }
        return niveles;
    }
    //actual:variable de nivel actual en movimiento
    //niv1 y niv2 :limites de la busqueda
    //n:nodo puntero
    //niveles:lista con los nodos entre los 2 niveles
    private void listarEntreNivRec(NodoGen n,Lista niveles ,int niv1,int niv2,int actual){
        if(n!=null && actual<=niv2){
              if(niv1<=actual && actual<=niv2){//lista entre esos 2 niveles
                  niveles.insertar(n.getElem(), niveles.longitud()+1);//inserta elemento
                  if(n.getHermanoDer()!=null){//SI HAY HERMANOS EN ESE NIVEL
                       //Me muevo hacia los hermanos
                       listarEntreNivRec(n.getHermanoDer(), niveles, niv1, niv2, actual);
                  }
              }
              //me muevo al siguiente nivel ,usando el hijo izquierdo
              listarEntreNivRec(n.getHijoIzq(), niveles, niv1, niv2, actual+1);
              
        }
    }
    public Boolean eliminar(Object elem){//elimina un elemento junto asu decendencia
         Boolean listo=false;
         if(!this.esVacia()){
            if(this.raiz.getElem().equals(elem)){//el elemento esta en la raiz
                 this.raiz=null;//vacia el arbol por completo
                 listo=true;
            }else{//busca por subArbol
                 listo=eliminarRec(this.raiz,this.raiz.getHijoIzq(),elem);//busca el elemento y elimina
            }
         }
         return listo;
    }
    private Boolean eliminarRec(NodoGen aux,NodoGen n,Object elem){
        Boolean listo=false;
        if(n!=null && aux !=null){
            System.out.println("aux"+aux.getElem());
            System.out.println("n"+n.getElem());
             if(n.getElem().equals(elem) ){//se encontro el elemento
                   if(aux.getHijoIzq()==n){//SI ES HIJO IZQUIERDO
                       if(n.getHermanoDer()!=null){//TIENE HERMANO
                           aux.setHijoIZq(n.getHermanoDer());
                       }else{
                           aux.setHijoIZq(null);
                       }
                   }else{//NO ES EL HIJO
                       if(n.getHermanoDer()!=null){//SI TIENE
                           aux.setHermanoDer(n.getHermanoDer());
                       }else{
                           aux.setHermanoDer(null);
                       }
                       
                   }
                   listo=true;
             }else{
                listo=eliminarRec(n,n.getHijoIzq(),elem);//me muevo al hijo
                if(!listo){
                   listo=eliminarRec(n,n.getHermanoDer(),elem);//me muevo al hermano
                }
             }
        }
        return listo;
    }
    //este metodo atraves de una lista si el ultimo elemento de la lista es hoja del arbol retorna true
    public Boolean verificarCamino2(Lista list){
        Lista camino=list.clone();//usamos un clon para no modificar la lista original
        return verificarCaminoAux2(this.raiz ,camino,1);
    }
    private Boolean verificarCaminoAux2(NodoGen n,Lista camino,int pos){
        Boolean existe=false;
        if(n!=null ){
              if(n.getElem().equals(camino.recuperar(pos)) && pos < camino.longitud()){//caso recursivo hijo izq,tienen mismo objeto
                  System.out.println("clon"+camino.recuperar(pos));
                  pos++;
                  existe=verificarCaminoAux2(n.getHijoIzq(),camino,pos);
              }else if (n.getHijoIzq()==null && n.getElem().equals(camino.recuperar(pos)) ){//caso base final de la lista,mismo objecto y el nodo del arbol es hoja
                         existe=true;
              }
              if(!existe){
                existe=verificarCaminoAux2(n.getHermanoDer(),camino,pos);//caso recursivo hermanoDerecho
              }
        }
        return existe;
    }
}
