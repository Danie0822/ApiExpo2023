package com.example.expo.Services;

import com.example.expo.Models.Contra;
import com.example.expo.Models.Credenciales;
import com.example.expo.Models.ObservacionesString;
import com.example.expo.Models.Recu;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CredencialesDB {

    static Connection _cn;

    public CredencialesDB() { _cn = new Conexion().openDB();}


    public CompletableFuture<?> obtenerPersonaByCodigo(String codigo){
        return CompletableFuture.supplyAsync(() -> {
            String query = "select * from tbCredenciales where codigo = ?;";

            try(PreparedStatement stmt = _cn.prepareStatement(query)){
                stmt.setString(1,codigo);
                ResultSet res =stmt.executeQuery();

                if(res.next()){
                    Credenciales newPersona = new Credenciales(
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
                    return newPersona;
                }

                stmt.close();
                return null;


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
    public CompletableFuture<?> obtenerNombre(int id){
        return CompletableFuture.supplyAsync(() -> {
            String query = "select nombrePersona from tbCredenciales where idPersona = ?;";

            try(PreparedStatement stmt = _cn.prepareStatement(query)){
                stmt.setInt(1,id);
                ResultSet res =stmt.executeQuery();

                if(res.next()){
                    return res.getString("nombrePersona");
                }

                stmt.close();
                return null;


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

    public static CompletableFuture<List<?>> obtenerCredencialesTipoAsync(int tipo){
        return CompletableFuture.supplyAsync(() -> {
            String query = "select * from tbCredenciales where idTipoPersona = ?";

            try(PreparedStatement stmt = _cn.prepareStatement(query)){
                stmt.setInt(1,tipo);
                List<Credenciales> credenciales = new ArrayList<>();

                ResultSet res =stmt.executeQuery();

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

    public static CompletableFuture<List<?>> obtenerCredencialesNoTipoAsync(int tipo){
        return CompletableFuture.supplyAsync(() -> {
            String query = "select * from tbCredenciales where idTipoPersona <> ?";

            try(PreparedStatement stmt = _cn.prepareStatement(query)){
                stmt.setInt(1,tipo);
                List<Credenciales> credenciales = new ArrayList<>();

                ResultSet res =stmt.executeQuery();

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

    public CompletableFuture<List<?>> obtenerCredencialesAsync(String correo, String claveCredenciales) {
        return CompletableFuture.supplyAsync(() -> {
            String query = "SELECT * FROM tbCredenciales WHERE correo = ? AND claveCredenciales = ?";

            try (PreparedStatement stmt = _cn.prepareStatement(query)) {
                stmt.setString(1, correo);
                stmt.setString(2, claveCredenciales);

                List<Credenciales> credenciales = new ArrayList<>();

                ResultSet res = stmt.executeQuery();

                while (res.next()) {
                    Credenciales ncredencial = new Credenciales(
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

                return credenciales;
            } catch (Exception e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        }).whenComplete((result, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public static CompletableFuture<Integer> UpdateContra(Contra Contra){
        return CompletableFuture.supplyAsync(() ->{
            PreparedStatement stmt = null;
            new CredencialesDB();
            try{
                stmt = _cn.prepareStatement("exec UpdateContraseñas ?,?;");
                stmt.setString(1,Contra.getClaveCredenciales());
                stmt.setInt(2,Contra.getIdPersona());
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

    public CompletableFuture<List<?>> ObtenerProfesor(String correo) {
        return CompletableFuture.supplyAsync(() -> {
            String query = "SELECT idPersona, idTipoPersona, correo FROM tbCredenciales where correo = ? ;";

            try (PreparedStatement stmt = _cn.prepareStatement(query)) {
                stmt.setString(1, correo);

                List<Recu> credenciales = new ArrayList<>();

                ResultSet res = stmt.executeQuery();

                while (res.next()) {
                    Recu ncredencial = new Recu(
                            res.getInt("idPersona"),
                            res.getInt("idTipoPersona"),
                            res.getString("correo")
                    );
                    credenciales.add(ncredencial);
                }

                return credenciales;
            } catch (Exception e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        }).whenComplete((result, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public static CompletableFuture<Integer> validarCredencialesNoEstudianteAsync(String codigo){
        return CompletableFuture.supplyAsync(() -> {
            String query = "SELECT * FROM TbCredenciales where codigo = ? AND idTipoEstudiante <> 2";
            try(PreparedStatement stmt = _cn.prepareStatement(query)){
                stmt.setString(1,codigo);

                ResultSet rs = stmt.executeQuery();

                if(rs.next()) return 1;
                else{
                    return 0;
                }

            }
            catch (Exception e){
                e.printStackTrace();
                return 2;
            }
        }).whenComplete((result, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<List<?>> buscarCredencialesEstudianteAsync(String filter){
        return CompletableFuture.supplyAsync(() -> {
            String realFilter = "%"+filter+"%";
            String query = "SELECT * FROM tbCredenciales WHERE ((nombrePersona+' '+apellidoPersona) like ? OR codigo like ?) AND idTipoPersona = 2;";

            try (PreparedStatement stmt = _cn.prepareStatement(query)) {
                stmt.setString(1, realFilter);
                stmt.setString(2,realFilter);

                List<Credenciales> credenciales = new ArrayList<>();

                ResultSet res = stmt.executeQuery();

                while (res.next()) {
                    Credenciales ncredencial = new Credenciales(
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

                return credenciales;
            } catch (Exception e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        }).whenComplete((result, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<String> obtenerSeccionTecnicaEstudianteAsync(int idPersona){
        return CompletableFuture.supplyAsync(() -> {
            String especialidad = "";
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                String query = "select idGradoAcademico from tbMatriculas where idEstudiante = " + idPersona;
                ResultSet res = statement.executeQuery(query);

                while(res.next()){
                    int idGrado = res.getInt("idGradoAcademico");
                    PreparedStatement stmt = _cn.prepareStatement("select * from tbGrados where idGrado = ?");
                    stmt.setInt(1,idGrado);
                    ResultSet resultSet = stmt.executeQuery();
                    if(resultSet.next()){
                        int idEspecialidad = resultSet.getInt("idSeccionBachillerato");
                        PreparedStatement stmt1 = _cn.prepareStatement("select * from tbSeccionesBachillerato where idSeccionBachillerato = ?");

                        stmt1.setInt(1,idEspecialidad);
                        ResultSet resultSet1 = stmt1.executeQuery();
                        if(resultSet1.next()){
                            especialidad = resultSet1.getString("seccionBachillerato");
                        }
                    }
                }

                res.close();
                return especialidad;
            } catch (SQLException e) {
                e.printStackTrace();
                return "";
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).whenComplete((Credenciales, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<String> obtenerSeccionAcademicaEstudianteAsync(int idPersona){
        return CompletableFuture.supplyAsync(() -> {
            String especialidad = "";
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                String query = "select (idGradoAcademico) from tbMatriculas where idEstudiante = " + idPersona;
                ResultSet res = statement.executeQuery(query);

                while(res.next()){
                    int idGrado = res.getInt("idGradoAcademico");
                    PreparedStatement stmt = _cn.prepareStatement("select * from tbGrados where idGrado = ?");
                    stmt.setInt(1,idGrado);
                    ResultSet resultSet = stmt.executeQuery();
                    if(resultSet.next()){
                        int idEspecialidad = resultSet.getInt("idSeccion");
                        PreparedStatement stmt1 = _cn.prepareStatement("select * from tbSecciones where idSeccion = ?");

                        stmt1.setInt(1,idEspecialidad);
                        ResultSet resultSet1 = stmt1.executeQuery();
                        if(resultSet1.next()){
                            especialidad = resultSet1.getString("seccion");
                        }
                    }
                }

                res.close();
                return especialidad;
            } catch (SQLException e) {
                e.printStackTrace();
                return "";
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).whenComplete((Credenciales, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<String> obtenerEspecialidadEstudianteAsync(int idPersona){
        return CompletableFuture.supplyAsync(() -> {
            String especialidad = "";
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                String query = "select (idGradoTecnico) from tbMatriculas where idEstudiante = " + idPersona;
                ResultSet res = statement.executeQuery(query);

                while(res.next()){
                    int idGrado = res.getInt("idGradoTecnico");
                    PreparedStatement stmt = _cn.prepareStatement("select * from tbGrados where idGrado = ?");
                    stmt.setInt(1,idGrado);
                    ResultSet resultSet = stmt.executeQuery();
                    if(resultSet.next()){
                        int idEspecialidad = resultSet.getInt("idEspecialidad");
                        PreparedStatement stmt1 = _cn.prepareStatement("select * from tbEspecialidades where idEspecialidad = ?");

                        stmt1.setInt(1,idEspecialidad);
                        ResultSet resultSet1 = stmt1.executeQuery();
                        if(resultSet1.next()){
                            especialidad = resultSet1.getString("especialidad");
                        }
                    }
                }

                res.close();
                return especialidad;
            } catch (SQLException e) {
                e.printStackTrace();
                return "";
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).whenComplete((Credenciales, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<String> obtenerGrupoEstudianteAsync(int idPersona){
        return CompletableFuture.supplyAsync(() -> {
            String especialidad = "";
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                String query = "select (idGradoTecnico) from tbMatriculas where idEstudiante = " + idPersona;
                ResultSet res = statement.executeQuery(query);

                while(res.next()){
                    int idGrado = res.getInt("idGradoTecnico");
                    PreparedStatement stmt = _cn.prepareStatement("select * from tbGrados where idGrado = ?");
                    stmt.setInt(1,idGrado);
                    ResultSet resultSet = stmt.executeQuery();
                    if(resultSet.next()){
                        int idEspecialidad = resultSet.getInt("idGrupoTecnico");
                        PreparedStatement stmt1 = _cn.prepareStatement("select * from tbGruposTecnicos where idGrupoTecnico = ?");

                        stmt1.setInt(1,idEspecialidad);
                        ResultSet resultSet1 = stmt1.executeQuery();
                        if(resultSet1.next()){
                            especialidad = resultSet1.getString("grupoTecnico");
                        }
                    }
                }

                res.close();
                return especialidad;
            } catch (SQLException e) {
                e.printStackTrace();
                return "";
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).whenComplete((Credenciales, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<String> obtenerGradoEstudianteAsync(int idPersona){
        return CompletableFuture.supplyAsync(() -> {
            String grado = "";
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                String query = "select (idGradoAcademico) from tbMatriculas where idEstudiante = " + idPersona;
                ResultSet res =statement.executeQuery(query);

                while(res.next()){
                    int idGrado = res.getInt("idGradoAcademico");
                    PreparedStatement stmt = _cn.prepareStatement("select * from tbGrados where idGrado = ?");
                    stmt.setInt(1,idGrado);
                    ResultSet resultSet = stmt.executeQuery();
                    if(resultSet.next()){
                        int idNivelAcademico = resultSet.getInt("idNivelAcademico");
                        int idSeccion = resultSet.getInt( "idSeccion");
                        PreparedStatement stmt1 = _cn.prepareStatement("select * from tbNivelesAcademicos where idNivelAcademico = ?");
                        PreparedStatement stmt2 = _cn.prepareStatement("select * from tbSecciones where idSeccion = ?");
                        stmt1.setInt(1,idNivelAcademico);
                        stmt2.setInt(1,idSeccion);
                        ResultSet resultSet1 = stmt1.executeQuery();
                        ResultSet resultSet2 = stmt2.executeQuery();
                        if(resultSet1.next() && resultSet2.next()){
                            grado+= resultSet1.getString("nivelAcademico") +" "+ resultSet2.getString("seccion");
                        }
                    }
                }

                res.close();
                return grado;
            } catch (SQLException e) {
                e.printStackTrace();
                return "";
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).whenComplete((Credenciales, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<List<?>> obtenerCredencialesEstudianteAsync(int IdPersona) {
        return CompletableFuture.supplyAsync(() -> {
            List<Credenciales> Credenciales = new ArrayList<>();
            Statement statement = null;

            try {
                statement = _cn.createStatement();
                String query = "select * from tbCredenciales where idPersona = " + IdPersona;
                ResultSet res =statement.executeQuery(query);

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
                    Credenciales.add(ncredencial);
                }

                res.close();
                return Credenciales;
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
        }).whenComplete((Credenciales, throwable) -> {
            try {
                if (_cn != null && !_cn.isClosed()) {
                    _cn.close();
                }
            } catch (SQLException e) {
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

    public static CompletableFuture<Integer> eliminarCredencialCodigoAsync(String codigo){
        return CompletableFuture.supplyAsync(() -> {
            PreparedStatement stmt = null;
            new CredencialesDB();
            try{
                stmt = _cn.prepareStatement("delete from tbCredenciales where codigo = ?");
                stmt.setString(1,codigo);
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
