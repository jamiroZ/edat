package tpFinal.dominio;

//importo para uso de txt ingreso
import java.util.Scanner;

import tpFinal.estructuras.conjuntistas.ArbolAVL;
import tpFinal.estructuras.conjuntistas.MapeoAMuchos;
import tpFinal.estructuras.grafoEtiquetado.Grafo;
import tpFinal.estructuras.lineales.Lista;
//import tpFinal.dominio.EquipoGoles;
import tpFinal.txt.Archivo;
public class CopaAmerica{
    public static void main(String[] args) {
        testingCopaAmerica();
    }
    public static void testingCopaAmerica(){
          //ESCTRUCTURAS USADAS
          Grafo mapa=new Grafo();//grafo etiquetado(ciudades y rutas)
          MapeoAMuchos partidos=new MapeoAMuchos();//tabla hash mapeado a muchos(partidos )
          ArbolAVL equipos=new ArbolAVL();//arbol avl(equipos)
         
          Archivo.crearLog();//AGREGAR ACTUALIZACIONES LOG EN EL MENU
          Archivo.leerArchivo(mapa, equipos, partidos);//leo el txt y cargo en las estructuras la informacion 
          //VALOR NUMERICO PARA USO DEL MENU
          int opcion;
          do{
               System.out.println(" ");//espacio
               opcion=menu();//MENU PRINCIPAL DE OPCIONES
               switch(opcion){

                     case 1: opcionesCiudades(mapa,partidos); break;

                     case 2: opcionesEquipos(equipos,partidos); break;

                     case 3: altasPartidos(partidos,mapa,equipos); break;

                     case 4: consultasEquipos(equipos); break;

                     case 5: consultasPartidos(partidos, equipos); break;

                     case 6: consultasViajes(mapa); break;

                     case 7: mostrarEquiposOrdenados(equipos); break;

                     case 8: mostrarSistema(mapa,equipos,partidos); break;

                     default: System.out.println(" ERROR "); break;
               }
               System.out.println(" ");//espacio
          }while(opcion!=9);


    }
    public static int menu(){
        //uso objeto de Clase Scanner para leer diferentes tipos de datos
        Scanner sc=new Scanner(System.in);
        System.out.println("                    MENU                   ");
        System.out.println("1. Altas, Bajas y Modificaciones de Ciudades");
        System.out.println("2. Altas, Bajas y Modificaciones de Equipos");
        System.out.println("3. Altas de Partidos");
        System.out.println("4. Consultas sobre Equipos");
        System.out.println("5. Consultas sobre Partidos");
        System.out.println("6. Consultas sobre Viajes");
        System.out.println("7. Mostrar equipos ordenados por cantidad de goles a favor");
        System.out.println("8. Mostrar Sistema");
        System.out.println("9. SALIR");
        System.out.println(" ");
        int ret=sc.nextInt();
        return ret;
    }
    public static void opcionesCiudades(Grafo mapa,MapeoAMuchos partidos){
        Scanner sc=new Scanner(System.in);
         int i;
        do{ //MENU 

            System.out.println("1. Dar Alta a Ciudades ");
            System.out.println("2. Dar Baja a Ciudades ");
            System.out.println("3. Modificar Ciudades  ");
            System.out.println("4. SALIR");
            i=sc.nextInt();
            switch(i){//alternativa con respecto al menu
                  case 1: altaCiudades(mapa); break;
                  case 2: bajaCiudades(mapa,partidos); break;
                  case 3: modificarCiudades(mapa,partidos); break;
                  default: System.out.println(" ERROR "); break;
            }
            System.out.println(" ");
       }while(i!=4);
    }
    //METODOS DE ALTA,BAJA Y MODIFICACION DE CIUDAD
    public static void altaCiudades(Grafo mapa){
         Scanner sc=new Scanner(System.in);
         Boolean alojamiento,sede;
         System.out.println("ingrese el nombre de la ciudad: ");
         String nombre=sc.nextLine();
        // System.out.println(mapa.existeVertice(new Ciudad(nombre)));

         if(!mapa.existeVertice(new Ciudad(nombre)) ){//si la ciudad no existe la inserto
               System.out.println("ingrese 'SI' pose alojamiento disponible o 'NO' en caso contrario: " );
               String alo=sc.nextLine();
               if(alo.equalsIgnoreCase("SI")){
                     alojamiento=true;
               }else{
                     alojamiento=false;
               }
               System.out.println("ingrese 'SI' es sede de un partido o 'NO' si no es asi: ");
               String se=sc.nextLine();
               if(se.equalsIgnoreCase("SI")){
                   sede=true;
               }else{
                  sede=false;
               }
               // System.out.println(mapa.insertarVertice(new Ciudad(nombre,alojamiento,sede)));
               Archivo.escribir("LA CIUDAD "+nombre+" SE UBICO EN EL MAPA");
         }else{
            System.out.println("LA CIUDAD "+nombre+" YA EXISTE EN EL MAPA");
         }
    }
    public static void bajaCiudades(Grafo mapa,MapeoAMuchos partidos){
        Scanner sc=new Scanner(System.in);
        if(!mapa.esVacio()){
            System.out.println("Ingrese el nombre de la ciudad para darle de baja: ");
            String ciudadP=sc.nextLine();
            if(mapa.existeVertice(new Ciudad(ciudadP) ) ){//EXISTE LA CIUDAD
                  System.out.println(" ");
                  if(((Ciudad) mapa.obtenerElem( new Ciudad(ciudadP))).getSede()){// si es sede de un partido hay que re ubicarlo
                           System.out.println("LA CIUDAD ES SEDE DE UN PARTIDO");
                           System.out.println(" ");
                           System.out.println("ingrese ciudad donde re ubicar el partido: ");
                           String ciudadS=sc.nextLine();
                           //obtengo todos los partidos para buscar los que tengan sede la ciudad
                           Lista rangoS=partidos.obtenerConjuntoRango();
                           System.out.println(rangoS.longitud());
                           if(!rangoS.estaVacia()){
                                while(!rangoS.estaVacia()){
                                    Partido part= (Partido) rangoS.recuperar(1);
                                    if(part.getCiudad().equals(ciudadP)){
                                        part.setCiudad(ciudadS);
                                    }
                                     rangoS.eliminar(1);
                                }
                            
                           }else{
                                System.out.println(" ");
                                System.out.println("NO HAY PARTIDOS REGISTRADOS");
                           }

                  }
                  mapa.eliminarVertice(new Ciudad(ciudadP));//ELIMINO LA CIUDAD
                  Archivo.escribir("ciudad "+ciudadP+" eliminada");
            }else{//NO EXISTE
                System.out.println("La ciudad No existe en el mapa para la copa");
            }
        }else{//CASO MAPA VACIO
            System.out.println("NO HAY CIUDADES PARA ELIMINAR");
        }
        System.out.println(" ");
    }
    public static void modificarCiudades(Grafo mapa,MapeoAMuchos equipos){
        Scanner sc=new Scanner(System.in);
        if(!mapa.esVacio()){
             System.out.println("ingrege la ciudad que quiere modificar datos: ");
             String nombre=sc.nextLine();
            Ciudad ciudad= (Ciudad) mapa.obtenerElem(new Ciudad(nombre));//obtengo la ciudad a modificar
            int opcion;
            if(mapa.existeVertice(nombre)) {
              do{
                  System.out.println("1. cambiar info del alojamiento");
                  System.out.println("2. cambiar info de la sede");
                  System.out.println("3. volver a modificar");
                  opcion=sc.nextInt();
                  Boolean alojamiento,sede;
                  switch (opcion) {
                    case 1:
                         System.out.println("ingrese 'SI' pose alojamiento disponible o 'NO' en caso contrario: " );
                         String alo=sc.nextLine();
                         if(alo.equalsIgnoreCase("SI")){
                             alojamiento=true;
                         }else{
                             alojamiento=false;
                         }
                         ciudad.setAlojamiento(alojamiento);
                         Archivo.escribir("LA CIUDAD "+nombre+"ACTUALIZO SU ALOJAMIENTO");
                         break;
                    case 2:
                         System.out.println("ingrese 'SI' es sede de un partido o 'NO' si no es asi: ");
                         String se=sc.nextLine();
                         if(se.equalsIgnoreCase("SI")){
                             sede=true;
                         }else{
                             sede=false;
                         }
                         ciudad.setSede(sede);
                         Archivo.escribir("LA CIUDAD "+nombre+"ACTUALIZO SI ES SEDE");

                         break;
                    default:
                        break;
                  }
              }while(opcion!=3);
            }else{
                System.out.println("No existe esa ciudad en el mapa");
            }
        }else{
            System.out.println();
        }
        
    }
    //METODOS DE ALTA,BAJA Y MODIFICACION DE EQUIPOS
    public static void opcionesEquipos(ArbolAVL equipos,MapeoAMuchos partidos){
        Scanner sc=new Scanner(System.in);
        int i;
        do{ 
           //MENU 
           System.out.println("1. Dar Alta a Equipos ");
           System.out.println("2. Dar Baja a Equipos ");
           System.out.println("3. Modificar Equipos  ");
           System.out.println("4. SALIR");
           i=sc.nextInt();
           switch(i){//alternativa con respecto al menu
                 case 1: altaEquipos(equipos); break;
                 case 2: bajaEquipos(equipos,partidos); break;
                 case 3: modificarEquipos(equipos); break;
                 default: System.out.println(" ERROR "); break;
           }
           System.out.println(" ");
        }while(i!=4);
    }
    public static void altaEquipos(ArbolAVL equipos){
        Scanner sc=new Scanner(System.in);
        
        System.out.println("ingrese el nombre del Equipo: ");
        String pais=sc.nextLine();
        if(!equipos.pertenece(new Equipo(pais))){//si el equipo NO EXISTE
               System.out.println("ingrese apellido del dt: " );
               String dt=sc.nextLine();
               System.out.println("ingrese la letra del grupo inicial 'A,B,C,D'");
               char grupo=sc.next().charAt(0);
               Equipo equipo=new Equipo(pais,dt,grupo);
               if(grupo=='A' || grupo=='B' || grupo=='C'|| grupo=='D'){
                   if(equipos.insertar(equipo)){
                      Archivo.escribir("EL EQUIPO "+pais+" SE GUARDO");
                   }else{
                       System.out.println("EL EQUIPO "+pais+" YA EXISTE ");
                   }
               }else{
                   System.out.println("ERROR: NO HAY UN GRUPO"+grupo);
               }
               
        }else{
            System.out.println("ESE EQUIPO YA EXISTE");
        }
        System.out.println(" ");
    }
    public static void bajaEquipos(ArbolAVL equipo,MapeoAMuchos partidos){
        Scanner sc=new Scanner(System.in);
        if(!equipo.esVacio()){
            System.out.println(" ingrese el nombre del equipo :");
            String pais=sc.nextLine();
            if(equipo.eliminar(new Equipo(pais))){//lo elimina
                Archivo.escribir("EQUIPO "+pais+" SE ELIMINO DE LA COPA");
            }else{
                System.out.println("EL EQUIPO "+pais+" NO EXISTE");
            }
        }else{
            System.out.println("NO EXISTEN EQUIPOS PARA ELIMINAR");
        }
    }
    public static void modificarEquipos(ArbolAVL equipo){
        Scanner sc=new Scanner(System.in);
        System.out.println("ingrese el nombre del equipo: ");
        String pais=sc.nextLine();
        if(equipo.pertenece(new Equipo(pais))){//si el equipo del pais existe
            int opcion;
            do{
               System.out.println("1. modificar apellido del dt");
               System.out.println("2. SALIR");
               opcion=sc.nextInt();
               Equipo eq= (Equipo) equipo.getElem(new Equipo(pais));
               switch(opcion){
                     case 1: 
                        System.out.println("ingrese apellido: ");
                        String dt=sc.nextLine();
                        eq.setDt(dt);
                     break;
               }
            }while(opcion!= 2);

        }else{
            System.out.println("EL EQUIPO NO JUEGA EN LA COPA AMERICA 2024");
        }
    }
    //ALTA DE PARTIDOS
    public static void altasPartidos(MapeoAMuchos partidos, Grafo mapa,ArbolAVL equipos){
        Scanner sc=new Scanner(System.in);
        if(!partidos.esVacio()){//existen partidos
            System.out.println("ingrese el primer equipo: ");
            String eq1=sc.nextLine();
            System.out.println("ingrese el segundo equipo: ");
            String eq2=sc.nextLine();
            if(!partidos.pertenece(new ClaveP(eq1,eq2))){//si el partido no existe lo crea
                    System.out.println(" ingrese ronda 'GRUPO, CUARTOS, SEMIFINAL, FINAL':");
                    String rond=sc.nextLine();
                    if(rond.equalsIgnoreCase("GRUPO") || rond.equalsIgnoreCase("CUARTOS") || rond.equalsIgnoreCase("SEMIFINAL") || rond.equalsIgnoreCase("FINAL")){
                         System.out.println(" ingrese ciudad: ");
                         String ciudad=sc.nextLine();
                         if(mapa.existeVertice(new Ciudad(ciudad))){
                               System.out.println(" ingrese estadio " );
                               String estadio=sc.nextLine();
                               System.out.println(" ingrese goles del primer equipo: ");
                               int golEq1=sc.nextInt();
                               System.out.println(" ingrese goles del segundo equipo: ");
                               int golEq2=sc.nextInt();
                               if( golEq1 >=0 && 0<= golEq2){
                                     partidos.insertar(new ClaveP(eq1,eq2));//creo la nueva clave partido
                                     partidos.asociar(new ClaveP(eq1,eq2), new Partido(eq1, eq2, rond, ciudad, estadio, golEq1, golEq2));
                                    //ACTUALIZO LOS EQUIPOS QUE JUEGAN EL PARTIDO
                                     Equipo equipo1=(Equipo) equipos.getElem(new Equipo(eq1));
                                     equipo1.setGoles(golEq1);
                                     equipo1.setGolesEnContra(golEq2);
                                     equipo1.setPuntos(golEq1,golEq2);

                                     Equipo equipo2=(Equipo) equipos.getElem(new Equipo(eq2));
                                     equipo2.setGoles(golEq2);
                                     equipo2.setGolesEnContra(golEq1);
                                     equipo2.setPuntos(golEq2,golEq1);
                                     Archivo.escribir("PARTIDO CARGADO :"+eq1+"-"+eq2);

                               }else{
                                     System.out.println("VALORES DE GOLES DEBEN SER MAYORES O IGUALES A 0");
                               }
                         }else{
                            System.out.println("LA CIUDAD "+ciudad+" NO FORMA PARTE DEL MAPA");
                         }
                    }else{
                        System.out.println("EL GRUPO INGRESADO NO EXISTE INGRESE ALGUNA DE LAS SIGUIENTES OPCIONES: 'GRUPO, CUARTOS, SEMIFINAL, FINAL'");
                    }
                   

                    
            }else{
                System.out.println("NO HAY UN PARTIDO ENTRE LOS EQUIPOS "+eq1+" "+eq2);
            }
            
        }else{
            System.out.println(" NO HAY EQUIPOS ");
        }
        System.out.println();
    }
    public static void consultasEquipos(ArbolAVL equipos){
        Scanner sc=new Scanner(System.in);
        if(!equipos.esVacio()){//si hay equipos consulta
            int opcion;
            do{
                System.out.println("1. mostrar datos de un equipo");
                System.out.println("2. listar entre (min y max) alfabeticamente equipos");
                System.out.println("3. SALIR");
                opcion=sc.nextInt();

                switch(opcion){
                    case 1://Dado un país, mostrar puntos ganados, goles a favor y en contra y diferencia de goles
                         System.out.println("ingrese el pais del equipo: ");
                         String pais=sc.next();
                         if(equipos.pertenece(new Equipo(pais))){//el pais del equipo existe
                             Equipo eq=(Equipo) equipos.getElem(new Equipo(pais));
                             System.out.println("puntos: "+eq.getPuntos()+", goles: "+eq.getGoles()+", goles encontra: "+eq.getGolesEnContra()+" diferencia: "+ (eq.getGoles()-eq.getGolesEnContra()));
            
                         }else{
                             System.out.println("EL EQUIPO DEL PAIS NO JUEGA EN LA COPA AMERICA 2024");
                         }
                    break;
                    case 2://Dadas dos cadenas (min y max) devolver todos los equipos cuyo nombre esté alfabéticamente en el rango [min, max].
                         System.out.println("ingrese cadena min: ");
                         String min=sc.nextLine();
                         sc.nextLine();
                         System.out.println("ingrese cadena max: ");
                         String max=sc.nextLine();
                         System.out.println(equipos.moverseEnRango(new Equipo(min),new Equipo(max) ).toString());
                    break;
                }
                System.out.println(" ");//espacio
            }while(opcion!=3);
        }else{
            System.out.println("NO EXISTEN EQUIPOS PARA CONSULTAR");
        }
        System.out.println(" ");
    }


