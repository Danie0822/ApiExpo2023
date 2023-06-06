package com.example.expo.Controllers;


import com.example.expo.Models.Secciones;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.SeccionesDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Secciones")
public class SeccionesController {
    @GetMapping("/list")
    public List<Secciones> ObtenerSecciones() {
        return new SeccionesDB().ObtenerSecciones();
    }
    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> save(@RequestBody Secciones Secciones) {
        ServiceResponse serviceResponse = new ServiceResponse();

        int result = SeccionesDB.InsertarSecciones(Secciones);
        if (result == 1) {
            serviceResponse.setMessage("Item saved with success");
        }
        return new  ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceResponse> update(@PathVariable int id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = SeccionesDB.EliminarSecciones(id);
        if (result == 1) {
            serviceResponse.setMessage("Item removed with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ServiceResponse> update(@RequestBody Secciones Secciones) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = SeccionesDB.Actulizar(Secciones);
        if (result == 1) {
            serviceResponse.setMessage("Item update with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
}

