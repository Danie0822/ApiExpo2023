package com.example.expo.Controllers;

import com.example.expo.Models.Especialidades;
import com.example.expo.Models.SeccionesBachillerato;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.EspecialidadesDB;
import com.example.expo.Services.SeccionesBachilleratoDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("SeccionesBachilleratos")
public class SeccionesBachilleratosController {
    @GetMapping("/list")
    public List<SeccionesBachillerato> SeccionesBachillerato() {
        return new SeccionesBachilleratoDB().ObtenerSeccionesBachillerato();
    }
    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> save(@RequestBody SeccionesBachillerato SeccionesBachillerato) {
        ServiceResponse serviceResponse = new ServiceResponse();

        int result = SeccionesBachilleratoDB.InsertarSeccionesBachillerato(SeccionesBachillerato);
        if (result == 1) {
            serviceResponse.setMessage("Item saved with success");
        }
        return new  ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceResponse> update(@PathVariable int id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = SeccionesBachilleratoDB.EliminarSeccionesBachillerato(id);
        if (result == 1) {
            serviceResponse.setMessage("Item removed with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ServiceResponse> update(@RequestBody SeccionesBachillerato SeccionesBachillerato) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = SeccionesBachilleratoDB.ActulizarSeccionesBachillerato(SeccionesBachillerato);
        if (result == 1) {
            serviceResponse.setMessage("Item update with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
}
