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
}
