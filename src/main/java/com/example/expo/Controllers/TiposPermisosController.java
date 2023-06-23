package com.example.expo.Controllers;

import com.example.expo.Models.Encargados;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Models.TiposPermisos;
import com.example.expo.Services.EncargadosDB;
import com.example.expo.Services.TiposPermisosDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("TiposPermisos")
public class TiposPermisosController {
    @GetMapping("/list")
    public List<?> obtenerGrupos() {
        CompletableFuture<List<?>> futureEspecialidades = new TiposPermisosDB().obtenerTiposPermisoAsync();
        List<?> TiposPermisos = futureEspecialidades.join();
        return TiposPermisos;
    }

    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody TiposPermisos TiposPermisos) {
        CompletableFuture<Integer> futureResult = TiposPermisosDB.insertarTiposPermisosAsync(TiposPermisos);

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
        CompletableFuture<Integer> futureResult = TiposPermisosDB.eliminarTiposPermisosAsync(id);

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
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody TiposPermisos TiposPermisos) {
        CompletableFuture<Integer> futureResult = TiposPermisosDB.ActulizarTiposPermisosAsync(TiposPermisos);

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
