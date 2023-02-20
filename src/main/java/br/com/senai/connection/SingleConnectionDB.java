package br.com.senai.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SingleConnectionDB {
    private static Connection connection = null;

    public static Connection getConnection(){
        if (connection != null){
            return connection;
        }
        else {
            try {
                Properties prop = new Properties();

                String user = "postgres";
                String password = "9925";
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pessoas-db", user, password);


            } catch (ClassNotFoundException e){
                e.printStackTrace();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }

}
