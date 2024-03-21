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
    //obetien el tope
    public Object obtenerTope(){
        return this.tope.getElem();
    }
    //vacia la pila
    public void vaciar(){
        this.tope=null;
    }
    //retorna true si la pila esta vacia
    public boolean esVacia(){
        return this.tope==null;
        
    }
    //muestra la pila por pantalla
    public String toString(){
        Nodo aux=this.tope;//se va a mover atraves de la pila con este nodo auxiliar sin cambiar el tope original
        return toStringRec(aux);
    }
    public String toStringRec(Nodo aux){
        String txt="";
        if(this.esVacia()){
            txt="LA PILA ESTA VACIA";
        }else{
            if(aux.getEnlace()==null){
                txt=""+aux.getElem();
            }else{
               txt=aux.getElem()+" "+toStringRec(aux.getEnlace());
            }
            
        }
        return txt;
    }
    //clonar pila
    public Pila clone(){
        Pila clon=new Pila();
        clonRec(this.tope);
        return clon;
    }
    public Nodo clonRec(Nodo aux){
        Nodo ret;
        if(aux.getEnlace()!=null){
            ret=new Nodo(aux.getElem(),clonRec(aux.getEnlace()) );
        }else{
         ret=null;
        } 
        return ret;
    }
}