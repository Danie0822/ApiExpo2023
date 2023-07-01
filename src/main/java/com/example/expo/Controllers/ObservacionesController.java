package com.example.expo.Controllers;

import com.example.expo.Models.CodigosConductualesPersonas;
import com.example.expo.Models.Observaciones;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.CodigosConductualesPersonasDB;
import com.example.expo.Services.ObservacionesDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("0bservaciones")
public class ObservacionesController {

    @GetMapping("/list")
    public List<?> obtenerGrupos() {
        CompletableFuture<List<?>> futureEspecialidades = new ObservacionesDB().obtenerObservacionesAsync();
        List<?> Observaciones = futureEspecialidades.join();
        return Observaciones;
    }

    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody Observaciones Observaciones) {
        CompletableFuture<Integer> futureResult = ObservacionesDB.insertarObservacionesAsync(Observaciones);

        return futureResult.thenApply(result -> {
            ServiceResponse serviceResponse = new ServiceResponse();

            if (result == 1) {
                serviceResponse.setMessage("Item saved with success");
                return ResponseEntity.ok(serviceResponse);
            } else {
                serviceResponse.setMessage("Failed to save item");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
            }
        });
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@PathVariable int id) {
        CompletableFuture<Integer> futureResult = ObservacionesDB.eliminarObservacionesAsync(id);

        return futureResult.thenApply(result -> {
            ServiceResponse serviceResponse = new ServiceResponse();

            if (result == 1) {
                serviceResponse.setMessage("Item removed with success");
                return ResponseEntity.ok(serviceResponse);
            } else {
                serviceResponse.setMessage("Failed to remove item");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
            }
        });
    }

    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody Observaciones Observaciones) {
        CompletableFuture<Integer> futureResult = ObservacionesDB.actualizarObservacionesAsync(Observaciones);

        return futureResult.thenApply(result -> {
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
