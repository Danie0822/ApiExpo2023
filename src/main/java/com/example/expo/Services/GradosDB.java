package com.example.expo.Services;

import com.example.expo.Controllers.*;
import com.example.expo.Models.Grados;
import com.example.expo.Models.GradosView;
import com.example.expo.Models.GruposTecnicos;

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
            String query = "select * from GradosView;";

            try (Statement stnt = _cn.createStatement()) {
                List<GradosView> grados = new ArrayList<>();

                ResultSet result = stnt.executeQuery(query);

                while (result.next()) {
                    GradosView gradosView = new GradosView(
                            result.getInt("idGrado"),
                            result.getString("nivelAcademico"),
                            result.getString("seccion"),
                            result.getString("seccionBachillerato"),
                            result.getString("codigo"),
                            result.getString("especialidad"),
                            result.getString("grupoTecnico"),
                            result.getBytes("horario")
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

    public CompletableFuture<?> obtenerGradoAcademico(int idNivelAcademico, int idSeccion, int idSeccionBachillerato){
        String query = "Select * from tbGrados where idNivelAcademico = ? and idSeccion = ? and idSeccionBachillerato = ?";
        return CompletableFuture.supplyAsync(() -> {

            try (PreparedStatement stnt = _cn.prepareStatement(query)) {

                stnt.setInt(1,idNivelAcademico);
                stnt.setInt(2, idSeccion);
                stnt.setInt(3, idSeccionBachillerato);

                ResultSet result = stnt.executeQuery();

                if (result.next()) {
                    Grados grado = new Grados(
                            result.getInt("idGrado"),
                            result.getInt("idNivelAcademico"),
                            result.getInt("idSeccion"),
                            result.getInt("idSeccionBachillerato"),
                            result.getInt("idDocenteEncargado"),
                            result.getInt("idEspecialidad"),
                            result.getInt("idGrupoTecnico"),
                            result.getBytes("horario")
                    );
                    return grado;
                }
                stnt.close();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
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

    public CompletableFuture<?> obtenerGradoTecnico(int idEspecialidad, int idGrupoTecnico){
        String query = "Select * from tbGrados where idEspecialidad = ? and idGrupoTecnico = ?";
        return CompletableFuture.supplyAsync(() -> {

            try (PreparedStatement stnt = _cn.prepareStatement(query)) {

                stnt.setInt(1,idEspecialidad);
                stnt.setInt(2, idGrupoTecnico);

                ResultSet result = stnt.executeQuery();

                if (result.next()) {
                    Grados grado = new Grados(
                            result.getInt("idGrado"),
                            result.getInt("idNivelAcademico"),
                            result.getInt("idSeccion"),
                            result.getInt("idSeccionBachillerato"),
                            result.getInt("idDocenteEncargado"),
                            result.getInt("idEspecialidad"),
                            result.getInt("idGrupoTecnico"),
                            result.getBytes("horario")
                    );
                    return grado;
                }
                stnt.close();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
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
                            result.getInt("idGrupoTecnico"),
                            result.getBytes("horarios")
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

    public CompletableFuture<?> obtenerGradoAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            String query = "select * from GradosView where idGrado = ?;";

            try (PreparedStatement stnt = _cn.prepareStatement(query)) {

                stnt.setInt(1,id);
                GradosView grados = new GradosView();

                ResultSet result = stnt.executeQuery();

                if (result.next()) {
                    GradosView Grados2 = new GradosView(
                            result.getInt("idGrado"),
                            result.getString("nivelAcademico"),
                            result.getString("seccion"),
                            result.getString("seccionBachillerato"),
                            result.getString("codigo"),
                            result.getString("especialidad"),
                            result.getString("grupoTecnico"),
                            result.getBytes("horario")
                    );

                    return Grados2;
                }
                stnt.close();
                return grados;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
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
                statement = _cn.prepareStatement("insert into tbGrados value(?,?,?,?,?,?,?);");
                statement.setInt(1, grados.getIdNivelAcademico());
                statement.setInt(2, grados.getIdSeccion());
                statement.setInt(3, grados.getIdSeccionBachillerato());
                statement.setInt(4, grados.getIdDocenteEncargado());
                statement.setInt(5, grados.getIdEspecialidad());
                statement.setInt(6, grados.getIdGrupoTecnico());
                statement.setBytes(7,grados.getHorario());
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
                statement = _cn.prepareStatement("exec deleteGradospr ?;");
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
                statement = _cn.prepareStatement("update tbGrados set idNivelAcademico = ? " +
                        "idSeccion = ?, " +
                        "idSeccionBachillerato = ?, " +
                        "idDocenteEncargado = ?, " +
                        "idEspecialidad = ?, " +
                        "idGrupoTecnico = ?, " +
                        "horario = ? " +
                        "where idGrado = ?;");
                statement.setInt(1, grados.getIdNivelAcademico());
                statement.setInt(2, grados.getIdSeccion());
                statement.setInt(3, grados.getIdSeccionBachillerato());
                statement.setInt(4, grados.getIdDocenteEncargado());
                statement.setInt(5, grados.getIdEspecialidad());
                statement.setInt(6, grados.getIdGrupoTecnico());
                statement.setBytes(7,grados.getHorario());
                statement.setInt(8,grados.getIdGrado());
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
