package Servidor.Modelo;

import java.util.ArrayList;

import Servidor.Dataobjects.Alumno;

public class ModeloAlumno{

    private ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

    public ArrayList<Alumno> getAlumnos(){
        return this.alumnos;
    }

    public ModeloAlumno(){
        //primer usuario administrador
        alumnos.add(new Alumno("Gabriel Macedo","gabrielalu","1234",1,7,"8342099019","gabrielalu@hotmail.com"));
        alumnos.add(new Alumno("Eduardo Velez","eduardoalu","1234",2,7,"8342688724","eduardoalu@hotmail.com"));
        alumnos.add(new Alumno("Jaime Ramirez","jaimealu","1234",3,6,"8342212140","jaimealu@hotmail.com"));
        alumnos.add(new Alumno("Chavira","chaviraalu","1234",4,6,"8342665150","chaviraalu@hotmail.com"));
        alumnos.add(new Alumno("Grimaldo","grimialu","1234",5,3,"8343508028","grimialu@hotmail.com"));

    }

    public Alumno login(String user, String pass){
        for(int i=0;i<alumnos.size(); i++){
            if(alumnos.get(i).getUser().equals(user)){
                if(alumnos.get(i).getPass().equals(pass)){
                    return alumnos.get(i);
                }
                
            }
        }
        return null;
    }

    public void alta(Alumno alumno){
        this.alumnos.add(alumno);
    }

    public boolean buscarAlumno(String user){
        for(int i=0; i<this.alumnos.size(); i++){
            if(this.alumnos.get(i).getUser().equals(user)){
                return true;
            }
        }
        return false;
    }

    public String buscarNombre(String user){
        for(int i=0; i<this.alumnos.size(); i++){
            if(this.alumnos.get(i).getUser().equals(user)){
                return alumnos.get(i).getNombre();
            }
        }
        return "Nada";
    }

    public void eliminar(String user){
        for(int i=0; i<this.alumnos.size(); i++){
            if(this.alumnos.get(i).getUser().equals(user)){
                this.alumnos.remove(i);
            }
        }
        
    }
     public int buscarMatricula(String user){
        for(int i=0; i<this.alumnos.size(); i++){
            if(this.alumnos.get(i).getUser().equals(user)){
                return alumnos.get(i).getMatricula();
            }
        }
        return 1;
    }

}