package com.example.expo.Services;

import com.example.expo.Models.Grados;
import com.example.expo.Models.GradosView;
import com.example.expo.Models.tbAdmi;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TiposAdminDB {
    static Connection _cn;


    public TiposAdminDB(){
        _cn = new Conexion().openDB();
    }
        public CompletableFuture<List<?>> obtenerTiposAdminAsync() {
            return CompletableFuture.supplyAsync(() -> {
                String query = "select * from tbAdmi;";

                try (Statement stnt = _cn.createStatement()) {
                    List<tbAdmi> Admi = new ArrayList<>();

                    ResultSet result = stnt.executeQuery(query);

                    while(result.next()){
                        tbAdmi tbAdmi2 = new tbAdmi(
                                result.getInt("idAdmi"),
                                result.getString("tipoAdmi")
                        );

                        Admi.add(tbAdmi2);
                    }
                    stnt.close();
                    return Admi;
                } catch (Exception e) {
                    e.printStackTrace();
                    return Collections.emptyList();
                }
            }).whenComplete((Admi, throwable) -> {
                try {
                    if (_cn != null && !_cn.isClosed()) {

                        _cn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }





    public static CompletableFuture<Integer> insertarTiposAdminAsync(tbAdmi Admi) {
        return CompletableFuture.supplyAsync(() -> {
            new TiposAdminDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec AgregarAdmi ?;");
                statement.setString(1, Admi.getTipoAdmi());
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

    public static CompletableFuture<Integer> eliminarTiposAdminAsync(int id){
        return CompletableFuture.supplyAsync(() -> {
            new TiposAdminDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec DeleteAdmin ?;");
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


    public static CompletableFuture<Integer> ActulizarTiposAdminAsync(tbAdmi Admi){
        return CompletableFuture.supplyAsync(() -> {
            new TiposAdminDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec UpdateAdmin ?,?;");
                statement.setString(1, Admi.getTipoAdmi());
                statement.setInt(2, Admi.getIdAdmi());
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

