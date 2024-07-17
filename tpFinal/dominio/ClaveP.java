package tpFinal.dominio;

/*CLASE UTILIZADA COMO CLAVE PARA ALMACENAR
 *RESULTADOS DE LOS PARTIDOS

 *UTILIZANDO LOS 2 EQUIPOS PRINCIPALES

 *CONCIDERANDO EL ORDEN ALFABETICO
 */
public class ClaveP {
    private String equipo1;
    private String equipo2;
    public ClaveP(String eq1,String eq2){
       
        int resultado=eq1.compareTo(eq2);
        if(resultado < 0){//el equipo1 esta antes en orden alfabetico
            this.equipo1=eq1;
            this.equipo2=eq2;
        }else{//el equipo2 esta antes en orden alfabetico
            this.equipo1=eq2;
            this.equipo2=eq1;
        }
       
    }
    public String getEq1(){
        return this.equipo1;
    }
    public String getEq2(){
        return this.equipo2;
    }

    public String toString(){
        return this.equipo1+" - "+this.equipo2;
    }
    @Override
    public boolean equals(Object obj) {
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }else{
             // Convertir obj a la clase actual
           ClaveP otra = (ClaveP) obj;
            // Comparar los atributos que componen la clave doble
            return this.equipo1.equalsIgnoreCase(otra.equipo1) && this.equipo2.equalsIgnoreCase(otra.equipo2) ;//campara equipo 1 y equipo2 deben ser iguales
        }
       
    }
}
