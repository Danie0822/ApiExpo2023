package com.example.expo.Controllers;

import com.example.expo.Models.Grados;
import com.example.expo.Models.Salones;
import com.example.expo.Models.SeccionesBachillerato;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.GradosDB;
import com.example.expo.Services.SalonesDB;
import com.example.expo.Services.SeccionesBachilleratoDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("Salones")
public class SalonesController {

    @GetMapping("/list")
    public CompletableFuture<Map<String, Object>> obtenerGrupos() {
        CompletableFuture<List<?>> salonesFuture = new SalonesDB().obtenerSalonesAsync();

        return salonesFuture.thenApply(salones -> {
            Map<String, Object> response = new HashMap<>();
            response.put("salones", salones);
            return response;
        });
    }




    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody Salones Salones) {
        return  SalonesDB.insertarSalonesAsync(Salones)
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
        return SalonesDB.eliminarSalonesAsync(id)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item removed with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }

    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody Salones Salones) {
        return SalonesDB.ActulizarSalonesAsync(Salones)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item updated with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }
}
