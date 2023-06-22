package com.example.expo.Services;

import com.example.expo.Models.Periodos;
import com.example.expo.Models.RangosHoras;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PeriodosDB {


    static Connection _cn;

    public PeriodosDB(){
        _cn = new Conexion().openDB();
    }
    public CompletableFuture<List<?>> obtenerPeriodosAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<Periodos> Periodos = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tbPeriodos;");

                while (result.next()) {
                    Periodos periodos = new Periodos(
                            result.getInt("idPeriodo"),
                            result.getString("inicio"),
                            result.getString("final")
                    );

                    Periodos.add(periodos);
                }

                result.close();
                return Periodos;
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
        }).whenComplete((Periodos, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> insertarPeriodosAsync(Periodos Periodos) {
        return CompletableFuture.supplyAsync(() -> {
            new PeriodosDB();
            String sql = "exec AgregarPeriodos ?,?;";

            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(sql);
                statement.setString(1, Periodos.getInicio());
                statement.setString(2, Periodos.getFinals());
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



    public static CompletableFuture<Integer> eliminarPeriodosAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            new PeriodosDB();
            String sql = "exec DeletePeriodos ?;";
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



    public static CompletableFuture<Integer> actualizarPeriodosAsync(Periodos Periodos) {
        return CompletableFuture.supplyAsync(() -> {
            new PeriodosDB();

            String sql = "exec UpdatePeriodos ?,?,?;";
            PreparedStatement statement = null;
            try  {
                statement = _cn.prepareStatement(sql);
                statement.setString(1, Periodos.getInicio());
                statement.setString(2, Periodos.getFinals());
                statement.setInt(3, Periodos.getIdPeriodo());
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
