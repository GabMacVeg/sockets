import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;

import java.util.Scanner;



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
                    MenuAdministrador menuAdmin = new MenuAdministrador(sc);
                    menuAdmin.show();
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
