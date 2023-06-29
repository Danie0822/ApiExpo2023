package com.example.expo.Services;

import com.example.expo.Models.Grados;
import com.example.expo.Models.GradosView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GradosDB {
    static Connection _cn;

    public GradosDB(){
        _cn = new Conexion().openDB();
    }
    public CompletableFuture<List<?>> obtenerGradosAsync() {
        return CompletableFuture.supplyAsync(() -> {
            String query = "select * from GradosString;";

            try (Statement stnt = _cn.createStatement()) {
                List<GradosView> grados = new ArrayList<>();

                ResultSet result = stnt.executeQuery(query);

                while (result.next()) {
                    GradosView gradosView = new GradosView(
                            result.getInt("idGrado"),
                            result.getString("idNivelAcademico"),
                            result.getString("idSeccion"),
                            result.getString("idSeccionBachillerato"),
                            result.getString("idDocenteEncargado"),
                            result.getString("idEspecialidad"),
                            result.getString("idGrupoTecnico")
                    );

                    grados.add(gradosView);
                }
                stnt.close();
                return grados;
            } catch (Exception e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        }).whenComplete((grados, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {

                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    public CompletableFuture<List<?>> obtenerGradosintAsync() {
        return CompletableFuture.supplyAsync(() -> {
            String query = "select * from tbGrados;";

            try (Statement stnt = _cn.createStatement()) {
                List<Grados> grados = new ArrayList<>();

                ResultSet result = stnt.executeQuery(query);

                while (result.next()) {
                    Grados Grados2 = new Grados(
                            result.getInt("idGrado"),
                            result.getInt("idNivelAcademico"),
                            result.getInt("idSeccion"),
                            result.getInt("idSeccionBachillerato"),
                            result.getInt("idDocenteEncargado"),
                            result.getInt("idEspecialidad"),
                            result.getInt("idGrupoTecnico")
                    );

                    grados.add(Grados2);
                }
                stnt.close();
                return grados;
            } catch (Exception e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        }).whenComplete((grados, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {

                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


    public static CompletableFuture<Integer> insertarGradosAsync(Grados grados) {
        return CompletableFuture.supplyAsync(() -> {
           new GradosDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec AgregarGrados ?,?,?,?,?,?;");
                statement.setInt(1, grados.getIdNivelAcademico());
                statement.setInt(2, grados.getIdSeccion());
                statement.setInt(3, grados.getIdSeccionBachillerato());
                statement.setInt(4, grados.getIdDocenteEncargado());
                statement.setInt(5, grados.getIdEspecialidad());
                statement.setInt(6, grados.getIdGrupoTecnico());
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

    public static CompletableFuture<Integer> eliminarGradosAsync(int id){
        return CompletableFuture.supplyAsync(() -> {
            new GradosDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec DeleteGrados ?;");
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

    public static CompletableFuture<Integer> ActulizarGradosAsync(Grados grados){
        return CompletableFuture.supplyAsync(() -> {
            new GradosDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec UpdateGrados ?,?,?,?,?,?,?;");
                statement.setInt(1, grados.getIdNivelAcademico());
                statement.setInt(2, grados.getIdSeccion());
                statement.setInt(3, grados.getIdSeccionBachillerato());
                statement.setInt(4, grados.getIdDocenteEncargado());
                statement.setInt(5, grados.getIdEspecialidad());
                statement.setInt(6, grados.getIdGrupoTecnico());
                statement.setInt(7, grados.getIdGrado());
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
