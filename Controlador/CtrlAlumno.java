package Controlador;

import java.util.Scanner;
import Vista.Menus.MenuAlumno;
import Modelo.ModeloCarrera;
import Modelo.ModeloMateria;
import Modelo.ModeloHorarioAlumno;
import Vista.Altas.SeleccionMateriaAlumno;
import Vista.Listas.ListaHorarioAlumno;
import Vista.Listas.VerDatosA;
import Modelo.ModeloAlumno;

public class CtrlAlumno{

    private Scanner sc;
    private ModeloCarrera modeloCarrera;
    private ModeloMateria modeloMateria;
    private ModeloHorarioAlumno modeloHorarioAlumno;
    private ModeloAlumno modeloAlumno;

    public CtrlAlumno(Scanner sc,String nombreA, int identificadorA,  ModeloCarrera modeloCarrera, ModeloMateria modeloMateria, ModeloHorarioAlumno modeloHorarioAlumno,ModeloAlumno modeloAlumno){
        this.sc=sc;
        this.modeloCarrera = modeloCarrera;
        this.modeloMateria = modeloMateria;
        this.modeloHorarioAlumno = modeloHorarioAlumno;
        this.modeloAlumno = modeloAlumno;

        MenuAlumno menualu = new MenuAlumno(sc);
        boolean existe,existe1;
        
        do{
            menualu.show();
            switch(menualu.getOpcion()){
                case 1://Seleccionar Materia
                    SeleccionMateriaAlumno seleccionmatalu = new SeleccionMateriaAlumno(sc,nombreA);
                    existe = modeloMateria.buscarMateria(seleccionmatalu.getHorario().getMateria());
                    if(existe){
                        seleccionmatalu.setMsg("Encontro la materia"); 
                        existe1= modeloHorarioAlumno.buscarMateria(seleccionmatalu.getHorario().getMateria(),nombreA);
                        if(!existe1){
                            modeloHorarioAlumno.altaMateria(seleccionmatalu.getHorario()); 
                            seleccionmatalu.setMsg("Materia Agregada"); 
                        }else{
                            seleccionmatalu.setMsg("La materia ya fue asignda");
                        }
                    }else{  
                        seleccionmatalu.setMsg("La Materia no existe"); 
                    }
                break;
                case 2://Ver Horario
                    ListaHorarioAlumno listadoHorarioAlumno = new ListaHorarioAlumno();
                    listadoHorarioAlumno.imprimirHorario(modeloHorarioAlumno.getHorario(), nombreA);
                break;
                case 3://Ver Datos
                    VerDatosA verDatosA = new VerDatosA();
                    verDatosA.imprimirDatosA(modeloAlumno.getAlumnos(),nombreA);
                break;
                case 20:// Cerrar sesion
                break;
            }
        }while(menualu.getOpcion()!=20);
    }
}