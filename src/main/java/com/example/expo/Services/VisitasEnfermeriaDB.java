package com.example.expo.Services;

import com.example.expo.Models.Encargados;
import com.example.expo.Models.ObservacionesString;
import com.example.expo.Models.VisitasEnfermeria;
import com.example.expo.Models.VisitasEnfermeriaString;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class VisitasEnfermeriaDB {
    static Connection _cn;

    public VisitasEnfermeriaDB(){
        _cn = new Conexion().openDB();
    }

    public CompletableFuture<List<?>> obtenerVisitasEnfereriaStringPorEstudianteAsync(int idEstudiante) {
        return CompletableFuture.supplyAsync(() -> {
            List<VisitasEnfermeriaString> VisitasEnfermeriaString = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                String query = "SELECT * FROM VisitasEnfermeriaString WHERE idPersona = " + idEstudiante;
                ResultSet result = statement.executeQuery(query);

                while (result.next()) {
                    VisitasEnfermeriaString visitasEnfermeria = new VisitasEnfermeriaString(
                            result.getInt("idVisitaEnfermeria"),
                            result.getString("Persona"),
                            result.getInt("idPeriodo"),
                            result.getString("fecha"),
                            result.getString("detalleVisitia"),
                            result.getInt("idPersona")
                    );

                    VisitasEnfermeriaString.add(visitasEnfermeria);
                }

                result.close();
                return VisitasEnfermeriaString;
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
        }).whenComplete((VisitasEnfermeriaString, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<List<?>> obtenerVisitasEnfereriaStringAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<VisitasEnfermeriaString> VisitasEnfermerias = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM VisitasEnfermeriaString;");

                while (result.next()) {
                    VisitasEnfermeriaString visitasEnfermeria = new VisitasEnfermeriaString(
                            result.getInt("idVisitaEnfermeria"),
                            result.getString("Persona"),
                            result.getInt("idPeriodo"),
                            result.getString("fecha"),
                            result.getString("detalleVisitia"),
                            result.getInt("idPersona")
                    );

                    VisitasEnfermerias.add(visitasEnfermeria);
                }

                result.close();
                return VisitasEnfermerias;
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
        }).whenComplete((VisitasEnfermerias, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Integer> insertarVisitasEnfermeriaAsync(VisitasEnfermeria VisitasEnfermeria) {
        return CompletableFuture.supplyAsync(() -> {
            new VisitasEnfermeriaDB();
            String sql = "exec AgregarVisitasEnfermeria ?,?,?,?;";

            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(sql);
                statement.setString(1, VisitasEnfermeria.getIdPersona());
                statement.setInt(2, VisitasEnfermeria.getIdPeriodo());
                statement.setString(3, VisitasEnfermeria.getFecha());
                statement.setString(4, VisitasEnfermeria.getDetalleVisitia());
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



    public static CompletableFuture<Integer> eliminarVisitasEnfermeriaAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            new VisitasEnfermeriaDB();
            String sql = "exec DeleteVisitasEnfermeria ?;";
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



    public static CompletableFuture<Integer> actualizarVisitasEnfermeriaAsync(VisitasEnfermeria VisitasEnfermeria) {
        return CompletableFuture.supplyAsync(() -> {
            new VisitasEnfermeriaDB();

            String sql = "exec UpdateVisitasEnfermeria ?,?,?,?,?;";
            PreparedStatement statement = null;
            try  {
                statement = _cn.prepareStatement(sql);
                statement.setString(1, VisitasEnfermeria.getIdPersona());
                statement.setInt(2, VisitasEnfermeria.getIdPeriodo());
                statement.setString(3, VisitasEnfermeria.getFecha());
                statement.setString(4, VisitasEnfermeria.getDetalleVisitia());
                statement.setInt(5, VisitasEnfermeria.getIdVisitaEnfermeria());
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
