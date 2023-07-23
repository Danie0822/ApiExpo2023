package com.example.expo.Services;

import com.example.expo.Models.Encargados;
import com.example.expo.Models.Especialidades;
import com.example.expo.Models.TiposCodigosConductuales;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TiposCodigosConductualesDB {

    static Connection _cn;

    public TiposCodigosConductualesDB(){_cn = new Conexion().openDB();}


    public CompletableFuture<String> getTipoCodigoConductualAsync(int id){
        return CompletableFuture.supplyAsync(()->{
            String tipoCodigoConductual = "";
            PreparedStatement statement = null;
            try{
                statement = _cn.prepareStatement("SELECT * FROM tbTiposCodigosConductuales WHERE idTipoCodigoConductual = ?");
                statement.setInt(1,id);
                ResultSet res = statement.executeQuery();
                if(res.next()){
                    tipoCodigoConductual = res.getString("nivelCodigoConductual");
                }
                return tipoCodigoConductual;
            }
            catch (SQLException e){
                e.printStackTrace();
                return "";
            } finally {
                try {
                    if (statement!=null){
                        statement.close();
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }).whenComplete((TiposCodigosConductuales, throwable) -> {
            try {
                if (_cn !=null && !_cn.isClosed()){
                    _cn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        });
    }
    public CompletableFuture<List<?>> obtenerTiposCodigosConductualesAsync(){
        return CompletableFuture.supplyAsync(()->{
            List<TiposCodigosConductuales> TiposCodigosConductuales = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tbTiposCodigosConductuales;");

                while (result.next()) {
                    TiposCodigosConductuales tiposCodigosConductuales = new TiposCodigosConductuales(
                            result.getInt("idTipoCodigoConductual"),
                            result.getString("tipoCodigoConductual")
                    );
                    TiposCodigosConductuales.add(tiposCodigosConductuales);
                }

                result.close();
                return TiposCodigosConductuales;
            } catch (SQLException e){
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
        }).whenComplete((TiposCodigosConductuales, throwable) -> {
            try {
                if (_cn !=null && !_cn.isClosed()){
                    _cn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> insertarTiposCodigosConductuales(TiposCodigosConductuales TiposCodigosConductuales){
        return CompletableFuture.supplyAsync(()-> {
            new TiposCodigosConductualesDB();
            String sql = "exec AgregarTiposCodigosConductuales ?;";

            PreparedStatement statement = null;
            try {
                statement= _cn.prepareStatement(sql);
                statement.setString(1,TiposCodigosConductuales.getTipoCodigoConductual());
                statement.executeUpdate();

                return 1;
            } catch (SQLException e){
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

    public static CompletableFuture<Integer> eliminarTiposCodigosConductualesAsync(int id) {
        return CompletableFuture.supplyAsync(()-> {
            new TiposCodigosConductualesDB();
            String sql = "exec DeleteTiposCodigosConductuales ?;";
            PreparedStatement statement = null;
            try {
                statement= _cn.prepareStatement(sql);
                statement.setInt(1,id);
                statement.executeUpdate();
                return 1;
            } catch (SQLException e){
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

    public static CompletableFuture<Integer> actualizarTiposCodigosConductuales(TiposCodigosConductuales TiposCodigosConductuales) {
        return  CompletableFuture.supplyAsync(()-> {
            new TiposCodigosConductualesDB();

            String sql ="exec UpdateTiposCodigosConductuales ?,?;";
             PreparedStatement statement = null;
             try {
                 statement = _cn.prepareStatement(sql);
                 statement.setInt(1,TiposCodigosConductuales.getIdTipoCodigoConductual());
                 statement.setString(2,TiposCodigosConductuales.getTipoCodigoConductual());
                 statement.executeUpdate();
                 return 1;
             } catch (SQLException e) {
                 e.printStackTrace();
                 return 0;
             }
             finally {
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
