package Dataobjects;

public class HorarioAlumno{


    private String materia;//Nombre
    private String nombreAlumno;
    private String nombreMaestro;//profe
    public float calificacion;
    
    public String getnombreMaestro(){
        return this.nombreMaestro;
    }
    public String getnombreAlumno(){
        return this.nombreAlumno;
    }
    public String getMateria(){
        return this.materia;
    }
    public float getCalificacion(){
        return this.calificacion;
    }

    public void setCalificacion(float calif){
        this.calificacion=calif;
    }
    
    public HorarioAlumno (String nombreAlumno, String materia,String nombreMaestro,float calif){
        this.materia=materia;
        this.nombreAlumno=nombreAlumno;
        this.nombreMaestro=nombreMaestro;
        this.calificacion=calif;
    }

     public String getDatos(){
        return this.nombreAlumno + "\t" + this.materia+ "\t" + this.nombreMaestro+ "\t" + this.calificacion;
    }
    public String toString(){
        return this.nombreMaestro + "\t" + this.materia+ "\t" + this.calificacion;
    }

}