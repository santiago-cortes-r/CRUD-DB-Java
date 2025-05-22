package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;


// complete singleton application
public class ConnectionDB2 {
    private static ConnectionDB2 dataSource = null;
    private Connection connected;

    private ConnectionDB2() throws SQLException {
        Dotenv dotenv = Dotenv.load();
        try{
            connected = DriverManager.getConnection("jdbc:mysql://"
                            +dotenv.get("DATABASE_HOST")+
                            ":"+dotenv.get("DATABASE_PORT")+
                            "/"+dotenv.get("DATABASE_NAME")+"",
                    dotenv.get("DATABASE_USER"),
                    dotenv.get("DATABASE_PASSWORD"));
            System.out.println("Conexión exitosa");
        }catch(Exception e){
            System.out.println(e);
        }

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
