package com.example.expo.Controllers;

import com.example.expo.Services.FuncionesDB;
import com.example.expo.Services.VisitasEnfermeriaDB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("Funciones")
public class FuncionesController {
    @GetMapping("/CodigosConductuales")
    public List<?> obtenerGrupos() {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().obtenerCodigosConductualesStringAsync();
        List<?> CodigosConductuales = futureEspecialidades.join();
        return CodigosConductuales;
    }
    @GetMapping("/LlegadasTardes")
    public List<?> obtenerLlegadas() {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().obtenerLlegadasTardeStringAsync();
        List<?> LlegadasTardes = futureEspecialidades.join();
        return LlegadasTardes;
    }
    @GetMapping("/Inasisitencias")
    public List<?> Inasisitenciastring() {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().obtenerInasisitenciastringAsync();
        List<?> Inasisitenciastring = futureEspecialidades.join();
        return Inasisitenciastring;
    }
    @GetMapping("/ReservacionesSalones")
    public List<?>  ReservacionesSalones() {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().obtenerReservacionesSalonestringAsync();
        List<?> Inasisitenciastring = futureEspecialidades.join();
        return Inasisitenciastring;
    }
    @GetMapping("/Notificaciones")
    public List<?>  Notificaciones() {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().obtenerNotificacionesstringAsync();
        List<?> Notificaciones = futureEspecialidades.join();
        return Notificaciones;
    }
    @GetMapping("/Observaciones")
    public List<?>  Observaciones() {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().obtenerObservacionesStringAsync();
        List<?> Observaciones = futureEspecialidades.join();
        return Observaciones;
    }



}
