package com.example.expo.Services;

import com.example.expo.Models.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FuncionesDB {
    static Connection _cn;

    public FuncionesDB(){
        _cn = new Conexion().openDB();
    }
    public CompletableFuture<List<?>> obtenerCodigosConductualesStringAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<CodigosConductualesString> CodigosConductualesString = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM CodigosConductualesString;");

                while (result.next()) {
                    CodigosConductualesString CodigosConductualesString2 = new CodigosConductualesString(
                            result.getInt("idCodigoConductualPersona"),
                            result.getString("Estudiante"),
                            result.getString("CodigoConductual"),
                            result.getString("tipoCodigoConductual"),
                            result.getString("Docente"),
                            result.getInt("idPeriodo"),
                            result.getString("fecha"),
                            result.getInt("idEstudiante")
                    );

                    CodigosConductualesString.add(CodigosConductualesString2);
                }

                result.close();
                return CodigosConductualesString;
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
        }).whenComplete((CodigosConductualesString, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    public CompletableFuture<List<?>> obtenerCodigosConductualesStringPorEstudianteAsync(int idEstudiante) {
        return CompletableFuture.supplyAsync(() -> {
            List<CodigosConductualesString> codigosConductualesString = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                String query = "SELECT * FROM CodigosConductualesString WHERE idEstudiante = " + idEstudiante;
                ResultSet result = statement.executeQuery(query);

                while (result.next()) {
                    CodigosConductualesString codigoConductual = new CodigosConductualesString(
                            result.getInt("idCodigoConductualPersona"),
                            result.getString("Estudiante"),
                            result.getString("CodigoConductual"),
                            result.getString("tipoCodigoConductual"),
                            result.getString("Docente"),
                            result.getInt("idPeriodo"),
                            result.getString("fecha"),
                            result.getInt("idEstudiante")
                    );

                    codigosConductualesString.add(codigoConductual);
                }

                result.close();
                return codigosConductualesString;
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
        }).whenComplete((codigosConductualesString, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<List<?>> obtenerLlegadasTardeStringPorEstudianteAsync(int idEstudiante) {
        return CompletableFuture.supplyAsync(() -> {
            List<LlegadasTardeString> LlegadasTardeString = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                String query = "SELECT * FROM LlegadasTardeString WHERE idEstudiante = " + idEstudiante;
                ResultSet result = statement.executeQuery(query);

                while (result.next()) {
                    LlegadasTardeString llegadasTardeString = new LlegadasTardeString(
                            result.getInt("idLlegadaTarde"),
                            result.getString("TipoLlegadaTarde"),
                            result.getString("Estudiante"),
                            result.getInt("idPeriodo"),
                            result.getString("Docente"),
                            result.getInt("estado"),
                            result.getString("fecha"),
                            result.getInt("idEstudiante")
                    );
                    LlegadasTardeString.add(llegadasTardeString);
                }

                result.close();
                return LlegadasTardeString;
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
        }).whenComplete((LlegadasTardeString, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<List<?>> obtenerLlegadasTardeStringAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<LlegadasTardeString> LlegadasTardeString = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM LlegadasTardeString;");

                while (result.next()) {
                    LlegadasTardeString llegadasTardeString = new LlegadasTardeString(
                            result.getInt("idLlegadaTarde"),
                            result.getString("TipoLlegadaTarde"),
                            result.getString("Estudiante"),
                            result.getInt("idPeriodo"),
                            result.getString("Docente"),
                            result.getInt("estado"),
                            result.getString("fecha"),
                            result.getInt("idEstudiante")
                    );

                    LlegadasTardeString.add(llegadasTardeString);
                }

                result.close();
                return LlegadasTardeString;
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
        }).whenComplete((LlegadasTardeString, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<List<?>> obtenerInasisitenciastringPorEstudianteAsync(int idEstudiante) {
        return CompletableFuture.supplyAsync(() -> {
            List<Inasisitenciastring> Inasisitenciastring = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                String query = "SELECT * FROM Inasisitenciastring WHERE idEstudiante = " + idEstudiante;
                ResultSet result = statement.executeQuery(query);

                while (result.next()) {
                    Inasisitenciastring Inasisitenciastring2 = new Inasisitenciastring(
                            result.getInt("idInasistencia"),
                            result.getString("TipoLlegadaTarde"),
                            result.getString("Estudiante"),
                            result.getString("Docente"),
                            result.getInt("idPeriodo"),
                            result.getInt("estado"),
                            result.getString("fecha"),
                            result.getInt("idEstudiante")
                    );

                    Inasisitenciastring.add(Inasisitenciastring2);
                }

                result.close();
                return Inasisitenciastring;
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
        }).whenComplete((Inasisitenciastring, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<List<?>> obtenerInasisitenciastringAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<Inasisitenciastring> Inasisitenciastring = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM Inasisitenciastring;");

                while (result.next()) {
                    Inasisitenciastring Inasisitenciastring2 = new Inasisitenciastring(
                            result.getInt("idInasistencia"),
                            result.getString("TipoLlegadaTarde"),
                            result.getString("Estudiante"),
                            result.getString("Docente"),
                            result.getInt("idPeriodo"),
                            result.getInt("estado"),
                            result.getString("fecha"),
                            result.getInt("idEstudiante")
                    );

                    Inasisitenciastring.add(Inasisitenciastring2);
                }

                result.close();
                return Inasisitenciastring;
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
        }).whenComplete((Inasisitenciastring, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<List<?>> obtenerReservacionesSalonestringAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<ReservacionesSalonestring> ReservacionesSalonestring = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM ReservacionesSalonestring;");

                while (result.next()) {
                    ReservacionesSalonestring ReservacionesSalonestring2 = new ReservacionesSalonestring(
                            result.getInt("idReservacionSalon"),
                            result.getString("Salon"),
                            result.getString("Inicio"),
                            result.getString("Final"),
                            result.getString("Reservante"),
                            result.getString("motivoReserva"),
                            result.getInt("idReservante")
                    );

                    ReservacionesSalonestring.add(ReservacionesSalonestring2);
                }

                result.close();
                return ReservacionesSalonestring;
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
        }).whenComplete((ReservacionesSalonestring, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<List<?>> obtenerNotificacionesstringPorEstudianteAsync(int idPersona) {
        return CompletableFuture.supplyAsync(() -> {
            List<Notificacionesstring> Notificacionesstring = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                String query = "SELECT * FROM Notificacionesstring WHERE idPersona = " + idPersona;
                ResultSet result = statement.executeQuery(query);

                while (result.next()) {
                    Notificacionesstring Notificacionesstring2 = new Notificacionesstring(
                            result.getInt("idInasistencia"),
                            result.getString("detalle"),
                            result.getString("TipoNotificacion"),
                            result.getInt("idPersona")
                    );

                    Notificacionesstring.add(Notificacionesstring2);
                }

                result.close();
                return Notificacionesstring;
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
        }).whenComplete((Notificacionesstring, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    public CompletableFuture<List<?>> obtenerNotificacionesstringAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<Notificacionesstring> Notificacionesstring = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM Notificacionesstring;");

                while (result.next()) {
                    Notificacionesstring Notificacionesstring2 = new Notificacionesstring(
                            result.getInt("idInasistencia"),
                            result.getString("detalle"),
                            result.getString("TipoNotificacion"),
                            result.getInt("idPersona")
                    );

                    Notificacionesstring.add(Notificacionesstring2);
                }

                result.close();
                return Notificacionesstring;
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
        }).whenComplete((Notificacionesstring, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    public CompletableFuture<List<?>> obtenerObservacionesStringPorEstudianteAsync(int idEstudiante) {
        return CompletableFuture.supplyAsync(() -> {
            List<ObservacionesString> ObservacionesString = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                String query = "SELECT * FROM ObservacionesString WHERE idEstudiante = " + idEstudiante;
                ResultSet result = statement.executeQuery(query);

                while (result.next()) {
                    ObservacionesString ObservacionesString2 = new ObservacionesString(
                            result.getInt("idObservacion"),
                            result.getString("Estudiante"),
                            result.getString("Docente"),
                            result.getInt("idPeriodo"),
                            result.getString("fecha"),
                            result.getString("detalle"),
                            result.getInt("idEstudiante")
                    );

                    ObservacionesString.add(ObservacionesString2);
                }

                result.close();
                return ObservacionesString;
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
        }).whenComplete((ObservacionesString, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    public CompletableFuture<List<?>> obtenerObservacionesStringAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<ObservacionesString> ObservacionesString = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM ObservacionesString;");

                while (result.next()) {
                    ObservacionesString ObservacionesString2 = new ObservacionesString(
                            result.getInt("idObservacion"),
                            result.getString("Estudiante"),
                            result.getString("Docente"),
                            result.getInt("idPeriodo"),
                            result.getString("fecha"),
                            result.getString("detalle"),
                            result.getInt("idEstudiante")
                    );

                    ObservacionesString.add(ObservacionesString2);
                }

                result.close();
                return ObservacionesString;
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
        }).whenComplete((ObservacionesString, throwable) -> {
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
