
package Bajas;

import java.util.Scanner;


public class BajaCarrera{
    private Scanner sc;
    private String nombre;

    public String getNombre(){
        return this.nombre;
    }

    public BajaCarrera(Scanner sc){
        this.sc =  sc;        
    }

    public void show(){
        System.out.print("Nombre de la carrera: ");
        nombre = sc.nextLine();
        nombre = sc.nextLine();
    }

    public void setMsg(String msg){
        System.out.println(msg);
    }

}
