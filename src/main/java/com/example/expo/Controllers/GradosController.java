package com.example.expo.Controllers;

import com.example.expo.Models.Grados;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.GradosDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("Grados")
public class GradosController {

    @GetMapping("/list")
    public CompletableFuture<List<?>> obtenerGrupos() {
        return new GradosDB().obtenerGradosAsync();
    }
    @GetMapping("/get/{id}")
    public CompletableFuture<?> obtenerGrado(@PathVariable int id) {
        return new GradosDB().obtenerGradoAsync(id);
    }
    @GetMapping("/Inint")
    public CompletableFuture<List<?>> obtenerGradosintAsync() {
        return new GradosDB().obtenerGradosintAsync();
    }

    @GetMapping("/gradoAcademico")
    public CompletableFuture<?> obtenerGradoAcademico(
            @RequestParam("idNivelAcademico") int idNivelAcademico,
            @RequestParam("idSeccion") int idSeccion,
            @RequestParam("idSeccionBachillerato") int idSeccionBachillerato){

            return new GradosDB().obtenerGradoAcademico(idNivelAcademico, idSeccion, idSeccionBachillerato);

    }

    @GetMapping("/gradoTecnico")
    public CompletableFuture<?> obtenerGradoTecnico(
            @RequestParam("idEspecialidad") int idEspecialidad,
            @RequestParam("idGrupoTecnico") int idGrupoTecnico
    ){
        return new GradosDB().obtenerGradoTecnico(idEspecialidad, idGrupoTecnico);
    }

    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody Grados Grados) {
        return GradosDB.insertarGradosAsync(Grados)
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
        return GradosDB.eliminarGradosAsync(id)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item removed with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }

    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody Grados Grados) {
        return GradosDB.ActulizarGradosAsync(Grados)
                .thenApply(result -> {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    if (result == 1) {
                        serviceResponse.setMessage("Item updated with success");
                    }
                    return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
                });
    }
}

