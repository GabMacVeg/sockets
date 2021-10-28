package Vista.Menus;

import java.util.Scanner;

public class MenuAlumno{

    private Scanner sc;
    private int opcion;

    public MenuAlumno(Scanner sc){
        this.sc=sc;
    }
    public int getOpcion(){
        return this.opcion;
    }

    public void show(){
        System.out.println("1) Seleccionar Materia");
        System.out.println("2) Ver Horario y Calificaciones");
        System.out.println("3) Ver Datos");
        System.out.println("20) Salir");
        System.out.print("Opcion: ");
        opcion=sc.nextInt();
    }
    
    public void setMsg(String msg){
        System.out.println(msg);
    }
}