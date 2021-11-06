package Menus;

import java.util.Scanner;

public class MenuAdministrador{

    private Scanner sc;
    private int opcion;
    private int opcionver;

    public MenuAdministrador(Scanner sc){
        this.sc=sc;
    }
    public int getOpcion(){
        return this.opcion;
    }
    public int getVer(){
        return this.opcionver;
    }
      
    public void show(){
        System.out.println(" 1)  Alta Administrador");
        System.out.println(" 2)  Baja Administrador");
        System.out.println(" 3)  Ver Usuarios");
        System.out.println(" 4)  Alta Maestro");
        System.out.println(" 5)  Baja Maestro");
        System.out.println(" 6)  Alta Alumno");
        System.out.println(" 7)  Baja Alumno");
        System.out.println(" 8)  Alta Materias");
        System.out.println(" 9)  Baja Materias");
        System.out.println("10)  Lista Materias");
        System.out.println("11)  Alta Carreras");
        System.out.println("12)  Baja Carreras");
        System.out.println("13)  Lista Carreras");
        System.out.println("14)  Ver Datos");
        System.out.println("20)  Cerrar Sesion");
        System.out.print("Opcion: ");
        opcion=sc.nextInt();
    }
    public void showVer(){
        System.out.println("1) Ver Administradores");
        System.out.println("2) Ver Maestros");
        System.out.println("3) Ver Alumnos");
        System.out.println("4) Ver Todos");
        System.out.println("5) Salir");
        System.out.println("Opcion: ");
        opcionver = sc.nextInt();
    }
    
    public void setMsg(String msg){
        System.out.println(msg);
    }
}