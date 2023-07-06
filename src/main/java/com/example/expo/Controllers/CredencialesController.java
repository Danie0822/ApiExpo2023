package com.example.expo.Controllers;

import com.example.expo.Models.Credenciales;
import com.example.expo.Models.ServiceResponse;
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
public class CredencialesController {

    @GetMapping("/list")
    public List<?> obtenerListaCredenciales(){
        CompletableFuture<List<?>> futurecredenciales = new CredencialesDB().obtenerCredencialesAsync();
        List<?> credenciales = futurecredenciales.join();
        return credenciales;
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
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody Credenciales credenciales){
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


    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody Credenciales credenciales){
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
