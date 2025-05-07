import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion
{
    public Connection get_connection()
    {
        Connection conexion= null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3308/mensajesapp","root","");
            if(conexion != null)
            {
                System.out.println("conexion exitosa");
            }
            else
            {
                System.out.println("No se pudo establecer la conexión es NULO");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return conexion;
    }
}
