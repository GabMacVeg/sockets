package Vista.Listas;

import Dataobjects.HorarioAlumno;
import Modelo.ModeloHorarioAlumno;
import Modelo.ModeloAlumno;
import Dataobjects.Alumno; 


import java.util.ArrayList;

public class ListaHorarioAlumno{

    public void imprimirHorario(ArrayList<HorarioAlumno> horarioAlumno, String nombreA){

        for(int i=0; i<horarioAlumno.size(); i++){

            if(horarioAlumno.get(i).getnombreAlumno().equals(nombreA)){
                System.out.println(horarioAlumno.get(i));
            }
        }
    }

    public void setMsg(String msg){
        System.out.println(msg);
    }

    public void imprimirHorarioMateria(ArrayList<HorarioAlumno> horarioAlumno, String nombre){

        for(int i=0; i<horarioAlumno.size(); i++){
            if(horarioAlumno.get(i).getMateria().equals(nombre)){
                System.out.println(horarioAlumno.get(i).getnombreAlumno()+ "\t" +horarioAlumno.get(i).getMateria());
                
            }
        }

    }

}