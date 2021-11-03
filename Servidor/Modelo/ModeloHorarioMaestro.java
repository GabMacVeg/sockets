package Servidor.Modelo;

import java.util.ArrayList;

import Servidor.Dataobjects.HorarioMaestro;
import Servidor.Dataobjects.Materia;
import Servidor.Modelo.ModeloMateria;

public class ModeloHorarioMaestro{

    private ArrayList<HorarioMaestro> horarioMaestro = new ArrayList<HorarioMaestro>();

    public ArrayList<HorarioMaestro> getHorario(){
        return this.horarioMaestro;
    }
    public ModeloHorarioMaestro(){
        //el primer id es el id del maestro cada materia que tenga el id 1  
        //pertece al maestro con el id 1 
        horarioMaestro.add(new HorarioMaestro("Gabriel Maestro","Programacion"));
        horarioMaestro.add(new HorarioMaestro("Gabriel Maestro","Ingles"));
        horarioMaestro.add(new HorarioMaestro("Eduardo Maestro","Quimica"));
        horarioMaestro.add(new HorarioMaestro("Eduardo Maestro","Matematicas"));
        horarioMaestro.add(new HorarioMaestro("Jaime Maestro","nada"));
        horarioMaestro.add(new HorarioMaestro("Paco Maestro","nada"));
        horarioMaestro.add(new HorarioMaestro("Alan Diaz","Programacion"));
        horarioMaestro.add(new HorarioMaestro("Mirna Meza","Contabilidad"));
        
        
    }


    public void altaMateria(HorarioMaestro horarioMaestro){
        this.horarioMaestro.add(horarioMaestro);
    }

    public boolean buscarMateria(String nombre,String nombreM){
        for(int i=0; i<this.horarioMaestro.size(); i++){
            if(this.horarioMaestro.get(i).getMateria().equals(nombre) && this.horarioMaestro.get(i).getnombreMaestro().equals(nombreM)){
                return true;
            }
        }
        return false;
    }
    



    public void eliminar(String nombre){
        for(int i=0; i<this.horarioMaestro.size(); i++){
            if(this.horarioMaestro.get(i).getMateria().equals(nombre)){
                this.horarioMaestro.remove(i);
            }
        }
        
    }

}