package Vista.Menus;

import java.util.Scanner;

public class MenuMaestro{

    private Scanner sc;
    private int opcion;

    public MenuMaestro(Scanner sc){
        this.sc=sc;
    }
    public int getOpcion(){
        return this.opcion;
    }

    public void show(){
        System.out.println("1) Alta Calificaciones");
        System.out.println("2) Correccion calificacion");
        System.out.println("3) Seleccionar Materia");
        System.out.println("4) Quitar Materias");
        System.out.println("5) Ver Horario");
        System.out.println("6) Ver Alumnos");
        System.out.println("7) Ver Datos");
        System.out.println("20) Cerrar Sesion");
        System.out.print("Opcion: ");
        opcion=sc.nextInt();
    }
    
    public void setMsg(String msg){
        System.out.println(msg);
    }
}