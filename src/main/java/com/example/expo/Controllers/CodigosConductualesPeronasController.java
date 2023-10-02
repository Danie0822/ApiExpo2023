package com.example.expo.Controllers;

import com.example.expo.Models.CodigosConductualesPersonas;
import com.example.expo.Models.Encargados;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.CodigosConductualesPersonasDB;
import com.example.expo.Services.EncargadosDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("CodigosConductualesPersonas")
@CrossOrigin(origins = "https://expo-movil.vercel.app")
public class CodigosConductualesPeronasController {
    @GetMapping("/list")
    public List<?> obtenerGrupos() {
        CompletableFuture<List<?>> futureEspecialidades = new CodigosConductualesPersonasDB().obtenerCodigosConductualesPersonasAsync();
        List<?> CodigosConductualesPersonas = futureEspecialidades.join();
        return CodigosConductualesPersonas;
    }
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody CodigosConductualesPersonas CodigosConductualesPersonas) {
        CompletableFuture<Integer> futureResult = CodigosConductualesPersonasDB.insertarCodigosConductualesPersonasAsync(CodigosConductualesPersonas);

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
        CompletableFuture<Integer> futureResult = CodigosConductualesPersonasDB.eliminarCodigosConductualesPersonasAsync(id);

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
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody CodigosConductualesPersonas CodigosConductualesPersonas) {
        CompletableFuture<Integer> futureResult = CodigosConductualesPersonasDB.actualizarCodigosConductualesPersonasAsync(CodigosConductualesPersonas);

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
