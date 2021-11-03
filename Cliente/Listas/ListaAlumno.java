package Cliente.Listas;

import Servidor.Dataobjects.Alumno;

import java.util.ArrayList;

public class ListaAlumno{    

    public void imprimirAlumnos(ArrayList<Alumno> alumnos){

        for(int i=0; i<alumnos.size(); i++)
            System.out.println(alumnos.get(i));
    }

}