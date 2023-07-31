package com.example.expo.Controllers;

import com.example.expo.Models.Comunicados;
import com.example.expo.Models.Matriculas;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.ComunicadosDB;
import com.example.expo.Services.MatriculasDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("Matriculas")
public class MatriculasController {

    @GetMapping("/list")
    public List<?> obtenerGrupos() {
        CompletableFuture<List<?>> futureEspecialidades = new MatriculasDB().obtenerMatriculasAsync();
        List<?> especialidades = futureEspecialidades.join();
        return especialidades;
    }

    @GetMapping("/get/{id}")
    public Matriculas obtenerMatricula(@PathVariable int id){
        CompletableFuture<?> futureMatricula = new MatriculasDB().obtenerMatriculaAsync(id);
        Matriculas matricula = (Matriculas) futureMatricula.join();
        return matricula;
    }

    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody Matriculas especialidad) {
        CompletableFuture<Integer> futureResult = MatriculasDB.insertarComunicadosAsync(especialidad);

        return futureResult.thenApply(result -> {
            ServiceResponse serviceResponse = new ServiceResponse();

            if (result == 1) {
                serviceResponse.setMessage("Item saved with success");
                return ResponseEntity.ok(serviceResponse);
            } else {
                serviceResponse.setMessage("Failed to save item");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
            }
        });
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@PathVariable int id) {
        CompletableFuture<Integer> futureResult = MatriculasDB.eliminarComunicadosAsync(id);

        return futureResult.thenApply(result -> {
            ServiceResponse serviceResponse = new ServiceResponse();

            if (result == 1) {
                serviceResponse.setMessage("Item removed with success");
                return ResponseEntity.ok(serviceResponse);
            } else {
                serviceResponse.setMessage("Failed to remove item");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
            }
        });
    }

    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody Matriculas especialidad) {
        CompletableFuture<Integer> futureResult = MatriculasDB.actualizarEspecialidadesAsync(especialidad);

        return futureResult.thenApply(result -> {
            ServiceResponse serviceResponse = new ServiceResponse();

            if (result == 1) {
                serviceResponse.setMessage("Item updated with success");
                return ResponseEntity.ok(serviceResponse);
            } else {
                serviceResponse.setMessage("Failed to update item");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
            }
        });
    }

}
