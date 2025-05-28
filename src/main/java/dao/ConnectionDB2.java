package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// complete singleton application
public class ConnectionDB2 {
    private static ConnectionDB2 dataSource = null;
    private Connection connected;

    private ConnectionDB2() throws SQLException {
        openConnection();
    }

    public void openConnection(){
        try(InputStream in = getClass().getResourceAsStream("/application.properties")){
            Properties props = new Properties();
            props.load(in);

            connected = DriverManager.getConnection("jdbc:mysql://"
                            +props.getProperty("DATABASE_HOST")+
                            ":"+props.getProperty("DATABASE_PORT")+
                            "/"+props.getProperty("DATABASE_NAME")+"",
                    props.getProperty("DATABASE_USER"),
                    props.getProperty("DATABASE_PASSWORD"));
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

    public Connection getConnection() throws SQLException {
        if (this.connected == null || this.connected.isClosed()) {
            openConnection();
        }
        return this.connected;
    }
}
