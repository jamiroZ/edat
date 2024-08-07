package tpFinal.dominio;
public class Partido {
     private ClaveP clave;
     private String ronda;//grupo,cuartos,semi,final
     private String ciudad;
     private String estadio;
     private int eq1Goles;
     private int eq2Goles;
     public Partido(String eq1,String eq2, String ronda,String evento, String estadio ,int eq1Goles,int eq2Goles){
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
          this.ronda=ronda;
          this.ciudad=evento;
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
     public String getCiudad(){
          return this.ciudad;
     }
     public void setCiudad(String otraCiudad){
          this.ciudad= otraCiudad;
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
          return "goles: "+eq1Goles+", goles encontra: "+eq2Goles+", ronda: "+ronda+",Ciudad: "+ciudad+", estadio: "+estadio;
     }
     @Override
     public boolean equals(Object obj) {
             Partido otra = (Partido) obj;
             //falta comparar clave partido ,usar equals de claveP
             return this.ronda.equalsIgnoreCase(otra.ronda) && ( ((ClaveP) this.clave).equals(  otra.getClaveP()));
     }
     
}
