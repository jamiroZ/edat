package tpFinal.estructuras.lista;
//el primer nodo de la lista tiene enlase a null
//el ultimo insertado es la cabecera
public class Lista {
      private Nodo cabecera;
      private int longitud;
      public Lista(){
          this.cabecera=null;
          this.longitud=0;
      } 
      public int longitud(){//devuelve 0 (lista vacia)o n numero si NO esta vacia
        return this.longitud;
      }
      public Boolean estaVacia(){//si la lista esta vacia retorna true
        return this.cabecera==null;//la lista esta vacia si la cabecera esta vacia
      }
      public void vaciar(){
        this.cabecera=null;
        this.longitud=0;
      }
      public boolean insertar(Object elem,int pos){
        //inserta un elemento en la posicion pos
        //detecta y reporta error posicion invalida
        boolean exito;
        if( pos < 1 || pos > this.longitud()+1){
            exito=false;
        }else{
            if(pos==1){//crea un nuevo nodo y se enlaza en la cabecera
                cabecera=new Nodo(elem,this.cabecera);
            }else{//avanza hasta el elementoen posicion pos-1
                Nodo aux=this.cabecera;
                int i=1;
                while(i < pos-1){
                    aux=aux.getEnlace();
                    i++;
                }
                //crea el nodo y lo enlaza
                Nodo nuevo=new Nodo(elem,aux.getEnlace());
                aux.setEnlace(nuevo);
            }
            this.longitud++;
            exito=true;
        }
        //nunca hay error de lista llena,entonces devuelve true
        return exito;
      }
      public boolean eliminar(int pos){//borra el elemento de la posicion pos
            boolean exito=true;
            if((pos>0 && pos <= this.longitud())){
               Nodo aux=this.cabecera;//puntero
               if(pos==1){//elimina primera posicion
                  this.cabecera=aux.getEnlace();
               }else{//avanzo hasta el elem de la pos-1
                  int i=2;//tiene al menos 2 elementos
                  while(i <= pos-1){
                    aux=aux.getEnlace();
                    i++;
                  }
                  if(i+1==pos){//caso especial eliminar ultima posicion
                     aux.setEnlace(null);
                  }else{//posicion intermedia
                    //enlaca el nodo pos-1 al nodo pos+1 ,eliminando el nodo pos
                    aux.setEnlace((aux.getEnlace()).getEnlace());
                  }
                 
               }
               this.longitud--;//disminuye el tamaño de la lista
            }else{
                exito=false;
            }
            return exito;
      }
     
      //recupera el elemento en la posicion que se introducio
      public Object recuperar(int pos){
        Object elem=null;
        if(pos>0 &&  pos <= this.longitud() ){
            //si la lista no esta vacia y la pos esta en el rango entonces busca
            Nodo aux=this.cabecera;//nodo para moverse
            int i=1;//existe al menos un objeto en la lista
            while(i < pos){
                aux=aux.getEnlace();
                i++;
            }
            elem=aux.getElem();//asigna el elemento
        }else{
          elem="la lista esta vacia o la posicion esta fuera de rango";
        }
        return elem;
      }
      //recupera la posicion si existe segun el elemento que se ingreso
      public int localizar(Object elem){  
         int pos=localizarRec(elem,this.cabecera,1);
         return pos;
      }
      public int localizarRec(Object elem, Nodo aux,int i){
          int ret;
          if(aux==null){
              ret=-1;
          }else{
            if(aux.getElem()== elem){//caso base
               ret=i;
            }else{//caso recursivo
               ret=localizarRec(elem,aux.getEnlace(),i+1);
            } 
          }
          
          return ret;
      }
      public Lista clone(){
        //crea un clon de la pila actual
        Lista clon = new Lista();
        
        if (!this.estaVacia()){ //tiene al menos 1 elemento
           Nodo aux = this.cabecera;
           clon.cabecera = new Nodo(aux.getElem(),null); //actualizo la cabecera
           clon.longitud++;
           cloneRecursivo(clon, clon.cabecera, aux.getEnlace());
        }
        return clon;
    }

    private void cloneRecursivo(Lista clon, Nodo puntero, Nodo n1){
        if (n1 != null) {
            Nodo nuevo = new Nodo(n1.getElem(), null);
            clon.longitud++;
            puntero.setEnlace(nuevo);
            cloneRecursivo(clon, puntero.getEnlace(), n1.getEnlace());
        }
    }

    
      public String toString(){
        //Crea y devuelve un string con todos los elementos de la lista
        String cad;
        
        if (this.estaVacia()){ //error pila vacia
            cad = "Lista Vacia";
        } else {
            cad = "| ";
            Nodo aux = this.cabecera;
            while (aux != null){
                cad = cad + aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null){
                    cad = cad + ", ";
                }
            }
            cad = cad + " |";
        }
        return cad;
    }
    //ejercicio de parcial
    /*retorna una lista con las pisiciones que son multiplos de num */
    public Lista obtenerMultiplos(int  num){
           Lista list=new Lista();
           if(!this.estaVacia()){
              obtenerMultiplosRec(list,this.cabecera,num,1);
           }
           return list;
    }
    public void obtenerMultiplosRec(Lista list,Nodo aux,int mult,int i){
           if(aux!=null){
                if(i % mult ==0){//si la posicion es multiplo 
                    if(list.longitud+1 == 1){//caso especial de insertar en pos 1(long es 0 le sumo 1 )
                        list.cabecera=new Nodo(aux.getElem(), null);
                    }else{//inserta en la ultima pos
                        Nodo pos=list.cabecera;
                        int j=1;
                        while(j < list.longitud){// avanza hasta el ultimo nodo
                             pos=pos.getEnlace();
                             j++;
                        }
                        //crea 
                        Nodo nuevo=new Nodo(aux.getElem(), null);
                        pos.setEnlace(nuevo);//enlaza el ultimo nodo de la lista al nuevo nodo
                    }
                    list.longitud++;
                }
                obtenerMultiplosRec(list, aux.getEnlace(), mult, i+1);//si busca el multiplo busca en la siguiente posicion y siguiente nodo
           }
    }
    //elimina un objeto de la Lista si aparece 1 o masveces (retorna true si se encontro el objeto o false si no existe)
    public void eliminarApariciones(Object x){
        if(!this.estaVacia()){
           eliminarAparicionRec(x,this.cabecera);
        }
    }
    public void eliminarAparicionRec(Object x, Nodo aux){
        if(aux!=null){
            if( aux.getElem() == x){//si la cabecera tiene el elemento 
                this.cabecera=this.cabecera.getEnlace();//cambia la cabecera al nodo enlazado por esta
                this.longitud--;
            }else{
                if(aux.getEnlace()!=null) {

                    if(aux.getEnlace().getElem()==x){
                       aux.setEnlace(aux.getEnlace().getEnlace());
                       this.longitud--;
                    }
                }
            }
            eliminarAparicionRec(x, aux.getEnlace());
        }
    }
}

