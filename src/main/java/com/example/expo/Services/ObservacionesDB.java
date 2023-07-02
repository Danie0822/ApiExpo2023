package com.example.expo.Services;

import com.example.expo.Models.CodigosConductualesPersonas;
import com.example.expo.Models.Observaciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ObservacionesDB {
    static Connection _cn;
    public ObservacionesDB(){
        _cn = new Conexion().openDB();
    }

    //SELECT
    public CompletableFuture<List<?>> obtenerObservacionesAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<Observaciones> Observaciones = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tbObservaciones;");

                while (result.next()) {
                    Observaciones observaciones = new Observaciones(
                            result.getInt("idObservacion"),
                            result.getInt("idEstudiante"),
                            result.getInt("idDocente"),
                            result.getInt("idPeriodo"),
                            result.getString("fecha"),
                            result.getString("detalle")
                    );

                    Observaciones.add(observaciones);
                }

                result.close();
                return Observaciones;
            } catch (SQLException e) {
                e.printStackTrace();
                return Collections.emptyList();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).whenComplete((Observaciones, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    //INSERT
    public static CompletableFuture<Integer> insertarObservacionesAsync(Observaciones Observaciones) {
        return CompletableFuture.supplyAsync(() -> {
            new ObservacionesDB();
            String sql = "exec AgregarObservaciones ?,?,?,?,?;";

            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(sql);
                statement.setInt(1, Observaciones.getIdEstudiante());
                statement.setInt(2, Observaciones.getIdDocente());
                statement.setInt(3, Observaciones.getIdPeriodo());
                statement.setString(4, Observaciones.getFecha());
                statement.setString(5, Observaciones.getDetalle());
                statement.executeUpdate();

                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            } finally {
                try {
                    if (statement != null && !statement.isClosed()) {
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

    //DELETE
    public static CompletableFuture<Integer> eliminarObservacionesAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            new ObservacionesDB();
            String sql = "exec DeleteObservaciones ?;";
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            } finally {
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

    //UPDATE
    public static CompletableFuture<Integer> actualizarObservacionesAsync(Observaciones Observaciones) {
        return CompletableFuture.supplyAsync(() -> {
            new ObservacionesDB();

            String sql = "exec UpdateObservaciones ?,?,?,?,?,?;";
            PreparedStatement statement = null;
            try  {
                statement = _cn.prepareStatement(sql);
                statement.setInt(1, Observaciones.getIdObservacion());
                statement.setInt(2, Observaciones.getIdEstudiante());
                statement.setInt(3, Observaciones.getIdDocente());
                statement.setInt(4, Observaciones.getIdPeriodo());
                statement.setString(5, Observaciones.getFecha());
                statement.setString(6,Observaciones.getDetalle());

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
