package com.example.expo.Controllers;


import com.example.expo.Models.Grados;
import com.example.expo.Models.Secciones;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.GradosDB;
import com.example.expo.Services.SeccionesDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("Secciones")
public class SeccionesController {

    @GetMapping("/get/{seccion}")
    public CompletableFuture<?> obtenerSeccionbyName(@PathVariable String seccion){
        return new SeccionesDB().obtenerSeccionAsyncbyName(seccion);
    }

    @GetMapping("/obtener/{id}")
    public CompletableFuture<?> obtenerSeccion(@PathVariable int id){
        return new SeccionesDB().obtenerSeccionAsync(id);
    }
    @GetMapping("/list")
    public CompletableFuture<List<?>> obtenerGrupos() {
        return new SeccionesDB().obtenerSeccionesAsync();
    }
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody Secciones Secciones) {
        return SeccionesDB.insertarSeccionesAsync(Secciones)
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
        return  SeccionesDB.eliminarSeccionesAsync(id)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item removed with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }

    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody Secciones Secciones) {
        return  SeccionesDB.ActulizarSeccionesAsync(Secciones)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item updated with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }
}

