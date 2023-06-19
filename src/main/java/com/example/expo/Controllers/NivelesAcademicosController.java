package com.example.expo.Controllers;

import com.example.expo.Models.Grados;
import com.example.expo.Models.NivelesAcademicos;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.GradosDB;
import com.example.expo.Services.NivelesAcademicosDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("NivelesAcademicos")
public class NivelesAcademicosController {

    @GetMapping("/list")
    public CompletableFuture<List<?>> obtenerGrupos() {
        return new NivelesAcademicosDB().obtenerNiveleAcademicosAsync();
    }
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody NivelesAcademicos NivelesAcademicos) {
        return  NivelesAcademicosDB.insertarNiveleAcademicosAsync(NivelesAcademicos)
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
        return NivelesAcademicosDB.eliminarNivelesAcademicosAsync(id)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item removed with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }

    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody NivelesAcademicos NivelesAcademicos) {
        return NivelesAcademicosDB.ActulizarNivelesAcademicosAsync(NivelesAcademicos)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item updated with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }
}
