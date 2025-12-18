package com.SaborLocalSpa.saborlocal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String codigoPostal;
    private List<ItemPedido> productos;
    private BigDecimal total;
    private String metodoPago;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemPedido {
        private Long id;
        private String nombre;
        private BigDecimal precio;
        private Integer cantidad;
    }
}
