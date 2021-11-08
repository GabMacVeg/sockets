
package Altas;

import java.util.Scanner;


public class AltaAlumno{
    
    private String nombre, user, pass,nt,c;
       private int matricula,semestre;

       public String getUser(){
        return this.user;
    }

    public String getPass(){
        return this.pass;
    }
    public String getNombre() {
        return this.nombre;
    }
    public int getMatricula(){
        return this.matricula;
    }
    public String getNTelefonico() {
        return this.nt;
    }
    public String getCorreo() {
        return this.c;
    }
    public int getSemestre(){
        return this.semestre;
    }


    public AltaAlumno(Scanner sc){
        
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
        
    }

    public void setMsg(String msg){
        System.out.println(msg);
     }
}