    public static void consultasPartidos(MapeoAMuchos partidos,ArbolAVL equipos){ //Dados 2 equipos, si jugaron algún partido entre sí, mostrar los resultados.

        Scanner sc=new Scanner(System.in);
        if(!partidos.esVacio()){//si hay equipos consulta
            int opcion;
            do{
                System.out.println("1. buscar partido por instancia");
                System.out.println("2. buscar todos los partidos entre 2 equipos");
                System.out.println("3. SALIR");
                opcion=sc.nextInt();
                sc.nextLine();
                System.out.println("ingrese el pais del primer equipo: ");
                String pais1=sc.nextLine();
                sc.nextLine();
                System.out.println("ingrese el pais del segundo equipo: ");
                String pais2=sc.nextLine();
                if(equipos.pertenece(new Equipo(pais1)) && equipos.pertenece(new Equipo(pais2))){//los 2 equipos deben existir
                        ClaveP clave=new ClaveP(pais1,pais2);
                        Lista rango= partidos.obtenerValor(clave);
                        //System.out.println(partidos.funcionHash(clave));
                        if(!rango.estaVacia()){//hay partidos con la clave
                            
                            if(opcion==1){
    
                                sc.nextLine();
                                System.out.println("ingrese instancia del partido 'GRUPO,SEMIFINAL,CUARTOS,FINAL' :");
                                String ronda=sc.nextLine();
                                
                                if(ronda.equalsIgnoreCase("grupo") || ronda.equalsIgnoreCase("cuartos")  || ronda.equalsIgnoreCase("semifinal") ||ronda.equalsIgnoreCase("final")){
                                      int i=1;
                                      while( ! ( ((Partido) rango.recuperar(i) ).getRonda() ).equals(ronda) ){//si no lo encontro que siga buscando
                                          i++;
                                      }
                                      Partido part=(Partido) rango.recuperar(i);
                                      if( (part ).getRonda().equals(ronda)){//si es la ronda 
                                            System.out.println(part.toString());
                                      }else{
                                            System.out.println("NO SE JUGO UN PARTIDO ( "+clave.toString()+") EN LA FASE"+ronda);
                                      }
                                }
                            }else if(opcion==2){
                                System.out.println(rango.toString());
                            }
                        }else{
                                System.out.println(" NO HAY PARTIDO ORGANIZADO CON ESTOS EQUIPOS ");//espacio
                        }
                       
            
                }else{
                    System.out.println("EL EQUIPO "+pais1+" O "+pais2+" NO JUEGAN LA CAPA AMERICA 2024");
                }
                   
                System.out.println(" ");//espacio
            }while(opcion!=3);
        }else{
            System.out.println("NO HAY PARTIDOS ORGANIZADOS");
        }
        System.out.println(" ");//espacio
    }
    public static void consultasViajes(Grafo mapa){
        Scanner sc=new Scanner(System.in);
        if(!mapa.esVacio()){//si hay equipos consulta
            int opcion;
            do{
            System.out.println("1. obtener el viaje mas corto entre 2 ciudades");
            System.out.println("2. obtener el camino que pase por menos ciudades entre ");
            System.out.println("3. SALIR");
            opcion=sc.nextInt();
            sc.nextLine();
            System.out.println("ingrese la primer ciudad: ");
            String ciu1=sc.nextLine();
            sc.nextLine();
            System.out.println("ingrese la segunda ciudad: ");
            String ciu2=sc.nextLine();
            //sc.nextLine();
            
            System.out.println(mapa.existeVertice(new Ciudad(ciu2)));

            if(mapa.existeVertice(new Ciudad(ciu1)) && mapa.existeVertice(new Ciudad(ciu2))){//las Ciudades deben existir
                switch(opcion){
                    case 1://
                        System.out.println(" VIAJE CON MENOR TIEMPO ENTRE "+ciu1+" y "+ciu2);
                        System.out.println(mapa.caminoConMenosPeso(new Ciudad(ciu1), new Ciudad(ciu2)).toString());
                    break;
                    case 2://
                        System.out.println("     VIAJE CON MENOR CANTIDAD DE ESCALAS ENTRE "+ciu1+" y "+ciu2+"    ");
                        System.out.println("REALIZA ESCALA EN LAS SIGUIENTES CIUDADES: ");
                        System.out.println(mapa.listarCaminoMasCorto(new Ciudad(ciu1), new Ciudad(ciu2)).toString());
                    break;
                    
                }
            }else{
                System.out.println("LAS CIUDADES "+ciu1+" O "+ciu2+" NO EXISTEN");
            }
                System.out.println(" ");//espacio
            }while(opcion!=3);
        }else{
            System.out.println("NO INFORMACION DE CIUDADES O VIAJES ALMACENADOS");
        }
        System.out.println(" ");
    }
    public static void mostrarEquiposOrdenados(ArbolAVL equipos){
        if(!equipos.esVacio()){
            Lista clon=equipos.listar();//copio una lista con todos los objetos del arbol
            ArbolAVL equiposGol=new ArbolAVL();
            //NOTA: SI TODOS LOS EQUIPOS TIENEN 0 GOLES INSERTA LA RAIZ SOLAMENTE
            while(!clon.estaVacia()){//vaciar LISTA 
                //System.out.println(clon.recuperar(i));
                 Equipo eq = (Equipo) (clon.recuperar(1));//
                 //System.out.println(eq);
                 EquipoGoles eqGol=new EquipoGoles(eq.getNombre(), eq.getDt(), eq.getGrupo(), eq.getPuntos(), eq.getGoles(), eq.getGolesEnContra());
                 equiposGol.insertar(eqGol);
                 clon.eliminar(1);
                 
            }
            System.out.println(equiposGol.toString());
        }else{
            System.out.println(" NO HAY EQUIPOS PARA ORDENAR ");
        }
        System.out.println(" ");
    }
    public static void mostrarSistema(Grafo mapa,ArbolAVL equipos,MapeoAMuchos partidos){
        System.out.println(" ");//espacio
        System.out.println("               MAPA               ");
        System.out.println(" ");//espacio
        System.out.println(mapa.toString());
        System.out.println(" ");//espacio
        System.out.println("               EQUIPOS              ");
        System.out.println(" ");//espacio
        System.out.println(equipos.toString());
        System.out.println(" ");//espacio
        System.out.println("               PARTIDOS              ");
        System.out.println(" ");//espacio
        System.out.println(partidos.toString());
        System.out.println(" ");//espacio

    }
}