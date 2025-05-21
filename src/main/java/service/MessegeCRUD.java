package service;
import dao.ConnectionDB2;
import dao.MessageDAO;
import model.Message;
import view.Utils;

import java.util.List;

public class MessegeCRUD
{
    public static void createMessage(Message nuevoMensaje) {
        if (nuevoMensaje.getMessage() == null || nuevoMensaje.getMessage().isEmpty() ||
                nuevoMensaje.getAutor_Message() == null || nuevoMensaje.getAutor_Message().isEmpty()) {
            System.out.println("Error: mensaje o autor vacío.");
            return;
        }

        boolean success = MessageDAO.insertMessage( nuevoMensaje);
        if (success) {
            System.out.println("Mensaje creado exitosamente.");
        } else {
            System.out.println("Error al crear el mensaje.");
        }
    }

    public static void readMessages() {
        List<Message> messages = MessageDAO.getAllMessagesDB();

        // Anchos fijos
        final int ID_WIDTH = 5;
        final int MSG_WIDTH = 30;
        final int AUTOR_WIDTH = 20;
        final int FECHA_WIDTH = 20;

        String separatorLine = "+" +
                "-".repeat(ID_WIDTH + 2) + "+" +
                "-".repeat(MSG_WIDTH + 2) + "+" +
                "-".repeat(AUTOR_WIDTH + 2) + "+" +
                "-".repeat(FECHA_WIDTH + 2) + "+";

        // Encabezado
        System.out.println(separatorLine);
        System.out.printf("| %s | %s | %s | %s |\n",
                Utils.truncateAndPad("ID", ID_WIDTH),
                Utils.truncateAndPad("Mensaje", MSG_WIDTH),
                Utils.truncateAndPad("Autor", AUTOR_WIDTH),
                Utils.truncateAndPad("Fecha", FECHA_WIDTH));
        System.out.println(separatorLine);

        // Filas
        for (Message msg : messages) {
            System.out.printf("| %s | %s | %s | %s |\n",
                    Utils.truncateAndPad(String.valueOf(msg.getId_Message()), ID_WIDTH),
                    Utils.truncateAndPad(msg.getMessage(), MSG_WIDTH),
                    Utils.truncateAndPad(msg.getAutor_Message(), AUTOR_WIDTH),
                    Utils.truncateAndPad(msg.getFecha_Message(), FECHA_WIDTH));
        }

        System.out.println(separatorLine);
    }





    public static void readOneMessages(int id){
         Message mensaje = MessageDAO.getOneMessagesDB(id);
        System.out.println("ID: " + mensaje.getId_Message());
        System.out.println("Mensaje: " + mensaje.getMessage());
        System.out.println("Autor: " + mensaje.getAutor_Message());
        System.out.println("Fecha: " + mensaje.getFecha_Message());
        System.out.println();

    }

    public static void deleteMessage(int id) {
        boolean result = MessageDAO.deleteMessage(id);
        if (!result) {
            System.out.println("No se pudo eliminar el mensaje.");
        }
    }


    // package service;

    public static void updateMessage(Message message) {
        boolean result = MessageDAO.updateMessage(message);
        if (!result) {
            System.out.println("No se pudo actualizar el mensaje.");
        }
    }



}
