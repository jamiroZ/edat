package tpFinal.estructuras.lineales;

public class Cola{
    private Nodo fin;
    private Nodo frente;
    //constructor vacio
    public Cola(){
       this.fin=null;
       this.frente=null;
    }
    //metodo que retorna vacio si fin y frente tienen como enlace=null(esta vacia la cola)
    public boolean esVacia(){//si esta vacia retorna true
       return (this.fin==null && this.frente==null);
    }
    public Object obtenerFrente(){
        Object elem=null;
        if(!this.esVacia()){
            elem=frente.getElem();
        }
        return elem;
    }
    //crea un nodo y inserta el elemento y enlasa el nodo
    public boolean poner(Object elem){
        boolean logico=false;
        Nodo nodo=new Nodo(elem, null);//crea y instancia el nuevo nodo,enlazado a null
        if(this.esVacia()){//si la cola esta vacia enlaza el frente
            this.frente=nodo;
            this.fin=nodo;
            logico=true;
        }else{//si la cola NO esta vacia enlaza anterior nodo enlazado al fin al nuevo nodo
             this.fin.setEnlace(nodo);
             this.fin=nodo;//enlaza el fin al nuevo nodo
            logico=true;
        }
       
       return logico;
    }
    //elimina nodo de la cola y sus enlazes
    public boolean sacar(){
        boolean logico=false;
        if(!this.esVacia()){//si la cola NO esta vacia saca el elemento
            this.frente=this.frente.getEnlace();
            if(this.frente==null){//si se elimino el ultimo elemento
               this.fin=null;
            }

        }
        return logico;
    }
    //retorna un texto con los elementos de la cola
    public String toString(){
       return toStringRec(this.frente);
    }
    public String toStringRec(Nodo cursor){
         String txt="";
         if(!this.esVacia()){
           if(cursor!=null){
              txt=cursor.getElem()+" ; "+toStringRec(cursor.getEnlace());
           }else{
              txt=".";
           }
         }else{
           txt="LA COLA ESTA VACIA";
         }
         return txt;
    }
    //clona una cola
    public Cola clone(){
       Cola clon=new Cola();
       if(!this.esVacia()){
         clon.frente=this.frente;
         clon.fin=this.fin;
         cloneRec(this.frente);
       }
      
       return clon;
    }
    public Nodo cloneRec(Nodo cursor){
       Nodo ret;
       if(cursor!=null){
              ret=new Nodo(cursor.getElem(), cursor.getEnlace());
       }else{
             ret=null;
       }
       return ret;
    }
    //vacia la cola
    public void vaciar(){
        this.fin=null;
        this.frente=null;
    }
}