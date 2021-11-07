package Vista.Listas;

import Dataobjects.Materia;

import java.util.ArrayList;

public class ListaMaterias{    

    public void imprimirMaterias(ArrayList<Materia> materias){

        for(int i=0; i<materias.size(); i++)
            System.out.println(materias.get(i));
    }
    
}
