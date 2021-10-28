
package Vista.Altas;

import java.util.Scanner;

import Dataobjects.Materia;

public class AltaMateria{

    private Materia materia;

    public  Materia getMateria(){
        return this.materia;
    }

    public  AltaMateria(Scanner sc){
        String nombre;
        int matricula, semestre;
        System.out.print("Nombre: ");        
        nombre = sc.nextLine();
        nombre = sc.nextLine();
        System.out.print("Matricula: ");
        matricula = sc.nextInt();   
        System.out.println("Semestre: ");        
        semestre = sc.nextInt();
        this.materia = new Materia(nombre, matricula, semestre);
    }
    public void setMsg(String msg){
        System.out.println(msg);
     }
}
