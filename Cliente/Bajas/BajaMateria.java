package Cliente.Bajas;

import java.util.Scanner;
import Servidor.Dataobjects.Materia;

public class BajaMateria{
    private Scanner sc;
    private String user;

    public String getMateria(){
        return this.user;
    }

    public BajaMateria(Scanner sc){
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