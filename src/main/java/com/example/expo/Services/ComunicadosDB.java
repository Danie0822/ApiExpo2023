package com.example.expo.Services;

import com.example.expo.Models.Comunicados;
import com.example.expo.Models.Especialidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ComunicadosDB {
    static Connection _cn;

    public ComunicadosDB(){
        _cn = new Conexion().openDB();
    }
    public CompletableFuture<List<?>> obtenerEspecialidadesAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<Comunicados> especialidades = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tbComunicados;");

                while (result.next()) {
                    Comunicados especialidad = new Comunicados(
                            result.getInt("idComunicado"),
                            result.getInt("idGrado"),
                            result.getString("detalle"),
                            result.getString("fecha"),
                            result.getBytes("archivo")
                    );

                    especialidades.add(especialidad);
                }

                result.close();
                return especialidades;
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
        }).whenComplete((especialidades, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }



    public static CompletableFuture<Integer> insertarComunicadosAsync(Comunicados Comunicados) {
        return CompletableFuture.supplyAsync(() -> {
            new ComunicadosDB();
            String sql = "exec AgregarComunicados ?,?,?,?;";

            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(sql);
                statement.setInt(1, Comunicados.getIdGrado());
                statement.setString(2, Comunicados.getDetalle());
                statement.setString(3, Comunicados.getFecha());
                statement.setBytes(4, Comunicados.getArchivo());
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



    public static CompletableFuture<Integer> eliminarComunicadosAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            new ComunicadosDB();
            String sql = "exec DeleteComunicados ?;";
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



    public static CompletableFuture<Integer> actualizarEspecialidadesAsync(Comunicados Comunicados) {
        return CompletableFuture.supplyAsync(() -> {
            new ComunicadosDB();

            String sql = "exec UpdateComunicados ?,?,?,?,?;";
            PreparedStatement statement = null;
            try  {
                statement = _cn.prepareStatement(sql);
                statement.setInt(1, Comunicados.getIdGrado());
                statement.setString(2, Comunicados.getDetalle());
                statement.setString(3, Comunicados.getFecha());
                statement.setBytes(4, Comunicados.getArchivo());
                statement.setInt(5, Comunicados.getIdComunicado());
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

