package com.example.expo.Services;

import com.example.expo.Models.TiposInasistencias;
import com.example.expo.Models.TiposLlegadasTarde;
import com.example.expo.Models.TiposPermisos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TiposInasistenciasDB {
    static Connection _cn;

    public TiposInasistenciasDB() { _cn = new Conexion().openDB();}
    //Select
    public CompletableFuture<List<?>> obtenerTiposInasistenciasAsync() {
        return CompletableFuture.supplyAsync(() -> {
            String query = "select * from tbTiposInasistencias;";

            try (Statement stnt = _cn.createStatement()){
                List<TiposInasistencias> TiposLlegadasTarde = new ArrayList<>();

                ResultSet result = stnt.executeQuery(query);

                while (result.next()){
                    TiposInasistencias tiposLlegadasTarde = new TiposInasistencias(
                            result.getInt("idTipoInasistencia"),
                            result.getString("tipoInasistencia")
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

    public static CompletableFuture<Integer> insertarTiposInasistenciasAsync(TiposInasistencias TiposInasistencias) {
        return CompletableFuture.supplyAsync(()-> {
            new TiposInasistenciasDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec AgregarInasistenciasTipos ?;");
                statement.setString(1, TiposInasistencias.getTipoInasistencias());
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
    //Delete
    public static CompletableFuture<Integer> eliminarTiposInasistenciasAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            new TiposInasistenciasDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec DeleteTiposInasistencias ?;");
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
    //UPDATE

    public static CompletableFuture<Integer> ActulizarTiposInasistenciasAsync(TiposInasistencias TiposInasistencias) {
        return CompletableFuture.supplyAsync(() -> {
            new TiposInasistenciasDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec UpdateTiposInasistencias ?, ?;");
                statement.setString(1, TiposInasistencias.getTipoInasistencias());
                statement.setInt(2, TiposInasistencias.getIdTipoInasistencia());
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
