package Dataobjects;

public class Administrador{
    private String user;
    private String pass;
    private int matricula;
    private String nombre;
    private String NTelefonico,Correo;
    //private Fecha fechaNa;

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
    public String getNTelefonico() {
        return this.NTelefonico;
    }
    public String getCorreo() {
        return this.Correo;
    }
    

    public Administrador(String nombre, String user, String pass,int matricula,String NTelefonico,String Correo){
        this.user=user;
        this.pass=pass;
        this.nombre=nombre;
        this.matricula=matricula;
        this.NTelefonico=NTelefonico;
        this.Correo=Correo;
    }

    public String getTodo(){
        return this.user + "\t" + this.nombre;
    }
    public String getDatos(){
         return this.nombre+"\t"+this.user+"\t"+this.matricula+"\t"+this.NTelefonico+"\t"+this.Correo;
    }
}