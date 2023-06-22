package com.example.expo.Services;


import com.example.expo.Models.Grados;
import com.example.expo.Models.GradosView;
import com.example.expo.Models.Secciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SeccionesDB {
    static Connection _cn;

    public SeccionesDB(){
        _cn = new Conexion().openDB();
    }

        public CompletableFuture<List<?>> obtenerSeccionesAsync() {
            return CompletableFuture.supplyAsync(() -> {
                String query = "   select * from tbSecciones;";

                try (Statement stnt = _cn.createStatement()) {
                    List<Secciones> Secciones = new ArrayList<>();

                    ResultSet result = stnt.executeQuery(query);

                    while(result.next()){
                        Secciones Secciones2 = new Secciones(
                                result.getInt("idSeccion"),
                                result.getString("seccion")
                        );

                        Secciones.add(Secciones2);
                    }
                    stnt.close();
                    return Secciones;
                } catch (Exception e) {
                    e.printStackTrace();
                    return Collections.emptyList();
                }
            }).whenComplete((Secciones, throwable) -> {
                try {
                    if (_cn != null && !_cn.isClosed()) {

                        _cn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }

    public static CompletableFuture<Integer> insertarSeccionesAsync(Secciones Secciones) {
        return CompletableFuture.supplyAsync(() -> {
            new SeccionesDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec AgregarSecciones ?;");
                statement.setString(1, Secciones.getSeccion());
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
    public static CompletableFuture<Integer> eliminarSeccionesAsync(int id){
        return CompletableFuture.supplyAsync(() -> {
            new SeccionesDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(" exec DeleteSeccion ?;");
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

    public static CompletableFuture<Integer> ActulizarSeccionesAsync(Secciones Secciones){
        return CompletableFuture.supplyAsync(() -> {
            new SeccionesDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec UpdateSecciones ?, ?;");
                statement.setString(1, Secciones.getSeccion());
                statement.setInt(2, Secciones.getIdSeccion());
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
