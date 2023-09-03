package com.example.expo.Services;

import com.example.expo.Models.Grados;
import com.example.expo.Models.GradosView;
import com.example.expo.Models.GruposTecnicos;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GruposTecnicosDB {

    static Connection _cn;

    public GruposTecnicosDB(){
        _cn = new Conexion().openDB();
    }
    public CompletableFuture<?> obtenerGrupo(int id) {
        return CompletableFuture.supplyAsync(() -> {
            String query = "select grupoTecnico from tbGruposTecnicos where idGrupoTecnico = ?;";

            try (PreparedStatement stnt = _cn.prepareStatement(query)) {
                stnt.setInt(1,id);
                ResultSet result = stnt.executeQuery();

                if(result.next()){
                    return result.getString("grupoTecnico");
                }
                stnt.close();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).whenComplete((gruposTecnicos, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {

                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<List<?>> obtenerGruposTecnicosAsync() {
            return CompletableFuture.supplyAsync(() -> {
                String query = "select * from tbGruposTecnicos;";

                try (Statement stnt = _cn.createStatement()) {
                    List<GruposTecnicos> gruposTecnicos = new ArrayList<>();

                    ResultSet result = stnt.executeQuery(query);

                    while(result.next()){
                        GruposTecnicos GruposTecnicos2 = new GruposTecnicos(
                                result.getInt("idGrupoTecnico"),
                                result.getString("grupoTecnico")
                        );

                        gruposTecnicos.add(GruposTecnicos2);
                    }
                    stnt.close();
                    return gruposTecnicos;
                } catch (Exception e) {
                    e.printStackTrace();
                    return Collections.emptyList();
                }
            }).whenComplete((gruposTecnicos, throwable) -> {
                try {
                    if (_cn != null && !_cn.isClosed()) {

                        _cn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }


    public static CompletableFuture<Integer> insertarGruposTecnicosAsync(GruposTecnicos GruposTecnicos) {
        return CompletableFuture.supplyAsync(() -> {
            new GruposTecnicosDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec AgregarGruposTecnicos ?;");
                statement.setString(1, GruposTecnicos.getGrupoTecnico());
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


    public static CompletableFuture<Integer> eliminarGruposTecnivosAsync(int id){
        return CompletableFuture.supplyAsync(() -> {
            new GruposTecnicosDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec DeleteGruposTecnicos ?;");
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



    public static CompletableFuture<Integer> ActulizarGruposTecnicosAsync(GruposTecnicos GruposTecnicos){
        return CompletableFuture.supplyAsync(() -> {
            new GruposTecnicosDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec UpdateGruposTecnicos ?, ?;");
                statement.setString(1, GruposTecnicos.getGrupoTecnico());
                statement.setInt(2, GruposTecnicos.getIdGrupoTecnico());
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
