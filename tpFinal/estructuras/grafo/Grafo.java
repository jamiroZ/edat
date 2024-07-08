package tpFinal.estructuras.grafo;

public class Grafo {
    private NodoVert inicio;
    public Grafo(NodoVert inicio){
         
    }
    //METODOS QUE INVOLUCRAN EL VERTICE
    public Boolean insertarVertice(Object elem){
        Boolean flag=false;
        
        return flag;
    }
    public Boolean eliminarVertice(Object elem){
        Boolean flag=false;

        return flag;
    }
    public Boolean existeVertice(Object elem){
        Boolean flag=false;
        return flag;
    }
    //METODOS QUE INVOLUCRAN EL ARCO
    public Boolean insertarArco(NodoVert n, NodoAdy n2, Object tipEtiqueta){
        Boolean flag=false;

        return flag;
    }
    public Boolean eliminarArco(Object elem){
        Boolean flag=false;

        return flag;
    }
    public Boolean existeArco(Object elem){
        Boolean flag=false;
        return flag;
    }
    //OTROS
    public Boolean existeCamino(Object elem){
        Boolean flag=false;
        return flag;
    }
    public Lista listarEnProfundidad(){//retorna LISTA DE ELEMENTOS TIPO VERTICE

    }
    public Lista listarEnAnchura(){//retorna LISTA DE ELEMENTOS  TIPO VERTICE

    }
    public Lista listarCaminoMasCorto(O){//retorna LISTA DE ELEMENTOS TIPO VERTICE

    }
    public Boolean esVacio(){
        return this.inicio==null;
    }

}
