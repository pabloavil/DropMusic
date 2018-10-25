
import java.util.Scanner;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIClient {

    private static void menuEditar(){

        try {
            RMIServerInterface server = (RMIServerInterface) LocateRegistry.getRegistry(7000).lookup("servidor");

            int n;

            do {

                System.out.println("");
                System.out.println("1. Editar Artista");
                System.out.println("2. Editar Album");
                System.out.println("3. Editar Cancion");
                System.out.println("4. Volver");

                Scanner sc = new Scanner(System.in);
                System.out.print("Introduzca un número: ");
                n = sc.nextInt();

                switch (n) {
                    case 1:
                        menuCrearArtista(server);
                        break;
                    case 2:
                        menuCrearAlbum(server);
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            } while (n != 4);
        } catch (NotBoundException e) {
        e.printStackTrace();
    } catch (RemoteException e) {
        e.printStackTrace();
    }

    }

    private static void menuCrearArtista(RMIServerInterface server){

        int n;
        do {
            System.out.println("1. Añadir artista");
            System.out.println("2. Editar artista");
            System.out.println("3. Eliminar artista");
            System.out.println("4. Volver");
            System.out.println("Selecciona una opción: ");
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
        }while(n < 0 || n > 4);

        switch (n){
            case 1:
                System.out.println("Vas a añadir un nuevo artista");
                Scanner sc = new Scanner(System.in);
                System.out.print("Introduce el nombre del artista: ");
                String a = sc.nextLine();
                Scanner sc1 = new Scanner(System.in);
                System.out.print("Introduce el genero de la musica del artista: ");
                String g = sc1.nextLine();
                //Artista ar = new Artista(a, g);
                try {
                    boolean r = server.anadirArtista(a, g);

                    if(r){
                        System.out.println("El artista ha sido añadido correctamente.");
                    }else{
                        System.out.println("Ya existe un artista con ese nombre");
                    }
                } catch (RemoteException e) {
                    System.out.println("No se pudo añadir el artista.");
                    e.printStackTrace();
                }
                menuCrearArtista(server);
                break;
            case 2:
                System.out.println("Editar un artista");
                Scanner sc2 = new Scanner(System.in);
                System.out.print("Introduce el nombre del artista que deseas editar: ");
                String a1 = sc2.nextLine();
                System.out.print("Introduce el nombre del artista que deseas editar: ");
                String a11 = sc2.nextLine();
                System.out.print("Introduce el nuevo genero del artista: ");
                String a111 = sc2.nextLine();




                try {

                    boolean r = server.editarArtista(a1,a11,a111);
                    if(r){
                        System.out.println("Se han modificado los datos");
                    }else{
                        System.out.println("No existe ese artista o el nuevo nombre ya existe");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                menuCrearArtista(server);
                break;




            case 3:
                System.out.println("Eliminar un artista");
                Scanner sc3 = new Scanner(System.in);
                System.out.print("Introduce el nombre del artista que deseas eliminar: ");
                String a2 = sc3.nextLine();

                try {
                    boolean r = server.eliminarArtista(a2);
                    if(r){
                        System.out.println("Artista eliminado.");
                    }else{
                        System.out.println("No existe ese artista");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                break;
        }

    }


   private static void menuCrearAlbum(RMIServerInterface server){

       int n;
       do {
           System.out.println("1. Añadir Album");
           System.out.println("2. Editar Album");
           System.out.println("3. Eliminar Album");
           System.out.println("4. Volver");
           System.out.println("Selecciona una opción: ");
           Scanner sc = new Scanner(System.in);
           n = sc.nextInt();
       }while(n < 0 || n > 4);

       switch (n){
           case 1:

               System.out.println("Vas a añadir un nuevo album");
               Scanner sc = new Scanner(System.in);
               System.out.print("Introduce el titulo del album: ");
               String titulo = sc.nextLine();
               System.out.print("Introduce el nombre del artista: ");
               String nombArtista = sc.nextLine();
               System.out.print("Introduce una descripccion del album: ");
               String descripcion = sc.nextLine();

               Artista artista=new Artista();
               artista.setNombre(nombArtista);
               Album album = new Album(titulo,artista,descripcion);

               System.out.println(album.getNombre());


               try {
                   boolean r = server.anadirAlbum(album);

                   if(r){
                       System.out.println("El album ha sido añadido correctamente.");
                   }else{
                       System.out.println("Ya existe un album con ese nombre");
                   }
               } catch (RemoteException e) {
                   System.out.println("No se pudo añadir el album.");
                   e.printStackTrace();
               }
               menuCrearAlbum(server);
               break;
           case 2:

               break;




           case 3:

               break;
           case 4:
               break;
       }

   }
    public void menuGestion(){
        System.out.println("1. Registrarse");
        System.out.println("2. Autenticarse");
        System.out.println("3. Salir");
    }
    public static void main(String args[]) {
        try {
            RMIServerInterface server = (RMIServerInterface) LocateRegistry.getRegistry(7000).lookup("servidor");
            System.out.println(server.sayHello());
           // Artista a = server.obtenerArtista("pedro");
            //a.mostrar();
            int n;
            do{
                System.out.println("");
                System.out.println("1. Registrarse");
                System.out.println("2. Autenticarse");
                System.out.println("3. Salir");

                Scanner sc = new Scanner(System.in);
                System.out.print("Introduzca un número: ");
                n = sc.nextInt();
            }while(n<0 || n>3);
            boolean r = false;
            if(n == 1){
                do {

                    System.out.println("Autenticación DropMusic");
                    System.out.println("Introduzca su nombre: ");
                    Scanner sc1 = new Scanner(System.in);
                    String nom = sc1.nextLine();
                    System.out.println("Introduzca sus apellidos: ");
                    Scanner sc2 = new Scanner(System.in);
                    String ape = sc2.nextLine();
                    System.out.println("Introduzca su nombre de usuario: ");
                    Scanner sc3 = new Scanner(System.in);
                    String usu = sc3.nextLine();
                    System.out.println("Introduzca su contrasena: ");
                    Scanner sc4 = new Scanner(System.in);
                    String pass = sc4.nextLine();

                    r = server.regisUser(nom, ape, usu, pass);

                    if(r){
                        System.out.print("Usuario registrado correctamente.");
                    }else{
                        System.out.print("Error al registrar usuario. Inténtelo de nuevo.");
                    }

                }while( r == false);
            }else if(n == 2){
                System.out.println("Autenticación DropMusic");
                do {
                    System.out.print("Nombre de usuario: ");
                    Scanner sc1 = new Scanner(System.in);
                    String user = sc1.nextLine();
                    System.out.print("Contrasena: ");
                    Scanner sc2 = new Scanner(System.in);
                    String pass = sc2.nextLine();
                    r = server.login(user, pass);

                    System.out.println(r);
                    if(r){
                        System.out.println("Te has logueado.");
                    }
                }while(r == false);
            }else{
                System.exit(0);
            }


            if(r){
                do {

                    System.out.println("");
                    System.out.println("1. Editar Artista Album o Cancion(Editor)");
                    System.out.println("2. Ver Musica Artista o Album");
                    System.out.println("3. Consulta detalles de Album o Artista");
                    System.out.println("4. Escribe una critica de un Album");
                    System.out.println("5. Dar privilegios de editor(Editor)");
                    System.out.println("6. Subir musica");
                    System.out.println("7. Compartir musica");
                    System.out.println("8. Bajar musica");
                    System.out.println("9. Salir ");

                    Scanner sc = new Scanner(System.in);
                    System.out.print("Introduzca un número: ");
                    n = sc.nextInt();

                    switch(n){
                        case 1:
                            menuEditar();
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 55:
                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                        case 8:
                            break;
                        default:
                            break;

                    }



                }while (n!=9);





            }

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
