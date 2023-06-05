package com.example.expo.Services;

import com.example.expo.Models.Materias;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriasDB {
    static Connection _cn;

    public MateriasDB(){
        _cn = new Conexion().openDB();
    }
    public List<Materias> ObtenerMaterias(){
        try {
            Statement stnt = _cn.createStatement();
            String query = "select * from tbMaterias";

            List<Materias> Materias = new ArrayList<>();

            ResultSet result = stnt.executeQuery(query);

            while(result.next()){
                Materias Materias2 = new Materias(
                        result.getInt("idMateria"),
                        result.getString("materia")
                );

                Materias.add(Materias2);

            }
            result.close();
            stnt.close();
            return Materias;
        } catch (Exception e) {
            System.out.println("ocurrio una excepcion en mascotasDB");
            int x = 1;
        }
        return null;
    }

    public static int InsertarMateria(Materias Materias){
        new MateriasDB();
        String sql1 = "exec AgregaMaterias ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, Materias.getMateria());
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
    public static int EliminarMaterias(int id){
        new MateriasDB();
        String sql1 = "exec DeleteMateria ?;";

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

    public static int Actulizar(Materias Materias){
        new MateriasDB();

        String sql1 = "exec UpdateMateria ?, ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, Materias.getMateria());
            statement.setInt(2, Materias.getIdMateria());
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
