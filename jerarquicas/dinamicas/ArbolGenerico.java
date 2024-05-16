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
       public Boolean pertenece(Object elem){
            Boolean pertenece=false;
            return pertenece;
       }
       public NodoGen obtenerNodo(NodoGen n,Object elem){//retorna el nodo buscado
            NodoGen ret=null;
            if(n!=null){
                if(n.getElem().equals(elem)){
                    ret=n;
                }else{
                    ret=obtenerNodo(n.getHijoIzq(), elem);//si no encuentra por el hijo izquierdo
                    if(ret!=null){
                        ret=obtenerNodo(n.getHermanoDer(), elem);//busca por el hijo derecho
                    }
                }
            }
            return ret;
       }
       public Lista ancestros(){

       }
       public int altura(){//

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
}
