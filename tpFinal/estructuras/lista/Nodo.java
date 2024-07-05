package tpFinal.estructuras.lista;

public class Nodo {

    
    private Object elemento;
    private Nodo enlace;
    //construtctor
    public Nodo(Object unElem,Nodo unEnlace){
            this.enlace=unEnlace;
            this.elemento=unElem;
    }
    //observadores
    public Object getElem(){
        return this.elemento;
    }
    public Nodo getEnlace(){
        return this.enlace;
    }
    //modificadores
    public void setElem(Object elem){
        this.elemento=elem;
    }
    public void setEnlace(Nodo enla){
        this.enlace=enla;
    }

}