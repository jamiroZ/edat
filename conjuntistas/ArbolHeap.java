package conjuntistas;
//HEAP MAXIMO
//mueve el elemento mayor para arriba
public class ArbolHeap {
    private int TAMANIO=20;
    private Object []heap;
    private int ultimo;
    public ArbolHeap(){//constructor vacio
        this.heap=new Object[TAMANIO-1];
        this.ultimo=0;//la posicion 0 nunca se usa
    }
    public Boolean insertar(Object elem){
        Boolean ins=false;
        if(this.ultimo < heap.length){
            if(this.esVacio()){//si el arbol esta vacio lo inserta como laraiz del arbol
                  heap[1]=elem;
                  this.ultimo=1;
            }else{//nivel 1 en adelante
                  if(this.ultimo+1 < TAMANIO){
                       this.ultimo++;
                       heap[this.ultimo]=elem;
                       ins=true;
                  }
                  if(ins){
                     hacerSubir(this.ultimo);
                  }
            }
        }
        
        return ins;
    } 
    public void hacerSubir(int j){
        Object aux;//auxiliar para el intercambio
        Boolean intercambiado=true;//si no se realiza un intercambio el arbol no se ordena
        while((j/2) >0 && intercambiado){
                if((int) heap[j/2]< (int) heap[j]){//si es menor el padre que el hijo
                        //intercambio
                        aux=heap[j/2];
                        heap[j/2]=heap[j];
                        heap[j]=aux;
                        //actualizo para la siguiente comparacion
                        j=(j/2);//me muevo al padre
                }else{
                        intercambiado=false;
                } 
        }
    }
    public Boolean eliminarCima(){
         Boolean exito;
         if(this.ultimo==0){
             exito=false;//estructura vacia
         }else{
             //cambia el valor de la raiz por el ultimo y decrementa el ultimo
             this.heap[1]=this.heap[this.ultimo];
             this.ultimo--;
             //restablece el heap maximo
             hacerSubir(this.ultimo);
             exito=true;

         }
         return exito;
    }
    public Object recuperarCima(){//recupera la raiz del arbol
        Object ret=null;
        if(!this.esVacio()){
            ret=heap[1];
        }
        return ret;
    }
    public Boolean esVacio(){//revisa si esta vacio o no el arbol retorna true si esta vacio
        return (this.ultimo==0);
    }
    public void vaciar(){//vacia arbol
        this.heap=new Object[TAMANIO-1];
        this.ultimo=0;
    }
    public String mostrarArray(){
        String txt=" ";
        for(int i=1 ;i<this.TAMANIO-1 ;i++){
            if(heap[i]!=null){
                txt=  txt+" "+heap[i];
            }
        }
        return txt;
    }
    public String toString(){//retorna el arbol por pantalla
         String ret="";
         if(this.esVacio()){
            int i=1;
            while(i<=this.ultimo){
                 if(i==1){
                    
                 }
            }
         }
         return ret;
    }
}