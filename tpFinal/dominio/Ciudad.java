package tpFinal.dominio;

import java.util.Objects;

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
     @Override
    public boolean equals(Object obj) {
        // Verificar si es la misma instancia
        if (this == obj) {
            return true;
        }
        // Verificar si el objeto es nulo o de distinto tipo
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // Cast del objeto a Ciudad para comparar
        Ciudad otraCiudad = (Ciudad) obj;
        // Comparar las claves (en este caso, el nombre)
        return nombre.equals(otraCiudad.nombre);
    }

    // Método hashCode (opcional, pero recomendado si se sobrescribe equals)
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

}