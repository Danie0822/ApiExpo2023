package com.example.expo.Services;

import com.example.expo.Models.LlegadasTarde;
import com.example.expo.Models.NivelesAcademicos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LlegadasTardeDB {
    static Connection _cn;
    public LlegadasTardeDB(){
        _cn = new Conexion().openDB();
    }
    public CompletableFuture<List<?>> obtenerLlegadasTardeAsync() {
        return CompletableFuture.supplyAsync(() -> {
            String query = "select * from tbLlegadasTarde;";

            try (Statement stnt = _cn.createStatement()) {
                List<LlegadasTarde> LlegadasTarde = new ArrayList<>();

                ResultSet result = stnt.executeQuery(query);

                while(result.next()){
                    LlegadasTarde LlegadasTarde2 = new LlegadasTarde(
                            result.getInt("idLlegadaTarde"),
                            result.getInt("idTipoLlegadaTarde"),
                            result.getInt("idPersona"),
                            result.getInt("idPeriodo"),
                            result.getInt("idDocente"),
                            result.getString("TiposLlegadasTarde")
                    );

                    LlegadasTarde.add(LlegadasTarde2);
                }
                stnt.close();
                return LlegadasTarde;
            } catch (Exception e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        }).whenComplete((LlegadasTarde, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {

                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> insertarLlegadasTardeAsync(LlegadasTarde LlegadasTarde) {
        return CompletableFuture.supplyAsync(() -> {
            new LlegadasTardeDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec AgregarLlegadasTarde ?, ? , ?, ?, ?, ?;");
                statement.setString(1,LlegadasTarde.getTiposLlegadasTarde());
                statement.setInt(2, LlegadasTarde.getIdLlegadaTarde());
                statement.setInt(3, LlegadasTarde.getIdTipoLlegadaTarde());
                statement.setInt(4, LlegadasTarde.getIdPersona());
                statement.setInt(5, LlegadasTarde.getIdPeriodo());
                statement.setInt(6, LlegadasTarde.getIdDocente());
                statement.executeUpdate();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            } finally {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if (_cn != null && !_cn.isClosed()) {
                        _cn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static CompletableFuture<Integer> eliminarLlegadasTardeAsync(int id){
        return CompletableFuture.supplyAsync(() -> {
            new LlegadasTardeDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec DeleteLlegadasTarde ?;");
                statement.setInt(1, id);

                statement.executeUpdate();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            } finally {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if (_cn != null && !_cn.isClosed()) {
                        _cn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });



    }

    public static CompletableFuture<Integer> ActualizarLlegadasTardeAsync(LlegadasTarde LlegadasTarde){
        return CompletableFuture.supplyAsync(() -> {
            new LlegadasTardeDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec UpdateLlegadasTarde ?, ?, ?, ?, ?;");
                statement.setString(1,LlegadasTarde.getTiposLlegadasTarde());
                statement.setInt(2, LlegadasTarde.getIdTipoLlegadaTarde());
                statement.setInt(3, LlegadasTarde.getIdPersona());
                statement.setInt(4, LlegadasTarde.getIdPeriodo());
                statement.setInt(5, LlegadasTarde.getIdDocente());
                statement.executeUpdate();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            } finally {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if (_cn != null && !_cn.isClosed()) {
                        _cn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

