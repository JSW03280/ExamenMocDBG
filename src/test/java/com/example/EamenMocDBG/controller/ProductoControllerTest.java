package com.example.EamenMocDBG.controller;

import com.example.EamenMocDBG.entity.Producto;
import com.example.EamenMocDBG.service.ProductoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {
    @Mock
    ProductoServiceImpl productoService;

    @InjectMocks
    ProductoController productoController;

    @Test
    void addProducto() {
        LocalDate hoy = LocalDate.now();
        Producto productoInput = new Producto(null, "Laptop", "Gaming i7", "electronica",
                999.99f, hoy, "Nueva", 10);
        Producto productoGuardado = new Producto(1L, "Laptop", "Gaming i7", "electronica",
                999.99f, hoy, "Nueva", 10);

        when(productoService.addProducto(productoInput)).thenReturn(productoGuardado);

        // Act
        Producto resultado = productoController.addProducto(productoInput);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Laptop", resultado.getNombre());
        assertEquals("Gaming i7", resultado.getDescripcion());
        assertEquals(999.99f, resultado.getPrecio());
        verify(productoService).addProducto(productoInput);
    }

    @Test
    void deleteProducto() {
        Long productoId = 5L;

        // Act
        productoController.deleteProducto(productoId);

        // Assert
        verify(productoService).eliminarProductoById(productoId);
        verifyNoMoreInteractions(productoService);
    }
}