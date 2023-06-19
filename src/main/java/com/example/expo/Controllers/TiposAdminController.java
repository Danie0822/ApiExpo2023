package com.example.expo.Controllers;

import com.example.expo.Models.Grados;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Models.tbAdmi;
import com.example.expo.Services.GradosDB;
import com.example.expo.Services.TiposAdminDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("TiposAdmin")
public class TiposAdminController {

    @GetMapping("/list")
    public CompletableFuture<List<?>> obtenerGrupos() {
        return new TiposAdminDB().obtenerTiposAdminAsync();
    }
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody tbAdmi tbAdmi) {
        return TiposAdminDB.insertarTiposAdminAsync(tbAdmi)
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
        return TiposAdminDB.eliminarTiposAdminAsync(id)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item removed with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }

    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody tbAdmi tbAdmi) {
        return TiposAdminDB.ActulizarTiposAdminAsync(tbAdmi)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item updated with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }
}
