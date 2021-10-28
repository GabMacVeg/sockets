package Controlador;

import java.util.ArrayList;

import java.util.Scanner;
import Vista.Menus.MenuMaestro;
import Modelo.ModeloMaestro;
import Modelo.ModeloCarrera;
import Modelo.ModeloMateria;
import Vista.Altas.SeleccionMateria;
import Modelo.ModeloHorarioMaestro;
import Vista.Listas.ListaHorarioMaestro;
import Dataobjects.Maestro;
import Vista.Bajas.QuitarMateria;
import Vista.Listas.AlumnosEnMaterias;
import Modelo.ModeloHorarioAlumno;
import Vista.Listas.ListaHorarioAlumno;
import Modelo.ModeloHorarioAlumno;
import Vista.Altas.Calificaciones;
import Dataobjects.HorarioAlumno;
import Vista.Listas.VerDatosM;

public class CtrlMaestro{

    private Scanner sc;
    private ModeloMaestro modeloMaestro;
    private ModeloCarrera modeloCarrera;
    private ModeloMateria modeloMateria;
    private ModeloHorarioMaestro modeloHorarioMaestro;
    private ModeloHorarioAlumno modeloHorarioAlumno;

    public CtrlMaestro(Scanner sc,String nombreM,int identificadorM ,ModeloMaestro modeloMaestro,ModeloCarrera modeloCarrera, ModeloMateria modeloMateria,ModeloHorarioMaestro modeloHorarioMaestro, ModeloHorarioAlumno modeloHorarioAlumno){
        this.sc=sc;
        this.modeloMaestro=modeloMaestro;
        this.modeloCarrera=modeloCarrera;
        this.modeloMateria=modeloMateria;


        MenuMaestro menuMae = new MenuMaestro(sc);
        boolean existe,existe1;
        do{
            menuMae.show();
            switch(menuMae.getOpcion()){
                case 1://Alta Calificaciones
                    Calificaciones calificacion = new Calificaciones(sc);
                    calificacion.show();
                    existe=modeloHorarioAlumno.buscarMateria(calificacion.getMateria(),calificacion.getAlumno());
                        if(existe){
                            modeloHorarioAlumno.buscarMateriaC(calificacion.getMateria(),calificacion.getAlumno(),calificacion.getCalificacion());
                            calificacion.setMsg("Calificacion agregada");
                        }else{
                            calificacion.setMsg("La materia o el alumno son incorrectos");
                        }
                       
                break;
                case 2://Correccion calificacion
                   Calificaciones calificacion2 = new Calificaciones(sc);
                    calificacion2.corregir();
                    existe=modeloHorarioAlumno.buscarMateria(calificacion2.getMateria(),calificacion2.getAlumno());
                        if(existe){
                            modeloHorarioAlumno.buscarMateriaC(calificacion2.getMateria(),calificacion2.getAlumno(),calificacion2.getCalificacion());
                            calificacion2.setMsg("Calificacion agregada");
                        }else{
                            calificacion2.setMsg("La materia o el alumno son incorrectos");
                        } 
                break;
                case 3://Seleccionar Materia
                    SeleccionMateria seleccionmat = new SeleccionMateria(sc,nombreM);
                    existe = modeloMateria.buscarMateria(seleccionmat.getHorario().getMateria());
                    if(existe){
                        seleccionmat.setMsg("Encontro la materia"); 
                        existe1= modeloHorarioMaestro.buscarMateria(seleccionmat.getHorario().getMateria(),nombreM);
                        if(!existe1){
                            modeloHorarioMaestro.altaMateria(seleccionmat.getHorario()); 
                            seleccionmat.setMsg("Materia Agregada"); 
                        }else{
                            seleccionmat.setMsg("La materia ya fue asignda");
                        }
                    }else{  
                        seleccionmat.setMsg("La Materia no existe"); 
                    }
                break;
                case 4://Quitar Materias
                    QuitarMateria quitarmat = new QuitarMateria(sc);
                    quitarmat.show();
                    existe = modeloMateria.buscarMateria(quitarmat.getMateria());
                    if(existe){
                        quitarmat.setMsg("Encontro la materia"); 
                        existe1= modeloHorarioMaestro.buscarMateria(quitarmat.getMateria(),nombreM);
                        if(existe1){
                            modeloHorarioMaestro.eliminar(quitarmat.getMateria());
                            quitarmat.setMsg("Materia Eliminada"); 
                        }else{
                            quitarmat.setMsg("La materia no esta en tu horario");
                        }
                    }else{  
                        quitarmat.setMsg("La Materia no existe"); 
                    }
                break;
                case 5://Ver Horario
                    ListaHorarioMaestro listadoMaestro = new ListaHorarioMaestro();
                    listadoMaestro.imprimirHorario(modeloHorarioMaestro.getHorario(), nombreM);
                break;
                case 6://Ver Alumnos
                    AlumnosEnMaterias alumnoenmaterias = new AlumnosEnMaterias(sc);
                    alumnoenmaterias.show();
                    existe = modeloHorarioMaestro.buscarMateria(alumnoenmaterias.getNombreMateria(), nombreM);
                    if(existe){
                        ListaHorarioAlumno listadohorarioalumno = new ListaHorarioAlumno();
                        listadohorarioalumno.imprimirHorarioMateria(modeloHorarioAlumno.getHorario(),alumnoenmaterias.getNombreMateria());
               
                    }else{
                        alumnoenmaterias.setMsg("No Hay Alumnos");
                    }
                break;
                case 7:
                    VerDatosM verDatosM= new VerDatosM();
                    verDatosM.imprimirDatosM(modeloMaestro.getMaestros(),nombreM);
                break;
                case 20://Cerrar Sesion
                break;
                default://opcion incorrecta
                menuMae.setMsg("Opcion incorrecta");
            }
        }while(menuMae.getOpcion()!=20);
    }
}