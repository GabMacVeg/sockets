package Cliente.Listas;

import Servidor.Dataobjects.Carrera;

import java.util.ArrayList;

public class ListaCarreras{    

    public void imprimirCarreras(ArrayList<Carrera> carreras){

        for(int i=0; i<carreras.size(); i++)
            System.out.println(carreras.get(i));
    }
    
}
