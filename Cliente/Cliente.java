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

import Altas.AltaAlumno;
import Bajas.BajaAlumno;

import Altas.AltaMateria;
import Bajas.BajaMateria;

import Altas.AltaCarrera;
import Bajas.BajaCarrera;

import Altas.Calificaciones;
import Altas.SeleccionMateria;

import Menus.MenuAdministrador;
import Menus.MenuAlumno;
import Menus.MenuMaestro;

public class Cliente{
    private int cmd;
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
            
            dos.writeInt(0);
            dos.writeInt(20);

            dos.writeUTF(login.getUser());
            dos.writeUTF(login.getPass());
            cmd = (int)dis.readInt();
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
                            dos.writeInt(1);
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
                                dos.writeInt(1);
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
                                dos.writeInt(1);
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
                                dos.writeInt(6);
                                dos.writeInt(1);
                                AltaAlumno altaAlumno = new AltaAlumno(sc);
                                dos.writeUTF(altaAlumno.getNombre());
                                dos.writeUTF(altaAlumno.getUser());
                                dos.writeUTF(altaAlumno.getPass());
                                dos.writeUTF(altaAlumno.getNTelefonico());
                                dos.writeUTF(altaAlumno.getCorreo());
                                dos.writeInt(altaAlumno.getMatricula());
                                dos.writeInt(altaAlumno.getSemestre());
                                opcion=(int)dis.readInt();
                                if(opcion==0){
                                    altaAlumno.setMsg("El maestro ya existe");
                                }else{
                                    altaAlumno.setMsg("Maestro creado");
                                }  
                            break;
                            case 7:// baja alumnos
                                dos.writeInt(7);
                                dos.writeInt(1);
                                BajaAlumno bajaAlumno =  new BajaAlumno(sc);
                                bajaAlumno.show();
                                dos.writeUTF(bajaAlumno.getAlumno());
                                opcion=(int)dis.readInt();
                                if(opcion==0){
                                    bajaAlumno.setMsg("El alumno no existe");
                                }else{
                                    bajaAlumno.setMsg("Alumno eliminado");
                                }  
                            break;
                            case 8://alta materias
                                dos.writeInt(8);
                                dos.writeInt(1);
                                AltaMateria altaMat = new AltaMateria(sc);
                                dos.writeUTF(altaMat.getNombre());
                                dos.writeInt(altaMat.getSemestre());
                                dos.writeInt(altaMat.getMatricula());
                                opcion=(int)dis.readInt();
                                if(opcion==0){
                                    altaMat.setMsg("La materia ya existe");
                                }else{
                                    altaMat.setMsg("Materia creada");
                                }  
                            break;
                            case 9://baja materias
                                dos.writeInt(9);
                                dos.writeInt(1);
                                BajaMateria bajaMat =  new BajaMateria(sc);
                                bajaMat.show();
                                dos.writeUTF(bajaMat.getMateria());
                                opcion=(int)dis.readInt();
                                if(opcion==0){
                                    bajaMat.setMsg("La materia no existe");
                                }else{
                                    bajaMat.setMsg("Materia eliminada");
                                }  
                            break;
                            case 10://lista materias
                                
                            break;
                            case 11://alta carreras
                                dos.writeInt(11);
                                dos.writeInt(1);
                                AltaCarrera altaCar = new AltaCarrera(sc);
                                dos.writeUTF(altaCar.getNombre());
                                dos.writeInt(altaCar.getMatricula());
                                opcion=(int)dis.readInt();
                                if(opcion==0){
                                    altaCar.setMsg("La carrera ya existe");
                                }else{
                                    altaCar.setMsg("Carrera creada");
                                }  
                            break;

                            case 12://baja carreras
                                dos.writeInt(12);
                                dos.writeInt(1);
                                BajaCarrera bajaCar =  new BajaCarrera(sc);
                                bajaCar.show();
                                dos.writeUTF(bajaCar.getNombre());
                                opcion=(int)dis.readInt();
                                if(opcion==0){
                                    bajaCar.setMsg("La carrera no existe");
                                }else{
                                    bajaCar.setMsg("Carrera eliminada");
                                }  
                            break;
                            case 13://lista carreras
                            
