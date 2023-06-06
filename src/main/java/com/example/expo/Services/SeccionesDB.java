package com.example.expo.Services;


import com.example.expo.Models.Secciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeccionesDB {
    static Connection _cn;

    public SeccionesDB(){
        _cn = new Conexion().openDB();
    }
    public List<Secciones> ObtenerSecciones(){
        try {
            Statement stnt = _cn.createStatement();
            String query = "select * from tbSecciones";

            List<Secciones> Secciones = new ArrayList<>();

            ResultSet result = stnt.executeQuery(query);

            while(result.next()){
                Secciones Secciones2 = new Secciones(
                        result.getInt("idSeccion"),
                        result.getString("seccion")
                );

                Secciones.add(Secciones2);

            }
            result.close();
            stnt.close();
            return Secciones;
        } catch (Exception e) {
            System.out.println("ocurrio una excepcion en Secciones");
            int x = 1;
        }
        return null;
    }

    public static int InsertarSecciones(Secciones Secciones){
        new SeccionesDB();
        String sql1 = "exec AgregarSecciones ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, Secciones.getSeccion());
            try {
                statement.executeUpdate();
                _cn.close();
                statement.close();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;
    }
    public static int EliminarSecciones(int id){
        new SeccionesDB();
        String sql1 = "exec DeleteSeccion ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setInt(1, id);

            try {
                statement.executeUpdate();
                _cn.close();
                statement.close();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;



    }

    public static int Actulizar(Secciones Secciones){
        new SeccionesDB();

        String sql1 = "exec UpdateSecciones ?, ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, Secciones.getSeccion());
            statement.setInt(2, Secciones.getIdSeccion());
            try {
                statement.executeUpdate();
                _cn.close();
                statement.close();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;
    }
}
