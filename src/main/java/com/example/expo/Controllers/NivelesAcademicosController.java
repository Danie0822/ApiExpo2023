package com.example.expo.Controllers;

import com.example.expo.Models.NivelesAcademicos;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.NivelesAcademicosDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("NivelesAcademicos")
public class NivelesAcademicosController {

    @GetMapping("/list")
    public List<NivelesAcademicos> ObtenerMaterias() {
        return new NivelesAcademicosDB().ObtenerNivelesAcademicos();
    }

    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> save(@RequestBody NivelesAcademicos NivelesAcademicos) {
        ServiceResponse serviceResponse = new ServiceResponse();

        int result = NivelesAcademicosDB.InsertarNivelesAcademicos(NivelesAcademicos);
        if (result == 1) {
            serviceResponse.setMessage("Item saved with success");
        }
        return new  ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceResponse> update(@PathVariable int id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = NivelesAcademicosDB.EliminarNivelesAcademicos(id);
        if (result == 1) {
            serviceResponse.setMessage("Item removed with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ServiceResponse> update(@RequestBody NivelesAcademicos NivelesAcademicos) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = NivelesAcademicosDB.ActulizarNivelesAcademicos(NivelesAcademicos);
        if (result == 1) {
            serviceResponse.setMessage("Item update with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
}
