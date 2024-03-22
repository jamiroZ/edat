package lineales.estaticas;
//COLA VACIA: fin=frente=0
//COLA LLENA: fin+1=frente
public class Cola{
    private static final int TAMANIO=20;
    private Object[]cola;
    private int frente;
    private int fin;
    //constructor:crea una cola vacia
    public Cola(){
        this.frente=0;
        this.fin=0;
        cola=new Object[TAMANIO-1];
    }
    //logico que devuelve verdad si esta vacia la cola
    public boolean esVacia(){
        return this.fin==this.frente;
    }
    //logico que devuelve verdad si esta llena la cola
    public boolean estaLlena(){
        return(this.fin+1)%(this.TAMANIO-1)==this.frente;
    }
    //vacia la cola
    public void vaciar(){
        cola=new Object[TAMANIO-1];
        this.fin=0;
        this.frente=0;
    }
    //coloca en la cola un elemento si no esta llena
    public boolean poner(Object elem){
         boolean puso=false;
         if(!this.estaLlena()){//si la cola no esta llena coloca el elemento
             this.cola[this.fin]=elem;//coloca el elemento en la posicion fin (esta en blanco)
             this.fin=(this.fin+1)%(this.TAMANIO-1);//el fin se mueve una posicion en el arreglo
             puso=true; 
         }
         return puso;
    }
    //saca elemento de la cola
    public boolean sacar(){
        boolean saco=false;
        if(!this.esVacia()){//si la cola no esta llena saca el elemento
            this.cola[this.frente]=null;//borra elemento
            this.frente=(this.frente+1)%(this.TAMANIO-1);//mueve el frente una posicion
            saco=true;
        }
        return saco;
    }
    //retorna el objeto del frente
    public Object obtenerFrente(){
        Object elem="la cola esta vacia";
        if(!this.esVacia()){//la cola no esta vacia "condicion"
           elem=cola[this.frente];
        }
        return elem;
    }
    //retorna un texto con los elementos de la cola
    public String toString(){
        return toStringRec(this.frente);
    }
    public String toStringRec(int aux){
        String txt="";
        if(this.esVacia()){
            txt="COLA VACIA NO TIENE ELEMENTOS";

        }else{
            if(aux!=this.fin){
               txt=cola[aux]+" ; "+toStringRec(((aux+1) % (this.TAMANIO-1)));
            }else{
                txt=".";
            }
        }
        return txt;
    }
    //clona la cola 
    public Cola clone(){
        Cola clon=new Cola();
        if(!this.esVacia()){
            int aux=this.frente;
            clon.frente=this.frente;
            clon.fin=this.fin;
            while(aux!=this.fin){
                clon.cola[aux]=this.cola[aux];
                aux=(aux+1)% (this.TAMANIO-1);
            }
        }
        return clon;
    }
    /*
    public Cola clone(){
        Cola colaClone = new Cola();

        if (!esVacia()) {
            int recorre = this.frente;
            while (recorre!= this.fin) {
                colaClone.arreglo[recorre]=this.arreglo[recorre];
                recorre = (recorre+1)%TAMANIO;
            }
        }

       for (int i = 0; i < TAMANIO; i++) {
            colaClone.arreglo[i]=this.arreglo[i];
       
       colaClone.frente = this.frente;
       colaClone.fin = this.fin;

       return colaClone;
     }
     */

}