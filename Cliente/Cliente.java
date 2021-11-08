import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;

import java.util.Scanner;

import Altas.AltaAdministrador;
import Bajas.BajaAdministrador;

import Altas.AltaMaestro;
import Bajas.BajaMaestro;

import Menus.MenuAdministrador;
import Menus.MenuAlumno;
import Menus.MenuMaestro;

public class Cliente{
    private Scanner sc =  new Scanner(System.in);
    private Socket socket_client;
    private OutputStream os;
    private DataOutputStream dos;
    private InputStream is;
    private DataInputStream dis;
    private int opcion;

    public Cliente(){  
        try {
            System.out.println("Conectandose al servidor....");
            socket_client =  new Socket("localhost",9000);        
            os = socket_client.getOutputStream();
            dos = new DataOutputStream(os);
            is = socket_client.getInputStream();
            dis = new DataInputStream(is);              
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Error, no se puedo conectar al servidor");            
            System.exit(1);
        }              
    }

    public void login() throws IOException{
        Scanner sc =  new Scanner(System.in);
        Login login = new Login(sc);
        do{
            login.show(); 
            dos.writeInt(1);//Un uno, significa login
            dos.writeUTF(login.getUser());
            dos.writeUTF(login.getPass());
            int cmd = (int)dis.readInt();
            switch(cmd){
                case 0:
                    login.setMsg("Login incorrecto");
                    break;
                case 1:
                    MenuAdministrador menuAdmi = new MenuAdministrador(sc);
                    do{
                        menuAdmi.show();
                        switch(menuAdmi.getOpcion()){
                            case 1://ALTA ADMINISTRADOR
                            dos.writeInt(1);
                            AltaAdministrador altaAdmin = new AltaAdministrador(sc);
                            dos.writeUTF(altaAdmin.getNombre());
                            dos.writeUTF(altaAdmin.getUser());
                            dos.writeUTF(altaAdmin.getPass());
                            dos.writeUTF(altaAdmin.getNTelefonico());
                            dos.writeUTF(altaAdmin.getCorreo());
                            dos.writeInt(altaAdmin.getMatricula());
                            opcion=(int)dis.readInt();
                            if(opcion==0){
                                altaAdmin.setMsg("El administrador ya existe");
                            }else{
                                altaAdmin.setMsg("Administrador creado");
                            }                                 
                            break;
                            
                            case 2://BAJA ADMINISTRADOR
                            dos.writeInt(2);
                            BajaAdministrador bajaadmin =  new BajaAdministrador(sc);
                            bajaadmin.show();
                            dos.writeUTF(bajaadmin.getAdmin());
                            opcion=(int)dis.readInt();
                            if(opcion==0){
                                bajaadmin.setMsg("El administrador no existe");
                            }else{
                                bajaadmin.setMsg("Administrador eliminado");
                            }  
                            break;

                            case 3://LISTADOS - ADMINISTRADORES - MESTROS - ALUMNOS
                                
                            break;
                            case 4://Alta maestros
                                dos.writeInt(4);
                                
                                AltaMaestro altaMaestro = new AltaMaestro(sc);
                                dos.writeUTF(altaMaestro.getNombre());
                                dos.writeUTF(altaMaestro.getUser());
                                dos.writeUTF(altaMaestro.getPass());
                                dos.writeUTF(altaMaestro.getNTelefonico());
                                dos.writeUTF(altaMaestro.getCorreo());
                                dos.writeInt(altaMaestro.getMatricula());
                                opcion=(int)dis.readInt();
                                if(opcion==0){
                                    altaMaestro.setMsg("El maestro ya existe");
                                }else{
                                    altaMaestro.setMsg("Maestro creado");
                                }      
                            
                            break;
                            case 5://baja maestros
                                dos.writeInt(5);
                                BajaMaestro bajaMaestro =  new BajaMaestro(sc);
                                bajaMaestro.show();
                                dos.writeUTF(bajaMaestro.getMaestro());
                                opcion=(int)dis.readInt();
                                if(opcion==0){
                                    bajaMaestro.setMsg("El maestro no existe");
                                }else{
                                    bajaMaestro.setMsg("Maestro eliminado");
                                }  
                            break;
                            case 6://alta alumnos
                            
                            break;
                            case 7:// baja alumnos
                            
                            break;
                            case 8://alta materias
                           
                            break;
                            case 9://baja materias
                           
                            break;
                            case 10://lista materias
                            
                            break;
                            case 12://baja carreras
                           
                            break;
                            case 13://lista carreras
                            
                            break;
                            case 14://Ver DATOS
                            
                            break;
                            case 20://salir
                            break;
                            default://opcion incorrecta
                            menuAdmi.setMsg("Opcion incorrecta");
                        }

                    }while(menuAdmi.getOpcion()!=20);
                    break;
                case 2:
                    MenuMaestro menuMae = new MenuMaestro(sc);
                    menuMae.show();
                    break;
                case 3:
                    MenuAlumno menuAlu = new MenuAlumno(sc);
                    menuAlu.show();
                    break;
            }

        }while(true);
    }


    public static void main(String[] args) throws IOException{
        
        Cliente cliente = new Cliente();
        cliente.login();
        

    }
}
