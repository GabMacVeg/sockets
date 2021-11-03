package Cliente.Listas;

import Servidor.Dataobjects.Administrador;

import java.util.ArrayList;

public class ListaAdministrador{    

    public void imprimirAdministradores(ArrayList<Administrador> administradores){

        for(int i=0; i<administradores.size(); i++)
            System.out.println(administradores.get(i));
    } 
    
}