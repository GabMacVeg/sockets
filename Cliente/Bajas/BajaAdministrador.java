package Cliente.Bajas;

import java.util.Scanner;
import Servidor.Dataobjects.Administrador;

public class BajaAdministrador{
    private Scanner sc;
    private String user;

    public String getAdmin(){
        return this.user;
    }

    public BajaAdministrador(Scanner sc){
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