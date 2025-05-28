package dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDAO {
    public static Connection getConnection(){
        try {
            return   ConnectionDB2.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };
}
