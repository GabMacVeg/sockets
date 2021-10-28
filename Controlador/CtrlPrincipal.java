package Controlador;
import java.util.Scanner;

import Vista.Login;

import Modelo.ModeloAdministrador;
import Dataobjects.Administrador;

import Modelo.ModeloMaestro;
import Dataobjects.Maestro;

import Modelo.ModeloAlumno;
import Dataobjects.Alumno;

import Modelo.ModeloCarrera;
import Dataobjects.Carrera;

import Modelo.ModeloMateria;
import Dataobjects.Materia;

import Dataobjects.HorarioMaestro;
import Modelo.ModeloHorarioMaestro;

import Dataobjects.HorarioAlumno;
import Modelo.ModeloHorarioAlumno;

public class CtrlPrincipal{
    private Scanner sc = new Scanner(System.in);
    private Login login = new Login(sc);
    private ModeloAdministrador modeloAdministrador = new ModeloAdministrador();
    private ModeloMaestro modeloMaestro = new ModeloMaestro();
    private ModeloAlumno modeloAlumno = new ModeloAlumno();
    private ModeloCarrera modeloCarrera = new ModeloCarrera();
    private ModeloMateria modeloMateria = new ModeloMateria();
    private ModeloHorarioMaestro modeloHorarioMaestro = new ModeloHorarioMaestro();
    private ModeloHorarioAlumno modeloHorarioAlumno = new ModeloHorarioAlumno();

    public CtrlPrincipal(){
        do{
            login.show();
            Maestro maestro = modeloMaestro.login(login.getUser(),login.getPass());
            Administrador administrador = modeloAdministrador.login(login.getUser(),login.getPass());
            Alumno alumno = modeloAlumno.login(login.getUser(),login.getPass());
            if(maestro!=null){
                int identificadorM = modeloMaestro.buscarMatricula(login.getUser());
                String nombreM = modeloMaestro.buscarNombre(login.getUser());
                new CtrlMaestro(sc,nombreM,identificadorM , modeloMaestro,modeloCarrera,modeloMateria, modeloHorarioMaestro, modeloHorarioAlumno);                
            }else{
                if(administrador!=null){
                    String nombreAd = modeloAdministrador.buscarNombre(login.getUser());
                    new CtrlAdministrador(sc,nombreAd, modeloAdministrador, modeloCarrera, modeloMaestro, modeloAlumno, modeloMateria);
                }else{
                    if(alumno!=null){
                        int identificadorA = modeloAlumno.buscarMatricula(login.getUser());
                        String nombreA = modeloAlumno.buscarNombre(login.getUser());
                        new CtrlAlumno(sc,nombreA, identificadorA, modeloCarrera, modeloMateria, modeloHorarioAlumno, modeloAlumno);
                    }else{
                        login.setMsg("Incorrecto");
                        
                    }
                }
            }        
        }while(true);
    }
}
