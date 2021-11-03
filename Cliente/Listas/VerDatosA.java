package Cliente.Listas;

import Servidor.Dataobjects.Alumno;

import java.util.ArrayList;

public class VerDatosA{    

    public void imprimirDatosA(ArrayList<Alumno> alumnos, String nombreA){
        for(int i=0; i<alumnos.size(); i++){
            if(alumnos.get(i).getNombre().equals(nombreA)){
                System.out.println(alumnos.get(i).getNombre()+"\t"+alumnos.get(i).getUser()+"\t"+alumnos.get(i).getMatricula()+"\t"+alumnos.get(i).getNTelefonico()+"\t"+alumnos.get(i).getCorreo());
            }
        }
    } 
    
}