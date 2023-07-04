package com.example.expo.Services;

import com.example.expo.Models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
public class InasistenciasDB {

    static Connection _cn;

    public InasistenciasDB() { _cn = new Conexion().openDB();}

    public CompletableFuture<List<?>> obtenerInasistenciasAsync() {
        return CompletableFuture.supplyAsync(() -> {
            String query = "select * from tbInasisitencias;";

            try (Statement stnt = _cn.createStatement()){
                List<Inasistencias> Inasistencias = new ArrayList<>();

                ResultSet result = stnt.executeQuery(query);

                while(result.next()){
                    Inasistencias Inasistenicas = new Inasistencias(
                            result.getInt("idInasistencia"),
                            result.getInt("idTipoInasistencia"),
                            result.getInt("idPersona"),
                            result.getInt("idDocente"),
                            result.getInt("idPeriodo"),
                            result.getString("Estado"),
                            result.getString("Fecha")
                    );

                    Inasistencias.add(Inasistenicas);
                }
                stnt.close();
                return Inasistencias;
            }catch (Exception e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        }).whenComplete((Inasistencias, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {

                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> insertarInasistencias(Inasistencias Inasistencias){
        return CompletableFuture.supplyAsync(() -> {
            new InasistenciasDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec AgregarInasistencias ?;");
                statement.setString(1, Inasistencias.getInasistencias());
                statement.executeUpdate();
                return 1;} catch (SQLException e) {
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

    public static CompletableFuture<Integer> eliminarInasistenciasAsync(int id){
        return CompletableFuture.supplyAsync(() -> {
            new InasistenciasDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec DeleteInasistencias ?;");
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

    public static CompletableFuture<Integer> ActualizarInasistenciasAsync(Inasistencias Inasistencias){
        return CompletableFuture.supplyAsync(() -> {
            new InasistenciasDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec UpdateInasistencias ?, ?, ?, ?, ?, ?, ?;");
                statement.setString(1, Inasistencias.getEstado());
                statement.setString(2, Inasistencias.getFecha());
                statement.setInt(3, Inasistencias.getIdInasistencia());
                statement.setInt(4, Inasistencias.getIdTipoInasistencia());
                statement.setInt(5, Inasistencias.getIdPersona());
                statement.setInt(6, Inasistencias.getIdDocente());
                statement.setInt(7, Inasistencias.getIdPeriodo());
                statement.executeUpdate();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
            finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).whenComplete((result, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

}

