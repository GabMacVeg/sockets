
package Altas;

import java.util.Scanner;


public class AltaCarrera{

  

    public  Carrera getCarrera(){
        return this.carrera;
    }

    public  AltaCarrera(Scanner sc){
        String nombre;
        int matricula;
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
