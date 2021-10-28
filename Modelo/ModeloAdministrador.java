package Modelo;

import java.util.ArrayList;

import Dataobjects.Administrador;

public class ModeloAdministrador{

    private ArrayList<Administrador> administradores = new ArrayList<Administrador>();

    public ArrayList<Administrador> getAdministradores(){
        return this.administradores;
    }

    public ModeloAdministrador(){
        //ADMISTRADORES
        administradores.add(new Administrador("Gabriel Macedo","gabriela","1234",1,"8342099019","gabriela@hotmail.com"));
        administradores.add(new Administrador("Eduardo Velez","eduardoa","1234",2,"8342688724","eduardoa@hotmail.com"));
        administradores.add(new Administrador("Jaime Ramirez","jaimea","1234",3,"8342212140","jaimea@hotmail.com"));

    }

    public Administrador login(String user, String pass){
        for(int i=0;i<administradores.size(); i++){
            if(administradores.get(i).getUser().equals(user)){
                if(administradores.get(i).getPass().equals(pass)){
                    return administradores.get(i);
                }
                
            }
        }
        return null;
    }

    public void alta(Administrador administrador){
        this.administradores.add(administrador);
    }

    public boolean buscarAdmi(String user){
        for(int i=0; i<this.administradores.size(); i++){
            if(this.administradores.get(i).getUser().equals(user)){
                return true;
            }
        }
        return false;
    }
    public String buscarNombre(String user){
        for(int i=0; i<this.administradores.size(); i++){
            if(this.administradores.get(i).getUser().equals(user)){
                return administradores.get(i).getNombre();
            }
        }
        return "Nada";
    }

    public void eliminar(String user){
        for(int i=0; i<this.administradores.size(); i++){
            if(this.administradores.get(i).getUser().equals(user)){
                this.administradores.remove(i);
                return;
            }
        }
        
    }

}