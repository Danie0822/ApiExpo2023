package com.example.expo.Services;

import com.example.expo.Models.CodigosConductuales;
import com.example.expo.Models.CodigosConductualestring;
import com.example.expo.Models.NivelesCodigosConductuales;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CodigosConductualesDB {
    static Connection _cn;
    public CodigosConductualesDB(){_cn = new Conexion().openDB();}

    public CompletableFuture<List<?>> obtenerCodigosConductualesAsync(){
        return CompletableFuture.supplyAsync(()->{
            List<CodigosConductuales> CodigosConductuales = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tbCodigosConductuales;");

                while (result.next()){
                    CodigosConductuales codigosConductuales = new CodigosConductuales(
                            result.getInt("idCodigoConductual"),
                            result.getInt("idTipoCodigoConductual"),
                            result.getInt("idNivelCodigoConductual"),
                            result.getString("codigoConductual")
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
                    if (statement!=null){
                        statement.close();
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
    public CompletableFuture<List<?>> obtenerCodigosConductualesStringAsync(){
        return CompletableFuture.supplyAsync(()->{
            List<CodigosConductualestring> CodigosConductuales = new ArrayList<>();
            Statement statement = null;
            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tbCodigosConductualestring;");

                while (result.next()){
                    CodigosConductualestring codigosConductuales = new CodigosConductualestring(
                            result.getInt("idCodigoConductual"),
                            result.getString("nivelCodigoConductual"),
                            result.getString("codigoConductual")
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
                    if (statement!=null){
                        statement.close();
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

    public static CompletableFuture<Integer> insertarCodigosConductualesAsync(CodigosConductuales CodigosConductuales){
        return CompletableFuture.supplyAsync(()-> {
            new CodigosConductualesDB();

            String sql = "exec AgregarCodigosConductuales ?,?,?;";

            PreparedStatement statement= null;
            try {
                statement= _cn.prepareStatement(sql);
                statement.setInt(1,CodigosConductuales.getIdTipoCodigoConductual());
                statement.setInt(2,CodigosConductuales.getIdNivelCodigoConductual());
                statement.setString(3,CodigosConductuales.getCodigoConductual());
                statement.executeUpdate();
                return 1;
            }catch (SQLException e){
                e.printStackTrace();
                return 0;
            } finally {
                try {
                    if (statement!=null && !statement.isClosed()){
                        statement.close();
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }).whenComplete((result, throwable)-> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> eliminarCodigosConductualesAsync(int id){
        return CompletableFuture.supplyAsync(()-> {
            new CodigosConductualesDB();
            String sql="exec DeleteCodigosConductuales ?;";
            PreparedStatement statement= null;

            try {
                statement= _cn.prepareStatement(sql);
                statement.setInt(1,id);
                statement.executeUpdate();
                return 1;
            }catch (SQLException e){
                e.printStackTrace();
                return 0;
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).whenComplete((result, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


    public static CompletableFuture<Integer> actualizarCodigosConductualesAsync(CodigosConductuales CodigosConductuales){
        return CompletableFuture.supplyAsync(()-> {
            new CodigosConductualesDB();
            String sql = "exec UpdateCodigosConductuales ?,?,?,?;";
            PreparedStatement statement = null;
            try {
                statement= _cn.prepareStatement(sql);
                statement.setInt(1,CodigosConductuales.getIdCodigoConductual());
                statement.setInt(2,CodigosConductuales.getIdTipoCodigoConductual());
                statement.setInt(3,CodigosConductuales.getIdNivelCodigoConductual());
                statement.setString(4,CodigosConductuales.getCodigoConductual());
                statement.executeUpdate();
                return 1;
            }catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }finally {
                try {
                    if (statement !=null){
                        statement.close();
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }).whenComplete((result, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
