package jerarquicas.dinamicas;
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
                ancestrosRec(listAncestros,this.raiz,elem,false);
            }
            return listAncestros;
       }
       private void ancestrosRec(Lista list,NodoGen n,Object elem,Boolean v){
            if(n!=null){
                 if(n.getElem().equals(elem)){//caso base
                      list.insertar(elem, list.longitud()+1);
                      v=true;
                 }
                 ancestrosRec(list, n.getHijoIzq(), elem,v);
                 if(v){
                    list.insertar(n.getElem(),list.longitud()+1);
                 }
                 ancestrosRec(list, n.getHermanoDer(),elem,v);
            }
       }
       public int altura(){//
             int alt=-1;
             return alt;
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
    public Lista listarPreorden(){
        Lista list=new Lista();
        if(!this.esVacia()){
             preOrdenRec(this.raiz,list);
        }
        return list;
    }
    private void preOrdenRec(NodoGen n ,Lista list){
        if(n!=null){
            list.insertar(n.getElem(),list.longitud()+1);//visita el nodo n
            if(n.getHijoIzq()!=null){//llamado recursivo por el primer hijo de n
                listarInordenRec(n.getHijoIzq(),list);
            }
            NodoGen hijo=n.getHijoIzq().getHermanoDer();//me muevo a los hermanos del hijo izq
            while(hijo!=null){
               listarInordenRec(hijo,list);
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
           
            if(n.getHijoIzq()!=null){//llamado recursivo por el primer hijo de n
                listarInordenRec(n.getHijoIzq(),list);
            }
            NodoGen hijo=n.getHijoIzq().getHermanoDer();//me muevo a los hermanos del hijo izq
            while(hijo!=null){
               listarInordenRec(hijo,list);
               hijo=hijo.getHermanoDer();
            } 
            list.insertar(n.getElem(),list.longitud()+1);//visita el nodo n
      } 
    }
}
