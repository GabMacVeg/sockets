package Altas;

import java.util.Scanner;

public class SeleccionMateriaAlumno{

    String materia,nombreProfe;

    public String getNombreProfe(){
        return this.nombreProfe;
    }
    public String getMateria(){
        return this.materia;
    }


    public SeleccionMateriaAlumno(Scanner sc){
        System.out.print("Nombre Materia: ");        
        materia = sc.nextLine();
        materia = sc.nextLine(); 
        System.out.println("Nombre Profesor: ");
        nombreProfe = sc.nextLine();
        
    }
    public void setMsg(String msg){
        System.out.println(msg);
     }
}
