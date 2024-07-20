package tpFinal.estructuras.grafoEtiquetado;

public class NodoAdy {
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Double etiqueta;
    public NodoAdy(NodoVert vertice, NodoAdy sigAdyacente , double etiqueta){
             this.vertice=vertice;
             this.sigAdyacente=sigAdyacente;
             this.etiqueta=etiqueta;
    }
    //modifica o observa el nodo 
    public NodoVert getVertice(){
        return this.vertice;
    }
    public void setVertice(NodoVert vertice){
        this.vertice=vertice;
    }
    //modifica o observa el enlace guardado de su siguiente nodo adyacente
    public NodoAdy getSigAdyacente(){
        return this.sigAdyacente;
    }
    public void setSigAdyacente(NodoAdy sigAdyacente){
        this.sigAdyacente=sigAdyacente;
    }
    //
    public Object getEtiqueta(){
        return this.etiqueta;
    }
    public void setEtiqueta(Double etiqueta){
        this.etiqueta=etiqueta;
    }
}
