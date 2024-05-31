package conjuntistas.dinamicas;
import lineales.dinamicas.Lista;

public class ArbolBB{
    private NodoABB raiz;
    //constructor vacio
    public ArbolBB(){
        this.raiz=null;
    }
    public Boolean insertar(Comparable elem){
        Boolean p=true;
        if(this.esVacio()){//si el arbol esta vacio el elemento se coloca en la raiz
             this.raiz=new NodoABB(elem);
        }else{
             p=insertarRec(this.raiz,elem);
        }
        return p;
    }
    public Boolean insertarRec(NodoABB n, Comparable elem){
        Boolean exito=true;
        //precondicion n no es nulo
        if(elem.compareTo(n.getElem())==0){
            exito=false;//reporta el error:elemento repetido
        }else if(elem.compareTo(n.getElem())<0){//CASO sub arbol izquierdo
            if(n.getIzquierdo()!=null){
                 exito=insertarRec(n.getIzquierdo(),elem);
            }else{//enlaza como hijo izquierdo
                n.setIzquierdo(new NodoABB(elem));
            }
        }else if(elem.compareTo(n.getElem())>0){//CASO sub arbol derecho
            if(n.getDerecho()!=null){
                exito=insertarRec(n.getDerecho(),elem);
            }else{//enlaza como hijo derecho
                n.setDerecho(new NodoABB(elem));
            }
        }
        return exito;
    }
    public Boolean eliminar(Comparable elem){ 
        Boolean elimino=false;
        if(!this.esVacio() && pertenece(elem)) {
            elimino= eliminarRec(this.raiz,elem,null);
        } 
        return elimino;
    }
    private Boolean eliminarRec(NodoABB n,Comparable elem,NodoABB padre){
        Boolean elim=false;
        if(n!=null){
             if(elem.equals(n.getElem())){
                 if(n.getDerecho()==null && n.getIzquierdo()==null){//si no tiene hijos(caso hoja)
                      casoHoja(n,padre);
                 }else if((n.getIzquierdo()!=null && n.getDerecho()!=null) ){//si tiene los 2 hijos
                      Object comp= (Object) menorEnSubArbol(n.getDerecho());//candidato a reemplazar a su padre
                      eliminarRec(n.getDerecho(), elem, n);//elimina al candidato
                      n.setElem(comp);//remplazo el nodo actual con el candidato
                 }else{//tiene 1 hijo puede ser derecho o izquierdo
                      caso1Hijo(n,padre);
                 }
                 elim=true;
             }else{
                 if(elem.compareTo(n.getElem())<0){//se mueve por el subArbol izquierdo
                    elim=eliminarRec(n.getIzquierdo(), elem, n);
                 }else{//se mueve por el subArbol derecho
                    elim=eliminarRec(n.getDerecho(), elem, n);
                 }
             }
        }
        return elim;
    }
    private void casoHoja(NodoABB n,NodoABB padre){
        if(padre==null){//el elemento es la raiz del arbol
              this.raiz=null;
        }else{//si no enlaza el nodo PADRE con el hijo de su hijo(null)
            if(((Comparable) padre.getElem()).compareTo(n.getElem())<0){//si el hijo es menor cambia hijo izquierdo
                  padre.setIzquierdo(null);
            }else{//si el hijo es mayor cambia hijo derecho
                  padre.setDerecho(null);
            }
        }
    }
    private void caso1Hijo(NodoABB n , NodoABB padre){
        if(padre!=null){
               if(n.getDerecho()==null){//si el hijo derecho es null(solo tiene hijo izquierdo)
                       if(((Comparable) padre.getElem()).compareTo(n.getElem()) < 0 ){//y el hijo izquierdo es menor que su padre
                           padre.setIzquierdo(n.getIzquierdo());
                       }else{//si el hijo izquierdo es mayor que su padre
                           padre.setDerecho(n.getIzquierdo());
                       }
               }else{//su hijo izquierdo es null(solo tiene hijo derecho)
                       if(((Comparable) padre.getElem()).compareTo(n.getElem())  < 0 ){//y el hijo izquierdo es menor que su padre
                           padre.setIzquierdo(n.getDerecho());
                       }else{//si el hijo izquierdo es mayor que su padre
                           padre.setDerecho(n.getDerecho());
                       }
               }
        }else{//caso espacial:si el elemento es raiz lo intercambio por su hijo
             if(n.getIzquierdo()==null){
                  this.raiz=n.getDerecho();
             }else{
                  this.raiz=n.getIzquierdo();
             }
        }
       
    }

