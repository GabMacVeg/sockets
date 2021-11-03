package Cliente.Bajas;

import java.util.Scanner;
import Servidor.Dataobjects.Alumno;

public class BajaAlumno{
    private Scanner sc;
    private String user;

    public String getAlumno(){
        return this.user;
    }

    public BajaAlumno(Scanner sc){
        this.sc =  sc;        
    }

    public void show(){
        System.out.print("User: ");
        user = sc.nextLine();
        user = sc.nextLine();
    }

    public void setMsg(String msg){
        System.out.println(msg);
    }

}