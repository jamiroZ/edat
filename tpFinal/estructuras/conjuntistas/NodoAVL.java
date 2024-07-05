package tpFinal.estructuras.conjuntistas;

public  class NodoAVL{
    private int altura;//altura del nodo
    private Comparable elem;//objeto del nodo
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    public NodoAVL(Comparable elem,NodoAVL izquierdo,NodoAVL derecho){
            this.elem=elem;
            this.izquierdo=izquierdo;
            this.derecho=derecho;
            this.recalcularAltura();
    }
    public NodoAVL (Comparable elem){
         this.elem=elem;
         this.izquierdo=null;
         this.derecho=null;
         this.altura=0;
    }
    //OBJETO DEL NODO
    public Comparable getElem(){
       return this.elem;
    }
    public void setElem(Comparable elem){
       this.elem=elem;
    }
    //ALTURA DEL NODO
    public int getAltura(){
       return this.altura;
    }
    public void recalcularAltura(){//recalcula la altura del nodo
        int altIzquierda=-1,altDerecha=-1;//planteamos si estan vacios
        //los nodos deben existir
        if(this.getIzquierdo() !=null){
           altIzquierda=this.getIzquierdo().altura;
        }
        if(this.getDerecho()!=null){
           altDerecha=this.getDerecho().altura;
        }
        //obtiene la mayor altura entre sus hijos
        this.altura=Math.max(altIzquierda, altDerecha)+1;
    }
    
    //NODO  HIJO IZQUIERDO
    public NodoAVL getIzquierdo(){
       return this.izquierdo;
    }
    public void setIzquierdo(NodoAVL izquierdo){
       this.izquierdo=izquierdo;
    }
    //NODO HIJO DERECHO
    public NodoAVL getDerecho(){
       return this.derecho;
    }
    public void setDerecho(NodoAVL derecho){
       this.derecho=derecho;
    }
}
