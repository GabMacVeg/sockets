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
                    do{
                        menuAdmi.show();
                        switch(menuAdmi.getOpcion()){
                            case 1://ALTA ADMINISTRADOR
                            dos.writeInt(2);
                            /*
                                AltaAdministrador altaadmin = new AltaAdministrador(sc);
                                existe = modeloAdministrador.buscarAdmi(altaadmin.getAdmin().getUser());
                                if(existe)
                                    altaadmin.setMsg("El usuario ya existe!");
                                else{
                                    modeloAdministrador.alta(altaadmin.getAdmin());    
                                    altaadmin.setMsg("Usuario creado"); 
                                } 
                                */                     
                            break;
                            
                            case 2://BAJA ADMINISTRADOR
                                BajaAdministrador bajaadmin =  new BajaAdministrador(sc);
                                    bajaadmin.show();
                                    existe = modeloAdministrador.buscarAdmi(bajaadmin.getAdmin());
                                    if(existe){
                                        //Existe el usuario
                                        modeloAdministrador.eliminar(bajaadmin.getAdmin());
                                        bajaadmin.setMsg("Se da de baja!");
                                    }else{
                                        bajaadmin.setMsg("Usuario inexistente");
                                    }
                            break;

                            case 3://LISTADOS - ADMINISTRADORES - MESTROS - ALUMNOS
                            
                                ListaAdministrador listadoadmi  = new ListaAdministrador();
                                ListaMaestro listadomae  = new ListaMaestro();
                                ListaAlumno listadoalu = new ListaAlumno();
                                do{
                                    menuAdmi.showVer();
                                    switch(menuAdmi.getVer()){
                                        case 1: //Ver Administradores
                                            listadoadmi.imprimirAdministradores(modeloAdministrador.getAdministradores());
                                        break;
                                        case 2://Ver Maestros
                                            listadomae.imprimirMaestros(modeloMaestro.getMaestros());
                                        break;
                                        case 3://Ver Alumnos
                                            listadoalu.imprimirAlumnos(modeloAlumno.getAlumnos());
                                        break;
                                        case 4://Ver Todos
                                            listadoadmi.imprimirAdministradores(modeloAdministrador.getAdministradores());
                                            listadomae.imprimirMaestros(modeloMaestro.getMaestros());
                                            listadoalu.imprimirAlumnos(modeloAlumno.getAlumnos());
                                        break;
                                        case 5://Salir
                                        
                                        break;
                                    }         
                                }while(menuAdmi.getVer()!=5);
                            break;
                            case 4://Alta maestros
                                AltaMaestro altamae = new AltaMaestro(sc);
                                existe = modeloMaestro.buscarMaestro(altamae.getMaestro().getUser());
                                if(existe)
                                    altamae.setMsg("El Maestro ya existe!");
                                else{
                                    modeloMaestro.alta(altamae.getMaestro());    
                                    altamae.setMsg("Maestro creado"); 
                                }
                            break;
                            case 5://baja maestros
                                BajaMaestro bajamae =  new BajaMaestro(sc);
                                    bajamae.show();
                                    existe = modeloMaestro.buscarMaestro(bajamae.getMaestro());
                                    if(existe){
                                        //Existe el usuario
                                        modeloMaestro.eliminar(bajamae.getMaestro());
                                        bajamae.setMsg("Se da de baja!");
                                    }else{
                                        bajamae.setMsg("Maestro inexistente");
                                    }
                            break;
                            case 6://alta alumnos
                                AltaAlumno altaalum = new AltaAlumno(sc);
                                existe = modeloAlumno.buscarAlumno(altaalum.getAlumno().getUser());
                                if(existe)
                                    altaalum.setMsg("El Alumno ya existe!");
                                else{
                                    modeloAlumno.alta(altaalum.getAlumno());    
                                    altaalum.setMsg("Alumno creado"); 
                                }
                            break;
                            case 7:// baja alumnos
                                BajaAlumno bajaalum =  new BajaAlumno(sc);
                                    bajaalum.show();
                                    existe = modeloAlumno.buscarAlumno(bajaalum.getAlumno());
                                    if(existe){
                                        //Existe el usuario
                                        modeloAlumno.eliminar(bajaalum.getAlumno());
                                        bajaalum.setMsg("Se da de baja!");
                                    }else{
                                        bajaalum.setMsg("Alumno inexistente");
                                    }
                            break;
                            case 8://alta materias
                                AltaMateria altamat = new AltaMateria(sc);
                                existe = modeloMateria.buscarMateria(altamat.getMateria().getNombre());
                                if(existe)
                                    altamat.setMsg("La Materia ya existe!");
                                else{
                                    modeloMateria.altaMateria(altamat.getMateria());    
                                    altamat.setMsg("Materia creada"); 
                                }
                            break;
                            case 9://baja materias
                                BajaMateria bajamat =  new BajaMateria(sc);
                                    bajamat.show();
                                    existe = modeloMateria.buscarMateria(bajamat.getMateria());
                                    if(existe){
                                        //Existe la materia
                                        modeloMateria.eliminar(bajamat.getMateria());
                                        bajamat.setMsg("Se da de baja!");
                                    }else{
                                        bajamat.setMsg("Materia inexistente");
                                    }
                            break;
                            case 10://lista materias
                                ListaMaterias listadomaterias = new ListaMaterias();
                                listadomaterias.imprimirMaterias(modeloMateria.getMaterias());
                            break;
                            case 11://alta carreras
                            AltaCarrera altacarrera = new AltaCarrera(sc);
                                existe = modeloCarrera.buscarCarrera(altacarrera.getCarrera().getNombre());
                                if(existe)
                                    altacarrera.setMsg("La carrera  ya existe!");
                                else{
                                    modeloCarrera.altacarrera(altacarrera.getCarrera());
                                    altacarrera.setMsg("La carrera se creo");
                                }
                            break;
                            case 12://baja carreras
                            BajaCarrera bajacarrera = new BajaCarrera(sc);
                                do{
                                    bajacarrera.show();
                                    existe = modeloCarrera.buscarCarrera(bajacarrera.getNombre());
                                    if(existe){
                                        modeloCarrera.eliminar(bajacarrera.getNombre());
                                        bajacarrera.setMsg("Se da de baja!");
                                    }else{
                                        bajacarrera.setMsg("Carrera inexistente. Intenta de nuevo!");
                                    }
                                }while(!existe);
                            break;
                            case 13://lista carreras
                                ListaCarreras listadocarreras = new ListaCarreras();
                                listadocarreras.imprimirCarreras(modeloCarrera.getCarreras());  
                            break;
                            case 14://Ver DATOS
                                VerDatosAdmin verDatosAdmin = new VerDatosAdmin();
                                verDatosAdmin.imprimirDatosAdmin(modeloAdministrador.getAdministradores(),nombreAd);
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
