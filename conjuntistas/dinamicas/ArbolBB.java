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
                      Comparable comp=  menorEnSubArbol(n.getDerecho());//candidato a reemplazar a su padre
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
        if(this.raiz.getIzquierdo()==null){//la raiz es el elemento minimo no tiene subArbolIzquierdo
            this.raiz=this.raiz.getDerecho();
            eliminado=true;
        }else{
              eliminado=eliminarMinAux(this.raiz,this.raiz.getIzquierdo());
        }
        return eliminado;
    }
    public Boolean eliminarMinAux(NodoABB aux,NodoABB n){
        Boolean el=false;
        if(n!=null){
            if(aux.getIzquierdo()==null){//si la raiz tiene hijo izquierdo es nulo
               this.raiz=this.raiz.getDerecho();//elimina y asigna como raiz al hijo derecho 
               el=true;
            }else{//
                if(n!=null){//se mueve por el hijo izquierdo hasta el minimo
                     if(n.getIzquierdo()==null){//llego al minimo
                     //al padre le enlaza el nuevo hijo izquierdo que corresponde al antiguo hijo derecho
                     //funciona si no tiene hijo derecho
                     aux.setIzquierdo(n.getDerecho());
                     el=true;
                     }else{
                        el=eliminarMinAux(aux.getIzquierdo(),n.getIzquierdo());
                     }
                }
            }
        }
        return el;
    }
    public ArbolBB clonarParteInvertida(Comparable elem){
          ArbolBB arbol=new ArbolBB();
          if(!this.esVacio()){
            clonarInvertidoAux(this.raiz,elem,arbol);
          }
          return arbol;
    }
    //SI EL ELEMENTO NO EXISTE EN EL ARBOL RETORNA UN ARBOL VACIO
    //BUSCA  EL ELEMENTO SI EXISTE Y CREA UN SUBARBOL  CON EL ELEMENTO DE RAIZ Y SUS HIJOS INVERTIDOS
    private void clonarInvertidoAux(NodoABB n,Comparable elem, ArbolBB arbol){
        //busca hasta encontrar el elemento o hasta ya haber recorrido el arbol en el peor caso
        if(n!=null && elem.compareTo((Comparable) n.getElem())!=0){//si no es el elemento sigue buscando
                System.out.println(n.getElem());
                if( elem.compareTo((Comparable) n.getElem())<0){//el objeto es menor que el objeto del nodo
                    clonarInvertidoAux(n.getIzquierdo(),elem, arbol);
                }else{
                    clonarInvertidoAux(n.getDerecho(),elem, arbol);
                }
         }else if(n!=null){//encontro el elemento(por lo tanto no es n nulo)
                arbol.raiz=new NodoABB(elem);
                clonarSubArbolInvertido(n,arbol.raiz);//invierte el arbol cambiando sus hijos de lugar
        }
    }
    private void clonarSubArbolInvertido(NodoABB n,NodoABB clon){
        if(n!=null && clon!=null){
            
            if(n.getIzquierdo()!=null){
                //enlaza el hijo derecho con un nuevo nodo con el objeto del hijo izquierdo de n
                clon.setDerecho(new NodoABB(n.getIzquierdo().getElem()));
            }
            if(n.getDerecho()!=null){
                  //enlaza el hijo izquierdo con un nuevo nodo con el objeto del hijo derecho de n
                  clon.setIzquierdo(new NodoABB(n.getDerecho().getElem()));
            }
            clonarSubArbolInvertido(n.getIzquierdo(),clon.getDerecho());
            clonarSubArbolInvertido(n.getDerecho(), clon.getIzquierdo());
        }
    }
    public Lista listarMayorIgual(Comparable elem){//metodo publico que implementa metodos privados de busqueda y insercion
        Lista mayorIgual=new Lista();
        if(!this.esVacio()){
             listarMayorIgualRec(this.raiz,elem,mayorIgual);
        }
        return mayorIgual;
    }
    private void listarMayorIgualRec(NodoABB n,Comparable elem,Lista list){//metodo privado que busca el elemento que quiero como base para la lista
        if(n!=null && elem.compareTo((Comparable) n.getElem())!=0){//repite hasta encontrar el objeto o en el peor caso el arbol este vacio 
                if(elem.compareTo((Comparable) n.getElem())<0){//el EL OBJETO ES MENOR QUE EL DEL NODO 
                    
                     listarMayorIgualRec(n.getIzquierdo(),elem,list);
                 
                     list.insertar(n.getElem(), 1);//INSERTA EL OBJETO DEL NODO

                     if(n.getDerecho()!=null){//si tiene hijo derecho
                         listarSubArbolMayor(n.getDerecho(),list);
                     }
                }else if(elem.compareTo((Comparable) n.getElem())>0){
                     listarMayorIgualRec(n.getDerecho(),elem,list);
                }
               
        }else if( n!=null){
            list.insertar(elem, 1);//inserta el objeto en la lista
            
            listarSubArbolMayor(n.getDerecho(), list);//lista subArbol derecho(objetos mayores)
        }
    }
    private void listarSubArbolMayor(NodoABB n,Lista list){
        if(n!=null){

             //lista de menor a mayor, osea de izquierda a derecha
             //con respecto a la lista en posicion(1),la cabecera
             listarSubArbolMayor(n.getIzquierdo(),list);//nodos del subArbol izquierdo (menores que el padre) 
             list.insertar(n.getElem(),1);
             listarSubArbolMayor(n.getDerecho(),list); //nodos del subArbol derecho(mayores que el padre)
        }
    }

    public Lista listarMenorIgual(Comparable elem){
        Lista list=new Lista();
        listarMenorRec(this.raiz,elem,list);
        return list;
    }
    private void listarMenorRec(NodoABB n,Comparable elem,Lista list){
        if(n!=null && elem.compareTo((Comparable) n.getElem())!=0){
               if(elem.compareTo((Comparable) n.getElem())>0){//si son menores al objeto
                  listarMenorRec(n.getDerecho(),elem,list);//llama recursivamente al subArbol derecho para encontrar el objeto
                  list.insertar(n.getElem(),1);//inserta el objeto de ese nodo del arbol que es menor
                  listarSubArbolMenor(n.getIzquierdo(),list);//lista el subArbol izquierdo con los nodos con objetos mas pequeños
               }else{//si son mayores
                  
                  listarMenorRec(n.getDerecho(),elem,list);
               }
        }else if(n!=null){//si es el objeto NO es nulo
               list.insertar(elem,1);
               listarSubArbolMenor(n.getIzquierdo(),list);
        }
    }
    private void listarSubArbolMenor(NodoABB n,Lista list){
        if(n!=null){
            listarSubArbolMenor(n.getDerecho(),list);
            list.insertar(n.getElem(),1);
            listarSubArbolMenor(n.getIzquierdo(),list);
        }
    }
    public void eliminarHojasEnRango(Comparable min,Comparable max){

        moverseEnRango(this.raiz,min,max);
    }
    private void moverseEnRango(NodoABB n,Comparable min,Comparable max){
        if( n!= null &&  min.compareTo((Comparable) n.getElem()) <= 0 && max.compareTo((Comparable) n.getElem()) >= 0 ){
              
              //si el objeto es mas grande que el min se mueve a la izquierda
              if(min.compareTo((Comparable) n.getElem())<=0 ){
                
                  moverseEnRango(n.getIzquierdo(),min,max);
                  //si estoy en el subArbol izquierdo me muevo al sub arbol derecho del nodo 
                  eliminarHoja(n,n.getDerecho(),min,max);
                
              }else{ //si estoy en el subArbol derecho me muevo al subArbol izquierdo del nodo
                  moverseEnRango(n.getDerecho(),min,max);
                  System.out.println(n.getElem());

                  eliminarHoja(n, n.getIzquierdo(),min ,max);
              }
    
        }
    }
    //recorre el subArbol para eliminar las hojas

    private void eliminarHoja(NodoABB padre,NodoABB n,Comparable min,Comparable max){
        if(n!=null && min.compareTo((Comparable) n.getElem()) <= 0 && max.compareTo((Comparable) n.getElem()) >= 0){
                System.out.println(n.getElem());
                if(n.getIzquierdo()==null && n.getDerecho()==null){//caso base (hoja)
                    if(padre.getDerecho()==n){//si es el hijo derecho
                       padre.setDerecho(null);
                    }else{//si es hijo izquierdo
                       padre.setIzquierdo(null);
                    }
                }
                eliminarHoja(n, n.getDerecho(),min,max);
                eliminarHoja(n,n.getIzquierdo(),min,max);
        }
    }
     /*public Boolean eliminarElemAnterior(Comparable elem){
        Boolean exito=false;
        exito=eliminarAnteriorRec(this.raiz,elem);
        return exito;
    }
    private Boolean eliminarAnteriorRec(NodoABB n,Comparable elem){
        Boolean hecho=false;
        if(n!=null && !hecho){
            System.out.println(n.getElem());
             if(elem.equals(n.getElem())){
                  if(n.getIzquierdo()!=null){
                      hecho=anteriorSubArbol(n,n.getIzquierdo());
                  }else{
                      hecho=false;
                  }
             }else{
                if(elem.compareTo((Comparable) n.getElem())<0){
                   hecho= eliminarAnteriorRec(n.getIzquierdo(),elem);
                }else{
                   hecho= eliminarAnteriorRec(n.getDerecho(),elem);
                }
                
             }
        }
        return hecho;
    }
    private Boolean anteriorSubArbol(NodoABB padre,NodoABB n ){
        Boolean exito=false;
        if(n!=null && !exito){
             if(n.getDerecho()!=null){//SI TIENE HIJO DERECHO ME MUEVO HASTA EL (PUES ES EL MENOR ANTERIOR)
                 exito=anteriorSubArbol(n, n.getDerecho());
             }else {//casos base
                 if(n.getIzquierdo()!=null){//el nodo a eliminar tiene hijo izquierdo remplazo
                     padre.setDerecho(n.getIzquierdo());
                 }else{//no tiene hijo izquierdo inserto null
                     padre.setDerecho(null); 
                 }
                 exito=true;
             }
        }
        return exito;
    }*/
    public Object mejorCandidato(Comparable elem ){//retorna el mejor candidato para el objeto
        Comparable n=-1;
        n=mejorCanRec(this.raiz,elem);
        return (Object) n;
    }
    private Comparable mejorCanRec(NodoABB n,Comparable elem){//busco el objeto
        Comparable ret=-1;
        if(n!=null){
              if(elem.equals(n.getElem())){//encontro el objeto
                  //n es la raiz del subArbol
                  Integer i,j;
                  if(n.getDerecho()!=null || n.getIzquierdo()!=null){
                     i= (Integer) buscarIzq(n.getIzquierdo());
                     j= (Integer) buscarDer(n.getDerecho());
                     if( i -  (Integer) elem < j - (Integer) elem){
                            ret=i;
                     }else{
                            ret=j;
                     }
                  }
                
              }else{//busca
                   if(elem.compareTo(n.getElem())<=0){//el objeto es menor al del nodo
                        ret=mejorCanRec(n.getIzquierdo(),elem);//recorre subArbol Izquierdo
                   }else{//si es mayor al del nodo
                    ret=mejorCanRec(n.getDerecho(),elem);//recorre subArbol Derecho
                   }
              }
        }
        return ret;
    }
    private Comparable buscarIzq(NodoABB n){
        Comparable max;
        if(n.getDerecho()!=null){
            max=buscarIzq(n.getDerecho());
        }else{
            max=n.getElem();
        }
        return max;
    }
    private Comparable buscarDer(NodoABB n){
        Comparable max;
        if(n.getIzquierdo()!=null){
            max=buscarIzq(n.getIzquierdo());
        }else{
            max=n.getElem();
        }
        return max;
    }
    public void eliminarHojaSubArbol(Comparable objeto){//elimina hojas de un nodo
        eliminarHojaRec(this.raiz,objeto);
    } 
    private void eliminarHojaRec(NodoABB n,Comparable objeto){
        if(n!=null){
            System.out.println(n.getElem());
            if(objeto.equals(n.getElem())){//encotro el objeto
                eliminarHojas(n,n);//elimina sus hojas
            }else{
                if(objeto.compareTo(n.getElem())<=0){//el objeto es menor al del nodo
                    eliminarHojaRec(n.getIzquierdo(),objeto);
                }else{//es mayor
                    eliminarHojaRec(n.getDerecho(),objeto);
                }
            } 
        }
    }
    private void eliminarHojas(NodoABB n,NodoABB padre){//ELIMINA HOJAS DE UN NODO
        if(n!=null){
             if(n.getIzquierdo()==null && n.getDerecho()==null){//es hoja
                  if(padre.getIzquierdo()==n){//es hijo izquierdo
                       padre.setIzquierdo(null);
                  }else{ //es hijo derecho
                       padre.setDerecho(null);
                  }
             }else{//casos recursivos 
                eliminarHojas(n.getIzquierdo(),n);
                eliminarHojas(n.getDerecho(),n);
             }
             
        }
    }
    public Boolean eliminarElemAnterior(Comparable elem ){
        Boolean exito=false;
        if(!this.esVacio()){
            exito=eliminarAnterior(this.raiz,elem);//busca el elemento
        }
        return exito;
    }
    private Boolean eliminarAnterior(NodoABB n,Comparable elem){
        Boolean exito=false;
        if(n!=null){
              System.out.println(n.getElem());
              if(elem.equals(n.getElem())){//lo encontro deja de buscar
                     exito=eliminarSubArbol(n.getIzquierdo(),n);
              }else{
                  if(elem.compareTo(n.getElem())<0){//el elemento es menor que el del nodo
                      exito=eliminarAnterior(n.getIzquierdo(), elem);//me muevo al subArbol izquierdo
                  }else{//es mayor que el del nodo
                      exito=eliminarAnterior(n.getDerecho(), elem);//me muevo al subArbol derecho
                  }
              }
        }
        return exito;
    }
    private Boolean eliminarSubArbol(NodoABB n,NodoABB padre){
          Boolean v=false;
          if(n!=null){
                  if(n.getIzquierdo()==null ){//es el menor del subArbol
                      if(n.getDerecho()!=null){//tiene hijo derecho
                            padre.setIzquierdo(n.getDerecho());
                      }else{
                            padre.setIzquierdo(null);//no tiene hijo derecho
                      }
                      v=true;
                  }else{
                     v=eliminarSubArbol(n.getIzquierdo(), n);
                  }
          }
          return v;
    }
}