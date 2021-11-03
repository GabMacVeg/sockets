package Servidor.Dataobjects;

public class HorarioMaestro{

    
    private String materia,nombreMaestro;
   
    public String getnombreMaestro(){
        return this.nombreMaestro;
    }
    public String getMateria(){
        return this.materia;
    }
    
    public HorarioMaestro (String nombreMaestro, String materia){
        this.nombreMaestro = nombreMaestro;
        this.materia=materia;
    }

    public String toString(){
        return this.nombreMaestro + "\t" + this.materia;
    }
}