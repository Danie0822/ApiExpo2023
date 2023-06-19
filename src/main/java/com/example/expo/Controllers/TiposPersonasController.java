package com.example.expo.Controllers;

import com.example.expo.Models.Grados;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.GradosDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.expo.Models.TiposPersonas;
import com.example.expo.Services.TiposPersonasDB;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("TiposPersonas")
public class TiposPersonasController {

    @GetMapping("/list")
    public CompletableFuture<List<?>> obtenerGrupos() {
        return new TiposPersonasDB().obtenerTiposPersonasAsync();
    }
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody TiposPersonas TiposPersonas) {
        return TiposPersonasDB.insertarTiposPersonasAsync(TiposPersonas)
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
        return  TiposPersonasDB.eliminarTiposPersonasAsync(id)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item removed with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }

    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody TiposPersonas TiposPersonas) {
        return  TiposPersonasDB.ActulizarTiposPersonasAsync(TiposPersonas)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item updated with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }

}
