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
        Object ret;
        if(this.esVacia()){
            ret=null;
        }else{
            ret=this.tope.getElem();
        }
        return ret;
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
        return toStringRec(this.tope);
    }
    public String toStringRec(Nodo aux){
        String txt="";
        if(this.esVacia()){
            txt="LA PILA ESTA VACIA";
        }else{
            if(aux == null){
                txt="";
            }else{
               txt=toStringRec(aux.getEnlace())+","+aux.getElem();
            }
            
        }
        return txt;
    }
    //clonar pila
    public Pila clone(){
        Pila clon=new Pila();
        clon.tope=this.tope;
        if(!this.esVacia()){
            clonRec(this.tope);
        }
        return clon;
    }
    public Nodo clonRec(Nodo aux){
        Nodo ret;
        if(aux!=null){
            ret=new Nodo(aux.getElem(),aux.getEnlace() );
        }else{
         ret=null;
        } 
        return ret;
    }
    public Boolean equals(Pila p){//retorna true si la pila ingresada es igual a la misma pila
          Boolean v=false;
          if(!this.esVacia()){
               v=equalsRec(this.tope,p.tope);
          }
          return v;
    }
    public Boolean equalsRec(Nodo t,Nodo t2){
         Boolean v=true;
         if(t!=null){
            //System.out.println(t.getElem()+" "+t2.getElem());
             if(t.getElem().equals(t2.getElem())){
                v=equalsRec(t.getEnlace(),t2.getEnlace());
             }else{
                v=false;
             }
         }
         return v;
    }
}