package tpFinal.estructuras.grafoEtiquetado;

import tpFinal.estructuras.lista.Lista;
public class Grafo {
    private NodoVert inicio;
    public Grafo(){//constructor vacio
         this.inicio=null;
    }
    //METODOS QUE INVOLUCRAN EL VERTICE
    public Boolean insertarVertice(Object elem){
        Boolean flag=false;
        NodoVert aux=ubicarVertice(elem);
        if(aux==null){//si no lo encontro lo inserta
            flag=true;
            this.inicio=new NodoVert(elem, this.inicio ,null);
        }
        return flag;
    }
    public Boolean eliminarVertice(Object elem){
        Boolean flag=false;
        NodoVert aux=this.inicio, ant=null;//un nodo de busqueda y otro que siga el paso anterior
        while(aux !=null && !aux.getElem().equals(elem)){//NO LO ENCONTRO
            ant=aux;
            aux=aux.getSigVertice();//SIGUE BUSCANDO
        }
        if(aux!=null){//si lo encontro 
             NodoAdy adyacente=aux.getPrimerAdy();
             while(adyacente!=null){//tiene nodos adyacente
                //a cada nodo vertice lo desenlazo del elemento que se quiere eliminar
                 eliminarArcoAux(adyacente.getVertice(), elem);
                 adyacente=adyacente.getSigAdyacente();
             }
             if(ant==null){//CASO ESPECIAL: EL NODO QUE ELIMINO ES EL DE INICIO DE LA LISTA DE NODOS 
                 this.inicio=aux.getSigVertice();
             }else{
                 ant.setSigVertice(aux.getSigVertice());//ENLAZO EL ANTERIOR CON EL SIGUIENTE DEL NODO QUE QUIERO ELIMINAR
                 aux.setSigVertice(null);//ELIMINO EL ENLACE DEL NODO ELIMINADO
             }
        }
        return flag;
    }
    public Boolean existeVertice(Object elem){//retorna un boolean si existe "true" si no "false"
        Boolean flag=false;
        NodoVert aux=this.inicio;
        while(aux != null && !flag){//NO LO ENCONTRO
            flag=aux.getElem().equals(elem);//si lo encontro flag se instancia true
            aux=aux.getSigVertice();//SIGUE BUSCANDO
        }
        return flag;
    }
    private NodoVert ubicarVertice(Object elem){//retorna el nodo que contiene el elemento buscado para uso privado
        NodoVert aux=this.inicio;//arranca desde el inicio
        while(aux != null && !aux.getElem().equals(elem)){//NO LO ENCONTRO
              aux=aux.getSigVertice();//SIGUE BUSCANDO
        }
        return aux;
    }

