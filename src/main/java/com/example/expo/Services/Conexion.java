package com.example.expo.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public Connection openDB(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            String connectionUrl = "jdbc:sqlserver://SAE2023.mssql.somee.com;"
            + "databaseName=SAE2023;"
            + "user=Expo_SQLLogin_2;"
            + "password=u2agtmow35;"
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
