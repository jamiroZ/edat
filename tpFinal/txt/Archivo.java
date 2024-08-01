package tpFinal.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
		FileWriter fichero = null;//
        PrintWriter pw = null;

       // System.out.println(entrada);

        try{
            fichero = new FileWriter("C:\\Users\\jamir\\OneDrive\\Documentos\\facultad\\segundoAño\\edat\\EDAT\\edat-1\\tpFinal\\txt\\log.txt", true);
            pw = new PrintWriter(fichero);//para escribir en el archivo atraves del fichero

            pw.println(entrada);
        } catch (Exception e) {//excepción que pueda ocurrir durante la apertura, escritura o cierre del archivo 
            e.printStackTrace();
        } finally {//cierro el archivo
           try {
              if (fichero != null)
                  fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}
    public static void leerArchivo(Grafo mapa,ArbolAVL equipos,MapeoAMuchos partidos){
        try{//FileReader y BufferedReader se inicializan dentro de este bloque
            FileReader archivo;//se usa para leer caracteres del archivo
            archivo=new FileReader("C:\\Users\\jamir\\OneDrive\\Documentos\\facultad\\segundoAño\\edat\\EDAT\\edat-1\\tpFinal\\txt\\datos.txt");
            BufferedReader lector=new BufferedReader(archivo);//bufer de lectura

            String linea; //lee linea por linea del archivo y la guarda en la variable

            while(( linea = lector.readLine() ) != null ){//se repite hasta que se alcanza el final del archivo (null).

                //if (!linea.trim().isEmpty()) { // Verificar si la línea no está vacía
                    chequearLinea(linea, mapa, equipos, partidos);
                //}R
            }
            lector.close();
            escribir("INFO DE COPA AMERICA CARGADA");//
            //System.out.println(" ");//espacio
        }catch (FileNotFoundException exception){//caso de excepcion
             System.err.println("ERROR: "+exception.getMessage());
        }catch(IOException exception){
             System.err.println("error leyendo  ARCHIVO");
        }

    }
    public static void chequearLinea(String linea, Grafo mapa, ArbolAVL equipos,MapeoAMuchos partidos){
        StringTokenizer atributo = new StringTokenizer(linea, ";"); // separa los atributos de la linea
 
        switch (atributo.nextToken().trim()){
            case "C"://Ciudad(vertices del grafo)
                //Ciudad: (nombre; Disponibilidad de alojamiento ; sede de la copa )
                String nombre = atributo.nextToken().trim();//NOMBRE DE LA CIUDAD
                Boolean alojamiento = atributo.nextToken().trim().equalsIgnoreCase("TRUE");//SI HAY ALOJAMIENTO O NO
                Boolean sede = atributo.nextToken().trim().equalsIgnoreCase("TRUE");//SI ES SEDE DE LA COPA O NO
                Ciudad ciudad=new Ciudad(nombre,sede, alojamiento);
                 if(mapa.insertarVertice(ciudad)){
                    escribir("CIUDAD CARGADA: "+nombre);
                }
                break;
            case "R"://RUTA aerea entre 2 ciudades(arcos entre nodos del grafo)
                //Ruta: ( Ciudad 1, Ciudad 2, tiempo)
                String ciudad1= atributo.nextToken().trim();
                String ciudad2= atributo.nextToken().trim();
                String par=atributo.nextToken().trim();
                int etiqueta= Integer.parseInt( par );//casteo a entero
                //System.out.println(ciudad1+ciudad2+par);
                Double cast= (double) etiqueta;
                //si las 2 ciudades existen y no existe ya una ruta la crea
                if(!mapa.insertarArco((Object) new Ciudad(ciudad1),(Object) new Ciudad(ciudad2), cast)){
                   escribir("RUTA CARGADA: ENTRE "+ciudad1+" Y "+ciudad2+" TIEMPO "+etiqueta);
                }
                break;
            case "P"://PARTIDO
                //Partido:(equipo1, equipo2, instancia, ciudad, estadio, golesEq1 ,golesEq2)
                
                String eq1=atributo.nextToken().trim();
                String eq2=atributo.nextToken().trim();
                if(equipos.pertenece(new Equipo(eq1)) && equipos.pertenece(new Equipo(eq1)) ){

                    String ins=atributo.nextToken().trim();
                    String ciu=atributo.nextToken().trim();
                    if(mapa.existeVertice(new Ciudad(ciu))){
                        String estadio=atributo.nextToken().trim();

                        String part=atributo.nextToken().trim();
                        int golEq1=Integer.parseInt(part);

                        String part2=atributo.nextToken().trim();
                        int golEq2=Integer.parseInt(part2);
                        //ACTUALIZO LOS EQUIPOS QUE JUEGAN EL PARTIDO
                        Equipo equipo1=(Equipo) equipos.getElem(new Equipo(eq1));
                        equipo1.setGoles(golEq1);
                        equipo1.setGolesEnContra(golEq2);
                        equipo1.setPuntos(golEq1, golEq2);

                        Equipo equipo2=(Equipo) equipos.getElem(new Equipo(eq2));
                        equipo2.setGoles(golEq2);
                        equipo2.setGolesEnContra(golEq1);
                        equipo2.setPuntos(golEq2, golEq1);
                        //CREO EL PARTIDO 
                        ClaveP clave=new ClaveP(eq1,eq2);
                        //System.out.println(clave.toString());
                        partidos.insertar(clave);//inserta el dominio (clave del Partido: nombre eq1 y eq2
                        if(partidos.asociar( clave, new Partido(eq1, eq2, ins, ciu, estadio, golEq1, golEq2 ))){//relaciona el partido con su clave si existe
                             escribir("PARTIDO CARGADO: "+clave.toString());
                        }
                   }
                }
                break;
            case "E"://EQUIPO
                //Equipo:(nombre del pais, apellido del dt, grupo inicial(A,B,C,D), los puntos se cargan a parte despues de terminado el partido)
                String pais = atributo.nextToken().trim();
                String dt = atributo.nextToken().trim();
                char grupo= atributo.nextToken().trim().charAt(0);
                if(grupo=='A' ||grupo=='B' || grupo=='C' || grupo=='D'){//EL GRUPO TIENE QUE SER UNO DE ESTOS 4
                    Equipo equipo=new Equipo(pais,dt,grupo);
                    if(equipos.insertar(equipo) ){
                          escribir("EQUIPO CARGADO: "+pais);
                    }
                }
                break;
        }
    }
   
    
}
