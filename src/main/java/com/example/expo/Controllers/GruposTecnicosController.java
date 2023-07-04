package com.example.expo.Controllers;
import com.example.expo.Models.Grados;
import com.example.expo.Models.GruposTecnicos;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.GradosDB;
import com.example.expo.Services.GruposTecnicosDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("Grupos")
public class GruposTecnicosController {
    @GetMapping("/list")
    public CompletableFuture<List<?>> obtenerGrupos() {
        return new GruposTecnicosDB().obtenerGruposTecnicosAsync();
    }
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody GruposTecnicos GruposTecnicos) {
        return GruposTecnicosDB.insertarGruposTecnicosAsync(GruposTecnicos)
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
        return  GruposTecnicosDB.eliminarGruposTecnivosAsync(id)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item removed with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }


    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody GruposTecnicos GruposTecnicos) {
        return GruposTecnicosDB.ActulizarGruposTecnicosAsync(GruposTecnicos)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item updated with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }
}

