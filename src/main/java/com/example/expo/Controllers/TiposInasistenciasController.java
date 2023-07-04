package com.example.expo.Controllers;

import com.example.expo.Models.ServiceResponse;
import com.example.expo.Models.TiposInasistencias;
import com.example.expo.Models.TiposPermisos;
import com.example.expo.Services.TiposInasistenciasDB;
import com.example.expo.Services.TiposPermisosDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("TiposInasistencias")
public class TiposInasistenciasController {
    @GetMapping("/list")
    public List<?> obtenerGrupos() {
        CompletableFuture<List<?>> futureEspecialidades = new TiposInasistenciasDB().obtenerTiposInasistenciasAsync();
        List<?> TiposInasistencias = futureEspecialidades.join();
        return TiposInasistencias;
    }
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody TiposInasistencias TiposInasistencias) {
        CompletableFuture<Integer> futureResult = TiposInasistenciasDB.insertarTiposInasistenciasAsync(TiposInasistencias);

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
        CompletableFuture<Integer> futureResult = TiposInasistenciasDB.eliminarTiposInasistenciasAsync(id);

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
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody TiposInasistencias TiposInasistencias) {
        CompletableFuture<Integer> futureResult = TiposInasistenciasDB.ActulizarTiposInasistenciasAsync(TiposInasistencias);

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
