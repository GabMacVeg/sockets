
package Altas;

import java.util.Scanner;


public class AltaCarrera{

    String nombre;
        int matricula;
    public String getNombre(){
        return this.nombre;
    }
    public int getMatricula(){
        return this.matricula;
    }

    public  AltaCarrera(Scanner sc){
        
        System.out.print("Nombre: ");        
        nombre = sc.nextLine();
        nombre = sc.nextLine();
        System.out.print("Matricula: ");
        matricula = sc.nextInt();
        
    }
    public void setMsg(String msg){
        System.out.println(msg);
     }
     
}
