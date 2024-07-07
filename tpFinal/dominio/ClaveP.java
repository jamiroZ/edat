package tpFinal.dominio;

/*CLASE UTILIZADA COMO CLAVE PARA ALMACENAR
 *RESULTADOS DE LOS PARTIDOS

 *UTILIZANDO LOS 2 EQUIPOS PRINCIPALES

 *CONCIDERANDO EL ORDEN ALFABETICO
 */
public class ClaveP {
    private String clave;
    public ClaveP(String equipo1,String equipo2){
        int resultado=equipo1.compareTo(equipo2);
        if(resultado < 0){//el equipo1 esta antes en orden alfabetico
            this.clave=equipo1+", "+equipo2;
        }else{//el equipo2 esta antes en orden alfabetico
            this.clave=equipo2+", "+equipo1;
        }
    }

    
}
