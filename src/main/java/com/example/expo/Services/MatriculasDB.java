package com.example.expo.Services;


import com.example.expo.Models.Comunicados;
import com.example.expo.Models.Matriculas;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MatriculasDB {

    static Connection _cn;

    public MatriculasDB(){
        _cn = new Conexion().openDB();
    }

    public CompletableFuture<List<?>> obtenerMatriculasAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<Matriculas> especialidades = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tbMatriculas;");

                while (result.next()) {
                    Matriculas especialidad = new Matriculas(
                            result.getInt("idMatricula"),
                            result.getInt("idEstudiante"),
                            result.getInt("idGradoAcademico"),
                            result.getInt("idGradoTecnico"),
                            result.getBytes("horario")
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

    public CompletableFuture<?> obtenerMatriculaAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            Matriculas especialidades = new Matriculas();
            PreparedStatement statement = null;

            try {
                statement = _cn.prepareStatement("SELECT * FROM tbMatriculas WHERE idEstudiante = ?;");
                statement.setInt(1,id);
                ResultSet result = statement.executeQuery();

                if (result.next()) {
                    Matriculas especialidad = new Matriculas(
                            result.getInt("idMatricula"),
                            result.getInt("idEstudiante"),
                            result.getInt("idGradoAcademico"),
                            result.getInt("idGradoTecnico"),
                            result.getBytes("horario")
                    );

                    especialidades = especialidad;
                }

                result.close();
                return especialidades;
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



    public static CompletableFuture<Integer> insertarComunicadosAsync(Matriculas matricula) {
        return CompletableFuture.supplyAsync(() -> {
            new ComunicadosDB();
            String sql = "insert into tbMatriculas values(?,?,?,?)";

            PreparedStatement statement = null;
            try {
                statement = _cn.prepareStatement(sql);
                statement.setInt(1, matricula.getIdEstudiante());
                statement.setInt(2, matricula.getIdGradoAcademico());
                statement.setInt(3, matricula.getIdGradoTecnico());
                statement.setBytes(4, matricula.getHorario());
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



    public static CompletableFuture<Integer> eliminarComunicadosAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            new ComunicadosDB();
            String sql = "delete from tbMatriculas where idMatricula = ?";
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



    public static CompletableFuture<Integer> actualizarEspecialidadesAsync(Matriculas matricula) {
        return CompletableFuture.supplyAsync(() -> {
            new ComunicadosDB();

            String sql = "update tbMatriculas set idEstudiante = ?, idGradoAcademico = ?, idGradoTecnico = ?, horario = ? where idMatricula = ?";
            PreparedStatement statement = null;
            try  {
                statement = _cn.prepareStatement(sql);
                statement.setInt(1, matricula.getIdEstudiante());
                statement.setInt(2, matricula.getIdGradoAcademico());
                statement.setInt(3, matricula.getIdGradoTecnico());
                statement.setBytes(4, matricula.getHorario());
                statement.setInt(5, matricula.getIdMatricula());
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
