package com.example.expo.Services;

import com.example.expo.Models.Credenciales;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CredencialesDB {

    static Connection _cn;

    public CredencialesDB() { _cn = new Conexion().openDB();}

    public static CompletableFuture<List<?>> obtenerCredencialesAsync(){
        return CompletableFuture.supplyAsync(() -> {
            String query = "select * from tbCredenciales;";

            try(Statement stmt = _cn.createStatement()){
                List<Credenciales> credenciales = new ArrayList<>();

                ResultSet res =stmt.executeQuery(query);

                while(res.next()){
                    Credenciales ncredencial =new Credenciales(
                        res.getInt("idPersona"),
                            res.getString("codigo"),
                            res.getString("nombrePersona"),
                            res.getString("apellidoPersona"),
                            res.getString("nacimientoPersona"),
                            res.getInt("idTipoPersona"),
                            res.getString("correo"),
                            res.getString("claveCredenciales"),
                            res.getBytes("foto")
                    );
                    credenciales.add(ncredencial);
                }

                stmt.close();
                return credenciales;


            }
            catch (Exception e){
                e.printStackTrace();
                return Collections.emptyList();
            }
        }).whenComplete((Credenciales, throwable) -> {
            try{
                if(_cn != null && !_cn.isClosed()){
                    _cn.close();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> insertarCredencialesAsync(Credenciales credencial){
        return CompletableFuture.supplyAsync(() ->{
            PreparedStatement stmt = null;
            new CredencialesDB();
            try{
                stmt = _cn.prepareStatement("exec AgregarCredenciales ?,?,?,?,?,?,?,?;");
                stmt.setString(1,credencial.getCodigo());
                stmt.setString(2,credencial.getNombrePersona());
                stmt.setString(3, credencial.getApellidoPersona());
                stmt.setString(4,  credencial.getNacimientoPersona());
                stmt.setInt(5, credencial.getIdTipoPersona());
                stmt.setString(6, credencial.getCorreo());
                stmt.setString(7, credencial.getClaveCredenciales());
                stmt.setBytes(8, credencial.getFoto());
                stmt.executeUpdate();
                return 1;
            }
            catch (Exception e){
                e.printStackTrace();
                return 0;
            }
            finally {
                if (stmt != null) {
                    try {
                        stmt.close();
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

    public static CompletableFuture<Integer> actualizarCredencialesAsync(Credenciales credencial){
        return CompletableFuture.supplyAsync(() ->{
            PreparedStatement stmt = null;
            new CredencialesDB();
            try{
                stmt = _cn.prepareStatement("exec ActualizarCredenciales ?,?,?,?,?,?,?,?,?;");
                stmt.setInt(1, credencial.getIdPersona());
                stmt.setString(2,credencial.getCodigo());
                stmt.setString(3,credencial.getNombrePersona());
                stmt.setString(4, credencial.getApellidoPersona());
                stmt.setString(5, credencial.getNacimientoPersona());
                stmt.setInt(6, credencial.getIdTipoPersona());
                stmt.setString(7, credencial.getCorreo());
                stmt.setString(8, credencial.getClaveCredenciales());
                stmt.setBytes(9, credencial.getFoto());
                stmt.executeUpdate();
                return 1;
            }
            catch (Exception e){
                e.printStackTrace();
                return 0;
            }
            finally {
                if (stmt != null) {
                    try {
                        stmt.close();
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

    public static CompletableFuture<Integer> eliminarCredencialAsync(int id){
        return CompletableFuture.supplyAsync(() -> {
            PreparedStatement stmt = null;
            new CredencialesDB();
            try{
                stmt = _cn.prepareStatement("exec DeleteCredencial ?;");
                stmt.setInt(1,id);
                stmt.executeUpdate();
                return 1;
            }
            catch (Exception e){
                e.printStackTrace();
                return 0;
            }
            finally {
                if (stmt != null) {
                    try {
                        stmt.close();
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
