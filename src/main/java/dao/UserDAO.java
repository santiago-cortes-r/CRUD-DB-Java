package dao;


import model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static void createUserOnDB(User user) {
        String sql = "INSERT INTO usuarios (email, password, full_name) VALUES (?, ?, ?)";

        try (Connection con = ConnectionDB2.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.executeUpdate();
            System.out.println("Usuario creado con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al crear el usuario: " + e.getMessage());
        }
    }

    public static List<User> listUsersOnDB() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection con = ConnectionDB2.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("full_name")
                );
                users.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }

        return users;
    }

    public static void editUserOnDB(User user) {
        String sql = "UPDATE usuarios SET email=?, password=?, full_name=? WHERE user_id=?";

        try (Connection con = ConnectionDB2.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.setInt(4, user.getUserId());

            ps.executeUpdate();
            System.out.println("Usuario actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al editar usuario: " + e.getMessage());
        }
    }

    public static User loginDB(User login) {
        String sql = "SELECT * FROM usuarios WHERE email=? AND password=?";
        try (Connection con = ConnectionDB2.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, login.getEmail());
            ps.setString(2, login.getPassword());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("full_name")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }
        return null;
    }
}
