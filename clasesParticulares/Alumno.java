package clasesParticulares;
/**
 *
 
@author silvi*/
public class Alumno {
    private String legajo;
    private String nombre;
    private String apellido;
    private String tipo;
    private int numDni;
    private String domicilio;
    private int telefono;
    private String usuario;
    private String clave;

    public Alumno(String unLegajo){
        legajo ="FAI-"+unLegajo;
        nombre="";
        apellido="";
        tipo="";
        numDni=0;
        domicilio="";
        telefono=0;
        usuario="";
        clave="";
    }
    public String getLegajo(){
        return legajo;
    }
    public int getNumDni(){
        return numDni;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String getTelefono(){
        return nombre;
    }
    public void setTelefono(int unTel){
        telefono = unTel;
    }
    public void setDomicilio(String unDomicilio){
        domicilio = unDomicilio;
    }


}