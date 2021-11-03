package Cliente.Listas;

import Servidor.Dataobjects.Maestro;

import java.util.ArrayList;

public class ListaMaestro{

    public void imprimirMaestros(ArrayList<Maestro> maestros){

        for(int i=0; i<maestros.size(); i++)
            System.out.println(maestros.get(i));
    }

}