
package Vista.Listas;

import java.util.ArrayList;
import java.util.Scanner;

import Modelo.ModeloHorarioAlumno;
import Modelo.ModeloHorarioMaestro;

public class AlumnosEnMaterias{    
    private Scanner sc;

    private String nombreMateria;

    public String getNombreMateria(){
        return this.nombreMateria;
    }

    public AlumnosEnMaterias(Scanner sc){
        this.sc =  sc; 
    }

    public void show(){
        System.out.println("De que materia desea ver los alumnos");
        System.out.println();
        nombreMateria = sc.nextLine();
        nombreMateria = sc.nextLine();
    }

    public void setMsg(String msg){
        System.out.println(msg);
    }

    
    
}
