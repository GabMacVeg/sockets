package Controlador;

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

import DataObjects.Alumno;
import DataObjects.Maestro;
import DataObjects.Administrador;

public class ClientConnection extends Thread{

    private Socket socket;
    private InputStream inputStream;
    private DataInputStream dataInputStream;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    
    private ModeloAdministrador modeloAdministrador = new ModeloAdministrador();
    private ModeloAlumno modeloAlumno = new ModeloAlumno();
    private ModeloMaestro ModeloMaestro = mew ModeloMaestro();
    

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
        Usuario usuario = modeloUsuarios.login(user, pass);
        if(usuario!=null){
            dataOutputStream.writeInt(usuario.getTipo());
            
            /*switch(usuario.getTipo()){
                case 1://Administrador
                    new CtrlAdministrador(sc, modeloUsuarios);
                    break;
                case 2://Vendedor
                    new CtrlVentas(sc, modeloProductos);
                    break;
            }*/
            //Login correcto
            //login.setMsg("Login correcto");
        }else{
            //Login incorrecto
            //login.setMsg("Login incorrecto");
            dataOutputStream.writeInt(0);
        }
    }
    
    public void run(){        
        String user,pass,nombre;
        int tipo;
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
                        user = (String)dataInputStream.readUTF(); 
                        nombre = (String)dataInputStream.readUTF(); 
                        pass = (String)dataInputStream.readUTF(); 
                        tipo = (int)dataInputStream.readInt(); 
                        modeloUsuarios.alta(new Usuario(user, pass, nombre, tipo));
                        break;
                    case 3://Verificar si existe usuario
                        user = (String)dataInputStream.readUTF(); 
                        existe = modeloUsuarios.buscarUsuario(user);
                        dataOutputStream.writeBoolean(existe);
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


