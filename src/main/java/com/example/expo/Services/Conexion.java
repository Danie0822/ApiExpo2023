package com.example.expo.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public Connection openDB(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            String connectionUrl = "jdbc:sqlserver://expo2023.mssql.somee.com;"
            + "databaseName=expo2023;"
            + "user=Daniel0822_SQLLogin_1;"
            + "password=upb7jvougo;"
            + "trustServerCertificate=true";
            Connection con = DriverManager.getConnection(connectionUrl);
            return con;
        } catch (SQLException E) {
            System.out.println("ERROR DE CONEXION xd");
            int x = 1;
        }
        catch(ClassNotFoundException cnfex){
            System.out.println("error en clase conexion");
            int x = 1;
        }
        return null;
    }
    
}
