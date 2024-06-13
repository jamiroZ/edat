package conjuntistas.dinamicas;

public class ArbolAVL{
    private NodoAVL raiz;
    public ArbolAVL(){//CONSTRUCTOR VACIO
        this.raiz=null;
    }
    public Boolean pertenece(Comparable elem){
       
    }
    public Boolean insertar(Comparable elem){
        Boolean exito=false;
        if(this.esVacio()){//CASO ARBOL VACIO
            this.raiz=new NodoAVL(elem, null,null);
            exito=true;
        }else{//ARBOL NO VACIO
            exito=insertarRec(this.raiz,elem);
        }
        return exito;
    }
    public Boolean insertarRec(NodoAVL n, Comparable elem){
        Boolean exito=true;
        //precondicion n no es nulo
        if(elem.compareTo(n.getElem())==0){
            exito=false;//reporta el error:elemento repetido
        }else if(elem.compareTo(n.getElem())<0){//CASO sub arbol izquierdo
            if(n.getIzquierdo()!=null){
                 exito=insertarRec(n.getIzquierdo(),elem);
            }else{//enlaza como hijo izquierdo
                n.setIzquierdo(new NodoAVL(elem,null,null));
            }
        }else if(elem.compareTo(n.getElem())>0){//CASO sub arbol derecho
            if(n.getDerecho()!=null){
                exito=insertarRec(n.getDerecho(),elem);
            }else{//enlaza como hijo derecho
                n.setDerecho(new NodoAVL(elem,null,null));
            }
        }
        balanceDeN(n);//BALANCEA SI ES NECESARIO 
        return exito;
    }
    public void balanceDeN(NodoAVL n){
        NodoAVL izq=n.getIzquierdo(),der=n.getDerecho();
        if(izq.getAltura()-der.getAltura()== 2){//SI HAY DESBALANCE ES MAYOR O IGUAL A 2 (desbalance a la izquierda)
            NodoAVL auxI=izq.getIzquierdo(),auxD=der.getDerecho();
            if(izq.getAltura()-auxD.getAltura()==-1){//desbalance a la derecha (rotacion doble izquierda-derecha)
                
            }else{//si no 0 o 1 desbalance a la izquierda (rotacion simple izquierda)

            }
        }else if(izq.getAltura()-der.getAltura()<= -2){//O MENOR O IGUAL A -2 (desbalance a la derecha)
            NodoAVL auxI=izq.getIzquierdo(),auxD=der.getDerecho();
            if(izq.getAltura()-auxD.getAltura()==1){//desbalance a la izquierda (rotacion doble derecha-izquierda)
               
            }else{//si no 0 o 1 desbalance a la izquierda (rotacion simple derecha)

            }
        }
    }
    public Boolean eliminar(){
        
    }
    public Boolean esVacio(){//RETORNA VERDADERO SI ESTA VACIO EL ARBOL
        return (this.raiz==null);
    }
}