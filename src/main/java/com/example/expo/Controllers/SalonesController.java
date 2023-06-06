package com.example.expo.Controllers;

import com.example.expo.Models.Salones;
import com.example.expo.Models.SeccionesBachillerato;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.SalonesDB;
import com.example.expo.Services.SeccionesBachilleratoDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Salones")
public class SalonesController {
    @GetMapping("/list")
    public List<Salones> SeccionesBachillerato() {
        return new SalonesDB().ObtenerSalones();
    }
    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> save(@RequestBody Salones Salones) {
        ServiceResponse serviceResponse = new ServiceResponse();

        int result = SalonesDB.InsertarSalones(Salones);
        if (result == 1) {
            serviceResponse.setMessage("Item saved with success");
        }
        return new  ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceResponse> update(@PathVariable int id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = SalonesDB.EliminarSalones(id);
        if (result == 1) {
            serviceResponse.setMessage("Item removed with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ServiceResponse> update(@RequestBody Salones Salones) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = SalonesDB.ActulizarSalones(Salones);
        if (result == 1) {
            serviceResponse.setMessage("Item update with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
}
