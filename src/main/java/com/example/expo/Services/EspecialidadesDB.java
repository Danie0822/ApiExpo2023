package com.example.expo.Services;



import com.example.expo.Models.Especialidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EspecialidadesDB {

    static Connection _cn;

    public EspecialidadesDB(){
        _cn = new Conexion().openDB();
    }

    public CompletableFuture<?> obtenerNombre(int id){
        return CompletableFuture.supplyAsync(() -> {
            PreparedStatement statement = null;

            try {
                statement = _cn.prepareStatement("SELECT especialidad FROM tbEspecialidades where idEspecialidad = ?;");
                statement.setInt(1,id);
                ResultSet result = statement.executeQuery();

                if (result.next()) {
                    return result.getString("especialidad");
                }

                result.close();
                return null;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).whenComplete((especialidades, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    public CompletableFuture<List<?>> obtenerEspecialidadesAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<Especialidades> especialidades = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tbEspecialidades;");

                while (result.next()) {
                    Especialidades especialidad = new Especialidades(
                            result.getInt("idEspecialidad"),
                            result.getString("especialidad")
                    );

                    especialidades.add(especialidad);
                }

                result.close();
                return especialidades;
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
        }).whenComplete((especialidades, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }



    public static CompletableFuture<Integer> insertarEspecialidadesAsync(Especialidades especialidad) {
        return CompletableFuture.supplyAsync(() -> {
            new EspecialidadesDB();
            String sql = "exec AgregarEspecialidades ?;";

            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(sql);
                statement.setString(1, especialidad.getEspecialidad());
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



    public static CompletableFuture<Integer> eliminarEspecialidadesAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            new EspecialidadesDB();
            String sql = "exec DeleteEspecialidades ?;";
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



    public static CompletableFuture<Integer> actualizarEspecialidadesAsync(Especialidades especialidad) {
        return CompletableFuture.supplyAsync(() -> {
            new EspecialidadesDB();

            String sql = "exec UpdateEspecialidades ?, ?;";
            PreparedStatement statement = null;
            try  {
                statement = _cn.prepareStatement(sql);
                statement.setString(1, especialidad.getEspecialidad());
                statement.setInt(2, especialidad.getIdEspecialidad());
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
