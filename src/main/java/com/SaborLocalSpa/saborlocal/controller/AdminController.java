package com.SaborLocalSpa.saborlocal.controller;

import com.SaborLocalSpa.saborlocal.model.Cliente;
import com.SaborLocalSpa.saborlocal.model.Producto;
import com.SaborLocalSpa.saborlocal.model.Productor;
import com.SaborLocalSpa.saborlocal.service.ClienteService;
import com.SaborLocalSpa.saborlocal.service.ProductoService;
import com.SaborLocalSpa.saborlocal.service.ProductorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    private final ProductoService productoService;
    private final ProductorService productorService;
    private final ClienteService clienteService;

    public AdminController(ProductoService productoService, 
                          ProductorService productorService,
                          ClienteService clienteService) {
        this.productoService = productoService;
        this.productorService = productorService;
        this.clienteService = clienteService;
    }

    // ========== PRODUCTOS ==========
    @GetMapping("/productos")
    public ResponseEntity<?> getProductos(@RequestHeader(value = "Authorization", required = false) String token) {
        if (!isAdminToken(token)) {
            return ResponseEntity.status(403).body(Map.of("error", "No autorizado"));
        }
        return ResponseEntity.ok(productoService.listar());
    }

    @PostMapping("/productos")
    public ResponseEntity<?> createProducto(@Valid @RequestBody Producto producto,
                                           @RequestHeader(value = "Authorization", required = false) String token) {
        if (!isAdminToken(token)) {
            return ResponseEntity.status(403).body(Map.of("error", "No autorizado"));
        }
        Producto creado = productoService.crear(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id, 
                                           @Valid @RequestBody Producto producto,
                                           @RequestHeader(value = "Authorization", required = false) String token) {
        if (!isAdminToken(token)) {
            return ResponseEntity.status(403).body(Map.of("error", "No autorizado"));
        }
        Producto actualizado = productoService.actualizar(id, producto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id,
                                           @RequestHeader(value = "Authorization", required = false) String token) {
        if (!isAdminToken(token)) {
            return ResponseEntity.status(403).body(Map.of("error", "No autorizado"));
        }
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ========== PRODUCTORES ==========
    @GetMapping("/productores")
    public ResponseEntity<?> getProductores(@RequestHeader(value = "Authorization", required = false) String token) {
        if (!isAdminToken(token)) {
            return ResponseEntity.status(403).body(Map.of("error", "No autorizado"));
        }
        return ResponseEntity.ok(productorService.listar());
    }

    @PostMapping("/productores")
    public ResponseEntity<?> createProductor(@Valid @RequestBody Productor productor,
                                            @RequestHeader(value = "Authorization", required = false) String token) {
        if (!isAdminToken(token)) {
            return ResponseEntity.status(403).body(Map.of("error", "No autorizado"));
        }
        Productor creado = productorService.crear(productor);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/productores/{id}")
    public ResponseEntity<?> updateProductor(@PathVariable Long id,
                                            @Valid @RequestBody Productor productor,
                                            @RequestHeader(value = "Authorization", required = false) String token) {
        if (!isAdminToken(token)) {
            return ResponseEntity.status(403).body(Map.of("error", "No autorizado"));
        }
        Productor actualizado = productorService.actualizar(id, productor);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/productores/{id}")
    public ResponseEntity<?> deleteProductor(@PathVariable Long id,
                                            @RequestHeader(value = "Authorization", required = false) String token) {
        if (!isAdminToken(token)) {
            return ResponseEntity.status(403).body(Map.of("error", "No autorizado"));
        }
        productorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ========== USUARIOS ==========
    @GetMapping("/usuarios")
    public ResponseEntity<?> getUsuarios(@RequestHeader(value = "Authorization", required = false) String token) {
        if (!isAdminToken(token)) {
            return ResponseEntity.status(403).body(Map.of("error", "No autorizado"));
        }
        return ResponseEntity.ok(clienteService.listar());
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> createUsuario(@Valid @RequestBody Cliente usuario,
                                          @RequestHeader(value = "Authorization", required = false) String token) {
        if (!isAdminToken(token)) {
            return ResponseEntity.status(403).body(Map.of("error", "No autorizado"));
        }
        Cliente creado = clienteService.crear(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id,
                                          @Valid @RequestBody Cliente usuario,
                                          @RequestHeader(value = "Authorization", required = false) String token) {
        if (!isAdminToken(token)) {
            return ResponseEntity.status(403).body(Map.of("error", "No autorizado"));
        }
        Cliente actualizado = clienteService.actualizar(id, usuario);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id,
                                          @RequestHeader(value = "Authorization", required = false) String token) {
        if (!isAdminToken(token)) {
            return ResponseEntity.status(403).body(Map.of("error", "No autorizado"));
        }
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private boolean isAdminToken(String token) {
        return token != null && !token.isEmpty();
    }
}
