package Vista.Listas;

import Dataobjects.Administrador;

import java.util.ArrayList;

public class VerDatosAdmin{    

    public void imprimirDatosAdmin(ArrayList<Administrador> administradores, String nombreAd){
        for(int i=0; i<administradores.size(); i++){
            if(administradores.get(i).getNombre().equals(nombreAd)){
                System.out.println(administradores.get(i).getNombre()+"\t"+administradores.get(i).getUser()+"\t"+administradores.get(i).getMatricula()+"\t"+administradores.get(i).getNTelefonico()+"\t"+administradores.get(i).getCorreo());
            }
        }
    } 
    
}