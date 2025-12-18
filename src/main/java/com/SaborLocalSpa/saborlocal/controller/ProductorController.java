package com.SaborLocalSpa.saborlocal.controller;

import com.SaborLocalSpa.saborlocal.model.Productor;
import com.SaborLocalSpa.saborlocal.service.ProductorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productores")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductorController {

    private final ProductorService productorService;

    public ProductorController(ProductorService productorService) {
        this.productorService = productorService;
    }

    @Operation(summary = "Listar productores", description = "Devuelve todos los productores registrados")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Productor.class))))
    })
    @GetMapping
    public List<Productor> listar() {
        return productorService.listar();
    }

    @Operation(summary = "Obtener productor por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Productor encontrado"),
        @ApiResponse(responseCode = "404", description = "Productor no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Productor> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(productorService.obtenerPorId(id));
    }

    @Operation(summary = "Obtener productores activos")
    @GetMapping("/activos/todos")
    public List<Productor> obtenerActivos() {
        return productorService.obtenerActivos();
    }

    @Operation(summary = "Buscar productores por nombre")
    @GetMapping("/search")
    public List<Productor> buscar(@RequestParam String nombre) {
        return productorService.buscarPorNombre(nombre);
    }

    @Operation(summary = "Crear productor", description = "Registra un nuevo productor")
    @ApiResponse(responseCode = "201", description = "Productor creado")
    @PostMapping
    public ResponseEntity<Productor> crear(@Valid @RequestBody Productor productor) {
        Productor creado = productorService.crear(productor);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Actualizar productor")
    @PutMapping("/{id}")
    public ResponseEntity<Productor> actualizar(@PathVariable Long id, @Valid @RequestBody Productor productor) {
        return ResponseEntity.ok(productorService.actualizar(id, productor));
    }

    @Operation(summary = "Eliminar productor")
    @ApiResponse(responseCode = "204", description = "Productor eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
