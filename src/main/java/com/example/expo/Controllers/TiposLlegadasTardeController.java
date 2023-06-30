package com.example.expo.Controllers;
import com.example.expo.Models.*;
import com.example.expo.Services.EncargadosDB;
import com.example.expo.Services.TiposCodigosConductualesDB;
import com.example.expo.Services.TiposLlegadasTardeDB;
import com.example.expo.Services.TiposPermisosDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("TiposLlegadasTarde")
public class TiposLlegadasTardeController {

    @GetMapping("/list")
    public List<?> obtenerGrupos() {
        CompletableFuture<List<?>> futureEspecialidades = new TiposLlegadasTardeDB().obtenerTiposLlegadasTardeAsync();
        List<?> TiposLlegadasTarde = futureEspecialidades.join();
        return TiposLlegadasTarde;
    }

        @PostMapping("/save")
        public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody TiposLlegadasTarde TiposLlegadasTarde) {
        CompletableFuture<Integer> futureResult = TiposLlegadasTardeDB.insertarTiposLlegadasTardeAsync(TiposLlegadasTarde);

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
           CompletableFuture<Integer> futureResult = TiposLlegadasTardeDB.eliminarTiposLlegadasTardeAsync(id);
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
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody TiposLlegadasTarde TiposLlegadasTarde) {
        CompletableFuture<Integer> futureResult = TiposLlegadasTardeDB.ActualizarTiposLlegadasTardeAsync(TiposLlegadasTarde);

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



