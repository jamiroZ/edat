package tpFinal.estructuras.conjuntistas;

import tpFinal.estructuras.lineales.Lista;
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
    public int getCant(){
        return this.cant;
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
         if(aux!=null){//si lo encontro lo asocia con el tipo del Rango
             asociado= aux.agregarRango(tipRango);//agrega el elemento a la lista RANGO dentro del nodo 

         }
         return asociado;
    }
    public Boolean desasociar(Object tipDom, Object tipRango){
        Boolean asociado=false;
        NodoHashMapeoM aux=obtenerNodo(tipDom);
        if(aux!=null){
            aux.getRango().eliminarApariciones(tipRango);
        }
        return asociado;
   }
   public Lista obtenerValor(Object tipDom){//retora LISTA (TIPO RANGO)
        Lista list=new Lista();
        NodoHashMapeoM n=obtenerNodo(tipDom);
        if(n!=null){
             list=n.getRango();//retorna el Rango del Dominio buscado
        }
        return list;
   }
   public Lista obtenerConjuntoDominio(){//retorna LISTA (TIPO DOMINIO)
        Lista list=new Lista();
        int i=0,j=0;
        while(i < this.TAMANIO-1 && j <= this.cant){//va a recorrer lo justo
            if(this.tabla[i]!=null){//hay al menos un nodo en la posicion
               NodoHashMapeoM aux=this.tabla[i];
               System.out.println("x");
               while(aux.getEnlace()!=null){
                    list.insertar(aux.getDominio(), list.longitud()+1);
                    aux=aux.getEnlace();
                    j++;//cuenta cada objeto de la lista
               }
               System.out.println(list.toString());
            }
            i++;
        }
        return list; 
   }
   public Lista obtenerConjuntoRango(){//retorna LISTA (TIPO RANGO)
        Lista list=new Lista();
        int i=0,j=0;
        while(i < this.TAMANIO-1 && j <= this.cant){//va a recorrer lo justo
            if(this.tabla[i]!=null){//hay al menos un nodo en la posicion
               NodoHashMapeoM aux=this.tabla[i];
               
               while(aux.getEnlace()!=null){
                    System.out.println("x");
                    list.insertar(aux.getRango(), list.longitud()+1);
                    aux=aux.getEnlace();
                    j++;//cuenta cada objeto de la lista
               }
            }
            i++;
        }
        return list;
   }
   public int funcionHash(Object nuevoElem){//retorna la posicion del elemento si existe
        int pos= nuevoElem.hashCode()% this.TAMANIO;//obtengo la posicion
        if( pos <= TAMANIO ){
           if(this.tabla[pos]!=null){//existe al menos un nodo en la posicion
               NodoHashMapeoM aux=this.tabla[pos];
               while(!aux.getDominio().equals(nuevoElem)){//si no e el objeto
                    aux= aux.getEnlace();//se mueve en la lista
                    pos++;
               }
           }else{
              pos=-1;//si no lo encontro devuelve -1
           }
        }
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
      if(!encontrado){//SINO LO ENCONTRO retorna null
           aux=null;
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
   public void mostrarRango(){
       ;
   }
   public String mostrarPartidosConClave(Object tipDom){
       String ret="";
       NodoHashMapeoM aux=obtenerNodo(tipDom);//obtiene el nodo con el dominio
       if(aux!=null){//si existe es diferente de null
            ret= aux.getDominio().toString()+" ";//agregar toString clase claveP
            int i=1;
            while(i <= aux.getRango().longitud()){
                ret=ret+aux.getRango().recuperar(i).toString()+" ; ";//agragar
                i++;
            }
            
       }
       return ret;
   }
}

