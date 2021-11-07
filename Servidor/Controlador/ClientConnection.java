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
       
        
        do{
            Maestro maestro = modeloMaestro.login(user,pass);
            Administrador administrador = modeloAdministrador.login(user,pass);
            Alumno alumno = modeloAlumno.login(user,pass);
            if(maestro!=null){
                //int identificadorM = modeloMaestro.buscarMatricula(login.getUser());
                //String nombreM = modeloMaestro.buscarNombre(login.getUser());
                modeloMaestro.setMsg("si se pudo");
                dataOutputStream.writeInt(2);
                //System.exit(1);                              
            }else{
                if(administrador!=null){
                    //String nombreAd = modeloAdministrador.buscarNombre(login.getUser());
                    modeloMaestro.setMsg("si se pudo");
                    dataOutputStream.writeInt(1);
                    //System.exit(1);
                }else{
                    if(alumno!=null){
                       // int identificadorA = modeloAlumno.buscarMatricula(login.getUser());
                        //String nombreA = modeloAlumno.buscarNombre(login.getUser());
                        modeloMaestro.setMsg("si se pudo");
                        dataOutputStream.writeInt(3);
                        //System.exit(1);
                    }else{                        
                        modeloMaestro.setMsg("no se pudo");
                        System.exit(1);
                    }
                }
            }        
        }while(true);

        // if(usuario!=null){
        //    // dataOutputStream.writeInt(usuario.getTipo());
            
        //     /*switch(usuario.getTipo()){
        //         case 1://Administrador
        //             new CtrlAdministrador(sc, modeloUsuarios);
        //             break;
        //         case 2://Vendedor
        //             new CtrlVentas(sc, modeloProductos);
        //             break;
        //     }*/
        //     //Login correcto
        //     //login.setMsg("Login correcto");
        // }else{
        //     //Login incorrecto
        //     //login.setMsg("Login incorrecto");
        //     dataOutputStream.writeInt(0);
        // }
    }
    
    public void run(){        
        String user,pass;
        boolean existe;
        int opcion;
        try {
            //user = (String)dataInputStream.readUTF();
            //usuarios.add(new Usuario(user, dataOutputStream));

            //Recibiendo de los clientes 
            do{
                opcion = (int)dataInputStream.readInt();//La instruccion que desea hacer
                switch(opcion){
                    case 1://El cliente quiere loguearse
                        this.login();
                        break;
                    case 2://Alta usuario
                        // user = (String)dataInputStream.readUTF(); 
                        // nombre = (String)dataInputStream.readUTF(); 
                        // pass = (String)dataInputStream.readUTF(); 
                        // tipo = (int)dataInputStream.readInt(); 
                        // modeloUsuarios.alta(new Usuario(user, pass, nombre, tipo));
                        break;
                    case 3://Verificar si existe usuario
                        // user = (String)dataInputStream.readUTF(); 
                        // existe = modeloUsuarios.buscarUsuario(user);
                        // dataOutputStream.writeBoolean(existe);
                        break;
                    case 9999://Cerrar sesion
                        break;
                }
            }while(opcion!=9999);        

            closeConnection(); 
        } catch (Exception e) {
            //TODO: handle exception
        }                
    }

}


