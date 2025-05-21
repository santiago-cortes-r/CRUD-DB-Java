package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// complete singleton application
public class ConnectionDB2 {
    private static ConnectionDB2 dataSource = null;
    private Connection connected;

    private ConnectionDB2() throws SQLException {
        String url = "jdbc:mysql://localhost:3308/mensajesapp";
        String user = "root";
        String password = "";
        this.connected = DriverManager.getConnection(url, user, password);
        System.out.println("Conexión exitosa");
    }

    public static synchronized ConnectionDB2 getInstance() throws SQLException {
        if (dataSource == null) {
            dataSource = new ConnectionDB2();
        }
        return dataSource;
    }

    public Connection getConnection() {
        return this.connected;
    }
}
