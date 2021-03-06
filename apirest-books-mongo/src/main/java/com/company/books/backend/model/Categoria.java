package com.company.books.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "categorias")
public class Categoria {

	@Id
	private String id;
	private String id_ident;
	private String nombre;
	private String descripcion;

}
