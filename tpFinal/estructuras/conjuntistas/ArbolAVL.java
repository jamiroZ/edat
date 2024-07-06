package tpFinal.estructuras.conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolAVL{
    
    private NodoAVL raiz;
    public ArbolAVL(){//CONSTRUCTOR VACIO
        this.raiz=null;
    }
    public Boolean insertar(Comparable elem){
        Boolean exito=false;
        if(this.esVacio()){//CASO ARBOL VACIO
            this.raiz=new NodoAVL(elem, null,null);
            exito=true;
        }else{//ARBOL NO VACIO
            exito=insertarRec(this.raiz,elem,null);
        }
        return exito;
    }
    public Boolean insertarRec(NodoAVL n, Comparable elem,NodoAVL padre){
        Boolean exito=true;
        //precondicion n no es nulo
        if (n != null){
            NodoAVL izq = n.getIzquierdo(), der = n.getDerecho(); 

            if (elem.compareTo(n.getElem()) == 0){ 
                exito = false; //error el elem ya existe en el arbol
                
            } else if (elem.compareTo(n.getElem()) > 0) { //si el elem es mayor que la raiz voy al HD
                if (der == null){ //si n no tiene HD inserto
                    n.setDerecho(new NodoAVL(elem, null, null));
                } else { //paso recursivo con subarbol der
                    exito = insertarRec(der, elem, n);
                }
            } else { //si el elem es menor que la raiz voy al HI
                if (izq == null){ //si n no tiene HI inserto
                    n.setIzquierdo(new NodoAVL(elem, null, null));
                } else { //Paso recursivo con subarbol izq
                    exito = insertarRec(izq, elem, n);
                }
            }

            if (exito){ //si fue insertado verifico si hay desbalance
                n.recalcularAltura();
                int balance = balance(n); //veo el balance de n

                if (balance < -1 || balance > 1){ //si esta desbalanceado
                    balancear(balance, n, padre);
                    n.recalcularAltura();
                } 
            }                     
        }   
        
        return exito;
    }
    private void casoHoja(NodoAVL n,NodoAVL padre){
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
    private void caso1Hijo(NodoAVL n , NodoAVL padre){
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
    private Comparable menorEnSubArbol(NodoAVL n){//retorna el elemento minimo de un subArbol 
        Comparable minimo;
        if(n.getIzquierdo()==null){
            minimo=(Comparable) n.getElem();//caos base
        }else{
            minimo=menorEnSubArbol(n.getIzquierdo());//caso recursivo
        }
        return minimo;
    }
    private Comparable mayorEnSubArbol(NodoAVL n){//retorna el elemento maximo de un subArbol
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
    private Boolean perteneceRec(NodoAVL n, Comparable elem){
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
    private int balance(NodoAVL n){
        //modulo que calcula el balance de un nodoAVL
        int izq = -1, der = -1; //altura de null es -1
    
        if (n.getIzquierdo() != null){
            izq = n.getIzquierdo().getAltura();
        } 
        if (n.getDerecho() != null){
            der = n.getDerecho().getAltura();
        }

        return izq - der; 
    }
    private void balancear(int balance, NodoAVL n, NodoAVL padreAux){
        /*Metodo aux que balancea el nodo (n) con estos 4 casos
        balance: variable con el balance de n
        padreAux: es el padre de n, usaado para setear a su hijo desbalanceado una vez termine el proceso
        precondicion: n no es vacio y balance es 2 o -2
        */
        NodoAVL aux;

        if (balance < -1){ //si esta torcido a der
            int balanceHDer = balance(n.getDerecho());

            if (balanceHDer <= 0){ //si el HD esta torcido a la der
                n = rotarIzq(n); //lo tuerzo a la izq
                if (padreAux == null){ //caso especial el nodo a balancear es raiz
                    this.raiz = n;
                } else {
                    //seteo uno de los hijos de PadreAux
                    if (n.getElem().compareTo(padreAux.getElem()) > 0){
                        padreAux.setDerecho(n);
                    } else {
                        padreAux.setIzquierdo(n);
                    }
                    padreAux.recalcularAltura();
                }
                
            } else { //el HD esta torcido a la izq
                aux = rotarDer(n.getDerecho()); //lo tuerzo al mismo lado q el padre
                n.setDerecho(aux);

                balancear(balance, n, padreAux); //reutilizo el metodo para balancear al padre(n)
            }
        } else { //si esta torcido a izq
            int balanceHIzq = balance(n.getIzquierdo());
            if (balanceHIzq >= 0){ //si HI esta torcido a la izq
                n = rotarDer(n);
 
                if (padreAux == null){
                    this.raiz = n;
                } else {
                    //seteo uno de los hijos de PadreAux
                    if (n.getElem().compareTo(padreAux.getElem()) > 0){
                        padreAux.setDerecho(n);
                    } else {
                        padreAux.setIzquierdo(n);
                    }
                    padreAux.recalcularAltura();
                }
                
            } else { //Si HI esta torcido a la der
                aux = rotarIzq(n.getIzquierdo()); //lo tuerzo al mismo lado q el padre
                n.setIzquierdo(aux); 

                balancear(balance, n, padreAux); //reutilizo el metodo para balancear al padre(n)
            }
            
        }
    }
    private NodoAVL rotarIzq(NodoAVL n){
        NodoAVL h=n.getDerecho();
        NodoAVL temp=h.getIzquierdo();
        h.setIzquierdo(n);
        n.setDerecho(temp);
        
        n.recalcularAltura();
        h.recalcularAltura();
        return h;//retorna nueva raiz del Sub arbol
    }
    private NodoAVL rotarDer(NodoAVL n){
        NodoAVL h=n.getIzquierdo();
        NodoAVL temp=h.getDerecho();
        h.setDerecho(n);
        n.setIzquierdo(temp);
        
        n.recalcularAltura();
        h.recalcularAltura();
        return h;//reotrna nueva raiz del sub Arbol
    }














    public void vaciar(){
        this.raiz=null;
    }
    public Comparable minimoElem(){
        return minRec(this.raiz);//recorre el arbol entero en busca el menor elemento
    }
    private Comparable minRec(NodoAVL n){
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
    private Comparable maxRec(NodoAVL n){
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
    private void listarRec(Lista list, NodoAVL n){
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
    private String toStringRec(NodoAVL n){
        String ret=" ";
        if(n!=null){
            ret=ret+"("+n.getElem()+") ->";
            NodoAVL izq,der;
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










    public Boolean eliminar(Comparable elem){ 
        Boolean elimino=false;
        if(!this.esVacio() && pertenece(elem)) {
            elimino= eliminarRec(this.raiz,elem,null);
        } 
        return elimino;
    }
    private Boolean eliminarRec(NodoAVL n,Comparable elem,NodoAVL padre){
        Boolean elim=false;
        if(n!=null){
             if(elem.equals(n.getElem())){
                 if(n.getDerecho()==null && n.getIzquierdo()==null){//si no tiene hijos(caso hoja)
                      casoHoja(n,padre);
                 }else if((n.getIzquierdo()!=null && n.getDerecho()!=null) ){//si tiene los 2 hijos
                      Comparable comp = menorEnSubArbol(n.getDerecho());//candidato a reemplazar a su padre
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
             if (elim){ //si se logro eliminar verifico balance de nodo actual
                n.recalcularAltura();
                int balance = balance(n); //veo el balance de n
                if (balance < -1 || balance > 1){ //si esta desbalanceado
                    balancear(balance, n, padre);
                    n.recalcularAltura();
                } 
            } 
        }
        return elim;
    }
    public Boolean esVacio(){//RETORNA VERDADERO SI ESTA VACIO EL ARBOL
        return (this.raiz==null);
    }
    public ArbolAVL clone(){
        //crea un clon del arbol actual
        ArbolAVL clon = new ArbolAVL();
        clon.raiz = cloneRec(this.raiz);
        return clon;
    }

    private NodoAVL cloneRec(NodoAVL n){
        //metodo aux que copia los nodos y enlaces del arbol
        NodoAVL ret;

        if (n != null){
            ret = new NodoAVL(n.getElem(), cloneRec(n.getIzquierdo()), cloneRec(n.getDerecho()));
        } else {
            ret = null;
        }

        return ret;
    }
    public Comparable getElem(Comparable elem){
        Comparable elemEncontrado=null;
        if(!this.esVacio()){//si el arbol no esta vacio busca 
            elemEncontrado=getElemRecursivo(this.raiz,elem);
        }
        return elemEncontrado;
    }
    private Comparable getElemRecursivo(NodoAVL n, Comparable elem){
        Comparable elemBuscado=null;
        if(n!=null){
            if(elem.equals(n.getElem())){//caso base
                 elemBuscado=n.getElem();
            }else{//casos recursivos
                if(elem.compareTo(n.getElem())<0){
                      elemBuscado=getElemRecursivo(n.getIzquierdo(),elem);//recorre hijo izquierdo
                }else{//recorre hijo derecho si no encontro por el hijo izquierdo
                      elemBuscado=getElemRecursivo(n.getDerecho(),elem);
                      
                }
            }
        }
        return elemBuscado;
    }
    
}