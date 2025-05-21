package dao; // data acces object - # Acceso a datos (conexión a la BD, consultas SQL)

import model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    public static boolean insertMessage(Message message) {

        String query = "INSERT INTO mensajes (mensaje, autor_mensaje) VALUES (?, ?)";

        try (Connection db_connect = ConnectionDB2.getInstance().getConnection();
             PreparedStatement ps = db_connect.prepareStatement(query))
        {

            ps.setString(1, message.getMessage());
            ps.setString(2, message.getAutor_Message());
            ps.executeUpdate();
            System.out.println("Mensaje creado");
            return true;

        } catch (SQLException ex) {
            System.out.println("Error al crear el mensaje: " + ex.getMessage());
            return false;
        }
    }
    public static List<Message> getAllMessagesDB() {

        String query="SELECT * FROM mensajes";
        List<Message> recoleccion = new ArrayList<>();

        try (
                Connection db_connect = ConnectionDB2.getInstance().getConnection();
                PreparedStatement ps = db_connect.prepareStatement(query);
                ResultSet rs = ps.executeQuery()
        ){
            while(rs.next()){

                Message mensaje = new Message();

                mensaje.setId_Message(rs.getInt("id_mensaje"));
                mensaje.setMessage(rs.getString("mensaje"));
                mensaje.setAutor_Message(rs.getString("autor_mensaje"));
                mensaje.setFecha_Message(rs.getString("fecha_mensaje"));

                recoleccion.add(mensaje);
            }
        }catch(SQLException e){
            System.out.println("no se pudieron recuperar los mensajes");
            System.out.println(e);
        }
        return recoleccion;
    }

    public static Message getOneMessagesDB(int id) {
        String query = "SELECT * FROM mensajes WHERE id_mensaje = ?";
        Message mensaje = null;

        try (
                Connection db_connect = ConnectionDB2.getInstance().getConnection();
                PreparedStatement ps = db_connect.prepareStatement(query)
        ) {
            ps.setInt(1, id); // Establecer el valor del parámetro ?

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mensaje = new Message();
                    mensaje.setId_Message(rs.getInt("id_mensaje"));
                    mensaje.setMessage(rs.getString("mensaje"));
                    mensaje.setAutor_Message(rs.getString("autor_mensaje"));
                    mensaje.setFecha_Message(rs.getString("fecha_mensaje"));
                }
            }

        } catch (SQLException e) {
            System.out.println("No se pudo recuperar el mensaje");
            System.out.println(e);
        }

        return mensaje;
    }
    // package dao;

    public static boolean deleteMessage(int id) {
        String query = "DELETE FROM mensajes WHERE id_mensaje = ?";

        try (
                Connection db_connect = ConnectionDB2.getInstance().getConnection();
                PreparedStatement ps = db_connect.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Mensaje eliminado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró un mensaje con ese ID.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar el mensaje: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateMessage(Message message) {
        String query = "UPDATE mensajes SET mensaje = ?, autor_mensaje = ? WHERE id_mensaje = ?";

        try (
                Connection db_connect = ConnectionDB2.getInstance().getConnection();
                PreparedStatement ps = db_connect.prepareStatement(query)
        ) {
            ps.setString(1, message.getMessage());
            ps.setString(2, message.getAutor_Message());
            ps.setInt(3, message.getId_Message());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Mensaje actualizado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró un mensaje con ese ID.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar el mensaje: " + e.getMessage());
            return false;
        }
    }




}
