package tpFinal.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//IMPORTO LOS TDA QUE USO
import tpFinal.dominio.Ciudad;
import tpFinal.dominio.ClaveP;
import tpFinal.dominio.Equipo;
import tpFinal.dominio.Partido;
import tpFinal.estructuras.conjuntistas.ArbolAVL;
import tpFinal.estructuras.conjuntistas.MapeoAMuchos;
import tpFinal.estructuras.grafoEtiquetado.Grafo;
public class Archivo {
    
    public static void crearLog(){
        try {
            PrintWriter log = new PrintWriter(new File("C:\\Users\\jamir\\OneDrive\\Documentos\\facultad\\segundoAño\\edat\\EDAT\\edat-1\\tpFinal\\txt\\log.txt"));//crea nuevo archivo de registro
        } catch (IOException e) {
            System.out.println("Error al crear el archivo :" + e.getMessage());
        }
    }
    public static void escribir(String entrada) {
		FileWriter fichero = null;
        PrintWriter pw = null;

        System.out.println(entrada);

        try{
            fichero = new FileWriter("C:\\Users\\jamir\\OneDrive\\Documentos\\facultad\\segundoAño\\edat\\EDAT\\edat-1\\tpFinal\\txt\\log.txt", true);
            pw = new PrintWriter(fichero);

            pw.println(entrada);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
              if (fichero != null)
                  fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}
    
    public static void leerArchivo(Grafo mapa,ArbolAVL equipos,MapeoAMuchos partidos){
        FileReader archivo;//se usa para leer caracteres del archivo

        try{//FileReader y BufferedReader se inicializan dentro de este bloque
            archivo=new FileReader("C:\\Users\\jamir\\OneDrive\\Documentos\\facultad\\segundoAño\\edat\\EDAT\\edat-1\\tpFinal\\txt\\datos.txt");
            BufferedReader lector=new BufferedReader(archivo);//bufer de lectura

            String linea; //lee linea por linea del archivo y la guarda en la variable

            while(( linea= lector.readLine() ) != null ){//se repite hasta que se alcanza el final del archivo (null).

                 chequearLinea(linea, mapa, equipos, partidos);
            }
            archivo.close();
            System.out.println("INFO DE COPA AMERICA CARGADA");
        }catch (Exception e){//caso de excepcion
             System.out.println("ERROR: "+e.getMessage());
        }

    }
    public static void chequearLinea(String linea, Grafo mapa, ArbolAVL equipos,MapeoAMuchos partidos){
        StringTokenizer parte = new StringTokenizer(linea, ":"); // separador del objeto E,C,R,P
        StringTokenizer atributo = new StringTokenizer(linea.substring(2), ";"); // separa los atributos de la linea
    
        
        switch (parte.nextToken()){
            case "C"://Ciudad(vertices del grafo)
               
                //Ciudad: (nombre; Disponibilidad de alojamiento ; sede de la copa )
                String nombre = atributo.nextToken();//NOMBRE DE LA CIUDAD
                Boolean alojamiento = atributo.nextToken().equalsIgnoreCase("TRUE");//SI HAY ALOJAMIENTO O NO
                Boolean sede = atributo.nextToken().equalsIgnoreCase("TRUE");//SI ES SEDE DE LA COPA O NO
                Ciudad ciudad=new Ciudad(nombre,sede, alojamiento);
                if(mapa.insertarVertice(ciudad)){
                    escribir("CIUDAD CARGADA: "+nombre);
                }
                break;
            case "R"://RUTA aerea entre 2 ciudades(arcos entre nodos del grafo)
                //Ruta: ( Ciudad 1, Ciudad 2, tiempo)
                String ciudad1= atributo.nextToken();
                String ciudad2= atributo.nextToken();
                int etiqueta=Integer.parseInt(atributo.nextToken());//casteo a entero
                //si las 2 ciudades existen y no existe ya una ruta la crea
                if(mapa.insertarArco(new Ciudad(ciudad1), new Ciudad(ciudad2), etiqueta)){
                    escribir("RUTA CARGADA: ENTRE "+ciudad1+"Y"+ciudad2+" TIEMPO "+etiqueta);
                }
                break;
            case "P"://PARTIDO
                //Partido:(equipo1, equipo2, instancia, ciudad, estadio, golesEq1 ,golesEq2)
                String eq1=atributo.nextToken();
                String eq2=atributo.nextToken();

                String ins=atributo.nextToken();
                String ciu=atributo.nextToken();
                String estadio=atributo.nextToken();
                int golEq1=Integer.parseInt(atributo.nextToken());
                int golEq2=Integer.parseInt(atributo.nextToken());
                ClaveP clave=new ClaveP(eq1,eq2);
                System.out.println(mapa.existeVertice(new Ciudad(ciu)));
                if(mapa.existeVertice(new Ciudad(ciu))){//si la ciudad donde se juega existe

                    System.out.println(partidos.insertar(clave));//inserta el dominio (clave del Partido: nombre eq1 y eq2)
                    if(partidos.asociar(clave, new Partido(eq1, eq2, ins, ciu, estadio, golEq1, golEq2 ))){//relaciona el partido con su clave si existe
                         System.out.println("PARTIDO CARGADO: "+clave.toString());
                    }
                }
                break;
            case "E"://EQUIPO
                //Equipo:(nombre del pais, apellido del dt, grupo inicial(A,B,C,D), los puntos se cargan a parte despues de terminado el partido)
                String pais = atributo.nextToken();
                String dt = atributo.nextToken();
                char grupo= atributo.nextToken().charAt(1);
                if(grupo=='A' ||grupo=='B' || grupo=='C' || grupo=='D'){//EL GRUPO TIENE QUE SER UNO DE ESTOS 4
                    Equipo equipo=new Equipo(pais,dt,grupo);
                    if(equipos.insertar(equipo) ){
                          System.out.println("EQUIPO CARGADO: "+pais);
                    }
                }
                break;
        }
    }
   
    
}
