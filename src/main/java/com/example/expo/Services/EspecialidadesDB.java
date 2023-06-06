package com.example.expo.Services;



import com.example.expo.Models.Especialidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadesDB {

    static Connection _cn;

    public EspecialidadesDB(){
        _cn = new Conexion().openDB();
    }
    public List<Especialidades> ObtenerEspecialidades(){
        try {
            Statement stnt = _cn.createStatement();
            String query = "select * from tbEspecialidades";

            List<Especialidades> Especialidades = new ArrayList<>();

            ResultSet result = stnt.executeQuery(query);

            while(result.next()){
                Especialidades Especialidades2 = new Especialidades(
                        result.getInt("idEspecialidad"),
                        result.getString("especialidad")
                );

                Especialidades.add(Especialidades2);

            }
            result.close();
            stnt.close();
            return Especialidades;
        } catch (Exception e) {
            System.out.println("ocurrio una excepcion en Secciones2");
            int x = 1;
        }
        return null;
    }

    public static int InsertarEspecialidades(Especialidades Especialidades){
        new EspecialidadesDB();
        String sql1 = "exec AgregarEspecialidades ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, Especialidades.getEspecialidad());
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
    public static int EliminarEspecialidades(int id){
        new EspecialidadesDB();
        String sql1 = "exec DeleteEspecialidades ?;";

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

    public static int Actulizar(Especialidades Especialidades){
        new EspecialidadesDB();

        String sql1 = "exec UpdateEspecialidades ?, ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, Especialidades.getEspecialidad());
            statement.setInt(2, Especialidades.getIdEspecialidad());
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
