package Modelo;

import java.util.ArrayList;

import Dataobjects.Maestro;

public class ModeloMaestro{

    private ArrayList<Maestro> maestros = new ArrayList<Maestro>();

    public ArrayList<Maestro> getMaestros(){
        return this.maestros;
    }

    public ModeloMaestro(){
        //primer usuario maestros
        maestros.add(new Maestro("Gabriel Maestro","gabrielm","1234",1,"8342099019","gabrielm@hotmail.com"));
        maestros.add(new Maestro("Eduardo Maestro","eduardom","1234",2,"8342688724","eduardom@hotmail.com"));
        maestros.add(new Maestro("Jaime Maestro","jaimem","1234",3,"8342212140","jaimem@hotmail.com"));
        maestros.add(new Maestro("Paco Maestro","paco","1234",4,"8342508028","grimim@hotmail.com"));
        maestros.add(new Maestro("Alan Diaz ","alan","1234",5,"8341262077","calix35@hotmail.com"));
        maestros.add(new Maestro("Mirna Meza","mirna","1234",6,"8340696969","mirna@hotmail.com"));
    }

    public Maestro login(String user, String pass){
        for(int i=0;i<maestros.size(); i++){
            if(maestros.get(i).getUser().equals(user)){
                if(maestros.get(i).getPass().equals(pass)){
                    return maestros.get(i);
                }
                
            }
        }
        return null;
    }
    

    public void alta(Maestro maestro){
        this.maestros.add(maestro);
    }

    public boolean buscarMaestro(String user){
        for(int i=0; i<this.maestros.size(); i++){
            if(this.maestros.get(i).getUser().equals(user)){
                return true;
            }
        }
        return false;
    }

    public String buscarNombre(String user){
        for(int i=0; i<this.maestros.size(); i++){
            if(this.maestros.get(i).getUser().equals(user)){
                return maestros.get(i).getNombre();
            }
        }
        return "Nada";
    }
    
    public int buscarMatricula(String user){
        for(int i=0; i<this.maestros.size(); i++){
            if(this.maestros.get(i).getUser().equals(user)){
                return maestros.get(i).getMatricula();
            }
        }
        return 1;
    }

    public void eliminar(String user){
        for(int i=0; i<this.maestros.size(); i++){
            if(this.maestros.get(i).getUser().equals(user)){
                this.maestros.remove(i);
            }
        }
        
    }

}