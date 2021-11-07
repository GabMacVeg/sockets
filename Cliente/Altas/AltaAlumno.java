
package Vista.Altas;

import java.util.Scanner;
import Dataobjects.Alumno;

public class AltaAlumno{
    
    private Alumno alumno;

    public Alumno getAlumno(){
        return this.alumno;
    }

    public AltaAlumno(Scanner sc){
        String nombre, user, pass,nt,c;
        int matricula,semestre;
        System.out.print("Nombre: ");        
        nombre = sc.nextLine();
        nombre = sc.nextLine();
        System.out.print("User: ");
        user = sc.nextLine();
        System.out.print("Pass: ");
        pass = sc.nextLine();
        System.out.print("Matricula: ");
        matricula = sc.nextInt();
        System.out.print("Semestre: ");
        semestre = sc.nextInt();
        System.out.print("Numero Telefonico: ");
        nt = sc.nextLine();
        nt = sc.nextLine();
        System.out.print("Correo : ");
        c = sc.nextLine();
        this.alumno = new Alumno(user, pass, nombre, matricula,semestre,nt,c);
    }

    public void setMsg(String msg){
        System.out.println(msg);
     }
}
