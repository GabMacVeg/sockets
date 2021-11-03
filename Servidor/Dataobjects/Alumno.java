package Servidor.Dataobjects;

public class Alumno{

    private String user;
    private String pass;
    private int matricula;
    private String nombre;
    private int semestre;
    private String NTelefonico,Correo;

    public String getUser(){
        return this.user;
    }

    public String getPass(){
        return this.pass;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public int getMatricula(){
        return this.matricula;
    }
    public String getNTelefonico(){
        return this.NTelefonico;
    }
    public int getSemestre(){
        return this.semestre;
    }
    public String getCorreo(){
        return this.Correo;
    }

    public Alumno(String nombre, String user, String pass,int matricula,int semestre,String NTelefonico,String Correo){
        this.user=user;
        this.pass=pass;
        this.nombre=nombre;
        this.matricula=matricula;
        this.NTelefonico=NTelefonico;
        this.Correo=Correo;
        this.semestre=semestre;
    }

    public String toString(){
        return this.user + "\t" + this.nombre + "\t" + this.matricula ;
    }
}