                            break;
                            case 14://Ver DATOS
                            
                            break;

                            
                            case 20://salir                    
                            break;
                            

                        }
                    }while(menuAdmi.getOpcion()!=20);
                break;  
                case 2: 
                    MenuMaestro menuMae = new MenuMaestro(sc);
                    do{
                        menuMae.show();
                        switch(menuMae.getOpcion()){
                        case 1://alta calificaciones
                            dos.writeInt(1);
                            dos.writeInt(2);
                            Calificaciones calificacion = new Calificaciones(sc);
                            calificacion.show();
                            dos.writeUTF(calificacion.getMateria());
                            dos.writeUTF(calificacion.getAlumno());
                            dos.writeFloat(calificacion.getCalificacion());
                            opcion=(int)dis.readInt();
                            if(opcion==0){
                                calificacion.setMsg("La materia o el alumno no existe");
                            }else{
                                calificacion.setMsg("Calificacion creada");
                            }        

                        break;

                        case 2://corereccion calificaciones
                            dos.writeInt(2);
                            dos.writeInt(2);
                            Calificaciones calificacion2 = new Calificaciones(sc);
                            calificacion2.corregir();
                            dos.writeUTF(calificacion2.getMateria());
                            dos.writeUTF(calificacion2.getAlumno());
                            dos.writeFloat(calificacion2.getCalificacion());
                            opcion=(int)dis.readInt();
                            if(opcion==0){
                                calificacion2.setMsg("La materia o el alumno no existe");
                            }else{
                                calificacion2.setMsg("Calificacion Corregida");
                            }   
                        break;

                        case 3://seleccioanr materias
                            dos.writeInt(3);
                            dos.writeInt(2);
                            String nombreM=dis.readUTF();
                            int identificadorM=(int)dis.readInt();
                            SeleccionMateria seleccionMateria = new SeleccionMateria(sc,nombreM);
                            dos.writeUTF(seleccionMateria.getNombreMateria());
                            opcion=(int)dis.readInt();
                            if(opcion==0){
                                seleccionMateria.setMsg("La materia ya existia en el horario");
                            }else if(opcion==1){
                                seleccionMateria.setMsg("Materia agregada");
                            }else{
                                seleccionMateria.setMsg("La materia no existe");
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

                    }while(menuMae.getOpcion()!=20);
                break; 

                case 3:
                    MenuAlumno menuAlu = new MenuAlumno(sc);
                    menuAlu.show();
                break;


            }

        }while(true);
    }

    /*public void login() throws IOException{
        Scanner sc =  new Scanner(System.in);
        Login login = new Login(sc);
        do{
            login.show(); 
            System.out.print("hola");
            dos.writeInt(0);//Un uno, significa login
            dos.writeUTF(login.getUser());
            dos.writeUTF(login.getPass());
            cmd = (int)dis.readInt();
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
                                dos.writeInt(6);
                                
                                AltaAlumno altaAlumno = new AltaAlumno(sc);
                                dos.writeUTF(altaAlumno.getNombre());
                                dos.writeUTF(altaAlumno.getUser());
                                dos.writeUTF(altaAlumno.getPass());
                                dos.writeUTF(altaAlumno.getNTelefonico());
                                dos.writeUTF(altaAlumno.getCorreo());
                                dos.writeInt(altaAlumno.getMatricula());
                                dos.writeInt(altaAlumno.getSemestre());
                                opcion=(int)dis.readInt();
                                if(opcion==0){
                                    altaAlumno.setMsg("El maestro ya existe");
                                }else{
                                    altaAlumno.setMsg("Maestro creado");
                                }  
                            break;
                            case 7:// baja alumnos
                                dos.writeInt(7);
                                BajaAlumno bajaAlumno =  new BajaAlumno(sc);
                                bajaAlumno.show();
                                dos.writeUTF(bajaAlumno.getAlumno());
                                opcion=(int)dis.readInt();
                                if(opcion==0){
                                    bajaAlumno.setMsg("El alumno no existe");
                                }else{
                                    bajaAlumno.setMsg("Alumno eliminado");
                                }  
                            break;
                            case 8://alta materias
                                dos.writeInt(8);
                                AltaMateria altaMat = new AltaMateria(sc);
                                dos.writeUTF(altaMat.getNombre());
                                dos.writeInt(altaMat.getSemestre());
                                dos.writeInt(altaMat.getMatricula());
                                opcion=(int)dis.readInt();
                                if(opcion==0){
                                    altaMat.setMsg("La materia ya existe");
                                }else{
                                    altaMat.setMsg("Materia creada");
                                }  
                            break;
                            case 9://baja materias
                                dos.writeInt(9);
                                BajaMateria bajaMat =  new BajaMateria(sc);
                                bajaMat.show();
                                dos.writeUTF(bajaMat.getMateria());
                                opcion=(int)dis.readInt();
                                if(opcion==0){
                                    bajaMat.setMsg("La materia no existe");
                                }else{
                                    bajaMat.setMsg("Materia eliminada");
                                }  
                            break;
                            case 10://lista materias
                                
                            break;
                            case 11://alta carreras
                                dos.writeInt(11);
                                AltaCarrera altaCar = new AltaCarrera(sc);
                                dos.writeUTF(altaCar.getNombre());
                                dos.writeInt(altaCar.getMatricula());
                                opcion=(int)dis.readInt();
                                if(opcion==0){
                                    altaCar.setMsg("La carrera ya existe");
                                }else{
                                    altaCar.setMsg("Carrera creada");
                                }  
                            break;

                            case 12://baja carreras
                                dos.writeInt(12);
                                BajaCarrera bajaCar =  new BajaCarrera(sc);
                                bajaCar.show();
                                dos.writeUTF(bajaCar.getNombre());
                                opcion=(int)dis.readInt();
                                if(opcion==0){
                                    bajaCar.setMsg("La carrera no existe");
                                }else{
                                    bajaCar.setMsg("Carrera eliminada");
                                }  
                            break;
                            case 13://lista carreras
                            
                            break;
                            case 14://Ver DATOS
                            
                            break;
                            case 20://salir
                            dos.writeInt(20);
                            break;

                            default://opcion incorrecta
                            menuAdmi.setMsg("Opcion incorrecta");
                        }
                        

                    }while(menuAdmi.getOpcion()!=20);
                    cmd=(int)dis.readInt();
                    break;
                case 2:
                    MenuMaestro menuMae = new MenuMaestro(sc);
                    do{
                        menuMae.show();
                        switch(menuMae.getOpcion()){
                        case 1://alta calificaciones
                            dos.writeInt(1);
                            Calificaciones calificacion = new Calificaciones(sc);
                            calificacion.show();
                            dos.writeUTF(calificacion.getMateria());
                            dos.writeUTF(calificacion.getAlumno());
                            dos.writeFloat(calificacion.getCalificacion());
                            opcion=(int)dis.readInt();
                            if(opcion==0){
                                calificacion.setMsg("La materia o el alumno no existe");
                            }else{
                                calificacion.setMsg("Calificacion creada");
                            }        

                        break;

                        case 2://corereccion calificaciones
                            dos.writeInt(2);
                            Calificaciones calificacion = new Calificaciones(sc);
                            calificacion.coreregir();
                            dos.writeUTF(calificacion.getMateria());
                            dos.writeUTF(calificacion.getAlumno());
                            dos.writeFloat(calificacion.getCalificacion());
                            opcion=(int)dis.readInt();
                            if(opcion==0){
                                calificacion.setMsg("La materia o el alumno no existe");
                            }else{
                                calificacion.setMsg("Calificacion Corregida");
                            }   
                        break;

                        case 3://seleccioanr materias
                            dos.writeInt(3);
                            float nombreM=dis.readUTF();
                            int identificadorM=(int)dis.readInt();
                            SeleccionMateria seleccionMateria = new SeleccionMateria(sc,nombreM);



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

                    }while(menuMae.getOpcion()!=20);
                break;    
                    
                case 3:
                    MenuAlumno menuAlu = new MenuAlumno(sc);
                    menuAlu.show();
                    break;

                default:

                break;   
                case 1000:
                break; 
            }

        }while(true);
    }*/


    public static void main(String[] args) throws IOException{
        
        Cliente cliente = new Cliente();
        cliente.login();
        

    }
}
