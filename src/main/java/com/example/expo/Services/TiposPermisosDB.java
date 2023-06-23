package com.example.expo.Services;

import com.example.expo.Models.GruposTecnicos;
import com.example.expo.Models.TiposPermisos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TiposPermisosDB {

    static Connection _cn;

    public TiposPermisosDB() {
        _cn = new Conexion().openDB();
    }

    public CompletableFuture<List<?>> obtenerTiposPermisoAsync() {
        return CompletableFuture.supplyAsync(() -> {
            String query = "select * from tbTiposPermisos;";

            try (Statement stnt = _cn.createStatement()) {
                List<TiposPermisos> TiposPermisos = new ArrayList<>();

                ResultSet result = stnt.executeQuery(query);

                while (result.next()) {
                    TiposPermisos tiposPermisos = new TiposPermisos(
                            result.getInt("idTipoPermiso"),
                            result.getString("tipoPermiso")
                    );

                    TiposPermisos.add(tiposPermisos);
                }
                stnt.close();
                return TiposPermisos;
            } catch (Exception e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        }).whenComplete((TiposPermisos, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {

                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


    public static CompletableFuture<Integer> insertarTiposPermisosAsync(TiposPermisos TiposPermisos) {
        return CompletableFuture.supplyAsync(() -> {
            new TiposPermisosDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec AgregarTiposPermisos ?;");
                statement.setString(1, TiposPermisos.getTipoPermiso());
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


    public static CompletableFuture<Integer> eliminarTiposPermisosAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            new TiposPermisosDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec DeleteTiposPermisos ?;");
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


    public static CompletableFuture<Integer> ActulizarTiposPermisosAsync(TiposPermisos TiposPermisos) {
        return CompletableFuture.supplyAsync(() -> {
            new TiposPermisosDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec UpdateTiposPermisos ?, ?;");
                statement.setString(1, TiposPermisos.getTipoPermiso());
                statement.setInt(2, TiposPermisos.getIdTipoPermiso());
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

