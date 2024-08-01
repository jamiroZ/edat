package tpFinal.dominio;


public class EquipoGoles implements Comparable{
    private String nombre;//nombre del pais del equipo
    private String dt;
    private char grupo;//los grupos son A,B,C,D
    private int puntos;
    private int goles;
    private int golesEnContra;
    public EquipoGoles(String nombre){
          this.nombre=nombre;
          this.dt=" ";
          this.grupo=' ';
          this.puntos=0;
          this.goles=0;
          this.golesEnContra=0;
    }
    public EquipoGoles(String nombre,String dt,char grupo,int puntos,int goles,int golesEnContra){
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
    public void setGrupo(char at){
        this.grupo=at;
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
    public int compareTo(Object otroEquipo){//comparo primero goles y si son iguales, despues los nombres
        int ret=(Integer.compare(this.goles, (((EquipoGoles) otroEquipo).getGoles()) ));
        if(ret==0){//si tienen la misma cantidad de goles compara por nombres
             ret=this.nombre.compareToIgnoreCase( ((EquipoGoles) otroEquipo).getNombre());//si el equipo1 es mayor que otroEquipo retorna 1,sino -1 y si son iguales 0
        }
        //System.out.println(ret);
        return ret;//comparo  cual equipo tiene mayor cant goles
    }
    //FALTA EQUALS
    public String toString(){
        return "Pais: "+nombre;
    }
    
}

