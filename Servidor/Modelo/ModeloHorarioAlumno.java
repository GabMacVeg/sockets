package Modelo;

import java.util.ArrayList;

import Dataobjects.HorarioAlumno;
import Dataobjects.Materia;
import Modelo.ModeloMateria;

public class ModeloHorarioAlumno{

    public int identificador;

    public int getIdentificador(){
        return this.identificador;
    }

    private ArrayList<HorarioAlumno> horarioAlumno = new ArrayList<HorarioAlumno>();

    public ArrayList<HorarioAlumno> getHorario(){
        return this.horarioAlumno;
    }
    public ModeloHorarioAlumno(){
        //el primer id es el id del maestro cada materia que tenga el id 1  
        //pertece al alumno con el id 1      alumno             materia       maestro        calif
        horarioAlumno.add(new HorarioAlumno("Gabriel Macedo","Programacion","Alan Diaz",0));
        horarioAlumno.add(new HorarioAlumno("Eduardo Velez","Programacion","Gabriel Maestro",0));
        horarioAlumno.add(new HorarioAlumno("Gabriel Macedo","Ingles","Gabriel Maestro",0));
        horarioAlumno.add(new HorarioAlumno("Chavira","Matematicas","Eduardo Maestro",0));        
        horarioAlumno.add(new HorarioAlumno("Eduardo Velez","Quimica","Eduardo Maestro",0)); 
        horarioAlumno.add(new HorarioAlumno("Grimaldo","Contabilidad","Mirna Meza",0));        
    }


    public void altaMateria(HorarioAlumno horarioAlumno){
        this.horarioAlumno.add(horarioAlumno);
    }

    public boolean buscarMateria(String nombre,String nombreA){
        for(int i=0; i<this.horarioAlumno.size(); i++){
            if(this.horarioAlumno.get(i).getMateria().equals(nombre) && this.horarioAlumno.get(i).getnombreAlumno().equals(nombreA)){
                identificador=i;
                return true;
            }
        }
        return false;
    }


    public void eliminar(String nombre){
        for(int i=0; i<this.horarioAlumno.size(); i++){
            if(this.horarioAlumno.get(i).getMateria().equals(nombre)){
                this.horarioAlumno.remove(i);
            }
        }
        
    }

    public boolean buscarMateriaC(String materia,String alumno, float calif){

        for(int i=0; i<this.horarioAlumno.size(); i++){

            if(this.horarioAlumno.get(i).getMateria().equals(materia) && this.horarioAlumno.get(i).getnombreAlumno().equals(alumno)){
                this.horarioAlumno.get(i).setCalificacion(calif);                
                return true;
            }
        }
        return false;
    }


}