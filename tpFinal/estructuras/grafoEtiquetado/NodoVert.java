package tpFinal.estructuras.grafoEtiquetado;

public class NodoVert {
    private NodoVert sigVertice;
    private NodoAdy primerAdy;
    private Object elem;
    public NodoVert(Object elem,NodoVert sigVertice, NodoAdy primerAdy){
         this.elem=elem;
         this.sigVertice=sigVertice;
         this.primerAdy=primerAdy;
    }
    public Object getElem(){
        return this.elem;
    }
    public void setElem(Object elem){
        this.elem= elem;
    }
    public NodoVert getSigVertice(){
        return this.sigVertice;
    }
    public void setSigVertice(NodoVert nVert){
        this.sigVertice=nVert;
    }
    public NodoAdy getPrimerAdy(){
        return this.primerAdy;
    }
    public void setPrimerAdy(NodoAdy nAdy){
          this.primerAdy=nAdy;
    }
}
