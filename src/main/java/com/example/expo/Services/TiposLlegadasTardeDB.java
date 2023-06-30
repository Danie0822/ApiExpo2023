package com.example.expo.Services;

import com.example.expo.Models.GruposTecnicos;
import com.example.expo.Models.TiposLlegadasTarde;
import com.example.expo.Models.TiposPermisos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TiposLlegadasTardeDB {
    static Connection _cn;

    public TiposLlegadasTardeDB() {
        _cn = new Conexion().openDB();
    }

    public CompletableFuture<List<?>> obtenerTiposLlegadasTardeAsync() {
        return CompletableFuture.supplyAsync(() -> {
            String query = "select * from tbTiposLlegadasTarde;";

            try (Statement stnt = _cn.createStatement()){
                List<TiposLlegadasTarde> TiposLlegadasTarde = new ArrayList<>();

                ResultSet result = stnt.executeQuery(query);

                while (result.next()){
                    TiposLlegadasTarde tiposLlegadasTarde = new TiposLlegadasTarde(
                            result.getInt("idTipoLlegadaTarde"),
                            result.getString("tipoLlegadaTarde")
                    );

                    TiposLlegadasTarde.add(tiposLlegadasTarde);
                }
                stnt.close();
                return TiposLlegadasTarde;
            }catch (Exception e) {
                e.printStackTrace();
                return Collections.emptyList();
            }

        }).whenComplete((TiposLlegadasTarde, throwable)-> {
            try {
                if (_cn != null && !_cn.isClosed()) {

                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> insertarTiposLlegadasTardeAsync(TiposLlegadasTarde TiposLlegadasTarde) {
        return CompletableFuture.supplyAsync(()-> {
            new TiposLlegadasTardeDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec AgregarTiposLlegadasTarde ?;");
                statement.setString(1, TiposLlegadasTarde.getTipoLlegadaTarde());
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

    public static CompletableFuture<Integer> eliminarTiposLlegadasTardeAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            new TiposLlegadasTardeDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec DeleteTiposLlegadasTarde ?;");
                statement.setInt(1, id);

                statement.executeUpdate();
                return 1;
            }catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }finally {
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

    public static CompletableFuture<Integer> ActualizarTiposLlegadasTardeAsync(TiposLlegadasTarde TiposLlegadasTarde){
        return CompletableFuture.supplyAsync(() -> {
            new TiposLlegadasTardeDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec UpdateTiposLlegadasTarde ?, ?;");
                statement.setString(1, TiposLlegadasTarde.getTipoLlegadaTarde());
                statement.setInt(2, TiposLlegadasTarde.getIdTipoLlegadaTarde());
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


