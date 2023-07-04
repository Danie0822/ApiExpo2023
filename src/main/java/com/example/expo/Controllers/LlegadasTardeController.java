package com.example.expo.Controllers;
import com.example.expo.Models.Grados;
import com.example.expo.Models.GruposTecnicos;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Models.TiposLlegadasTarde;
import com.example.expo.Services.GradosDB;
import com.example.expo.Services.GruposTecnicosDB;
import com.example.expo.Services.LlegadasTardeDB;
import com.example.expo.Services.TiposLlegadasTardeDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("LlegadasTarde")
public class LlegadasTardeController {

    @GetMapping("/list")
    public List<?> obtenerGrupos() {
        CompletableFuture<List<?>> futureEspecialidades = new LlegadasTardeDB().obtenerLlegadasTardeAsync();
        List<?> LlegadasTarde = futureEspecialidades.join();
        return LlegadasTarde;
    }

    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody LlegadasTarde LlegadasTarde) {
        CompletableFuture<Integer> futureResult = LlegadasTardeDB.insertarLlegadasTardeAsync(LlegadasTarde);

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
        CompletableFuture<Integer> futureResult = LlegadasTardeDB.eliminarLlegadasTardeAsync(id);
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
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody LlegadasTarde LlegadasTarde) {
        CompletableFuture<Integer> futureResult = LlegadasTardeDB.ActualizarLlegadasTardeAsync(LlegadasTarde);

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

