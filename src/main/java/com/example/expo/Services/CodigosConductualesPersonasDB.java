package com.example.expo.Services;

import com.example.expo.Models.CodigosConductualesPersonas;
import com.example.expo.Models.Encargados;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CodigosConductualesPersonasDB {
    static Connection _cn;
    public CodigosConductualesPersonasDB(){
        _cn = new Conexion().openDB();
    }

    //SELECT
    public CompletableFuture<List<?>> obtenerCodigosConductualesPersonasAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<CodigosConductualesPersonas> CodigosConductualesPersonas = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tbCodigosConductualesPersonas;");

                while (result.next()) {
                    CodigosConductualesPersonas codigosConductualesPersonas = new CodigosConductualesPersonas(
                            result.getInt("idCodigoConductualPersona"),
                            result.getInt("idEstudiante"),
                            result.getInt("idCodigoConductual"),
                            result.getInt("idDocente"),
                            result.getInt("idPeriodo"),
                            result.getString("fecha")
                    );

                    CodigosConductualesPersonas.add(codigosConductualesPersonas);
                }

                result.close();
                return CodigosConductualesPersonas;
            } catch (SQLException e) {
                e.printStackTrace();
                return Collections.emptyList();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).whenComplete((CodigosConductualesPersonas, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    //INSERT
    public static CompletableFuture<Integer> insertarCodigosConductualesPersonasAsync(CodigosConductualesPersonas CodigosConductualesPersonas) {
        return CompletableFuture.supplyAsync(() -> {
            new CodigosConductualesPersonasDB();
            String sql = "exec AgregarCODCPersonas ?,?,?,?,?;";

            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(sql);
                statement.setInt(1, CodigosConductualesPersonas.getIdEstudiante());
                statement.setInt(2, CodigosConductualesPersonas.getIdCodigoConductual());
                statement.setInt(3, CodigosConductualesPersonas.getIdDocente());
                statement.setInt(4, CodigosConductualesPersonas.getIdPeriodo());
                statement.setString(5, CodigosConductualesPersonas.getFecha());
                statement.executeUpdate();

                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            } finally {
                try {
                    if (statement != null && !statement.isClosed()) {
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


    //DELETE
    public static CompletableFuture<Integer> eliminarCodigosConductualesPersonasAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            new CodigosConductualesPersonasDB();
            String sql = "exec DeleteCODCPersonas ?;";
            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();
                return 1;
            } catch (SQLException e) {
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

    //UPDATE
    public static CompletableFuture<Integer> actualizarCodigosConductualesPersonasAsync(CodigosConductualesPersonas CodigosConductualesPersonas) {
        return CompletableFuture.supplyAsync(() -> {
            new CodigosConductualesPersonasDB();

            String sql = "exec UpdateCODCPersonas ?,?,?,?,?,?;";
            PreparedStatement statement = null;
            try  {
                statement = _cn.prepareStatement(sql);
                statement.setInt(1, CodigosConductualesPersonas.getIdCodigoConductualPersona());
                statement.setInt(2, CodigosConductualesPersonas.getIdEstudiante());
                statement.setInt(3, CodigosConductualesPersonas.getIdCodigoConductual());
                statement.setInt(4, CodigosConductualesPersonas.getIdDocente());
                statement.setInt(5, CodigosConductualesPersonas.getIdPeriodo());
                statement.setString(6,CodigosConductualesPersonas.getFecha());

                statement.executeUpdate();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
            finally {
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

}
