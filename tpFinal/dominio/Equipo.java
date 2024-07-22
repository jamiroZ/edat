package tpFinal.dominio;

public class Equipo implements Comparable{
    private String nombre;//nombre del pais del equipo
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
    public Equipo(String nombre,String dt,char grupo){
        this.nombre=nombre;
        this.dt=dt;
        this.grupo=grupo;
        this.puntos=0;
        this.goles=0;
        this.golesEnContra=0;
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
    public void setGrupo(char at){
        this.grupo=at;
    }
    //PUNTOS
    public int getPuntos(){
        return this.puntos;
    }
    
    // metodo practico con respecto a los partidos
    public void setPuntos(int golEq1, int golEq2){
       if(golEq1> golEq2){//si gano suma puntos (3)
           this.puntos=this.puntos +3;
       }if(golEq1==golEq2){//si empataron  suma 1 punto
        this.puntos=this.puntos +1;
       }
       
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
    public int compareTo(Object otroEquipo){
        return this.nombre.compareToIgnoreCase( ((Equipo) otroEquipo).getNombre());//si el equipo1 es mayor que otroEquipo retorna 1,sino -1 y si son iguales 0
    }
    public String toString(){
        return "Pais: "+nombre;
    }
    
}
