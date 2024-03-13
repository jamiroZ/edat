package lineales.dinamicas;

public class Pila{
    

    private Nodo tope;
    //constructor
    public Pila(){
        this.tope=null;
    }
    //Apilar
    public boolean apilar(Object elemento){
       //crea un nuevo nodo adelante de la pila
       Nodo nuevo=new Nodo(elemento, this.tope);
       //actualiza el tope para que apunte al nuevo nodo
       this.tope=nuevo;
       return true;
    }
    //Desapilar
    public boolean desapilar(){
        boolean logico=false;
        if(!this.esVacia()){
            this.tope=this.tope.getEnlace();
            logico=true;
        }
        return logico;
    }
    //retorna true si la pila esta vacia
    public boolean esVacia(){
        return this.tope==null;
        
    }
}