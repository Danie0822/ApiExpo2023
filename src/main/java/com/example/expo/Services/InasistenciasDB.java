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
                            result.getInt("idEstudiante"),
                            result.getInt("idDocente"),
                            result.getInt("idPeriodo"),
                            result.getInt("estado"),
                            result.getString("fecha")
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
                statement = _cn.prepareStatement("exec AgregarInasisitencias ?, ?, ? , ?, ?, ? ;");
                statement.setInt(1, Inasistencias.getIdTipoInasistencia());
                statement.setInt(2, Inasistencias.getIdEstudiante());
                statement.setInt(3, Inasistencias.getIdPeriodo());
                statement.setInt(4, Inasistencias.getIdDocente());
                statement.setInt(5, Inasistencias.getEstado());
                statement.setString(6, Inasistencias.getFecha());
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
                statement = _cn.prepareStatement("exec UpdateInasisitencias ?, ?, ?, ?, ?, ?,?;");
                statement.setInt(1, Inasistencias.getIdTipoInasistencia());
                statement.setInt(2, Inasistencias.getIdEstudiante());
                statement.setInt(3, Inasistencias.getIdPeriodo());
                statement.setInt(4, Inasistencias.getIdDocente());
                statement.setInt(5, Inasistencias.getEstado());
                statement.setString(6, Inasistencias.getFecha());
                statement.setInt(7, Inasistencias.getIdInasistencia());
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

    public static CompletableFuture<Integer> ActualizarEstadoAsync(InasisitenciasEstado InasisitenciasEstado){
        return CompletableFuture.supplyAsync(() -> {
            new InasistenciasDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec UpdateInasisitenciasEstado ?, ?;");
                statement.setInt(1, InasisitenciasEstado.getEstado());
                statement.setInt(2, InasisitenciasEstado.getIdInasistencia());
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

