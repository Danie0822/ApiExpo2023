package com.example.expo.Services;

import com.example.expo.Models.RangosHoras;
import com.example.expo.Models.ReservacionesSalones;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ReservacionesSalonesDB {

    static Connection _cn;

    public ReservacionesSalonesDB(){
        _cn = new Conexion().openDB();
    }
    public CompletableFuture<List<?>> obtenerReservacionesSalonesAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<ReservacionesSalones> ReservacionesSalones = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tbReservacionesSalones;");

                while (result.next()) {
                    ReservacionesSalones reservacionesSalones = new ReservacionesSalones(
                            result.getInt("idReservacionSalon"),
                            result.getInt("idSalon"),
                            result.getInt("idRangoHora"),
                            result.getInt("idReservante"),
                            result.getString("motivoReserva")
                    );

                    ReservacionesSalones.add(reservacionesSalones);
                }

                result.close();
                return ReservacionesSalones;
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
        }).whenComplete((ReservacionesSalones, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> insertarReservacionesSalonesAsync(ReservacionesSalones ReservacionesSalones) {
        return CompletableFuture.supplyAsync(() -> {
            new ReservacionesSalonesDB();
            String sql = "exec AgregarReservacionesSalones ?,?,?,?;";

            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(sql);
                statement.setInt(1, ReservacionesSalones.getIdSalon());
                statement.setInt(2, ReservacionesSalones.getIdRangoHora());
                statement.setInt(3, ReservacionesSalones.getIdReservante());
                statement.setString(4, ReservacionesSalones.getMotivoReserva());
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



    public static CompletableFuture<Integer> eliminarReservacionesSalonesAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            new ReservacionesSalonesDB();
            String sql = "exec DeleteReservacionesSalones ?;";
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



    public static CompletableFuture<Integer> actualizarReservacionesSalonesAsync(ReservacionesSalones ReservacionesSalones) {
        return CompletableFuture.supplyAsync(() -> {
            new ReservacionesSalonesDB();

            String sql = "exec UpdateReservacionesSalones ?,?,?,?,?;";
            PreparedStatement statement = null;
            try  {
                statement = _cn.prepareStatement(sql);
                statement.setInt(1, ReservacionesSalones.getIdSalon());
                statement.setInt(2, ReservacionesSalones.getIdRangoHora());
                statement.setInt(3, ReservacionesSalones.getIdReservante());
                statement.setString(4, ReservacionesSalones.getMotivoReserva());
                statement.setInt(5, ReservacionesSalones.getIdReservacionSalon());
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
