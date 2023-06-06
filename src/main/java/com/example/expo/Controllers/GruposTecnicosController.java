package com.example.expo.Controllers;
import com.example.expo.Models.GruposTecnicos;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.GruposTecnicosDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Grupos")
public class GruposTecnicosController {
    @GetMapping("/list")
    public List<GruposTecnicos> ObtenerGrupos() {
        return new GruposTecnicosDB().ObtenerGruposTecnicos();
    }
    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> save(@RequestBody GruposTecnicos GruposTecnicos) {
        ServiceResponse serviceResponse = new ServiceResponse();

        int result = GruposTecnicosDB.InsertarGruposTecnicos(GruposTecnicos);
        if (result == 1) {
            serviceResponse.setMessage("Item saved with success");
        }
        return new  ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceResponse> update(@PathVariable int id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = GruposTecnicosDB.EliminarGruposTecnicos(id);
        if (result == 1) {
            serviceResponse.setMessage("Item removed with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ServiceResponse> update(@RequestBody GruposTecnicos GruposTecnicos) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = GruposTecnicosDB.Actulizar(GruposTecnicos);
        if (result == 1) {
            serviceResponse.setMessage("Item update with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
}

