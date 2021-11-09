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
                            System.out.print("llego");
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
                        break;

                        case 5://ver horario
                        break;

                        case 6://ver alumnos
                        break;

                        case 7://ver datos
                        break;
                    }
                    break;

                    case 3://alumno

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

    /*public void run(){        
        String user,pass,nombre,nt,c,materia;
        boolean existe;
        int opcion,matricula,semestre;
        float calificacion;
        try {
            do{
                opcion = (int)dataInputStream.readInt();//La instruccion que desea hacer
                switch(opcionMenu){
                    case 1:
                        switch(opcion){
                            
                            case 0:
                            opcionMenu=0;
                            break;
                            
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

                            case 20://Cerrar sesion
                            dataOutputStream.writeInt(1000);
                            opcionMenu=100;
                            break;
                        }
                    break;

                    case 2:
                    switch(opcion){
                        case 0:
                        opcionMenu=0;
                        break;
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
                        break;

                        case 4://quitar materias
                        break;

                        case 5://ver horario
                        break;

                        case 6://ver alumnos
                        break;

                        case 7://ver datos
                        break;
                    }
                    
                    break;

                    case 0:
                    this.login();
                    break;

                    default:
                    System.out.print("jalo");
                    this.login();
                    break;
                }
                
            }while(opcion!=20);        

            closeConnection(); 
        } catch (Exception e) {
            //TODO: handle exception
        }                
    }*/

}


