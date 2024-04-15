package jerarquicas.estaticas;
public class ArbolBinario {
    private int TAMANIO=10;
    private CeldaBinaria []arbol;
    private int raiz;
    public ArbolBinario(){
         this.raiz=-1;
         this.arbol=new CeldaBinaria[TAMANIO-1];
    }
    public Boolean esVacio(){
        return raiz==-1;
    }
    public void vaciar(){
        this.raiz=-1;
        this.arbol=new CeldaBinaria[TAMANIO-1];
    }
    public Boolean insertar(Object nuevoElem,Object elemPadre, int lado){
        Boolean colocado=false;
        if(this.esVacio()){
             this.raiz=0;
             this.arbol[this.raiz]=new CeldaBinaria(null, lado, lado, colocado);
        }else{

        }
        return colocado;
    }
    
    /*public Object padre(){

    }
    public int altura(){

    }
    public int nivel(){

    }
    public String listarPreorden(){

    }
    public String listarInorden(){
        
    } 
    public String listarPosorden(){
        
    } 
    public String listarNivel(){
        
    }
    public ArbolBinario clone(){

    }
    public String toString(){

    }*/

}
