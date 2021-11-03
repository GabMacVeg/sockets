package Servidor.Dataobjects;

public class Maestro{
    private String user;
    private String pass;
    private String nombre;
    private int matricula;
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
    public String getCorreo(){
        return this.Correo;
    }
        

    public Maestro(String nombre, String user, String pass,int matricula,String NTelefonico,String Correo){
        this.user=user;
        this.pass=pass;
        this.nombre=nombre;
        this.matricula=matricula;
        this.NTelefonico=NTelefonico;
        this.Correo=Correo;
    }

    public String toString(){
        return this.user + "\t" + this.nombre + "\t" + this.matricula ;
    }
}