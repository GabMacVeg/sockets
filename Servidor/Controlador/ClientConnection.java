package Controlador;
import java.util.Scanner;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;

import java.util.ArrayList;

import Modelo.ModeloAdministrador;
import Modelo.ModeloAlumno;
import Modelo.ModeloMaestro;
import Modelo.ModeloCarrera;
import Modelo.ModeloHorarioAlumno;
import Modelo.ModeloHorarioMaestro;
import Modelo.ModeloMateria;

import Dataobjects.Alumno;
import Dataobjects.Maestro;
import Dataobjects.Administrador;
import Dataobjects.Carrera;
import Dataobjects.HorarioAlumno;
import Dataobjects.HorarioMaestro;
import Dataobjects.Materia;


public class ClientConnection extends Thread{


    private int opcionMenu=0;
    private int identificadorM;
    private String nombreM;
    private String nombreAd;
    private int identificadorA;
    private String nombreA;
    
    private Socket socket;
    private InputStream inputStream;
    private DataInputStream dataInputStream;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    
    private Scanner sc = new Scanner(System.in);
    
    private ModeloAdministrador modeloAdministrador = new ModeloAdministrador();
    private ModeloMaestro modeloMaestro = new ModeloMaestro();
    private ModeloAlumno modeloAlumno = new ModeloAlumno();
    private ModeloCarrera modeloCarrera = new ModeloCarrera();
    private ModeloMateria modeloMateria = new ModeloMateria();
    private ModeloHorarioMaestro modeloHorarioMaestro = new ModeloHorarioMaestro();
    private ModeloHorarioAlumno modeloHorarioAlumno = new ModeloHorarioAlumno();
    

    public ClientConnection(Socket socket) throws IOException {
        this.socket =  socket;
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        dataInputStream = new DataInputStream(inputStream);
        dataOutputStream = new DataOutputStream(outputStream);
        System.out.println("Nuevo cliente conectado...");
    }

    public void closeConnection() throws IOException{
        inputStream.close();
        outputStream.close();
        dataInputStream.close();
        dataOutputStream.close();
        socket.close();
    }

    public void login() throws IOException{

        String user = (String)dataInputStream.readUTF();
        String pass = (String)dataInputStream.readUTF();

        Maestro maestro = modeloMaestro.login(user,pass);
        Administrador administrador = modeloAdministrador.login(user,pass);
        Alumno alumno = modeloAlumno.login(user,pass);
            if(maestro!=null){
                modeloMaestro.setMsg("si se pudo");
                identificadorM = modeloMaestro.buscarMatricula(user);
                nombreM = modeloMaestro.buscarNombre(user);
                dataOutputStream.writeInt(2);                    
            }else{
                if(administrador!=null){
                    modeloMaestro.setMsg("si se pudo");
                    nombreAd = modeloAdministrador.buscarNombre(user);
                    dataOutputStream.writeInt(1);
                }else{
                    if(alumno!=null){
                        modeloMaestro.setMsg("si se pudo");
                        identificadorA = modeloAlumno.buscarMatricula(user);
                        nombreA = modeloAlumno.buscarNombre(user);
                        dataOutputStream.writeInt(3);
                    }else{
                        dataOutputStream.writeInt(0);
                    }
                }
            }
    }

