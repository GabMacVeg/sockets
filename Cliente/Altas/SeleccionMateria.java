package Altas;

import java.util.Scanner;


public class SeleccionMateria{

    String materia,nombreM;

    

    public SeleccionMateria(Scanner sc,String nombreM){
    
       
        this.nombreM = nombreM; 
         
        System.out.print("Nombre Materia: ");        
        materia = sc.nextLine();
        materia = sc.nextLine(); 

        
    }
    public void setMsg(String msg){
        System.out.println(msg);
     }
}
