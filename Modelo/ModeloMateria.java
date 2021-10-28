
package Modelo;

import java.util.ArrayList;

import Dataobjects.Materia;

public class ModeloMateria{

    private ArrayList<Materia> materias = new ArrayList<Materia>();

    public ArrayList<Materia> getMaterias(){
        return this.materias;
    }

    public ModeloMateria(){
        //primer usuario administrador
        materias.add(new Materia("Programacion",2222,7));
        materias.add(new Materia("Ingles",3333,4));
        materias.add(new Materia("Algoritmos",4444,3));
        materias.add(new Materia("Matematicas",5555,1));
        materias.add(new Materia("Quimica",6666,2));
        materias.add(new Materia("Contabilidad",7777,5));
    }

    public void altaMateria(Materia materia){
        this.materias.add(materia);
    }

    public boolean buscarMateria(String nombre){
        for(int i=0; i<this.materias.size(); i++){
            if(this.materias.get(i).getNombre().equals(nombre)){
                return true;
            }
        }
        return false;
    }


    public void eliminar(String nombre){
        for(int i=0; i<this.materias.size(); i++){
            if(this.materias.get(i).getNombre().equals(nombre)){
                this.materias.remove(i);
            }
        }
        
    }

}