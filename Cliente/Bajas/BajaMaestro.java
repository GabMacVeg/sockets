package Vista.Bajas;

import java.util.Scanner;
import Dataobjects.Maestro;

public class BajaMaestro{
    private Scanner sc;
    private String user;

    public String getMaestro(){
        return this.user;
    }

    public BajaMaestro(Scanner sc){
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