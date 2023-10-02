package com.example.expo.Controllers;

import com.example.expo.Services.FuncionesDB;
import com.example.expo.Services.VisitasEnfermeriaDB;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("Funciones")
@CrossOrigin
public class FuncionesController {
    @GetMapping("/CodigosConductuales")
    public List<?> obtenerGrupos() {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().obtenerCodigosConductualesStringAsync();
        List<?> CodigosConductuales = futureEspecialidades.join();
        return CodigosConductuales;
    }
    @GetMapping("/CodigosConductuales/{idEstudiante}")
    public List<?> obtenerCodigosConductualesStringPorEstudianteAsync(@PathVariable int idEstudiante) {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().obtenerCodigosConductualesStringPorEstudianteAsync(idEstudiante);
        List<?> CodigosConductuales = futureEspecialidades.join();
        return CodigosConductuales;
    }
    @GetMapping("/LlegadasTardes/{idEstudiante}")
    public List<?> obtenerLlegadasTardeStringPorEstudianteAsync(@PathVariable int idEstudiante) {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().obtenerLlegadasTardeStringPorEstudianteAsync(idEstudiante);
        List<?> LlegadasTardes = futureEspecialidades.join();
        return LlegadasTardes;
    }

    @GetMapping("/LlegadasTardes")
    public List<?> obtenerLlegadas() {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().obtenerLlegadasTardeStringAsync();
        List<?> LlegadasTardes = futureEspecialidades.join();
        return LlegadasTardes;
    }
    @GetMapping("/Inasisitencias/{idEstudiante}")
    public List<?> obtenerInasisitenciastringPorEstudianteAsync(@PathVariable int idEstudiante) {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().obtenerInasisitenciastringPorEstudianteAsync(idEstudiante);
        List<?> Inasisitenciastring = futureEspecialidades.join();
        return Inasisitenciastring;
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
    @GetMapping("/Notificaciones/{idEstudiante}")
    public List<?>obtenerNotificacionesstringPorEstudianteAsync(@PathVariable int idEstudiante) {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().obtenerNotificacionesstringPorEstudianteAsync(idEstudiante);
        List<?> Notificaciones = futureEspecialidades.join();
        return Notificaciones;
    }

    @GetMapping("/Notificaciones")
    public List<?>  Notificaciones() {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().obtenerNotificacionesstringAsync();
        List<?> Notificaciones = futureEspecialidades.join();
        return Notificaciones;
    }
    @GetMapping("/Observaciones/{idEstudiante}")
    public List<?>obtenerObservacionesStringPorEstudianteAsync(@PathVariable int idEstudiante) {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().obtenerObservacionesStringPorEstudianteAsync(idEstudiante);
        List<?> Observaciones = futureEspecialidades.join();
        return Observaciones;
    }
    @GetMapping("/Observaciones")
    public List<?>  Observaciones() {
        CompletableFuture<List<?>> futureEspecialidades = new FuncionesDB().ObtenerObservaciones();
        List<?> Observaciones = futureEspecialidades.join();
        return Observaciones;
    }
    @GetMapping("/Mensajes")
    public List<?>  ObtenerMensajes() {
        CompletableFuture<List<?>> MensajesFuture = new FuncionesDB().ObtenerMensajes();
        List<?> Mensajes = MensajesFuture.join();
        return Mensajes;
    }
    @GetMapping("/Crede")
    public List<?>  ObtenerCrede() {
        CompletableFuture<List<?>> CredeFuture = new FuncionesDB().ObtenerCrede();
        List<?> Crede = CredeFuture.join();
        return Crede;
    }

}
