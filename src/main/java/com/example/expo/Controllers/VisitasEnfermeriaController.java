package com.example.expo.Controllers;

import com.example.expo.Models.Encargados;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Models.VisitasEnfermeria;
import com.example.expo.Services.EncargadosDB;
import com.example.expo.Services.FuncionesDB;
import com.example.expo.Services.VisitasEnfermeriaDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("VisitasEnfermeria")
@CrossOrigin(origins = "https://expo-movil.vercel.app")
public class VisitasEnfermeriaController {


    @GetMapping("/String/{idEstudiante}")
    public List<?>obtenerVisitasEnfereriaStringPorEstudianteAsync(@PathVariable int idEstudiante) {
        CompletableFuture<List<?>> futureEspecialidades = new VisitasEnfermeriaDB().obtenerVisitasEnfereriaStringPorEstudianteAsync(idEstudiante);
        List<?> VisitasEnfermeria = futureEspecialidades.join();
        return VisitasEnfermeria;
    }
    @GetMapping("/String")
    public List<?> Obtener() {
        CompletableFuture<List<?>> futureEspecialidades = new VisitasEnfermeriaDB().obtenerVisitasEnfereriaStringAsync();
        List<?> VisitasEnfermeria = futureEspecialidades.join();
        return VisitasEnfermeria;
    }

    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody VisitasEnfermeria VisitasEnfermeria) {
        CompletableFuture<Integer> futureResult = VisitasEnfermeriaDB.insertarVisitasEnfermeriaAsync(VisitasEnfermeria);

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
        CompletableFuture<Integer> futureResult = VisitasEnfermeriaDB.eliminarVisitasEnfermeriaAsync(id);

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
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody VisitasEnfermeria VisitasEnfermeria) {
        CompletableFuture<Integer> futureResult = VisitasEnfermeriaDB.actualizarVisitasEnfermeriaAsync(VisitasEnfermeria);

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
