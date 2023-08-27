package com.example.expo.Services;

import com.example.expo.Models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SalonesDB {
    static Connection _cn;
    public SalonesDB(){
        _cn = new Conexion().openDB();
    }
        public CompletableFuture<List<?>> obtenerSalonesAsync() {
            return CompletableFuture.supplyAsync(() -> {
                String query = "select * from tbSalones;";

                try (Statement stnt = _cn.createStatement()) {
                    List<Salones> Salones = new ArrayList<>();

                    ResultSet result = stnt.executeQuery(query);

                    while(result.next()){
                        Salones Salones2 = new Salones(
                                result.getInt("idSalon"),
                                result.getString("codigoSalon")
                        );

                        Salones.add(Salones2);
                    }
                    stnt.close();
                    return Salones;
                } catch (Exception e) {
                    e.printStackTrace();
                    return Collections.emptyList();
                }
            }).whenComplete((Salones, throwable) -> {
                try {
                    if (_cn != null && !_cn.isClosed()) {

                        _cn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }



    public static CompletableFuture<Integer> insertarSalonesAsync(    Salones Salones) {
        return CompletableFuture.supplyAsync(() -> {
            new SalonesDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec AgregarSalones ?");
                statement.setString(1, Salones.getCodigoSalon());
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

    public CompletableFuture<List<?>> BuscarCodigoConductualesAsync(String filter){
        return CompletableFuture.supplyAsync(()->{
            List<Salones> CodigosConductuales = new ArrayList<>();
            PreparedStatement stmt = null;
            try {
                String realFilter = "%"+filter+"%";
                stmt = _cn.prepareStatement("SELECT * FROM tbSalones WHERE codigoSalon like ?;");
                stmt.setString(1,realFilter);
                ResultSet result = stmt.executeQuery();
                while (result.next()){
                    Salones codigosConductuales = new Salones(
                            result.getInt("idSalon"),
                            result.getString("codigoSalon")
                    );
                    CodigosConductuales.add(codigosConductuales);
                }
                result.close();
                return CodigosConductuales;
            }catch (SQLException e){
                e.printStackTrace();
                return Collections.emptyList();
            } finally {
                try {
                    if (stmt!=null){
                        stmt.close();
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }).whenComplete((CodigosConductuales, throwable) -> {
            try {
                if (_cn !=null && !_cn.isClosed()){
                    _cn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> eliminarSalonesAsync(int id){
        return CompletableFuture.supplyAsync(() -> {
            new SalonesDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec DeleteSalones ?;");
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



    public static CompletableFuture<Integer> ActulizarSalonesAsync(Salones Salones){
        return CompletableFuture.supplyAsync(() -> {
            new SalonesDB();
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement("exec UpdateSalones ?, ?;");
                statement.setString(1, Salones.getCodigoSalon());
                statement.setInt(2, Salones.getIdSalon());
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
