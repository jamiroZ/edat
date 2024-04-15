package jerarquicas.estaticas;

public class CeldaBinaria {
    private Object elem;
    private int izquierdo;
    private int derecho;
    private Boolean enUso;
    public CeldaBinaria(Object unElem,int izq,int der,Boolean uso){
             this.elem=unElem;
             this.izquierdo=izq;
             this.derecho=der;
             this.enUso=uso;
    }
    //modificadores
    public void setElem(Object unElem){
        this.elem=unElem;
    }
    public void setIzquierda(int izq){
        this.izquierdo=izq;
    }
    public void setDerecha(int der){
        this.derecho=der;
    }
    public void setEnUso(Boolean uso){
        this.enUso=uso;
    }
    //observadores
    public Object getElem(){
        return this.elem;
    }
    public int getIzquierdo(){
        return this.izquierdo;
    }
    public int getDerecho(){
        return this.derecho;
    }
    public Boolean getEnUso(){
        return this.enUso;
    }
}
