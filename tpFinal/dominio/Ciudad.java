package tpFinal.dominio;

public class Ciudad{
    private String nombre;//nombre unico de la ciudad
    private Boolean sede;//si o no 
    private int alojamiento;//cant de alojamientos
    public Ciudad(String nombre){
         this.nombre=nombre;
         this.sede=null;
         this.alojamiento=0;
    }
    public Ciudad(String nombre,Boolean sede,int alojamiento){
        this.nombre=nombre;
        this.sede=sede;
        this.alojamiento=alojamiento;
    }
    public String getNombre(){
        return this.nombre;
    }
    public Boolean getSede(){
        return this.sede;
    }
    public void setSede(Boolean sede){
        this.sede=sede;
    }
    public int getAlojamiento(){
        return this.alojamiento;
    }
    public void setAlojamiento(int alojamiento){
        this.alojamiento=alojamiento;
    }

}