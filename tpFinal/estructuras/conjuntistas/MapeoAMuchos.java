package tpFinal.estructuras.conjuntistas;
//importo la estructura LISTA
import tpFinal.estructuras.lista.Lista;



//MAPEO A MUCHOS CON HASH ABIERTO
//
/* */
public class MapeoAMuchos {
    private int TAMANIO=20;
    private NodoHashMapeoM []tabla ;
    private int cant=0;
    public MapeoAMuchos(){//CONSTRUCTOR VACIO
        this.tabla=new NodoHashMapeoM[TAMANIO];
        this.cant=0;
    }
    //METODOS DE INSERCION OBJETOS (DOMINIO)
    //hash no acepta elementos repetidos
    public Boolean insertar(Object elem){//si no lo encontro lo inserta y retorna true
        Boolean encontrado=false;
        int pos=elem.hashCode()% this.TAMANIO ;//posicion del dominio en la tabla hash
        NodoHashMapeoM aux=this.tabla[pos];
        while(!encontrado && aux!=null){
            encontrado=aux.getDominio().equals(elem);
            aux=aux.getEnlace();
        }
        if(!encontrado){//si no se encuentra entonces lo inserta
             this.tabla[pos]=new NodoHashMapeoM(elem, this.tabla[pos]);
             this.cant++;//incrementa la cantidad de elementos en la tablaHash
        }
        return !encontrado;
    }
    public Boolean eliminar(Object elem){
        Boolean exito=false;
        int pos=elem.hashCode()% this.TAMANIO;
        NodoHashMapeoM aux =this.tabla[pos];
        if(aux!=null){//debe existir al menos un objeto en la lista de la posicion para buscar
           if(aux.getDominio().equals(elem)){//es la cabecera
              this.tabla[pos]=aux.getEnlace();//elimino
              exito=true;
           }else{//busca en la lista
              exito=eliminarRec(aux,aux,elem);
           }
        }
        return exito;
    }
    public Boolean eliminarRec(NodoHashMapeoM n, NodoHashMapeoM ant, Object elem){
        Boolean flag=false;
        if(n!=null){
            System.out.println(n.getDominio());
            if(n.getDominio().equals(elem)){//lo encontro
                if(n.getEnlace()!=null){
                     ant.setEnlace(n.getEnlace());//enlazo anterior con el siguiente de n
                     n.setEnlace(null);//desenlazo n de su nodo siguiente
                }else{//es el ultimo nodo
                     ant.setEnlace(null);
                }
            }else{//sigue buscando
                flag=eliminarRec(n.getEnlace(),n,elem);
            }
        }
        return flag;
    }
    public Boolean pertenece(Object elem){
        Boolean existe=false;
        int pos=elem.hashCode() % this.TAMANIO;
        NodoHashMapeoM aux=this.tabla[pos];
        existe=perteneceRec(aux,elem);
        return existe;
    }
    public Boolean perteneceRec(NodoHashMapeoM n, Object elem){
          Boolean encontrado=false;
          if(n!=null){
              System.out.println(n.getDominio());
              if(elem.equals(n.getDominio())){//lo encontro termina
                 encontrado=true;
              }else{
                 encontrado=perteneceRec(n.getEnlace(),elem);
              }
          }
          return encontrado;
    }
    public Boolean esVacio(){
        return this.cant==0;//no hay elementos entonces devuelve true
    }
    //METODOS DE RELACION DOMINIO Y RANGO
    public Boolean asociar(Object tipDom, Object tipRango){
         Boolean asociado=false;
         NodoHashMapeoM aux=obtenerNodo(tipDom);//busca el nodo conde esta el elemento
         if(aux.getDominio().equals(tipDom)){//si lo encontro lo asocia con el tipo del Rango
              aux.setRango(tipRango);//agrega el elemento a la lista RANGO dentro del nodo 
         }
         return asociado;
    }
    public Boolean desasociar(Object tipDom, Object tipRango){
        Boolean asociado=false;
        NodoHashMapeoM aux=obtenerNodo(tipDom);
        if(aux.getDominio().equals(tipDom)){
             aux.setRango(tipRango);
        }
        return asociado;
   }
   public Lista obtenerValor(Object tipDom){//retora LISTA (TIPO RANGO)
        Lista list=new Lista();
        return list;
   }
   public Lista obtenerConjuntoDominio(){//retorna LISTA (TIPO DOMINIO)
        Lista list=new Lista();
        return list; 
   }
   public Lista obtenerConjuntoRango(){//retorna LISTA (TIPO RANGO)
        Lista list=new Lista();
        return list;
   }
   public int funcionHash(Object nuevoElem){//
        int pos=nuevoElem.hashCode()% this.TAMANIO;//obtengo la posicion
        
        return pos;
   }

   private NodoHashMapeoM obtenerNodo( Object elem){//busca un objeto y retorna su nodo para uso privado
      Boolean encontrado=false;
      int pos=elem.hashCode()% this.TAMANIO ;//posicion del dominio en la tabla hash
      NodoHashMapeoM aux=this.tabla[pos];
      while(!encontrado && aux!=null){
        encontrado=aux.getDominio().equals(elem);
        if(!encontrado){
            aux=aux.getEnlace();
        }
      }
      return aux;
   }




   public String toString(){
       String ret="ESTA VACIA ";
       if(!this.esVacio()){
           NodoHashMapeoM aux=this.tabla[0];
           int i=0;
           ret="[ ";
           while(i < TAMANIO){
              if(this.tabla[i]!=null){
                  ret= ret + this.tabla[i].getDominio()+", " +toStringR(this.tabla[i].getEnlace())+" ; ";
              }
              i++;
           }
           ret=ret +" ]";
          
       }
       return ret;
   }
   private String toStringR(NodoHashMapeoM n){
       String ret=" ";
       if(n!=null){
            ret= n.getDominio()+", "+toStringR(n.getEnlace());
       }else{
           ret=" ";
       }
       return ret;
   }

}

