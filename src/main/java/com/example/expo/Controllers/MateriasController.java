package com.example.expo.Controllers;

import com.example.expo.Models.Materias;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.MateriasDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Materias")
public class MateriasController {

    @GetMapping("/list")
    public List<Materias> ObtenerMaterias() {
        return new MateriasDB().ObtenerMaterias();
    }
    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> save(@RequestBody Materias Materias) {
        ServiceResponse serviceResponse = new ServiceResponse();

        int result = MateriasDB.InsertarMateria(Materias);
        if (result == 1) {
            serviceResponse.setMessage("Item saved with success");
        }
        return new  ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceResponse> update(@PathVariable int id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = MateriasDB.EliminarMaterias(id);
        if (result == 1) {
            serviceResponse.setMessage("Item removed with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ServiceResponse> update(@RequestBody Materias Materias) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = MateriasDB.Actulizar(Materias);
        if (result == 1) {
            serviceResponse.setMessage("Item update with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
}
