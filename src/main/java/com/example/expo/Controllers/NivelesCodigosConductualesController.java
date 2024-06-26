package com.example.expo.Controllers;

import com.example.expo.Models.NivelesCodigosConductuales;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Models.TiposCodigosConductuales;
import com.example.expo.Services.NivelesCodigosConductualesDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("NivelesCodigosConductuales")
public class NivelesCodigosConductualesController {
    @GetMapping("/list")
    public List<?> obtenerCodigos(){
        CompletableFuture<List<?>> futureEspecialidades = new NivelesCodigosConductualesDB().obtenerNivelesCodigosConductualesAsync();
        List<?> NivelesCodigosConductuales = futureEspecialidades.join();
        return NivelesCodigosConductuales;
    }

    @GetMapping("/get/{id}")
    public String getNivelCodigoConductual(@PathVariable int id){
        CompletableFuture<String> futureNivelCodigoConductual = new NivelesCodigosConductualesDB().getNivelCodigoConductualAsync(id);
        String nivelCodigoConductual = futureNivelCodigoConductual.join();
        return nivelCodigoConductual;
    }

    @GetMapping("/getName/{name}")
    public Integer getNivelCodigoConductualName(@PathVariable String name){
        CompletableFuture<Integer> futureIdNivelCodigoConductual = new NivelesCodigosConductualesDB().getNivelCodigoConductualNameAsync(name);
        Integer idNivelCodigoConductual = futureIdNivelCodigoConductual.join();
        return idNivelCodigoConductual;
    }
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody NivelesCodigosConductuales NivelesCodigosConductuales){
        CompletableFuture<Integer> futureResult= NivelesCodigosConductualesDB.insertarNivelesCodigosConductualesAsync(NivelesCodigosConductuales);

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
        CompletableFuture<Integer> futureResult = NivelesCodigosConductualesDB.eliminarNivelesCodigosConductualesAsync(id);

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
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody NivelesCodigosConductuales NivelesCodigosConductuales){
        CompletableFuture<Integer> futureResult = NivelesCodigosConductualesDB.actualizarNivelesCodigosConductualesAsync(NivelesCodigosConductuales);

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