    private Comparable menorEnSubArbol(NodoABB n){//retorna el elemento minimo de un subArbol 
        Comparable minimo;
        if(n.getIzquierdo()==null){
            minimo=(Comparable) n.getElem();//caos base
        }else{
            minimo=menorEnSubArbol(n.getIzquierdo());//caso recursivo
        }
        return minimo;
    }
    private Comparable mayorEnSubArbol(NodoABB n){//retorna el elemento maximo de un subArbol
         Comparable maximo;
         if(n.getDerecho()==null){
             maximo=(Comparable)n.getElem();
         }else{
             maximo=mayorEnSubArbol(n.getDerecho());
         }
         return maximo;
    }
    public Boolean pertenece(Comparable elem){
        Boolean p=false;
        if(!this.esVacio()){
            if(elem.equals(this.raiz.getElem())){//si es la raiz 
                p=true;
            }else{//sino es la raiz busca por sus sub arboles
                p=perteneceRec(this.raiz,elem);
            }
        }
        return p;
    }
    private Boolean perteneceRec(NodoABB n,Comparable elem){
        Boolean existe=false;
        if(n!=null){
              if(elem.equals(n.getElem())){//caso base
                   existe=true;
              }else{//casos recursivos
                  if(elem.compareTo(n.getElem())<0){
                        existe=perteneceRec(n.getIzquierdo(),elem);//recorre hijo izquierdo
                  }else{
                        if(existe==false){//recorre hijo derecho si no encontro por el hijo izquierdo
                           existe=perteneceRec(n.getDerecho(),elem);
                        }
                  }
              }
        }
        return existe;
    }
    public Boolean esVacio(){
        return (this.raiz==null);
    }
    public void vaciar(){
        this.raiz=null;
    }
    public Comparable minimoElem(){
        return minRec(this.raiz);//recorre el arbol entero en busca el menor elemento
    }
    private Comparable minRec(NodoABB n){
        Comparable minimo;
        if(n.getIzquierdo()==null){//caso base:ultimo nodo izquierdo del subArbol izquierdo
            minimo=(Comparable) n.getElem();
        }else{//caso recursivo
            minimo=minRec(n.getIzquierdo());
        }
        return minimo;
    }
    public Comparable maximoElem(){
        return maxRec(this.raiz);//recorre el arbol en busca de el maximo elemento
    }
    private Comparable maxRec(NodoABB n){
        Comparable maximo;
        if(n.getDerecho()==null){//caso base:ultimo nodo derecho del subArbol derecho
            maximo=(Comparable) n.getElem();
        }else{
            maximo=maxRec(n.getDerecho());
        }
        return maximo;
    }
    public Lista listar(){//retorna una lista con todo los elementos del arbol en orden 
        Lista list=new Lista();
        if(!this.esVacio()){
            listarRec(list,this.raiz);
        }
        return list;
    }
    private void listarRec(Lista list, NodoABB n){
           if(n!=null){
               list.insertar(n.getElem(), list.longitud()+1);
               listarRec(list,n.getIzquierdo());
               listarRec(list, n.getDerecho());
           }
    }
    public String toString(){
        String ret=" ";
        if(this.esVacio()){
            ret="Arbol Binario de Busqueda VACIO";
        }else{
            ret=toStringRec(this.raiz);
        }
        return ret;
    }
    private String toStringRec(NodoABB n){
        String ret=" ";
        if(n!=null){
            ret=ret+"("+n.getElem()+") ->";
            NodoABB izq,der;
            izq=n.getIzquierdo();
            der=n.getDerecho();
            if (izq != null){ //si no es nulo imprimo el elem izq
                ret = ret + "HI: " + izq.getElem() + "    ";
            } else {
                ret = ret + "HI: -    ";
            }
            if (der != null){ //si no es nulo imprimo el elem der
                ret = ret+ "HD: " + der.getElem() + "\n";
            } else {
                ret = ret + "HD: -\n";
            }
            ret = ret + toStringRec(izq); //voy al hijo izq para seguir imprimiendo
            ret = ret + toStringRec(der); //voy al hijo der para seguir imprimiendo
        }
        return ret;
    }
    public Boolean eliminarMinimo(){//elimina el elemento mas pequeño del arbol
        Boolean eliminado=false;
        /*if(this.raiz.getIzquierdo()==null){//la raiz es el elemento minimo no tiene subArbolIzquierdo
            this.raiz=this.raiz.getDerecho();
            eliminado=true;
        }else{
              eliminado=eliminarMinAux(this.raiz,this.raiz.getIzquierdo());
        }*/
        eliminado=eliminarMinAux(this.raiz,this.raiz.getIzquierdo());
        return eliminado;
    }
    private Boolean eliminarMinAux(NodoABB padre,NodoABB n){
        Boolean el=false;
        
        /*if(n!=null){
            if(n.getIzquierdo()==null){
               padre.setIzquierdo(n.getDerecho());
               el=true;
            }else{
               el=eliminarMinAux(padre.getIzquierdo(),n.getIzquierdo());
            }
        }*/
        if(padre.getIzquierdo()==null){//si la raiz tiene hijo izquierdo es nulo
            this.raiz=this.raiz.getDerecho();//elimina y asigna como raiz al hijo derecho 
            el=true;

        }else{//
            if(n!=null){//se mueve por el hijo izquierdo hasta el minimo
                if(n.getIzquierdo()==null){//llego al minimo
                  //al padre le enlaza el nuevo hijo izquierdo que corresponde al antiguo hijo derecho
                  //funciona si no tiene hijo derecho
                  padre.setIzquierdo(n.getDerecho());
                  el=true;
                }else{
                   el=eliminarMinAux(padre.getIzquierdo(),n.getIzquierdo());
                }
            }
        }
        return el;
    }
    public ArbolBB clonarParteInvertida(Comparable elem){
          ArbolBB arbol=new ArbolBB();
          if(!this.esVacio()){
            clonarInvertidoAux(this.raiz);
          }
          return arbol;
    }
    private void clonarInvertidoAux(NodoABB n){
          
    }
}