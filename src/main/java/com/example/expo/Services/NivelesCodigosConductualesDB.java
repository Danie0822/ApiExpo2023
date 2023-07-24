package com.example.expo.Services;

import com.example.expo.Models.NivelesCodigosConductuales;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NivelesCodigosConductualesDB {
    static Connection _cn;
    public NivelesCodigosConductualesDB(){_cn = new Conexion().openDB();}

    public CompletableFuture<Integer> getNivelCodigoConductualNameAsync(String name){
        return CompletableFuture.supplyAsync(()->{
            Integer tipoCodigoConductual = 0;
            PreparedStatement statement = null;
            try{
                statement = _cn.prepareStatement("SELECT * FROM tbNivelesCodigosConductuales WHERE nivelCodigoConductual = ?");
                statement.setString(1,name);
                ResultSet res = statement.executeQuery();
                if(res.next()){
                    tipoCodigoConductual = res.getInt("idNivelCodigoConductual");
                }
                return tipoCodigoConductual;
            }
            catch (SQLException e){
                e.printStackTrace();
                return 0;
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
    public CompletableFuture<String> getNivelCodigoConductualAsync(int id){
        return CompletableFuture.supplyAsync(()->{
            String nivelesCodigosConductuales = "";
            PreparedStatement statement = null;
            try{
                statement = _cn.prepareStatement("SELECT * FROM tbNivelesCodigosConductuales WHERE idNivelCodigoConductual = ?");
                statement.setInt(1,id);
                ResultSet res = statement.executeQuery();
                if(res.next()){
                    nivelesCodigosConductuales = res.getString("nivelCodigoConductual");
                }
                return nivelesCodigosConductuales;
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
        }).whenComplete((NivelesCodigosConductuales, throwable) -> {
            try {
                if (_cn !=null && !_cn.isClosed()){
                    _cn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        });
    }
    public CompletableFuture<List<?>> obtenerNivelesCodigosConductualesAsync(){
        return CompletableFuture.supplyAsync(()->{
            List<NivelesCodigosConductuales> NivelesCodigosConductuales = new ArrayList<>();
            Statement statement = null;

            try{
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tbNivelesCodigosConductuales;");

                while (result.next()){
                    NivelesCodigosConductuales nivelesCodigosConductuales = new NivelesCodigosConductuales(
                            result.getInt("idNivelCodigoConductual"),
                            result.getString("nivelCodigoConductual")
                    );
                    NivelesCodigosConductuales.add(nivelesCodigosConductuales);
                }
                result.close();
                return NivelesCodigosConductuales;
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
        }).whenComplete((NivelesCodigosConductuales, throwable) -> {
            try {
                if (_cn !=null && !_cn.isClosed()){
                    _cn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        });
    }


    public static CompletableFuture<Integer> insertarNivelesCodigosConductualesAsync(NivelesCodigosConductuales NivelesCodigosConductuales){
        return CompletableFuture.supplyAsync(()-> {
            new NivelesCodigosConductualesDB();
            String sql= "exec AgregarNivelesCodigosConductuales ?;";

            PreparedStatement statement = null;
            try {
                statement= _cn.prepareStatement(sql);
                statement.setString(1, NivelesCodigosConductuales.getNivelCodigoConductual());
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

    public static CompletableFuture<Integer> eliminarNivelesCodigosConductualesAsync(int id){
        return CompletableFuture.supplyAsync(()-> {
            new NivelesCodigosConductualesDB();
            String sql = "exec DeleteNivelesCodigosConductuales ?;";
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(sql);
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

    public static CompletableFuture<Integer> actualizarNivelesCodigosConductualesAsync(NivelesCodigosConductuales NivelesCodigosConductuales){
        return CompletableFuture.supplyAsync(()-> {
            new NivelesCodigosConductualesDB();
            String sql = "exec UpdateNivelesCodigosConductuales ?,?;";
            PreparedStatement statement= null;
            try {
                statement= _cn.prepareStatement(sql);
                statement.setInt(1,NivelesCodigosConductuales.getIdNivelCodigoConductual());
                statement.setString(2, NivelesCodigosConductuales.getNivelCodigoConductual());
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
