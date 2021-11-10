package Dataobjects;

public class Carrera{
    private String nombre;
    private int matricula;

    public String getNombre(){
        return this.nombre;
    }
    public int getMatricula(){
        return this.matricula;
    }
    
    public Carrera (String nombre, int matricula){
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public String getTodo(){
        return this.nombre + "\t" + this.matricula;
    }
}