package com.company.books.backend.model.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.company.books.backend.model.Categoria;

public interface ICategoriaDao extends MongoRepository<Categoria, String> {

}
