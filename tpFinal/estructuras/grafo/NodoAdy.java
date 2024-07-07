package tpFinal.estructuras.grafo;

public class NodoAdy {
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Object etiqueta;
    public NodoAdy(NodoVert vertice, NodoAdy sigAdyacente ,Object etiqueta){
             this.vertice=vertice;
             this.sigAdyacente=sigAdyacente;
             this.etiqueta=etiqueta;
    }
    //
    public NodoVert getVert(){
        return this.vertice;
    }
    public void setVert(NodoVert vertice){
        this.vertice=vertice;
    }
    //
    public NodoAdy getAdy(){
        return this.sigAdyacente;
    }
    public void setAdy(NodoAdy sigAdyacente){
        this.sigAdyacente=sigAdyacente;
    }
    //
    public Object getEtiqueta(){
        return this.etiqueta;
    }
    public void setEtiqueta(Object etiqueta){
        this.etiqueta=etiqueta;
    }
}
