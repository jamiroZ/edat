package tpFinal.dominio;

public class Ciudad{
    private String nombre;//nombre unico de la ciudad
    private Boolean sede;//si o no 
    private Boolean alojamiento;//cant de alojamientos
    public Ciudad(String nombre){
         this.nombre=nombre;
         this.sede=false;
         this.alojamiento=false;
    }
    public Ciudad(String nombre,Boolean sede,Boolean alojamiento){
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
    public Boolean getAlojamiento(){
        return this.alojamiento;
    }
    public void setAlojamiento(Boolean alojamiento){
        this.alojamiento=alojamiento;
    }
    public String toString(){
        String sed="No es sede",aloja="No hay alojamiento";
        if(sede){//si es sede de un partido
             sed="es sede";
        }
        if(alojamiento){//si tiene alojamiento
             aloja="hay alojamiento";
        }
        return nombre ;
    }
    public boolean equals(Object obj) {
         Ciudad otraCiudad=(Ciudad) obj;
         return this.nombre.equals(otraCiudad.getNombre());//comparo los nombres
    }
   

}