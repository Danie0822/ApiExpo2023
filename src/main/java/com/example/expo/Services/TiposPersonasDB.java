package com.example.expo.Services;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.expo.Models.Grados;
import com.example.expo.Models.GradosView;
import com.example.expo.Models.TiposPersonas;


public class TiposPersonasDB {
   static Connection _cn;

    public TiposPersonasDB(){
        _cn = new Conexion().openDB();
    }
        public CompletableFuture<List<?>> obtenerTiposPersonasAsync() {
            return CompletableFuture.supplyAsync(() -> {
                String query = "select * from tbTiposPersonas;";

                try (Statement stnt = _cn.createStatement()) {
                    List<TiposPersonas> TiposPersonas = new ArrayList<>();

                    ResultSet result = stnt.executeQuery(query);

                    while(result.next()){
                        TiposPersonas TiposPersonas2 = new TiposPersonas(
                                result.getInt("idTipoPersona"),
                                result.getString("tipoPersona")
                        );

                        TiposPersonas.add(TiposPersonas2);
                    }
                    stnt.close();
                    return TiposPersonas;
                } catch (Exception e) {
                    e.printStackTrace();
                    return Collections.emptyList();
                }
            }).whenComplete((TiposPersonas, throwable) -> {
                try {
                    if (_cn != null && !_cn.isClosed()) {

                        _cn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }

    public CompletableFuture<String> obtenerTipoPersonaAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            String query = "select * from tbTiposPersonas WHERE idTipoPersona = ?";

            try (PreparedStatement stnt = _cn.prepareStatement(query)) {
                stnt.setInt(1,id);
                String TiposPersonas = "";

                ResultSet result = stnt.executeQuery();

                if(result.next()){
                    TiposPersonas = result.getString("tipoPersona");
                }
                stnt.close();
                return TiposPersonas;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }).whenComplete((TiposPersonas, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {

                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> insertarTiposPersonasAsync(TiposPersonas TiposPersonas) {
        return CompletableFuture.supplyAsync(() -> {
            new TiposPersonasDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(" exec AgregarTiposPersonas ?;");
                statement.setString(1, TiposPersonas.getTipoPersona());
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
    public static CompletableFuture<Integer> eliminarTiposPersonasAsync(int id){
        return CompletableFuture.supplyAsync(() -> {
            new TiposPersonasDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec DeleteTiposPersonas ?;");
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

    public static CompletableFuture<Integer> ActulizarTiposPersonasAsync(TiposPersonas TiposPersonas){
        return CompletableFuture.supplyAsync(() -> {
            new TiposPersonasDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec UpdateTiposPersonas ?, ?;");
                statement.setString(1, TiposPersonas.getTipoPersona());
                statement.setInt(2, TiposPersonas.getIdTipoPersona());
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
