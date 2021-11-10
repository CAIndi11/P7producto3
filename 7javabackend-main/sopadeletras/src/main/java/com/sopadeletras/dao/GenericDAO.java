package com.sopadeletras.dao;

import java.util.List;


public interface GenericDAO<T, K> {
	
	void insertar(T t);
	
	void modificar(T t);
	
	void eliminar(T t);
	
	List<T> obtenerTodos();
	
	T obtener(K id);


}
