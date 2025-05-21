package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// singleton partial database connection
public class ConnectionDb
{
    private static Connection MyConnectionDb;
    private ConnectionDb(){}

    public static Connection get_connection() throws SQLException {
        if (MyConnectionDb == null || MyConnectionDb.isClosed())
        {
            try {
                MyConnectionDb = DriverManager.getConnection("jdbc:mysql://localhost:3308/mensajesapp", "root", "");
                if (MyConnectionDb != null) {
                    System.out.println("conexion exitosa");
                } else {
                    System.out.println("No se pudo establecer la conexión es NULO");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return MyConnectionDb;
    }
}
