package tpFinal.dominio;

//importo para uso de txt ingreso
import java.util.Scanner;

import tpFinal.estructuras.conjuntistas.ArbolAVL;
import tpFinal.estructuras.conjuntistas.MapeoAMuchos;
import tpFinal.estructuras.grafoEtiquetado.Grafo;
import tpFinal.txt.Archivo;

public class CopaAmerica{
    public static void main(String[] args) {
        testingCopaAmerica();
    }
    public static void testingCopaAmerica(){
          //ESCTRUCTURAS USADAS
          Grafo x=new Grafo();
          Grafo mapa=new Grafo();//grafo etiquetado
          MapeoAMuchos partidos=new MapeoAMuchos();//tabla hash mapeado a muchos
          ArbolAVL equipos=new ArbolAVL();
          System.out.println("EXISTE"+mapa.existeVertice(new Ciudad("LAS VEGAS")));
          System.out.println("OBTENER:"+mapa.obtenerElem(new Ciudad("LAS VEGAS")));
          Archivo.crearLog();
          Archivo.leerArchivo(mapa, equipos, partidos);//leo el txt y cargo en las estructuras la informacion 
          //VALOR NUMERICO PARA USO DEL MENU
          int opcion;
          do{ 
               opcion=menu();//MENU PRINCIPAL DE OPCIONES
               switch(opcion){

                     case 1: opcionesCiudades(mapa,partidos); break;

                     case 2: opcionesEquipos(equipos,partidos); break;

                     case 3: altasPartidos(); break;

                     case 4: consultasEquipos(); break;

                     case 5: consultasPartidos(); break;

                     case 6: consultasViajes(); break;

                     case 7: mostrarEquiposOrdenados(); break;

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
                           while(i< partidos.getCant()){//reviso todos los partidos
                               if(true){//todos los partidos que tienen esa ciudad

                               }
                               i++;
                           }
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
                System.out.println("No exist esa ciudad en el mapa");
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
        }while(i!=4);
    }
    public static void altaEquipos(ArbolAVL equipos){
        Scanner sc=new Scanner(System.in);
        
        System.out.println("ingrese el nombre del Equipo: ");
        String pais=sc.nextLine();
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
    }
    public static void bajaEquipos(ArbolAVL equipo,MapeoAMuchos partidos){
        Scanner sc=new Scanner(System.in);
        if(!equipo.esVacio()){
            System.out.println(" ingrese el nombre del equipo :");
            String pais=sc.nextLine();
            if(equipo.eliminar(new Equipo(pais))){//lo elimina
                
            }else{
                System.out.println("EL EQUIPO "+pais+" NO EXISTE");
            }
        }else{
            System.out.println("NO EXISTEN EQUIPOS PARA ELIMINAR");
        }
    }
    public static void modificarEquipos(ArbolAVL equipo){

    }
    //ALTA DE PARTIDOS
    public static void altasPartidos(){
        
    }
    public static void consultasEquipos(){
        
    }
    //METODOS DE CONSULTA

    public static void consultasPartidos(){
        
    }
    public static void consultasViajes(){
        
    }
    public static void mostrarEquiposOrdenados(){
        
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