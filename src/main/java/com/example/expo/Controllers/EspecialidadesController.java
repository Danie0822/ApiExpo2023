package com.example.expo.Controllers;

import com.example.expo.Models.Especialidades;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.EspecialidadesDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Especialidades")
public class EspecialidadesController {
    @GetMapping("/list")
    public List<Especialidades> ObtenerGrupos() {
        return new EspecialidadesDB().ObtenerEspecialidades();
    }
    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> save(@RequestBody Especialidades Especialidades) {
        ServiceResponse serviceResponse = new ServiceResponse();

        int result = EspecialidadesDB.InsertarEspecialidades(Especialidades);
        if (result == 1) {
            serviceResponse.setMessage("Item saved with success");
        }
        return new  ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceResponse> update(@PathVariable int id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = EspecialidadesDB.EliminarEspecialidades(id);
        if (result == 1) {
            serviceResponse.setMessage("Item removed with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ServiceResponse> update(@RequestBody Especialidades Especialidades) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = EspecialidadesDB.Actulizar(Especialidades);
        if (result == 1) {
            serviceResponse.setMessage("Item update with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
}
