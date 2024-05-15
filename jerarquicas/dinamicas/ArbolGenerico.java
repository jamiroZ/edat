package jerarquicas.dinamicas;
public class ArbolGenerico{
       private NodoGen raiz;
       public ArbolGenerico(){//CONSTRUCTOR VACIO
            this.raiz=null;
       }
       public Boolean insertar(Object elem,Object padre){
            Boolean inserto=false;
            if(!this.esVacia()){
                 NodoGen n=obtenerNodo(this.raiz,padre);
                 if(n!=null){
                    if(n.getHijoIzq()==null){
                        n.setHijoIZq(new NodoGen(elem,null,null));
                    }else{
                        while(){
                            
                        }
                    }
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
       public void vaciar(){//VACIA EL ARBOL
           this.raiz=null;
       }
       public Boolean esVacia(){//RETORNA TRUE SI ESTA VACIO EL ARBOL
           return (this.raiz==null);
       }
}
