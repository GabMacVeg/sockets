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
        int opcion;    
        
        do{
            Maestro maestro = modeloMaestro.login(user,pass);
            Administrador administrador = modeloAdministrador.login(user,pass);
            Alumno alumno = modeloAlumno.login(user,pass);
            if(maestro!=null){
                //int identificadorM = modeloMaestro.buscarMatricula(login.getUser());
                //String nombreM = modeloMaestro.buscarNombre(login.getUser());
                modeloMaestro.setMsg("si se pudo");
                opcionMenu=2;
                dataOutputStream.writeInt(2);
                //System.exit(1);                              
            }else{
                if(administrador!=null){
                    //String nombreAd = modeloAdministrador.buscarNombre(login.getUser());
                    modeloMaestro.setMsg("si se pudo");
                    opcionMenu=1;
                    dataOutputStream.writeInt(1);
                    //System.exit(1);
                }else{
                    if(alumno!=null){
                       // int identificadorA = modeloAlumno.buscarMatricula(login.getUser());
                        //String nombreA = modeloAlumno.buscarNombre(login.getUser());
                        modeloMaestro.setMsg("si se pudo");
                        opcionMenu=3;
                        dataOutputStream.writeInt(3);
                        //System.exit(1);
                    }else{
                        dataOutputStream.writeInt(0);
                    }
                }
            }        
        }while(opcionMenu==0);

    }
    
    public void run(){        
        String user,pass,nombre,nt,c;
        boolean existe;
        int opcion,matricula;
        try {
            do{
                opcion = (int)dataInputStream.readInt();//La instruccion que desea hacer
                switch(opcionMenu){
                    case 1:
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

                             

                            case 9999://Cerrar sesion
                                break;
                        }
                    break;
                    case 2:
                    break;
                    default:
                    this.login();
                    break;
                }
                
            }while(opcion!=9999);        

            closeConnection(); 
        } catch (Exception e) {
            //TODO: handle exception
        }                
    }

}


