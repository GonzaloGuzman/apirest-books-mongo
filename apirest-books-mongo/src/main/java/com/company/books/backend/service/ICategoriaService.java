package com.company.books.backend.service;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;

public interface ICategoriaService {

	public ResponseEntity<CategoriaResponseRest> buscarCategoria();

	public ResponseEntity<CategoriaResponseRest> buscarPorId(String id_ident);

	public ResponseEntity<CategoriaResponseRest> crearCategoria(Categoria categoria);

	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(Categoria categoria, String id_ident);

	public ResponseEntity<CategoriaResponseRest> eliminarCategoria(String id_ident);

}
