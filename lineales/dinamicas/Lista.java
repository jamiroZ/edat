package lineales.dinamicas;
//el primer nodo de la lista tiene enlase a null
//el ultimo insertado es la cabecera
public class Lista {
      private Nodo cabecera;
      private int longitud;
      public Lista(){
          this.cabecera=null;
          this.longitud=0;
      }
      public boolean insertar(Object elem,int pos){
        //inserta un elemento en la posicion pos
        //detecta y reporta error posicion invalida
        boolean exito=true;
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
        
        }
        //nunca hay error de lista llena,entonces devuelve true
        return exito;
      }
      public boolean eliminar(int pos){//borra el elemento de la posicion pos
            boolean exito=false;
            if(!this.estaVacia() && (pos>1 || this.longitud()+1>pos)){
               if(pos==1){
                  cabecera=null;
               }else{
                  Nodo aux=this.cabecera;
                  int i=1;
                  while(i < pos-1){
                    aux=aux.getEnlace();
                    i++;
                  }
                  aux.setEnlace((aux.getEnlace()).getEnlace());
                  this.longitud--;
               }
              exito=true;
            }
            return exito;
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
      //recupera el elemento en la posicion que se introducio
      public Object recuperar(int pos){
        Object elem=null;
        if(!this.estaVacia() && (pos>=1 || this.longitud()+1>pos)){
            //si la lista no esta vacia y la pos esta en el rango entonces busca
            Nodo aux=this.cabecera;//nodo para moverse
            int i=1;//existe al menos un objeto en la lista
            while(i<pos-1){
                aux=aux.getEnlace();
                i++;
            }
            elem=(aux.getEnlace()).getElem();//asigna el elemento
        }else{
          elem="la lista esta vacia o la posicion esta fuera de rango";
        }
        return elem;
      }
      //recupera la posicion si existe segun el elemento que se ingreso
      public int localizar(Object elem){
         int pos=-1;
         if(!this.estaVacia()){
             pos=1;
             Nodo aux=this.cabecera;
             while(aux.getElem()!=elem){
                aux=aux.getEnlace();
                pos++;
             }
             pos++;
         }
         return pos;
      }
      public Lista clone(){
          Lista listaClon=new Lista();
          if(!this.estaVacia()){
             listaClon.cabecera=this.cabecera;
             listaClon.longitud=this.longitud;
             cloneRec(listaClon.cabecera);
          }
          return listaClon;
      }
      public Nodo cloneRec(Nodo cursor){
          Nodo ret;
          if(cursor!=null){
              ret=new Nodo(cursor.getElem(),cursor.getEnlace());
          }else{
              ret=null;
          }
          return ret;
      }
      public String toString(){
         return toStringRec(this.cabecera);
      }
      public String toStringRec(Nodo cursor){
         String txt="";
         if(this.estaVacia()){
             txt="la lista esta vacia";
         }else{
             if(cursor!=null){
                 txt= cursor.getElem()+" ; "+toStringRec(cursor.getEnlace());
             }else{
                 txt="\t longitud "+this.longitud();
             }
         } 
         return txt;
      }
}
