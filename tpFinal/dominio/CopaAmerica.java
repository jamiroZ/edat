package tpFinal.dominio;

//importo para uso de txt ingreso
import java.util.Scanner;

import tpFinal.estructuras.conjuntistas.ArbolAVL;
import tpFinal.estructuras.conjuntistas.MapeoAMuchos;
import tpFinal.estructuras.grafoEtiquetado.Grafo;
import tpFinal.estructuras.lineales.Lista;
import tpFinal.txt.Archivo;
public class CopaAmerica{
    public static void main(String[] args) {
        testingCopaAmerica();
    }
    public static void testingCopaAmerica(){
          //ESCTRUCTURAS USADAS
          Grafo mapa=new Grafo();//grafo etiquetado
          MapeoAMuchos partidos=new MapeoAMuchos();//tabla hash mapeado a muchos
          ArbolAVL equipos=new ArbolAVL();
         
          Archivo.crearLog();
          Archivo.leerArchivo(mapa, equipos, partidos);//leo el txt y cargo en las estructuras la informacion 
          //VALOR NUMERICO PARA USO DEL MENU
          int opcion;
          do{ 
               opcion=menu();//MENU PRINCIPAL DE OPCIONES
               switch(opcion){

                     case 1: opcionesCiudades(mapa,partidos); break;

                     case 2: opcionesEquipos(equipos,partidos); break;

                     case 3: altasPartidos(partidos); break;

                     case 4: consultasEquipos(equipos); break;

                     case 5: consultasPartidos(partidos, equipos); break;

                     case 6: consultasViajes(mapa); break;

                     case 7: mostrarEquiposOrdenados(equipos); break;

                     case 8: mostrarSistema(mapa,equipos,partidos); break;
                     
                     default: System.out.println(" ERROR "); break;
               }
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
        System.out.println("                                          \n");
        int ret=sc.nextInt();
        return ret;
    }
    public static void opcionesCiudades(Grafo mapa,MapeoAMuchos partidos){
        Scanner sc=new Scanner(System.in);
         int i;
        do{ 
            //MENU 
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
       }while(i!=4);
    }
    //METODOS DE ALTA,BAJA Y MODIFICACION DE CIUDAD
    public static void altaCiudades(Grafo mapa){
         Scanner sc=new Scanner(System.in);
         Boolean alojamiento,sede;
         System.out.println("ingrese el nombre de la ciudad: ");
         String nombre=sc.nextLine();
         System.out.println(mapa.existeVertice(new Ciudad(nombre)));

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
            System.out.println(mapa.insertarVertice(new Ciudad(nombre,alojamiento,sede)));
               System.out.println("LA CIUDAD "+nombre+" SE UBICO EN EL MAPA");
         }else{
            System.out.println("LA CIUDAD "+nombre+" YA EXISTE EN EL MAPA");
         }
    }
    public static void bajaCiudades(Grafo mapa,MapeoAMuchos partidos){
        Scanner sc=new Scanner(System.in);
        if(!mapa.esVacio()){
            System.out.println("Ingrese el nombre de la ciudad para darle de baja: ");
            String nombre=sc.nextLine();
            if(mapa.existeVertice(new Ciudad(nombre) ) ){//EXISTE LA CIUDAD
                  if(((Ciudad) mapa.obtenerElem( new Ciudad(nombre))).getSede()){// si es sede de un partido hay que re ubicarlo
                           System.out.println("la ciudad es sede de un partido");
                           System.out.println("ingrese ciudad donde re ubicar el partido: ");
                           nombre=sc.nextLine();
                           int i=0;
                           
                  }
                  mapa.eliminarVertice(new Ciudad(nombre));//ELIMINO LA CIUDAD
                  System.out.println("ciudad"+nombre+" eliminada");
            }else{//NO EXISTE
                System.out.println("La ciudad No existe en el mapa para la copa");
            }
        }else{//CASO MAPA VACIO
            System.out.println("NO HAY CIUDADES PARA ELIMINAR");
        }
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
               System.out.println(equipos.pertenece(equipo));
               if(equipos.insertar(equipo)){
                     System.out.println("EL EQUIPO "+pais+" SE GUARDO");
               }else{
                    System.out.println("EL EQUIPO "+pais+" YA EXISTE ");
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
            String pais=" "+sc.nextLine();
            if(equipo.eliminar(new Equipo(pais))){//lo elimina
                System.out.println("EQUIPO "+pais+" SE ELIMINO DE LA COPA");
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
               System.out.println("2. modificar grupo 'A,B,C,D' ");
               System.out.println("3. modificar modificar puntaje");
               System.out.println("4. modificar goles hechos");
               System.out.println("5. modificar goles en contra");
               System.out.println("6. SALIR");
               opcion=sc.nextInt();
               Equipo eq= (Equipo) equipo.getElem(new Equipo(pais));
               switch(opcion){
                     case 1: 
                        System.out.println("ingrese apellido: ");
                        String dt=sc.nextLine();
                        eq.setDt(dt);
                     break;
                     case 2: 
                        System.out.println("ingrese letra del grupo 'A,B,C o D': ");
                        char grupo=sc.next().charAt(0);
                        eq.setGrupo(grupo);
                     break;
                     case 3: 
                        System.out.println("ingrese si suma o resta puntaje: ");
                        int punt=sc.nextInt();
                        eq.setPuntos(punt);
                     break;
                     case 4: 
                        System.out.println("cant goles: ");
                        int gol=sc.nextInt();
                        eq.setGoles(gol);
                     break;
                     case 5: 
                        System.out.println("cant goles encontra: ");
                        int encontra=sc.nextInt();
                        eq.setGolesEnContra(encontra);
                     break;
               }
            }while(opcion!= 6);

        }else{
            System.out.println("EL EQUIPO NO JUEGA EN LA COPA AMERICA 2024");
        }
    }
    //ALTA DE PARTIDOS
    public static void altasPartidos(MapeoAMuchos partidos){
        Scanner sc=new Scanner(System.in);
        if(!partidos.esVacio()){//existen partidos
            System.out.println("ingrese el primer equipo: ");
            String eq1=sc.nextLine();
            System.out.println("ingrese el segundo equipo: ");
            String eq2=sc.nextLine();
            if(partidos.pertenece(new ClaveP(eq1,eq2))){//si el partido no existe lo crea
                    partidos.insertar(new ClaveP(eq1,eq2));
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
                         
                         String pais=sc.nextLine();
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
                         System.out.println("ingrese cadena max: ");
                         String max=sc.nextLine();
                         System.out.println(equipos.moverseEnRango(min, max).toString());
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
                System.out.println("1. buscar partido");
                System.out.println("2. SALIR");
                opcion=sc.nextInt();
                if(opcion==1){

                         System.out.println("ingrese el pais del primer equipo: ");
                         String pais1=sc.nextLine();
                         System.out.println("ingrese el pais del segundo equipo: ");
                         String pais2=sc.nextLine();
                        
                         if(equipos.pertenece(new Equipo(pais1)) && equipos.pertenece(new Equipo(pais2))){//los 2 equipos deben existir
                             ClaveP clave=new ClaveP(pais1,pais2);
                             Lista rango= partidos.obtenerValor(clave);

                             if(!rango.estaVacia()){//hay partidos con la clave
                                  System.out.println("ingrese instancia del partido 'GRUPO,SEMIFINAL,CUARTOS,FINAL' :");
                                  String ronda=sc.nextLine();

                                  if(ronda.equalsIgnoreCase("grupo") || ronda.equalsIgnoreCase("cuartos")  || ronda.equalsIgnoreCase("semifinal") ||ronda.equalsIgnoreCase("final")){
                                      int i=1;
                                      while( ! ( ((Partido) rango.recuperar(i) ).getRonda() ).equals(ronda) ){//si no lo encontro que siga buscando
                                          i++;
                                      }
                                      if( ((Partido) rango.recuperar(i) ).getRonda().equals(ronda)){//si es la ronda 

                                      }else{
                                        System.out.println("NO SE JUGO UN PARTIDO ( "+clave.toString()+") EN LA FASE"+ronda);
                                      }
                                  }
                             }else{
                                System.out.println(" NO HAY PARTIDO ORGANIZADO CON ESTOS EQUIPOS ");//espacio
                             }
            
                         }else{
                            System.out.println("EL EQUIPO "+pais1+" O "+pais2+" NO JUEGAN LA CAPA AMERICA 2024");
                         }
                   
                }
                System.out.println(" ");//espacio
            }while(opcion!=2);
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
            System.out.println("3. el vuelo mas corto entre 2 ciudades que NO haga escala en una ciudad");
            System.out.println("4. todos los vuelos posibles entre 2 ciudades");
            System.out.println("5. SALIR");
            opcion=sc.nextInt();
            System.out.println("ingrese la primer ciudad: ");
            String ciu1=sc.nextLine();
            System.out.println("ingrese la segunda ciudad: ");
            String ciu2=sc.nextLine();
            if(mapa.existeVertice(new Ciudad(ciu1)) && mapa.existeVertice(new Ciudad(ciu2))){//las iudades deben existir
                switch(opcion){
                    case 1://
                        
                    break;
                    case 2://
                        
                    break;
                    case 3://
                        
                    break;
                    case 4://
                        
                    break;
                }
            }else{
                System.out.println("LAS CIUDADES "+ciu1+" O "+ciu2+" NO EXISTEN");
            }
                System.out.println(" ");//espacio
            }while(opcion!=5);
        }else{
            System.out.println("NO INFORMACION DE CIUDADES O VIAJES ALMACENADOS");
        }
        System.out.println(" ");
    }
    public static void mostrarEquiposOrdenados(ArbolAVL equipos){
        
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