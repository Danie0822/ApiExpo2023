package com.example.expo.Controllers;

import com.example.expo.Models.Contra;
import com.example.expo.Models.CredeInsertUpdate;
import com.example.expo.Models.Credenciales;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.CodigosConductualesDB;
import com.example.expo.Services.CredencialesDB;
import com.example.expo.Services.FuncionesDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("Credenciales")
@CrossOrigin(origins = "https://expo-movil.vercel.app")
public class CredencialesController {


    @GetMapping("/getName/{id}")
    public CompletableFuture<?> obtenerNombre(@PathVariable int id){
        return new CredencialesDB().obtenerNombre(id);
    }

    @GetMapping("/getPersona/{codigo}")
    public CompletableFuture<?> obtenerPersonaByCodigo(@PathVariable String codigo){
        return new CredencialesDB().obtenerPersonaByCodigo(codigo);
    }

    @GetMapping("/list")
    public List<?> obtenerListaCredenciales(){
        CompletableFuture<List<?>> futurecredenciales = new CredencialesDB().obtenerCredencialesAsync();
        List<?> credenciales = futurecredenciales.join();
        return credenciales;
    }

    @GetMapping("/getGrado/{id}")
    public String obtenerGradoEstudiante(@PathVariable int id){
        CompletableFuture<String> futureGrado = new CredencialesDB().obtenerGradoEstudianteAsync(id);
        String grado = futureGrado.join();
        return grado;
    }

    @GetMapping("/getEspecialidad/{id}")
    public String obtenerEspecialidad(@PathVariable int id){
        CompletableFuture<String> futureEspecialidad = new CredencialesDB().obtenerEspecialidadEstudianteAsync(id);
        String especialidad = futureEspecialidad.join();
        return especialidad;
    }

    @GetMapping("/getSeccionAcademica/{id}")
    public String obtenerSeccionAcademicaEstudiante(@PathVariable int id){
        CompletableFuture<String> futureEspecialidad = new CredencialesDB().obtenerSeccionAcademicaEstudianteAsync(id);
        String especialidad = futureEspecialidad.join();
        return especialidad;
    }

    @GetMapping("/getSeccionTecnica/{id}")
    public String obtenerSeccionTecnicaEstudiante(@PathVariable int id){
        CompletableFuture<String> futureEspecialidad = new CredencialesDB().obtenerSeccionTecnicaEstudianteAsync(id);
        String especialidad = futureEspecialidad.join();
        return especialidad;
    }

