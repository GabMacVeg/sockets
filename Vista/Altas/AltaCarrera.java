
package Vista.Altas;

import java.util.Scanner;
import Dataobjects.Carrera;

public class AltaCarrera{

    private Carrera carrera;

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
        this.carrera = new Carrera(nombre, matricula);
    }
    public void setMsg(String msg){
        System.out.println(msg);
     }
     
}
