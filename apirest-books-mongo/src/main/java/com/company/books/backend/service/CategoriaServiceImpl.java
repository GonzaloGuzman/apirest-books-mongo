package com.company.books.backend.service;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.response.CategoriaResponseRest;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

	@Autowired
	private ICategoriaDao categoriaDao;

	@Override
	public ResponseEntity<CategoriaResponseRest> buscarCategoria() {

		log.info("inicio metodo buscarCategoria()");
		CategoriaResponseRest response = new CategoriaResponseRest();

		try {

			List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();

			response.getCategoriaResponse().setCategoria(categoria);
			response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");

		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar categorias");
			log.error("error al consultar categorias: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoriaResponseRest> buscarPorId(String id_ident) {
		log.info("Inicio metodo buscarporId");

		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();

		try {

			Optional<Categoria> categoria = categoriaDao.findById(id_ident);

			if (categoria.isPresent()) {
				list.add(categoria.get());
				response.getCategoriaResponse().setCategoria(list);
			} else {
				log.error("Error en consultar categoria");
				response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			log.error("Error en consultar categoria");
			response.setMetadata("Respuesta nok", "-1", "Error al consultar Categoria por ID");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoriaResponseRest> crearCategoria(Categoria categoria) {
		log.info("Inicio metodo crear Categoria");

		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();

		try {

			Categoria categoriaGuardada = categoriaDao.save(categoria);

			if (categoriaGuardada != null) {
				list.add(categoriaGuardada);
				response.getCategoriaResponse().setCategoria(list);
			} else {
				log.error("Error en grabar categoria");
				response.setMetadata("Respuesta nok", "-1", "Categoria no guardada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			log.error("Error en grabar categoria");
			response.setMetadata("Respuesta nok", "-1", "Error al grabar Categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(Categoria categoria, String id_ident) {
		log.info("Inicio metodo actualizar Categoria");

		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();

		try {

			Optional<Categoria> categoriaBuscada = categoriaDao.findById(id_ident);

			if (categoriaBuscada.isPresent()) {
				categoriaBuscada.get().setNombre(categoria.getNombre());
				categoriaBuscada.get().setDescripcion(categoria.getDescripcion());

				Categoria categoriaActualizar = categoriaDao.save(categoriaBuscada.get());

				if (categoriaActualizar != null) {
					response.setMetadata("Respuesta ok", "200", "Categoria actualizada");
					list.add(categoriaActualizar);
					response.getCategoriaResponse().setCategoria(list);
				} else {
					log.error("error en actualizar categoria");
					response.setMetadata("Respuesta nok", "-1", "Categoria no actualizada");
					return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}

			} else {
				log.error("error en actualizar categoria");
				response.setMetadata("Respuesta nok", "-1", "Categoria no actualizada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {

			log.error("Error en actualizar categoria", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Error al actualizar Categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoriaResponseRest> eliminarCategoria(String id_ident) {
		log.info("Inicio metodo eliminar Categoria");

		CategoriaResponseRest response = new CategoriaResponseRest();

		try {
			categoriaDao.deleteById(id_ident); // Elimina un registro de la DB por id
			response.setMetadata("Respuesta ok", "200", "Categoria eliminada");

		} catch (Exception e) {
			log.error("Error en eliminar categoria", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Error al eliminar Categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

}
