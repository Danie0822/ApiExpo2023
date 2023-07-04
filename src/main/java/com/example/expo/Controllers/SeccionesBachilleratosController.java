package com.example.expo.Controllers;

import com.example.expo.Models.Especialidades;
import com.example.expo.Models.Grados;
import com.example.expo.Models.SeccionesBachillerato;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.EspecialidadesDB;
import com.example.expo.Services.GradosDB;
import com.example.expo.Services.SeccionesBachilleratoDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("SeccionesBachilleratos")
public class SeccionesBachilleratosController {

    @GetMapping("/list")
    public CompletableFuture<List<?>> obtenerGrupos() {
        return new SeccionesBachilleratoDB().obtenerSeccionesBachilleratoAsync();
    }
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody SeccionesBachillerato SeccionesBachillerato) {
        return SeccionesBachilleratoDB.insertarSeccionesBachilleratoAsync(SeccionesBachillerato)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item saved with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<ResponseEntity<ServiceResponse>> delete(@PathVariable int id) {
        return SeccionesBachilleratoDB.eliminarSeccionesBachilleratoAsync(id)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item removed with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }

    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody SeccionesBachillerato SeccionesBachillerato) {
        return SeccionesBachilleratoDB.ActulizarSeccionesBachilleratoAsync(SeccionesBachillerato)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item updated with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }
}
