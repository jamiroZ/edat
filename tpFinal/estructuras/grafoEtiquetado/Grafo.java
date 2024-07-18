package tpFinal.estructuras.grafoEtiquetado;

import tpFinal.estructuras.lineales.Cola;
import tpFinal.estructuras.lineales.Lista;
public class Grafo {
    private NodoVert inicio;
    public Grafo(){//constructor vacio
         this.inicio=null;
    }
    //METODOS QUE PRINCIPALES DEL VERTICE
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
             flag=true;
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
    public Object obtenerElem(Object elem){//retorno el elemento ubicado en el vertice
        if(ubicarVertice(elem)!=null){
           return ubicarVertice(elem).getElem();
        }else{
            return "NO EXISTE";
        }
    }
    public boolean existeVertice(Object elem){
        //verifica si un vertice esta en el grafo
        boolean flag;

        if (this.ubicarVertice(elem) == null){//si existe retorna NO NULO
            flag = false;
        }else{
            flag = true;
        }
        return flag;
    }
    private NodoVert ubicarVertice(Object elem){//retorna el nodo que contiene el elemento buscado para uso privado
        NodoVert aux=this.inicio;//arranca desde el inicio

        while(aux != null && ! elem.equals(aux.getElem())){//NO LO ENCONTRO
            
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
    public Boolean existeCamino(Object ini,Object fin){
        Boolean flag=false;
        if(!this.esVacio()){
            NodoVert aux=ubicarVertice(ini);
            if(aux!=null){
                   NodoAdy ady=aux.getPrimerAdy();//busca en sus nodos adyacentes la existencia de un camino
                   while(ady!=null  && !flag){//mientras tenga nodos adyacentes para buscar un camino y no lo encontro que busque
                        flag=ady.getVertice().getElem().equals(fin);//si es el elemento fin flag cambia a TRUE
                        ady=ady.getSigAdyacente();//recorre la lista
                   }
                   if(!flag){//si no existia un arco "camino directo" entre ini y fin que siga buscando
                        Lista vistos=new Lista();//lista auxiliar de elementos de nodos visitados
                        flag=existeCaminoRec(vistos,fin,aux);
                   }
            }
        }
        return flag;
    }
    private  Boolean existeCaminoRec(Lista vistos,Object fin, NodoVert n){
        Boolean ret=false;
        if(n!=null){
            System.out.println(n.getElem());
               if(fin.equals(n.getElem())){//encontro el camino
                     ret=true;
               }else{//sino verifica que n tiene camino a fin
                    vistos.insertar(n.getElem(), vistos.longitud()+1);//
                    NodoAdy ady=n.getPrimerAdy();
                    while(!ret && ady!=null){//comprueba si sus adyacentes poseen un camino a fin
                           //si el vertice perteneciente al nodo adyacente no se visito,revisa si este posee un camino a fin
                           if(vistos.localizar(ady.getVertice().getElem()) < 0){
                                 ret=existeCaminoRec(vistos, fin , ady.getVertice());
                           }
                           ady=ady.getSigAdyacente();
                    }
                    
               }
        }
        return ret;
    }
    //retorna  lista de elementos de tipo vertice 
    //ES EL CAMINO QUE PASA POR MENOS VERTICES PARA LLEGAR A FIN
    public Lista listarCaminoMasCorto(Object ini, Object fin){//retorna LISTA DE ELEMENTOS TIPO VERTICE
        Lista camino=new Lista();
        if(!this.esVacio()){
            NodoVert aux=ubicarVertice(ini);//busco nodo de partida
            if(aux!=null){
                   Boolean flag=false;
                   NodoAdy ady=aux.getPrimerAdy();//busca en sus nodos adyacentes la existencia de un camino
                   while(ady!=null  && !flag){//mientras tenga nodos adyacentes para buscar un camino y no lo encontro que busque
                        flag=ady.getVertice().getElem().equals(fin);//si es el elemento fin flag cambia a TRUE
                        ady=ady.getSigAdyacente();//recorre la lista
                   }
                   if(!flag){//si no existia un arco "camino directo" entre ini y fin que siga buscando
                        Lista camAct=new Lista(), vistos=new Lista();//lista auxiliar de elementos de nodos visitados
                        caminoMasCortoRec(vistos,camAct,camino,fin,aux);
                   }
            }
        }
        return camino;
    }
    private void caminoMasCortoRec(Lista vistos,Lista camAct, Lista camino, Object fin ,NodoVert n){

        if(n!=null){
             //
             vistos.insertar(n.getElem(), vistos.longitud()+1);//guardo los ya vistos
             camAct.insertar(n.getElem(), vistos.longitud()+1);//creo el camino para comparar
             if(fin.equals(n.getElem())){//se llego al final
                   if(camino.longitud() < camAct.longitud()){
                          camino=camAct.clone();//nueva lista de camino MINIMO
                   } 
             }else{
                 NodoAdy ady=n.getPrimerAdy();
                 while(ady!=null){//recorro lista de adyacentes a n
                        if(vistos.localizar(ady.getVertice().getElem())<0){//no visito el elemento en el vertice del NodoAdyacente, lo visita
                             caminoMasCortoRec(vistos,camAct,camino , fin ,ady.getVertice());
                        }
                        ady=ady.getSigAdyacente();
                 }
             }
             camAct.eliminar(camAct.longitud());//lo visite ,lo elimino
             vistos.eliminar(vistos.longitud());//puede haber mas caminos que recorran ese nodo, lo elimino de visto

        }

    }

    public Lista listarEnProfundidad(){//retorna LISTA DE ELEMENTOS TIPO VERTICe
        Lista vistos=new Lista();
        NodoVert aux=this.inicio;
        while(aux!=null ){//ME MUEVO EN LA LISTA DE NODOS ADYACENTES
              if(vistos.localizar(aux.getElem())< 0){//no se visito el elemento de aux
                   profundidadDesde( aux,vistos);
              }
              aux=aux.getSigVertice();

        }
        return vistos;
    }
    private void profundidadDesde(NodoVert n, Lista vistos){
        vistos.insertar(n.getElem(),vistos.longitud()+1);
        NodoAdy ady=n.getPrimerAdy();
        while(ady != null){//ME MUEVO EN LA LISTA DE NODOS ADYACENTES
             //si el vertice perteneciente al nodo adyacente no se visito
             if(vistos.localizar(ady.getVertice().getElem())<0){
                  profundidadDesde(ady.getVertice(), vistos);
             }
             ady=ady.getSigAdyacente();
        }
    }
    public Lista listarEnAnchura(){//retorna LISTA DE ELEMENTOS  TIPO VERTICE
        Lista vistos=new Lista();
        NodoVert aux=this.inicio;
        while(aux!=null){
             if(vistos.localizar(aux.getElem())<0){//no se habia visitado
                  anchuraDesde(aux,vistos);
             }
              aux=aux.getSigVertice();
        }
        return vistos;
    }
    private void anchuraDesde(NodoVert n,Lista vistos){
        Cola q=new Cola();
        vistos.insertar(n.getElem(), vistos.longitud()+1);//lo visito entonces lo guarda como visto
        q.poner(n);//
        NodoAdy ady;
        NodoVert aux;
        while(!q.esVacia()){//mientras la cola no este vacia
             aux= (NodoVert) q.obtenerFrente();
             q.sacar();
             ady=aux.getPrimerAdy();
             while(ady!=null){
                   if(vistos.localizar(ady.getVertice().getElem())<0){//SI NO SE VISITO EL ELEMENTO DEL VERTICE DEL NODO ADYACENTE 
                        vistos.insertar(ady.getVertice().getElem(), vistos.longitud()+1);//lo visite lo guardo
                        q.poner(ady.getVertice());
                   }
                   ady=ady.getSigAdyacente();//me muevo en la lista de nodos adyacentes de aux
             }

        }


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
                ret=ret +"-->"+ady.getEtiqueta()+" : "+ady.getVertice().getElem()+"\n    ";
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
