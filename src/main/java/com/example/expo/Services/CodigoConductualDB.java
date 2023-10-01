package com.example.expo.Services;

import com.example.expo.Models.CodigoConductual;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class CodigoConductualDB {
    static Connection _cn;

    public CodigoConductualDB(){
        _cn = new Conexion().openDB();
    }
    public static CompletableFuture<Integer> InsertaCodigos(CodigoConductual CodigoConductual) {
        return CompletableFuture.supplyAsync(() -> {
            new CodigoConductualDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec InsertarCodigoConductual ?,?;");
                statement.setString(1, CodigoConductual.getTipoCodigoConductual());
                statement.setInt(2, CodigoConductual.getIdNivelCodigoConductual());
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
    public static CompletableFuture<Integer> EliminarCodigos(int id){
        return CompletableFuture.supplyAsync(() -> {
            new CodigoConductualDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec EliminarCodigoConductual ?;");
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
    public static CompletableFuture<Integer> EditarCodigoConductual(CodigoConductual CodigoConductual){
        return CompletableFuture.supplyAsync(() -> {
            new CodigoConductualDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec EditarCodigoConductual ?, ?, ?;");
                statement.setInt(1, CodigoConductual.getIdCodigoConductual());
                statement.setString(2, CodigoConductual.getTipoCodigoConductual());
                statement.setInt(3, CodigoConductual.getIdNivelCodigoConductual());
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
