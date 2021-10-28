package Modelo;

import java.util.ArrayList;

import Dataobjects.Carrera;

public class ModeloCarrera{

    private ArrayList<Carrera> carreras = new ArrayList<Carrera>();

    public ArrayList<Carrera> getCarreras(){
        return this.carreras;
    }

    public ModeloCarrera(){
        //primer usuario administrador
        carreras.add(new Carrera("Programacion",2222));
        carreras.add(new Carrera("Ingles",3333));
    }

    public void altacarrera(Carrera carrera){
        this.carreras.add(carrera);
    }

    public boolean buscarCarrera(String nombre){
        for(int i=0; i<this.carreras.size(); i++){
            if(this.carreras.get(i).getNombre().equals(nombre)){
                return true;
            }
        }
        return false;
    }


    public void eliminar(String nombre){
        for(int i=0; i<this.carreras.size(); i++){
            if(this.carreras.get(i).getNombre().equals(nombre)){
                this.carreras.remove(i);
            }
        }
        
    }

}