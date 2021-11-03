import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;

import java.util.Scanner;

import Cliente.Login;
import Cliente.Menus.MenuAdministrador;
import Cliente.Altas.AltaAdministrador;
import Cliente.Bajas.BajaAdministrador;


public class Cliente{
    private Scanner sc =  new Scanner(System.in);
    private Socket socket_client;
    private OutputStream os;
    private DataOutputStream dos;
    private InputStream is;
    private DataInputStream dis;

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

    public void administrador() throws IOException{
        MenuAdministrador menuAdm = new MenuAdministrador(sc);
        do{
            menuAdm.show();
            //dos.writeInt(2);//Enviar una opcion del administrador
            //dos.writeInt(menuAdm.getOpcion());
            switch(menuAdm.getOpcion()){
                case 1://Altas de Usuarios
                    AltaUsuarios alta = new AltaUsuarios(sc);
                    dos.writeInt(3);
                    dos.writeUTF(alta.getUser());
                    boolean existe = dis.readBoolean();
                    if(existe)
                        alta.setMsg("El usuario ya existe!");
                    else{
                        dos.writeInt(2);
                        dos.writeUTF(alta.getUser());
                        dos.writeUTF(alta.getNombre());
                        dos.writeUTF(alta.getPass());
                        dos.writeInt(alta.getTipo());
                        //modeloUsuarios.alta(alta.getUsuario());                    
                    }
                    
                    break;
                case 2://Bajas
                    /*BajaUsuarios baja =  new BajaUsuarios(sc);                    
                    do{
                        baja.show();
                        
                        existe = modeloUsuarios.buscarUsuario(baja.getUsuario());
                        if(existe){
                            //Existe el usuario
                            modeloUsuarios.eliminar(baja.getUsuario());
                            //baja.setMsg("Se da de baja!");
                        }else{
                            baja.setMsg("Usuario inexistente. Intenta de nuevo!");
                        }
                    }while(!existe);*/
                    break;
                case 3://Salir
                    dos.writeInt(9999);
                    System.exit(1);
                    break;
                default://Opcion incorrecta
                    menuAdm.setMsg("Opcion Incorrecta");
            }
           
        }while(menuAdm.getOpcion()!=3);
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
                    administrador();
                    break;

            }

        }while(true);
    }

    /*
        Login
        Cliente       -----------------    Servidor
            1         ---------------->      1
          user        ---------------->      user
          pass        ---------------->      pass
          ERROR        <---------------       0
          ADMIN        <---------------       1
          VENTAS       <---------------       2
    
    */

        

    public static void main(String[] args) throws IOException{
        
        Cliente cliente = new Cliente();
        cliente.login();
        

    }
}
