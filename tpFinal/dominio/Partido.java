package tpFinal.dominio;
public class Partido {
     private ClaveP clave;
     private String ronda;//grupo,cuartos,semi,final
     private Ciudad evento;
     private String estadio;
     private int eq1Goles;
     private int eq2Goles;
     public Partido(String eq1,String eq2, String ronda,Ciudad evento, String estadio ,int eq1Goles,int eq2Goles){
          int resultado=eq1.compareTo(eq2);
          if(resultado < 0){//el equipo1 esta antes en orden alfabetico
               this.clave=new ClaveP(eq1,eq2);//creo objeto clave
               this.eq1Goles=eq1Goles;
               this.eq2Goles=eq2Goles;
          }else{//el equipo2 esta antes en orden alfabetico
               this.clave=new ClaveP(eq2,eq1);//creo objeto clave
               this.eq1Goles=eq2Goles;
               this.eq2Goles=eq1Goles;
          }
          if(ronda.equalsIgnoreCase("grupo") || ronda.equalsIgnoreCase("cuartos")  || ronda.equalsIgnoreCase("semifinal") ||ronda.equalsIgnoreCase("final")){
             this.ronda=ronda;
          }
          
          this.evento=evento;
          this.estadio=estadio;
     }
     public ClaveP getClaveP(){
          return this.clave;
     }
     public String getRonda(){
          return this.ronda;
     }
     public void setRonda(String ronda){
          if(ronda.equalsIgnoreCase("grupo") || ronda.equalsIgnoreCase("cuartos")  || ronda.equalsIgnoreCase("semifinal") ||ronda.equalsIgnoreCase("final")){
               this.ronda=ronda;
          }
     }
     public String getEstadio(){
          return this.estadio;
     }
     public void setEstadio(String estadio){
          this.estadio=estadio;
     }
     public String getGoles(){//retorna los goles de ambos equipos en txt
          return eq1Goles+", "+eq2Goles;
     }
     public String toString(){
          return clave+": "+eq1Goles+", "+eq2Goles+", "+ronda+", "+ evento.toString()+", "+estadio;
     }
}
