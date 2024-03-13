package lineales.estaticas;
public class Pila{
   
   private int TAMANIO=20;
   private Object [] arreglo;
   private int tope;
   public Pila(){//constructor
        this.arreglo=new Object[TAMANIO-1];
        this.tope=-1;
   }
   public boolean apilar(Object unObjeto){//apila el nuevo elemento en el tope
      boolean valor=false;
      if(this.tope<TAMANIO){//si la pila tiene espacio apila
         if(tope==-1){//la pila esta vacia
            this.tope=0;
            arreglo[0]=unObjeto;
         }else{//la pila tiene objetos
            this.tope=this.tope+1;
            arreglo[tope]=unObjeto;
         }
         valor=true;
      }
      return valor;
    }
    public boolean desapilar(){//saca tope
      boolean x=false;
        if(this.tope!=-1){
           if(this.tope==0){
              arreglo[this.tope]=null;
              this.tope=-1;
              x=true;
           }else{
              arreglo[this.tope]=null;
              this.tope=this.tope-1;
              x=true;
           }
        }
        return x;
   }
   public Object obtenerTope(){//retorna el elemento del Tope
       Object elem;
       if(this.tope!=-1){
         elem=arreglo[tope];
       }else{
         elem=null;
       }
       return elem;
   }
   public boolean esVacia(){//retorna verdadero si la pila es vacia
       return (this.tope==-1);
   }
   //vacia la pila
   public void vaciar(){
      this.arreglo=new Object[TAMANIO-1];
      this.tope=-1;
   }
   //retorna la pila en string
   public String toString(){
        return toStringRec(0);
   }
   public String toStringRec(int i){//crea el txt de la pila
       String txt="";
       if(this.esVacia()){
         return "la pila esta vacia";
       }else{
          if(i<this.tope){
             txt=arreglo[i]+","+toStringRec(i+1);
          }else if(i==this.tope){
             txt=""+arreglo[tope];
          }
       }
       return txt;
   }
   public Pila clone(){//clona la pila
        Pila clon=new Pila();
        int i=0;
        while(i<=this.tope){
                  clon.arreglo[i]=this.arreglo[i];
         }
        clon.tope=this.tope;
        return clon;
   }
 
}