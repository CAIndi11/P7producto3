package com.sopadeletras.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sopadeletras.model.Diccionario;
//import com.sopadeletras.model.hibernate.Sopa;

public class DiccionarioDAO implements GenericDAO<Diccionario, Integer> {

	private EntityManager manager;
	
	public DiccionarioDAO() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sopadeletras");
		manager = emf.createEntityManager();
		}

	@Override
	public void insertar(Diccionario t){
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();

	}
	@Override
	public void modificar(Diccionario t){
		manager.getTransaction().begin();
		manager.merge(t);
		manager.getTransaction().commit();
	}

	
	@Override
	public void eliminar(Diccionario t){
		manager.getTransaction().begin();
		manager.remove(t);
		manager.getTransaction().commit();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Diccionario> obtenerTodos() {
		return (List<Diccionario>) manager.createQuery("FROM Diccionario").getResultList();
	}


	@Override
	public Diccionario obtener(Integer id) {
		return manager.find(Diccionario.class, id);
		
	}

}
