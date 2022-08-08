package models;

import java.sql.*;

public class Conexion {
    public static Connection createConnection(){
        final String url = "jdbc:postgresql://127.0.0.1:5432/db_tienda";
        final String user = "postgres";
        final String password = "admin";
        try{
            return DriverManager.getConnection(url, user, password);
        }catch(SQLException ex){
            System.out.println("Error en la Conexión: "+ ex.getMessage());
            return null;
        }
    }
}
