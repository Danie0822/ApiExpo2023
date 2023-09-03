package com.example.expo.Services;

import com.example.expo.Models.Grados;
import com.example.expo.Models.GradosView;
import com.example.expo.Models.NivelesAcademicos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NivelesAcademicosDB {
    static Connection _cn;
    public NivelesAcademicosDB(){
        _cn = new Conexion().openDB();
    }
        public CompletableFuture<List<?>> obtenerNiveleAcademicosAsync() {
            return CompletableFuture.supplyAsync(() -> {
                String query = "select * from tbNivelesAcademicos;";

                try (Statement stnt = _cn.createStatement()) {
                    List<NivelesAcademicos> NivelesAcademicos = new ArrayList<>();

                    ResultSet result = stnt.executeQuery(query);

                    while(result.next()){
                        NivelesAcademicos NivelesAcademicos2 = new NivelesAcademicos(
                                result.getInt("idNivelAcademico"),
                                result.getString("nivelAcademico")
                        );

                        NivelesAcademicos.add(NivelesAcademicos2);
                    }
                    stnt.close();
                    return NivelesAcademicos;
                } catch (Exception e) {
                    e.printStackTrace();
                    return Collections.emptyList();
                }
            }).whenComplete((NivelesAcademicos, throwable) -> {
                try {
                    if (_cn != null && !_cn.isClosed()) {

                        _cn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }

    public CompletableFuture<?> obtenerNivelAcademicoAsync(int idNivelAcademico) {
        return CompletableFuture.supplyAsync(() -> {
            String query = "select nivelAcademico from tbNivelesAcademicos where idNivelAcademico = ?;";

            try (PreparedStatement stnt = _cn.prepareStatement(query)) {
                stnt.setInt(1,idNivelAcademico);

                ResultSet result = stnt.executeQuery(query);

                if (result.next()){
                    return result.getString("nivelAcademico");
                }
                stnt.close();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).whenComplete((NivelesAcademicos, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {

                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> insertarNiveleAcademicosAsync(NivelesAcademicos NivelesAcademicos) {
        return CompletableFuture.supplyAsync(() -> {
            new NivelesAcademicosDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec AgregarNivelesAcademicos ?;");
                statement.setString(1, NivelesAcademicos.getNivelAcademico());
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

    public static CompletableFuture<Integer> eliminarNivelesAcademicosAsync(int id){
        return CompletableFuture.supplyAsync(() -> {
            new NivelesAcademicosDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec DeleteNivelesAcademicos ?;");
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

    public static CompletableFuture<Integer> ActulizarNivelesAcademicosAsync(NivelesAcademicos NivelesAcademicos){
        return CompletableFuture.supplyAsync(() -> {
            new NivelesAcademicosDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec UpdateNivelesAcademicos ?, ?;");
                statement.setString(1, NivelesAcademicos.getNivelAcademico());
                statement.setInt(2, NivelesAcademicos.getIdNivelAcademico());
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

