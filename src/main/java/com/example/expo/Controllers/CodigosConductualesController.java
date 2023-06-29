package com.example.expo.Controllers;


import com.example.expo.Models.CodigosConductuales;
import com.example.expo.Models.NivelesCodigosConductuales;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.CodigosConductualesDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("CodigosConductuales")
public class CodigosConductualesController {
    @GetMapping("/list")
    public List<?> obtenerCodigos(){
        CompletableFuture<List<?>> futureEspecialidades= new CodigosConductualesDB().obtenerCodigosConductualesAsync();
        List<?> CodigosConductuales = futureEspecialidades.join();
        return CodigosConductuales;
    }

    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody CodigosConductuales CodigosConductuales){
        CompletableFuture<Integer> futureResult= CodigosConductualesDB.insertarCodigosConductualesAsync(CodigosConductuales);

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
        CompletableFuture<Integer> futureResult = CodigosConductualesDB.eliminarCodigosConductualesAsync(id);

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
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody CodigosConductuales CodigosConductuales){
        CompletableFuture<Integer> futureResult = CodigosConductualesDB.actualizarCodigosConductualesAsync(CodigosConductuales);

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
