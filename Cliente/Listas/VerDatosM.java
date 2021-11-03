package Cliente.Listas;

import Servidor.Dataobjects.Maestro;

import java.util.ArrayList;

public class VerDatosM{    

    public void imprimirDatosM(ArrayList<Maestro> maestros, String nombreM){
        for(int i=0; i<maestros.size(); i++){
            if(maestros.get(i).getNombre().equals(nombreM)){
                System.out.println(maestros.get(i).getNombre()+"\t"+maestros.get(i).getUser()+"\t"+maestros.get(i).getMatricula()+"\t"+maestros.get(i).getNTelefonico()+"\t"+maestros.get(i).getCorreo());
            }
        }
    } 
    
}