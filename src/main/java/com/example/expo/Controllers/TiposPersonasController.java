package com.example.expo.Controllers;

import com.example.expo.Models.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.expo.Models.TiposPersonas;
import com.example.expo.Services.TiposPersonasDB;

import java.util.*;

@RestController
@RequestMapping("TiposPersonas")
public class TiposPersonasController {

    @GetMapping("/list")
    public List<TiposPersonas> ObtenerMascotas() {
        return new TiposPersonasDB().obtenerMascotas();
    }
    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> save(@RequestBody TiposPersonas Mascotas) {
        ServiceResponse serviceResponse = new ServiceResponse();

        int result = TiposPersonasDB.Insertar(Mascotas);
        if (result == 1) {
            serviceResponse.setMessage("Item saved with success");
        }
        return new  ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceResponse> update(@PathVariable int id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = TiposPersonasDB.Eliminar(id);
        if (result == 1) {
            serviceResponse.setMessage("Item removed with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ServiceResponse> update(@RequestBody TiposPersonas Mascotas) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = TiposPersonasDB.Actulizar(Mascotas);
        if (result == 1) {
            serviceResponse.setMessage("Item update with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

}
