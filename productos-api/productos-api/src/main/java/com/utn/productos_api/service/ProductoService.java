package com.utn.productos_api.service;

import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.exception.ProductoNotFoundException;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    // Inyección del repositorio por constructor
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // --- Métodos de Lógica de Negocio (usando DTOs) ---

    // Crear Producto (Recibe DTO, devuelve DTO)
    public ProductoResponseDTO crearProducto(ProductoDTO productoDTO) {
        // Convertimos el DTO a Entidad
        Producto producto = convertirAEntidad(productoDTO);
        // Guardamos la entidad
        Producto productoGuardado = productoRepository.save(producto);
        // Convertimos la entidad guardada a DTO de respuesta
        return convertirARespuestaDTO(productoGuardado);
    }

    // Obtener todos los productos
    public List<ProductoResponseDTO> obtenerTodos() {
        return productoRepository.findAll()
                .stream()
                .map(this::convertirARespuestaDTO) // Mapeamos cada producto a su DTO
                .collect(Collectors.toList());
    }

    // Obtener producto por ID
    public ProductoResponseDTO obtenerPorId(Long id) {
        // Buscamos el producto. Si no existe, lanzamos la excepción
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con id: " + id));
        return convertirARespuestaDTO(producto);
    }

    // Obtener productos por Categoría
    public List<ProductoResponseDTO> obtenerPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria)
                .stream()
                .map(this::convertirARespuestaDTO)
                .collect(Collectors.toList());
    }

    // Actualizar producto completo (PUT)
    public ProductoResponseDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
        // 1. Validar que el producto existe
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con id: " + id));

        // 2. Actualizar los campos
        productoExistente.setNombre(productoDTO.nombre());
        productoExistente.setDescripcion(productoDTO.descripcion());
        productoExistente.setPrecio(productoDTO.precio());
        productoExistente.setStock(productoDTO.stock());
        productoExistente.setCategoria(productoDTO.categoria());

        // 3. Guardar los cambios
        Producto productoActualizado = productoRepository.save(productoExistente);
        return convertirARespuestaDTO(productoActualizado);
    }

    // Actualizar solo el stock (PATCH)
    public ProductoResponseDTO actualizarStock(Long id, ActualizarStockDTO stockDTO) {
        // 1. Validar que el producto existe
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con id: " + id));

        // 2. Actualizar solo el stock
        productoExistente.setStock(stockDTO.stock());

        // 3. Guardar los cambios
        Producto productoActualizado = productoRepository.save(productoExistente);
        return convertirARespuestaDTO(productoActualizado);
    }

    // Eliminar producto
    public void eliminarProducto(Long id) {
        // 1. Validar que el producto existe antes de borrar
        if (!productoRepository.existsById(id)) {
            throw new ProductoNotFoundException("Producto no encontrado con id: " + id);
        }
        // 2. Eliminar
        productoRepository.deleteById(id);
    }


    // --- Métodos Auxiliares (Tip del PDF)  ---

    // Convierte de DTO de entrada a Entidad
    private Producto convertirAEntidad(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecio(dto.precio());
        producto.setStock(dto.stock());
        producto.setCategoria(dto.categoria());
        return producto;
    }

    // Convierte de Entidad a DTO de respuesta
    private ProductoResponseDTO convertirARespuestaDTO(Producto producto) {
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCategoria()
        );
    }
}