    public void run(){        
        String user,pass,nombre,nt,c,materia;
        boolean existe,existe1;
        int opcion,matricula,semestre,opcionMenu;
        float calificacion;

        try {
            do{
                opcion = (int)dataInputStream.readInt();//La instruccion que desea hacer
                opcionMenu = (int)dataInputStream.readInt();
                
                switch(opcionMenu){
                    //menu del administrador con su switch 1 (ADMIN)
                    case 1://admin
                        switch(opcion){   
                            case 1://Alta admin                            
                                nombre = (String)dataInputStream.readUTF();
                                user=(String)dataInputStream.readUTF();
                                pass=(String)dataInputStream.readUTF();
                                nt=(String)dataInputStream.readUTF();
                                c=(String)dataInputStream.readUTF();
                                matricula= (int)dataInputStream.readInt();
                                Administrador administrador = new Administrador(nombre, user, pass,matricula,nt,c);
                                existe = modeloAdministrador.buscarAdmi(user);
                                if(!existe){
                                    modeloAdministrador.alta(administrador);
                                    dataOutputStream.writeInt(1);//se creo el admin
                                }else{
                                    dataOutputStream.writeInt(0);//ya existe
                                }

                            break;

                            case 2://baja admin
                                user=(String)dataInputStream.readUTF();
                                existe = modeloAdministrador.buscarAdmi(user);
                                if(existe){
                                    //Existe el usuario
                                    modeloAdministrador.eliminar(user);
                                    dataOutputStream.writeInt(1);//se elimino el admin
                                }else{
                                    dataOutputStream.writeInt(0);//no existe el admin
                                }
                            break; 

                            case 3://listas de ususrios
                                int listaver = (int)dataInputStream.readInt();
                                switch(listaver){
                                    case 1: //Ver Administradores 
                                        System.out.println("case 1 lista");                                      
                                        ArrayList<Administrador> administradores = (modeloAdministrador.getAdministradores());

                                        dataOutputStream.writeInt((administradores.size()));
                                        
                                        for(int i=0; i< administradores.size(); i++){
                                            dataOutputStream.writeUTF(administradores.get(i).getTodo());
                                        }  
                                    break;
                                    case 2://Ver Maestros
                                        System.out.println("case 2 lista"); 
                                        ArrayList<Maestro> maestros = (modeloMaestro.getMaestros());

                                        dataOutputStream.writeInt((maestros.size()));
                                        
                                        for(int i=0; i< maestros.size(); i++){
                                            dataOutputStream.writeUTF(maestros.get(i).getTodo());
                                        } 
                                    break;
                                    case 3://Ver Alumnos
                                        System.out.println("case 3 lista"); 
                                        ArrayList<Alumno> alumnos = (modeloAlumno.getAlumnos());

                                        dataOutputStream.writeInt((alumnos.size()));
                                        
                                        for(int i=0; i< alumnos.size(); i++){
                                            dataOutputStream.writeUTF(alumnos.get(i).getTodo());
                                        }
                                    break;
                                    case 4://Ver Todos
                                        System.out.println("case 4 lista"); 
                                        ArrayList<Administrador> administradores1 = (modeloAdministrador.getAdministradores());

                                        dataOutputStream.writeInt((administradores1.size()));
                                        
                                        for(int i=0; i< administradores1.size(); i++){
                                            dataOutputStream.writeUTF(administradores1.get(i).getTodo());
                                        }
                                        //   2
                                        ArrayList<Maestro> maestros1 = (modeloMaestro.getMaestros());

                                        dataOutputStream.writeInt((maestros1.size()));
                                        
                                        for(int i=0; i< maestros1.size(); i++){
                                            dataOutputStream.writeUTF(maestros1.get(i).getTodo());
                                        }
                                        //       3
                                        ArrayList<Alumno> alumnos1 = (modeloAlumno.getAlumnos());

                                        dataOutputStream.writeInt((alumnos1.size()));
                                        
                                        for(int i=0; i< alumnos1.size(); i++){
                                            dataOutputStream.writeUTF(alumnos1.get(i).getTodo());
                                        }
                                    break;
                                    case 5://Salir
                                    break;
                                }                             
                            break;

                            case 4:
                                nombre = (String)dataInputStream.readUTF();
                                user=(String)dataInputStream.readUTF();
                                pass=(String)dataInputStream.readUTF();
                                nt=(String)dataInputStream.readUTF();
                                c=(String)dataInputStream.readUTF();
                                matricula= (int)dataInputStream.readInt();
                                
                                Maestro maestro = new Maestro(nombre, user, pass,matricula,nt,c);
                                
                                existe = modeloMaestro.buscarMaestro(user);
                                if(!existe){
                                    modeloMaestro.alta(maestro);
                                    dataOutputStream.writeInt(1);//se creo el maestro
                                }else{
                                    dataOutputStream.writeInt(0);//ya existe
                                }
                                
                            break;

                            case 5:
                                user=(String)dataInputStream.readUTF();
                                existe = modeloMaestro.buscarMaestro(user);
                                if(existe){
                                    //Existe el usuario
                                    modeloMaestro.eliminar(user);
                                    dataOutputStream.writeInt(1);//se elimino el admin
                                }else{
                                    dataOutputStream.writeInt(0);//no existe el admin
                                }
                            break;  

                            case 6:
                                nombre = (String)dataInputStream.readUTF();
                                user=(String)dataInputStream.readUTF();
                                pass=(String)dataInputStream.readUTF();
                                nt=(String)dataInputStream.readUTF();
                                c=(String)dataInputStream.readUTF();
                                matricula= (int)dataInputStream.readInt();
                                semestre= (int)dataInputStream.readInt();
                                
                                Alumno alumno = new Alumno(nombre, user, pass,matricula,semestre,nt,c);
                                
                                existe = modeloAlumno.buscarAlumno(user);
                                if(!existe){
                                    modeloAlumno.alta(alumno);
                                    dataOutputStream.writeInt(1);//se creo el alumno
                                }else{
                                    dataOutputStream.writeInt(0);//ya existe
                                }
                                
                            break;

                            case 7:
                                user=(String)dataInputStream.readUTF();
                                existe = modeloAlumno.buscarAlumno(user);
                                if(existe){
                                    //Existe el usuario
                                    modeloAlumno.eliminar(user);
                                    dataOutputStream.writeInt(1);//se elimino el admin
                                }else{
                                    dataOutputStream.writeInt(0);//no existe el admin
                                }
                            break;

                            case 8:
                                nombre=(String)dataInputStream.readUTF();
                                semestre= (int)dataInputStream.readInt();
                                matricula= (int)dataInputStream.readInt();
                                Materia materiaN = new Materia(nombre,matricula,semestre);
                                existe = modeloMateria.buscarMateria(nombre);
                                if(!existe){
                                    modeloMateria.altaMateria(materiaN);
                                    dataOutputStream.writeInt(1);//se creo la materia
                                }else{
                                    dataOutputStream.writeInt(0);//ya existe
                                }
                            break;

                            case 9:
                                nombre=(String)dataInputStream.readUTF();
                                existe = modeloMateria.buscarMateria(nombre);
                                if(existe){
                                    //Existe el usuario
                                    modeloMateria.eliminar(nombre);
                                    dataOutputStream.writeInt(1);//se elimino la materia
                                }else{
                                    dataOutputStream.writeInt(0);//no existe la  materia
                                }
                            break;

                            case 10://lista materias
                                ArrayList<Materia> materias = (modeloMateria.getMaterias());
                                dataOutputStream.writeInt((materias.size()));

                                for(int i=0; i< materias.size(); i++){
                                    dataOutputStream.writeUTF(materias.get(i).getTodo());
                                }
                            break;

                            case 11:
                                nombre=(String)dataInputStream.readUTF();                                
                                matricula= (int)dataInputStream.readInt();
                                Carrera carrera = new Carrera(nombre,matricula);
                                existe = modeloCarrera.buscarCarrera(nombre);
                                if(!existe){
                                    modeloCarrera.altacarrera(carrera);
                                    dataOutputStream.writeInt(1);//se creo la carrera
                                }else{
                                    dataOutputStream.writeInt(0);//ya existe
                                }
                            break;

                            case 12:
                                nombre=(String)dataInputStream.readUTF();
                                existe = modeloCarrera.buscarCarrera(nombre);
                                if(existe){
                                    //Existe el usuario
                                    modeloCarrera.eliminar(nombre);
                                    dataOutputStream.writeInt(1);//se elimino la carrera
                                }else{
                                    dataOutputStream.writeInt(0);//no existe la  carrera
                                }
                            break;
                            case 13://lista carreras
                                ArrayList<Carrera> carreras = (modeloCarrera.getCarreras());
                                dataOutputStream.writeInt((carreras.size()));

                                for(int i=0; i< carreras.size(); i++){
                                    dataOutputStream.writeUTF(carreras.get(i).getTodo());
                                }
                            break;
                            case 14://ver datos
                                
                                ArrayList<Administrador> admin = (modeloAdministrador.getAdministradores()); 
                                                               
                                for(int i=0; i<admin.size(); i++){
                                    if(admin.get(i).getNombre().equals(nombreAd)){
                                         dataOutputStream.writeUTF(admin.get(i).getDatos());
                                    }else{
                                        dataOutputStream.writeUTF("No se encontro datos");
                                    }
                                }
                               
                            break;


                            case 50:
                            System.out.println("Entro al 50 en la opcion ");
                            break;
                        }
                    break;

                    case 2://maestro
                    switch(opcion){
                        case 1://alta calificaciones
                        materia=(String)dataInputStream.readUTF();
                        nombre=(String)dataInputStream.readUTF();
                        calificacion= (float)dataInputStream.readFloat();
                        existe=modeloHorarioAlumno.buscarMateria(materia,nombre);
                        if(existe){
                            modeloHorarioAlumno.buscarMateriaC(materia,nombre,calificacion);
                            dataOutputStream.writeInt(1);//se agrego la calificacion
                        }else{
                            dataOutputStream.writeInt(0);//no se pudo
                        }

                        break;

                        case 2://corereccion calificaciones
                            materia=(String)dataInputStream.readUTF();
                            nombre=(String)dataInputStream.readUTF();
                            calificacion= (float)dataInputStream.readFloat();
                            existe=modeloHorarioAlumno.buscarMateria(materia,nombre);
                            if(existe){
                                modeloHorarioAlumno.buscarMateriaC(materia,nombre,calificacion);
                                dataOutputStream.writeInt(1);//se agrego la calificacion
                            }else{
                                dataOutputStream.writeInt(0);//no se pudo
                            }
                        break;

                        case 3://seleccioanr materias
                        dataOutputStream.writeUTF(nombreM);
                        dataOutputStream.writeInt(identificadorM);
                        materia=(String)dataInputStream.readUTF();
                        HorarioMaestro horarioMaestro = new HorarioMaestro(nombreM,materia);
                        existe = modeloMateria.buscarMateria(materia);
                            if(existe){
                                existe1= modeloHorarioMaestro.buscarMateria(materia,nombreM);
                                if(!existe1){
                                    modeloHorarioMaestro.altaMateria(horarioMaestro); 
                                    dataOutputStream.writeInt(1);//materia agregada

                                }else{
                                    dataOutputStream.writeInt(0);//la materia ya estaba
                            }
                            }else{  
                            dataOutputStream.writeInt(2);//no exuste la materia
                            }
                        break;

                        case 4://quitar materias
                            materia=(String)dataInputStream.readUTF();
                            existe = modeloMateria.buscarMateria(materia);
                            if(existe){
                            existe1= modeloHorarioMaestro.buscarMateria(materia,nombreM);
                            if(existe1){
                                modeloHorarioMaestro.eliminar(materia);
                                dataOutputStream.writeInt(1);//materia eliminada
                            }else{
                                dataOutputStream.writeInt(0);//materia no existe en el horario
                            }
                            }else{  
                                dataOutputStream.writeInt(2);//materia inexistente

                            }
                        break;

                        case 5://ver horario
                            ArrayList<HorarioMaestro> horarioMaestro1 = (modeloHorarioMaestro.getHorario());
                            dataOutputStream.writeInt((horarioMaestro1.size()));
                            System.out.println(nombreM);
                            int tam = horarioMaestro1.size();
                            for(int i=0; i<horarioMaestro1.size(); i++){
                                if(horarioMaestro1.get(i).getnombreMaestro().equals(nombreM)){
                                     dataOutputStream.writeUTF(horarioMaestro1.get(i).getDatos());
                                }else{ 
                                    dataOutputStream.writeUTF("");
                                }
                            }
                        break;

                        case 6://ver alumnos
                            materia=(String)dataInputStream.readUTF();
                            ArrayList<HorarioAlumno> horarioalu = (modeloHorarioAlumno.getHorario()); 
                            dataOutputStream.writeInt((horarioalu.size()));
                            existe = modeloHorarioMaestro.buscarMateria(materia, nombreM);
                            if(existe){
                                for(int i=0; i<horarioalu.size(); i++){
                                    if(horarioalu.get(i).getMateria().equals(materia)){
                                        dataOutputStream.writeUTF(horarioalu.get(i).getnombreAlumno()+ "\t" +horarioalu.get(i).getMateria());                
                                    }else{
                                        dataOutputStream.writeUTF("");                
                                    }
                                }
                            }
                        break;

                        case 7://ver datos
                            ArrayList<Maestro> mae = (modeloMaestro.getMaestros()); 
                                                               
                                for(int i=0; i<mae.size(); i++){
                                    if(mae.get(i).getNombre().equals(nombreM)){
                                         dataOutputStream.writeUTF(mae.get(i).getDatos());
                                    }else{
                                        dataOutputStream.writeUTF("No se encontro datos");
                                    }
                                }

                        break;
                    }
                    break;

                    case 3://alumno
                        switch(opcion){
                            case 1://seleccion materia
                            dataOutputStream.writeUTF(nombreA);
                            dataOutputStream.writeInt(identificadorA);
                            materia=(String)dataInputStream.readUTF();
                            nombre=(String)dataInputStream.readUTF();
                            HorarioAlumno horarioAlumno = new HorarioAlumno(nombreA,materia,nombre,0);
                            existe = modeloMateria.buscarMateria(materia);
                            if(existe){
                                existe1= modeloHorarioAlumno.buscarMateria(materia,nombreA);
                                if(!existe1){
                                    modeloHorarioAlumno.altaMateria(horarioAlumno); 
                                    dataOutputStream.writeInt(1);//materia agregada
                                }else{
                                    dataOutputStream.writeInt(0);//materia ya estaba asignda
                                }
                                }else{  
                                    dataOutputStream.writeInt(2);//materia inexistente
                                }

                            break;

                            case 2://ver horarios y calififcaciones
                                ArrayList<HorarioAlumno> horarioAlu = (modeloHorarioAlumno.getHorario());

                                dataOutputStream.writeInt(horarioAlu.size());

                                for(int i=0; i<horarioAlu.size(); i++){
                                    if(horarioAlu.get(i).getnombreAlumno().equals(nombreA)){
                                        dataOutputStream.writeUTF(horarioAlu.get(i).toString());
                                    }else{ 
                                        dataOutputStream.writeUTF("");
                                    }
                                }
                            break;

                            case 3://ver datos
                                ArrayList<Alumno> alu = (modeloAlumno.getAlumnos()); 
                                                               
                                for(int i=0; i<alu.size(); i++){
                                    if(alu.get(i).getNombre().equals(nombreA)){
                                         dataOutputStream.writeUTF(alu.get(i).getDatos());
                                    }else{
                                        dataOutputStream.writeUTF("No se encontro datos");
                                    }
                                }
                            break;
                        }

                    break;

                    case 20://login
                        System.out.println("Entro al login");
                        this.login();
                    break;

                    case 50://no sirve
                        System.out.println("Entro al 50 en el opcion menu");
                    break;
                }
                
            }while(opcion!=50);        

            closeConnection(); 
        } catch (Exception e) {
            //TODO: handle exception
        }                
    }

    

}


