package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    public static Connection con;

    public DAO() {
        if (con == null) {
            String dbUrl = "jdbc:mySQL://localhost:3306/battleship_game?autoReconnect=true&useSSL=false";
            String dbClass = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection(dbUrl, "root", "cuongngocdo203");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
