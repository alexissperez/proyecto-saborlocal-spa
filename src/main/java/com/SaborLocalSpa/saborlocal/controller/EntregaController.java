package com.SaborLocalSpa.saborlocal.controller;

import com.SaborLocalSpa.saborlocal.model.Entrega;
import com.SaborLocalSpa.saborlocal.service.EntregaService;
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
@RequestMapping("/api/entregas")
@CrossOrigin(origins = "http://localhost:3000")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @Operation(summary = "Listar entregas", description = "Devuelve todas las entregas")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Entrega.class))))
    })
    @GetMapping
    public List<Entrega> listar() {
        return entregaService.listar();
    }

    @Operation(summary = "Obtener entrega por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Entrega encontrada"),
        @ApiResponse(responseCode = "404", description = "Entrega no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Entrega> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(entregaService.obtenerPorId(id));
    }

    @Operation(summary = "Obtener entregas por pedido")
    @GetMapping("/pedido/{pedidoId}")
    public List<Entrega> obtenerPorPedido(@PathVariable Long pedidoId) {
        return entregaService.obtenerPorPedidoId(pedidoId);
    }

    @Operation(summary = "Obtener entregas por estado")
    @GetMapping("/estado/{estado}")
    public List<Entrega> obtenerPorEstado(@PathVariable String estado) {
        return entregaService.obtenerPorEstado(estado);
    }

    @Operation(summary = "Crear entrega", description = "Crea una nueva entrega para un pedido")
    @ApiResponse(responseCode = "201", description = "Entrega creada")
    @PostMapping
    public ResponseEntity<Entrega> crear(@Valid @RequestBody Entrega entrega) {
        Entrega creada = entregaService.crear(entrega);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @Operation(summary = "Actualizar entrega")
    @PutMapping("/{id}")
    public ResponseEntity<Entrega> actualizar(@PathVariable Long id, @Valid @RequestBody Entrega entrega) {
        return ResponseEntity.ok(entregaService.actualizar(id, entrega));
    }

    @Operation(summary = "Actualizar estado de la entrega")
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Entrega> actualizarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        return ResponseEntity.ok(entregaService.actualizarEstado(id, nuevoEstado));
    }

    @Operation(summary = "Eliminar entrega")
    @ApiResponse(responseCode = "204", description = "Entrega eliminada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        entregaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
