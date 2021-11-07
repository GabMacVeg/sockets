package Vista.Altas;

import java.util.Scanner;


public class Calificaciones{

    private Scanner sc;

    private String materia;
    private String alumno;
    private float calif;

    public Calificaciones(Scanner sc){
        this.sc=sc;
    }

    public String getMateria(){
        return this.materia;
    }
    public String getAlumno(){
        return this.alumno;
    }
    public float getCalificacion(){
        return this.calif;
    }

    public void show(){
        System.out.println("Ingrese la materia a evaluar");
        materia=sc.nextLine();
        materia=sc.nextLine();
        System.out.println("Ingrese el alumno");
        alumno=sc.nextLine();
        System.out.println("Ingrese la calificacion");
        calif=sc.nextFloat();
    }
    public void corregir(){
         System.out.println("Ingrese la materia a corregir");
        materia=sc.nextLine();
        materia=sc.nextLine();
        System.out.println("Ingrese el alumno");
        alumno=sc.nextLine();
        System.out.println("Ingrese la calificacion");
        calif=sc.nextFloat();
    } 

    public void setMsg(String msg){
        System.out.println(msg);
    }

    public void cal(float calif){
        System.out.println(calif);
    }
}