    //METODOS QUE INVOLUCRAN EL ARCO
    public Boolean insertarArco(Object ini,Object fin, int etiqueta){
        Boolean flag=false;
        if(!ini.equals(fin)){//no puede un nodo enlazarse a si mismo
             NodoVert origen=ubicarVertice(ini);//nodo con el elemento ini
             if(origen != null){//existe un nodo vertice con el objeto ini
                   NodoVert destino=ubicarVertice(fin);//nodo con el elemento fin
                   NodoAdy ady=origen.getPrimerAdy();
                   while(!flag && ady!=null){//busca la existencia de un arco entre ini y fin
                          flag=fin.equals(ady.getVertice().getElem());
                          if(!flag){
                              ady=ady.getSigAdyacente();//se mueve en la lista adyacentes
                          }
                   }
                   if(!flag){//si no encontro un arco entre ini y fin lo hace
                        origen.setPrimerAdy(new NodoAdy(destino, origen.getPrimerAdy(), etiqueta));
                        destino.setPrimerAdy(new NodoAdy(origen, destino.getPrimerAdy(), etiqueta));
                   }
                

             }
        }
       
        return flag;
    }
    public Boolean eliminarArco(Object ini ,Object fin){
        Boolean flag=false;
        NodoVert aux=ubicarVertice(ini);//metodo privado que devuelve el nodo que contiene a "ini"
        if(aux!=null){//el nodo existe 
             //nodos auxiliares
             NodoAdy auxAdy=aux.getPrimerAdy();//puntero para buscar a "fin"
             NodoAdy antAdy=null;//anterior al puntero
             NodoVert destino=null;
             while(auxAdy != null &&  !flag){
                     flag= auxAdy.getVertice().getElem().equals(fin);//si es el objeto fin flag instancia "TRUE"
                     if(flag){
                        destino=auxAdy.getVertice();//nodo que contiene a "fin"
                        
                     }else{
                        antAdy = auxAdy;//anterior 
                        auxAdy=auxAdy.getSigAdyacente();//actual
                     }
                     
             }
             if(flag){//si lo encontro lo elimina
                  if(antAdy==null){//auxAdy es la cabecera
                      aux.setPrimerAdy(auxAdy.getSigAdyacente());
                  }else{//es auxAdy un nodo distinto de la cabecera
                      antAdy.setSigAdyacente(auxAdy.getSigAdyacente());
                  }
                  eliminarArcoAux(destino,ini);//desenlazo en el destino
             }
        }
        return flag;
    }
    private void eliminarArcoAux(NodoVert n,Object elem){
        NodoAdy aux=n.getPrimerAdy(),ant=null;
        Boolean flag=false;
        while(aux!=null && !flag){
              flag=elem.equals(aux.getVertice().getElem());
              if(!flag){
                   ant=aux;
                   aux=aux.getSigAdyacente();
              }
        }
        if(flag){//se encontro
            if(ant==null){//caso especial es la cabecera de los adyacentes
                n.setPrimerAdy(aux.getSigAdyacente());
            }else{//enlaza el anterior  con el siguiente del nodo aux
                ant.setSigAdyacente(aux.getSigAdyacente());
            }
    
        }

    }
    public Boolean existeArco(Object ini,Object fin){//retorna true si existe arco entre ini y fin
        Boolean flag=false;
        NodoVert aux=ubicarVertice(ini);//busca el nodo que contenga a ini
        if(aux!=null){//si existe
                  NodoAdy ady=aux.getPrimerAdy();//busca en sus nodos adyacentes
                  while(ady!=null && !flag){// sino lo encontro y la lista adyacente posee nodos sigue buscando
                       flag=ady.getVertice().getElem().equals(fin);//si es el elemento fin flag cambia a TRUE
                       ady=ady.getSigAdyacente();//recorre la lista
                  }
                  
             
        }
        return flag;
    }

    //OTROS
    public Boolean existeCamino(Object elem){
        Boolean flag=false;
        if(flag){

        }
        return flag;
    }
    public Lista listarEnProfundidad(){//retorna LISTA DE ELEMENTOS TIPO VERTICE
         Lista list=new Lista();
         return list;
    }
    public Lista listarEnAnchura(){//retorna LISTA DE ELEMENTOS  TIPO VERTICE
        Lista list=new Lista();
        return list;
    }
    public Lista listarCaminoMasCorto(){//retorna LISTA DE ELEMENTOS TIPO VERTICE
        Lista list=new Lista();
        return list;
    }
    public Boolean esVacio(){
        return this.inicio==null;//retorna true si esta el grafo vacio

    }
    public void vaciar(){
        this.inicio=null;
    }
    public String toString(){
        String ret=" ";
        if(this.esVacio()){//si el grafo esta vacio 
            ret="GRAFO ETIQUETADO VACIO";
        }else{
            ret=toStringAux(this.inicio);
        }
        return ret;
    }
    private String toStringAux(NodoVert n){
        String ret="";
        ret="+ ("+n.getElem()+"): \n   ";
        NodoAdy ady=n.getPrimerAdy();
        if(ady!=null){//si tiene nodos adyacentes n que concatene las etiquetas y los nodos con arco 
            while(ady!=null){//me muevo en la lista de nodos adyacentes
                ret=ret +" : "+ady.getVertice().getElem()+": "+ady.getEtiqueta()+"\n    ";
                ady=ady.getSigAdyacente();
            }
        }else{
            ret = ret + "\n";
        }
        if (n.getSigVertice() != null){//se mueve en la lista de nodos vertices
            ret = ret + "\n "+toStringAux(n.getSigVertice());
        }
        return ret;
    }

}
