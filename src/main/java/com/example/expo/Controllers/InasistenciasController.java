package com.example.expo.Controllers;

import com.example.expo.Models.InasisitenciasEstado;
import com.example.expo.Models.Inasistencias;
import com.example.expo.Models.LlegadasTarde;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.InasistenciasDB;
import com.example.expo.Services.LlegadasTardeDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("Inasistencias")
public class InasistenciasController {
    @GetMapping("/list")
    public List<?> obtenerGrupos() {
        CompletableFuture<List<?>> futureEspecialidades = new InasistenciasDB().obtenerInasistenciasAsync();
        List<?> LlegadasTarde = futureEspecialidades.join();
        return LlegadasTarde;
    }
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody Inasistencias Inasistencias) {
        CompletableFuture<Integer> futureResult = InasistenciasDB.insertarInasistencias(Inasistencias);

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
        CompletableFuture<Integer> futureResult = InasistenciasDB.eliminarInasistenciasAsync(id);
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
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody Inasistencias Inasistencias) {
        CompletableFuture<Integer> futureResult = InasistenciasDB.ActualizarInasistenciasAsync(Inasistencias);

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

    
    @PutMapping("/Estado")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody InasisitenciasEstado InasisitenciasEstado) {
        CompletableFuture<Integer> futureResult = InasistenciasDB.ActualizarEstadoAsync(InasisitenciasEstado);

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

