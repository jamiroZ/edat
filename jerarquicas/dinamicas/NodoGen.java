package jerarquicas.dinamicas;

public class NodoGen{
    private Object elem;
    private NodoGen hijoIzq;
    private NodoGen hermanoDer;
    public NodoGen(Object elem, NodoGen hijoIzq , NodoGen hermanoDer){
             this.elem=elem;
             this.hijoIzq=hijoIzq;
             this.hermanoDer=hermanoDer;
    }
    //observadores
    public Object getElem(){
        return this.elem;
    }
    public NodoGen getHijoIzq(){
        return this.hijoIzq;
    }
    public NodoGen getHermanoDer(){
        return this.hermanoDer;
    }
    //modificadores
    public void setElem(Object elem){
        this.elem=elem;
    }
    public void setHijoIZq(NodoGen hijoIzq){
        this.hijoIzq=hijoIzq;
    }
    public void setHermanoDer(NodoGen hermanoDer){
        this.hermanoDer=hermanoDer;
    }
}
