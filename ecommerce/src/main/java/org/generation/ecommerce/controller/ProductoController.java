package org.generation.ecommerce.controller;

import java.util.List;

import org.generation.ecommerce.model.Producto;
import org.generation.ecommerce.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/products/")
public class ProductoController {
	
	private final ProductoService productoService;
	
	@Autowired
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}
	
	//CRUD
	@GetMapping
	public List<Producto> getAllProductos(){
		return productoService.getProductos();
	}
	
	@GetMapping(path="{prodId}")
	public Producto getProducto(@PathVariable("prodId")Long id) {
		return productoService.getProducto(id);
	}
	
	@DeleteMapping(path="{prodId}")
	public Producto deleteProducto(@PathVariable("prodId") Long id) {
		return productoService.deleteProducto(id);
	}
	
	@PostMapping
	public Producto addProducto(@RequestBody Producto producto){
		return productoService.addProducto(producto);
	}
	
	@PutMapping(path="{prodId}")
	public Producto updateProducto(@PathVariable("prodId")Long id, 
			@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String descripcion,
			@RequestParam(required = false) String URL_imagen,
			@RequestParam(required = false) Double precio) {
		return productoService.updateProducto(id, nombre, descripcion, URL_imagen, precio);
	}
	 
	

}
