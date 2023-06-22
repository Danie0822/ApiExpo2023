package com.example.expo.Services;

import com.example.expo.Models.Encargados;
import com.example.expo.Models.RangosHoras;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RangosHorasDB {

    static Connection _cn;

    public RangosHorasDB(){
        _cn = new Conexion().openDB();
    }
    public CompletableFuture<List<?>> obtenerRangosHorasAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<RangosHoras> RangosHoras = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tbRangosHoras;");

                while (result.next()) {
                    RangosHoras rangosHoras = new RangosHoras(
                            result.getInt("idRangoHora"),
                            result.getString("titulo"),
                            result.getString("inicio"),
                            result.getString("final")
                    );

                    RangosHoras.add(rangosHoras);
                }

                result.close();
                return RangosHoras;
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
        }).whenComplete((RangosHoras, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> insertarRangosHorasAsync(RangosHoras RangosHoras) {
        return CompletableFuture.supplyAsync(() -> {
            new RangosHorasDB();
            String sql = "exec AgregarRangosHoras ?,?,?;";

            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(sql);
                statement.setString(1, RangosHoras.getTitulo());
                statement.setString(2, RangosHoras.getInicio());
                statement.setString(3, RangosHoras.getFinals());
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



    public static CompletableFuture<Integer> eliminarRangosHorasAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            new RangosHorasDB();
            String sql = "exec DeleteRangosHoras ?;";
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



    public static CompletableFuture<Integer> actualizarRangosHorasAsync(RangosHoras RangosHoras) {
        return CompletableFuture.supplyAsync(() -> {
            new RangosHorasDB();

            String sql = "exec UpdateRangosHoras ?,?,?,?;";
            PreparedStatement statement = null;
            try  {
                statement = _cn.prepareStatement(sql);
                statement.setString(1, RangosHoras.getTitulo());
                statement.setString(2, RangosHoras.getInicio());
                statement.setString(3, RangosHoras.getFinals());
                statement.setInt(4, RangosHoras.getIdRangoHora());
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

