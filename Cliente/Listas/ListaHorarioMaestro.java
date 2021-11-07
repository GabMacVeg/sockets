package Vista.Listas;

import Dataobjects.HorarioMaestro;
import Modelo.ModeloHorarioMaestro;
import Modelo.ModeloMaestro;
import Dataobjects.Maestro; 


import java.util.ArrayList;

public class ListaHorarioMaestro{

    public void imprimirHorario(ArrayList<HorarioMaestro> horarioMaestro, String nombreM){

        for(int i=0; i<horarioMaestro.size(); i++){

            if(horarioMaestro.get(i).getnombreMaestro().equals(nombreM)){
                System.out.println(horarioMaestro.get(i));
            }
        }
    }
}