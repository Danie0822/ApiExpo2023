package com.example.expo.Services;

import com.example.expo.Models.Grados;
import com.example.expo.Models.GradosView;
import com.example.expo.Models.NivelesAcademicos;
import com.example.expo.Models.SeccionesBachillerato;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SeccionesBachilleratoDB {
    static Connection _cn;
    public SeccionesBachilleratoDB(){
        _cn = new Conexion().openDB();
    }

    public CompletableFuture<?> obtenerSeccionBachilleratoAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            String query = "select seccionBachillerato from tbSeccionesBachillerato where idSeccionBachillerato = ?;";

            try (PreparedStatement stnt = _cn.prepareStatement(query)) {
                stnt.setInt(1,id);
                ResultSet result = stnt.executeQuery();

                if(result.next()){
                    return result.getString("seccionBachillerato");
                }
                stnt.close();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        }).whenComplete((SeccionesBachillerato, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {

                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
        public CompletableFuture<List<?>> obtenerSeccionesBachilleratoAsync() {
            return CompletableFuture.supplyAsync(() -> {
                String query = "select * from tbSeccionesBachillerato;";

                try (Statement stnt = _cn.createStatement()) {
                    List<SeccionesBachillerato> SeccionesBachillerato = new ArrayList<>();

                    ResultSet result = stnt.executeQuery(query);

                    while(result.next()){
                        SeccionesBachillerato SeccionesBachillerato2 = new SeccionesBachillerato(
                                result.getInt("idSeccionBachillerato"),
                                result.getString("seccionBachillerato")
                        );

                        SeccionesBachillerato.add(SeccionesBachillerato2);
                    }
                    stnt.close();
                    return SeccionesBachillerato;
                } catch (Exception e) {
                    e.printStackTrace();
                    return Collections.emptyList();
                }
            }).whenComplete((SeccionesBachillerato, throwable) -> {
                try {
                    if (_cn != null && !_cn.isClosed()) {

                        _cn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }

    public static CompletableFuture<Integer> insertarSeccionesBachilleratoAsync(SeccionesBachillerato SeccionesBachillerato) {
        return CompletableFuture.supplyAsync(() -> {
            new SeccionesBachilleratoDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec AgregarSeccionesBachillerato ?;");
                statement.setString(1, SeccionesBachillerato.getSeccionBachillerato());
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
    public static CompletableFuture<Integer> eliminarSeccionesBachilleratoAsync(int id){
        return CompletableFuture.supplyAsync(() -> {
            new SeccionesBachilleratoDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec DeleteSeccionesBachillerato ?;");
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

    public static CompletableFuture<Integer> ActulizarSeccionesBachilleratoAsync(SeccionesBachillerato SeccionesBachillerato){
        return CompletableFuture.supplyAsync(() -> {
            new SeccionesBachilleratoDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec UpdateSeccionesBachilleratos ?, ?;");
                statement.setString(1, SeccionesBachillerato.getSeccionBachillerato());
                statement.setInt(2, SeccionesBachillerato.getIdSeccionBachillerato());
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
