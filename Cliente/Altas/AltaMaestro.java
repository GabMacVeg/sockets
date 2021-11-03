
package Cliente.Altas;

import java.util.Scanner;
import Servidor.Dataobjects.Maestro;

public class AltaMaestro{
    
    private Maestro maestro;

    public Maestro getMaestro(){
        return this.maestro;
    }

    public AltaMaestro(Scanner sc){
        String nombre, user, pass,nt,c;
        int matricula;
        System.out.print("Nombre: ");        
        nombre = sc.nextLine();
        nombre = sc.nextLine();
        System.out.print("User: ");
        user = sc.nextLine();
        System.out.print("Pass: ");
        pass = sc.nextLine();
        System.out.print("Matricula: ");
        matricula = sc.nextInt();
        System.out.print("Numero Telefonico: ");
        nt = sc.nextLine();
        nt = sc.nextLine();
        System.out.print("Correo : ");
        c = sc.nextLine();
        this.maestro = new Maestro(nombre,user,pass,matricula,nt,c);
    }

    public void setMsg(String msg){
        System.out.println(msg);
     }
}
