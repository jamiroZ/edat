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
          this.dt=" ";
          this.grupo=' ';
          this.puntos=0;
          this.goles=0;
          this.golesEnContra=0;
    }
    public Equipo(String nombre,String dt,char grupo,int puntos,int goles,int golesEnContra){
        this.nombre=nombre;
        this.dt=dt;
        this.grupo=grupo;
        this.puntos=puntos;
        this.goles=goles;
        this.golesEnContra=golesEnContra;
    }

    //Nombre
    public String getNombre(){
          return this.nombre;
    }
    //Director Tecnico (dt)
    public String getDt(){
        return this.dt;
    }
    public void setDt(String dt){
        this.dt=dt;
    }
    //GRUPOS
    public char getGrupo(){
        return this.grupo;
    }
    //PUNTOS
    public int getPuntos(){
        return this.puntos;
    }
    public void setPuntos(int puntos){
        this.puntos=this.puntos + puntos;
    }
    //GOLES
    public int getGoles(){
        return this.goles;
    }
    public void setGoles(int goles){
        this.goles=this.goles + goles;
    }
    //GOLES EN CONTRA
    public int getGolesEnContra(){
        return this.golesEnContra;
    }
    public void setGolesEnContra(int en){
        this.golesEnContra=this.golesEnContra + en;
    }
    
}
