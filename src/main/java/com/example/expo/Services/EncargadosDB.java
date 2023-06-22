package com.example.expo.Services;

import com.example.expo.Models.Encargados;
import com.example.expo.Models.Especialidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EncargadosDB {

    static Connection _cn;

    public EncargadosDB(){
        _cn = new Conexion().openDB();
    }
    public CompletableFuture<List<?>> obtenerEncargadosAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<Encargados> Encargados = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tbEncargados;");

                while (result.next()) {
                    Encargados encargados = new Encargados(
                            result.getInt("idEncargado"),
                            result.getString("nombrePersona"),
                            result.getString("apellidoPersona"),
                            result.getInt("idPersona"),
                            result.getString("correoEncargado")
                    );

                    Encargados.add(encargados);
                }

                result.close();
                return Encargados;
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
        }).whenComplete((Encargados, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> insertarEncargadosAsync(Encargados Encargados) {
        return CompletableFuture.supplyAsync(() -> {
            new EncargadosDB();
            String sql = "exec AgregarEncargados ?,?,?,?;";

            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(sql);
                statement.setString(1, Encargados.getNombrePersona());
                statement.setString(2, Encargados.getApellidoPersona());
                statement.setInt(3, Encargados.getIdPersona());
                statement.setString(4, Encargados.getCorreoEncargado());
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



    public static CompletableFuture<Integer> eliminarEncargadosAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            new EncargadosDB();
            String sql = "exec DeleteEncargados ?;";
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



    public static CompletableFuture<Integer> actualizarEncargadosAsync(Encargados Encargados) {
        return CompletableFuture.supplyAsync(() -> {
            new EncargadosDB();

            String sql = "exec UpdateEncargados ?,?,?,?,?;";
            PreparedStatement statement = null;
            try  {
                statement = _cn.prepareStatement(sql);
                statement.setString(1, Encargados.getNombrePersona());
                statement.setString(2, Encargados.getApellidoPersona());
                statement.setInt(3, Encargados.getIdPersona());
                statement.setString(4, Encargados.getCorreoEncargado());
                statement.setInt(5, Encargados.getIdEncargado());
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
