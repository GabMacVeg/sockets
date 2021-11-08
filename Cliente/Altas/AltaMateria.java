
package Altas;

import java.util.Scanner;

public class AltaMateria{

    String nombre;
    int matricula, semestre;

    public String getNombre(){
        return this.nombre;
    }
    public int getMatricula(){
        return this.matricula;
    }
    public int getSemestre(){
        return this.semestre;
    }

    public  AltaMateria(Scanner sc){
        
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
