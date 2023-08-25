package com.example.expo.Controllers;

import com.example.expo.Models.RangosHoras;
import com.example.expo.Models.ReservacionesSalones;
import com.example.expo.Models.ReservacionesSalonesEstado;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.RangosHorasDB;
import com.example.expo.Services.ReservacionesSalonesDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("ReservacionesSalones")
public class ReservacionesSalonesController {
    @GetMapping("/list")
    public List<?> obtenerGrupos() {
        CompletableFuture<List<?>> futureEspecialidades = new ReservacionesSalonesDB().obtenerReservacionesSalonesAsync();
        List<?> Salones = futureEspecialidades.join();
        return Salones;
    }
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody ReservacionesSalones ReservacionesSalones) {
        CompletableFuture<Integer> futureResult = ReservacionesSalonesDB.insertarReservacionesSalonesAsync(ReservacionesSalones);

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
        CompletableFuture<Integer> futureResult = ReservacionesSalonesDB.eliminarReservacionesSalonesAsync(id);

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
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody ReservacionesSalonesEstado ReservacionesSalones) {
        CompletableFuture<Integer> futureResult = ReservacionesSalonesDB.actualizarReservacionesSalonesAsync(ReservacionesSalones);

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
