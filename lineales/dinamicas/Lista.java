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
        boolean insertado=false;
        if( pos < 1 || pos > this.longitud+1){
            
        }else{
            if(pos==1){//la lista esta vacia
                cabecera=new Nodo(elem,null);
            }else{
                while(this.longitud >= pos){
                    if(this.longitud==pos){

                    }
                    this.longitud++;
                }
            }
            
        
        }
        return insertado;
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
}
