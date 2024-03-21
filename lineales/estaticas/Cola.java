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
    //vacia la cola
    public void vaciar(){
        cola=new Object[TAMANIO-1];
        this.fin=0;
        this.frente=0;
    }
    public boolean poner(Object elem){
         boolean puso=false;
         if(this.fin+1!=this.frente){//si la cola no esta llena
              while()
         }
         return puso;
    }
    //retorna el objeto del frente
    public Object obtenerFrente(){
        Object elem=null;
        if(!this.esVacia()){//la cola no esta vacia "condicion"
           elem=cola[this.frente];
        }
        return elem;
    }
}