    @GetMapping("/getGrupoTecnico/{id}")
    public String obtenerGrupoTecnico (@PathVariable int id){
        CompletableFuture<String> futureEspecialidad = new CredencialesDB().obtenerGrupoEstudianteAsync(id);
        String especialidad = futureEspecialidad.join();
        return especialidad;
    }
    @GetMapping("/validar")
    public ResponseEntity<?> Obtener(@RequestParam("correo") String correo) {
        CompletableFuture<List<?>> futureCredenciales = new CredencialesDB().ObtenerProfesor(correo);

        try {
            List<?> credenciales = futureCredenciales.get();

            if (!credenciales.isEmpty()) {
                // Se encontraron coincidencias, se devuelve el primer resultado
                Object usuario = credenciales.get(0);
                return ResponseEntity.ok(usuario);
            } else {
                // No se encontraron coincidencias
                return ResponseEntity.notFound().build();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/get/{tipo}")
    public List<?> obtenerListaCredencialesTipo(@PathVariable int tipo){
        CompletableFuture<List<?>> futurecredenciales = new CredencialesDB().obtenerCredencialesTipoAsync(tipo);
        List<?> credenciales = futurecredenciales.join();
        return credenciales;
    }

    @GetMapping("/getNo/{tipo}")
    public List<?> obtenerLIstaCredencialesNoTipo(@PathVariable int tipo){
        CompletableFuture<List<?>> futureCrendenciales = new CredencialesDB().obtenerCredencialesNoTipoAsync(tipo);
        List<?> credenciales = futureCrendenciales.join();
        return credenciales;
    }

    @GetMapping("/getId/{codigo}")
    public ResponseEntity<?> obtenerIdEstudianteAsync(@PathVariable String codigo){
        CompletableFuture<?> futureCredencial = new CredencialesDB().obtenerPersonasCodigo(codigo);
        return ResponseEntity.ok(futureCredencial.join());
    }


    @GetMapping("/user")
    public ResponseEntity<?> obtenerUsuario(@RequestParam("correo") String correo, @RequestParam("claveCredenciales") String claveCredenciales) {
        CompletableFuture<List<?>> futureCredenciales = new CredencialesDB().obtenerCredencialesAsync(correo, claveCredenciales);

        try {
            List<?> credenciales = futureCredenciales.get();

            if (!credenciales.isEmpty()) {
                // Se encontraron coincidencias, se devuelve el primer resultado
                Object usuario = credenciales.get(0);
                return ResponseEntity.ok(usuario);
            } else {
                // No se encontraron coincidencias
                return ResponseEntity.notFound().build();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/list/{idEstudiante}")
    public List<?>obtenerObservacionesStringPorEstudianteAsync(@PathVariable int idEstudiante) {
        CompletableFuture<List<?>> futureEspecialidades = new CredencialesDB().obtenerCredencialesEstudianteAsync(idEstudiante);
        List<?> Observaciones = futureEspecialidades.join();
        return Observaciones;
    }

    @GetMapping("/Search/{idEstudiante}")
    public List<?> obtenerCodigosConductualesStringPorEstudianteAsync(@PathVariable String idEstudiante) {
        CompletableFuture<List<?>> futureResult = new CredencialesDB().buscarCredencialesEstudianteAsync(idEstudiante);
        List<?> Credenciales = futureResult.join();
        return Credenciales;
    }
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody CredeInsertUpdate credenciales){
        CompletableFuture<Integer> futureRes = CredencialesDB.insertarCredencialesAsync(credenciales);

        return futureRes.thenApply(result -> {
            ServiceResponse serviceResponse =new ServiceResponse();
            if(result == 1){
                serviceResponse.setMessage("Item saved with success");
                return ResponseEntity.ok(serviceResponse);
            }
            else{
                serviceResponse.setMessage("Failed to save item");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
            }
        });
    }

    @PutMapping("/validate/{codigo}")
    public CompletableFuture<ResponseEntity<ServiceResponse>> validateCodigo(@PathVariable String codigo){
        CompletableFuture<Integer> futureRes = CredencialesDB.validarCredencialesNoEstudianteAsync(codigo);
        return futureRes.thenApply(result -> {
            ServiceResponse serviceResponse = new ServiceResponse();
            if(result == 1){
                serviceResponse.setMessage("Codigo Validated");
                return ResponseEntity.ok(serviceResponse);
            }
            else{
                serviceResponse.setMessage("Invalidate codigo");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(serviceResponse);
            }
        });
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<ResponseEntity<ServiceResponse>> delete(@PathVariable int id){
        CompletableFuture<Integer> futureRes = CredencialesDB.eliminarCredencialAsync(id);
        return futureRes.thenApply(result -> {
            ServiceResponse serviceResponse = new ServiceResponse();
            if(result == 1){
                serviceResponse.setMessage("Item removed with success");
                return ResponseEntity.ok(serviceResponse);
            }
            else{
                serviceResponse.setMessage("Failed to remove item");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
            }
        });
    }

    @DeleteMapping("/deleteCodigo/{codigo}")
    public CompletableFuture<ResponseEntity<ServiceResponse>> deleteCodigo(@PathVariable String codigo){
        CompletableFuture<Integer> futureRes = CredencialesDB.eliminarCredencialCodigoAsync(codigo);
        return futureRes.thenApply(result -> {
            ServiceResponse serviceResponse = new ServiceResponse();
            if(result == 1){
                serviceResponse.setMessage("Item removed with success");
                return ResponseEntity.ok(serviceResponse);
            }
            else{
                serviceResponse.setMessage("Failed to remove item");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
            }
        });
    }
    @PutMapping("/Contra")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody Contra Contra){
        CompletableFuture<Integer> futureRes = CredencialesDB.UpdateContra(Contra);
        return futureRes.thenApply(result -> {
            ServiceResponse serviceResponse = new ServiceResponse();
            if (result == 1) {
                serviceResponse.setMessage("Item updated with success");
                return ResponseEntity.ok(serviceResponse);
            } else {
                serviceResponse.setMessage("Failed to update item");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
            }
        });
    }
    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody CredeInsertUpdate credenciales){
        CompletableFuture<Integer> futureRes = CredencialesDB.actualizarCredencialesAsync(credenciales);
        return futureRes.thenApply(result -> {
            ServiceResponse serviceResponse = new ServiceResponse();
            if (result == 1) {
                serviceResponse.setMessage("Item updated with success");
                return ResponseEntity.ok(serviceResponse);
            } else {
                serviceResponse.setMessage("Failed to update item");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
            }
        });
    }

}
