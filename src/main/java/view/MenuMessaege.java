package view;

import model.Message;
import model.User;
import  service.MessegeCRUD;

import java.util.Scanner;

import static service.MessegeCRUD.readMessages;

public class MenuMessaege {

    public static void mostrarMenu(User resultado, Scanner sc) {

        int opcion = 0;

        do {
            System.out.println("--------------------");
            System.out.println("MENÚ DE MENSAJES:");
            System.out.println("1. Crear mensaje");
            System.out.println("2. Leer mensajes");
            System.out.println("3. editar");
            System.out.println("4. borrar");
            System.out.println("5. Salir");

            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Escribe tu mensaje: ");
                    String texto = sc.nextLine();


                    Message nuevoMensaje = new Message();
                    nuevoMensaje.setMessage(texto);
                    int id = resultado.getUserId();
                    nuevoMensaje.setAutor_Message(id);
                    MessegeCRUD.createMessage(nuevoMensaje);
                    break;

                case 2:
                    System.out.println("--------------------");
                    System.out.println("1. Traer todos los mensajes:");
                    System.out.println("2. buscar por id");
                    opcion = sc.nextInt();
                    sc.nextLine();
                    switch (opcion) {
                        case 1:
                            readMessages();
                            break;
                        case 2:
                            System.out.println("ingresa el id del mensaje a mostrar:");
                            id = sc.nextInt();
                            MessegeCRUD.readOneMessages(id);
                            break;
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID del mensaje que desea editar: ");
                    int idEditar = sc.nextInt();
                    sc.nextLine(); // Limpiar buffer

                    System.out.print("Nuevo mensaje: ");
                    String nuevoTexto = sc.nextLine();

                    System.out.print("Nuevo autor: ");
                    int nuevoAutor = sc.nextInt();

                    Message mensajeEditado = new Message();
                    mensajeEditado.setId_Message(idEditar);
                    mensajeEditado.setMessage(nuevoTexto);
                    mensajeEditado.setAutor_Message(nuevoAutor);

                    MessegeCRUD.updateMessage(mensajeEditado);
                    break;

                case 4:
                    System.out.print("Ingrese el ID del mensaje que desea eliminar: ");
                    int idEliminar = sc.nextInt();
                    sc.nextLine(); // Limpiar buffer
                    MessegeCRUD.deleteMessage(idEliminar);
                    break;

                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion !=5);
    }
}