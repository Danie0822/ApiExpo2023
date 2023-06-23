package com.example.expo.Services;

import com.example.expo.Models.Encargados;
import com.example.expo.Models.Notificaciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NotificacionesDB {
    static Connection _cn;

    public NotificacionesDB(){
        _cn = new Conexion().openDB();
    }
    public CompletableFuture<List<?>> obtenerNotificacionesAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<Notificaciones> Notificaciones = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tbNotificaciones;");

                while (result.next()) {
                    Notificaciones notificaciones = new Notificaciones(
                            result.getInt("idNotificacion"),
                            result.getString("detalle"),
                            result.getInt("idTipoNotificacion"),
                            result.getInt("idPersona")
                    );

                    Notificaciones.add(notificaciones);
                }

                result.close();
                return Notificaciones;
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
        }).whenComplete((Notificaciones, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> insertarNotificacionesAsync(Notificaciones Notificaciones) {
        return CompletableFuture.supplyAsync(() -> {
            new NotificacionesDB();
            String sql = "exec AgregarNotificaciones ?,?,?;";

            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(sql);
                statement.setString(1, Notificaciones.getDetalle());
                statement.setInt(2, Notificaciones.getIdTipoNotificacion());
                statement.setInt(3, Notificaciones.getIdPersona());
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



    public static CompletableFuture<Integer> eliminarNotificacionesAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            new NotificacionesDB();
            String sql = "exec DeleteNotificaciones ?;";
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



    public static CompletableFuture<Integer> actualizarNotificacionesAsync(Notificaciones Notificaciones) {
        return CompletableFuture.supplyAsync(() -> {
            new NotificacionesDB();

            String sql = "exec UpdateNotificaciones ?,?,?,?;";
            PreparedStatement statement = null;
            try  {
                statement = _cn.prepareStatement(sql);
                statement.setString(1, Notificaciones.getDetalle());
                statement.setInt(2, Notificaciones.getIdTipoNotificacion());
                statement.setInt(3, Notificaciones.getIdPersona());
                statement.setInt(4, Notificaciones.getIdNotificacion());
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
