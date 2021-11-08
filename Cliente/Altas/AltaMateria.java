
package Altas;

import java.util.Scanner;



public class AltaMateria{

    

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
        
    }
    public void setMsg(String msg){
        System.out.println(msg);
     }
}
