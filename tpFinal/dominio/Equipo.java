package tpFinal.dominio;

public class Equipo {
    private String nombre;//nombre del pais 
    private String dt;
    private char grupo;//los grupos son A,B,C,D
    private int puntos;
    private int goles;
    private int golesEnContra;
    public Equipo(String nombre){
          this.nombre=nombre;
          this.dt="";
          this.grupo=' ';
          this.puntos=0;
          this.goles=0;
          this.golesEnContra=0;
    }
    public Equipo(String nombre,String dt,char grupo,int puntos,int goles,int golesEnContra){
        this.nombre=nombre;
        this.dt=dt;
        this.grupo=grupo;
        this.puntos=0;
        this.goles=0;
        this.golesEnContra=0;
  }
}
