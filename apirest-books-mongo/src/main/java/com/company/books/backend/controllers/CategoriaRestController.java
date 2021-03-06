package com.company.books.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.service.ICategoriaService;

@RestController
@RequestMapping("/v1")
public class CategoriaRestController {

	@Autowired
	private ICategoriaService service;

	@GetMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> consultaCat() {

		ResponseEntity<CategoriaResponseRest> response = service.buscarCategoria();
		return response;

	}

	@GetMapping("/categorias/{id_ident}")
	public ResponseEntity<CategoriaResponseRest> consultaPorId(@PathVariable String id_ident) {

		ResponseEntity<CategoriaResponseRest> response = service.buscarPorId(id_ident);
		return response;

	}

	@PostMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> crearCategoria(@RequestBody Categoria request) {

		ResponseEntity<CategoriaResponseRest> response = service.crearCategoria(request);
		return response;

	}

	@PutMapping("/categorias/{id_ident}")
	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(@RequestBody Categoria request,
			@PathVariable String id_ident) {

		ResponseEntity<CategoriaResponseRest> response = service.actualizarCategoria(request, id_ident);
		return response;

	}

	@DeleteMapping("/categorias/{id_ident}")
	public ResponseEntity<CategoriaResponseRest> eliminarCategoria(@PathVariable String id_ident) {

		ResponseEntity<CategoriaResponseRest> response = service.eliminarCategoria(id_ident);
		return response;

	}

}
