

import java.util.Scanner;

public class Login{
    private Scanner sc;
    private String user;
    private String pass;

    public String getUser(){
        return this.user;
    }

    public String getPass(){
        return this.pass;
    }

    public void show(){
        System.out.print("Usuario: ");
        user = sc.nextLine();
        
        System.out.print("Contrasena: ");
        pass = sc.nextLine();
    }

    public Login(Scanner sc){
        this.sc =  sc;        
    }

    public void setMsg(String msg){
        System.out.println(msg);
    }



}