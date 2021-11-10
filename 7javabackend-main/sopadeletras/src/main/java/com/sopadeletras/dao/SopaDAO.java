package com.sopadeletras.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import com.sopadeletras.model.Sopa;

public class SopaDAO implements GenericDAO<Sopa, Integer>{
	

	private EntityManager manager;
	
	public SopaDAO() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sopadeletras");
		manager = emf.createEntityManager();
		}

	@Override
	public void insertar(Sopa t){
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
	}

	@Override
	public void modificar(Sopa t){
		manager.getTransaction().begin();
		manager.merge(t);
		manager.getTransaction().commit();
	}

	@Override
	public void eliminar(Sopa t){
		manager.getTransaction().begin();
		manager.remove(t);
		manager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sopa> obtenerTodos() {
		return (List<Sopa>) manager.createQuery("FROM Sopa").getResultList();
	}

	@Override
	public Sopa obtener(Integer id){
		return manager.find(Sopa.class, id);
	}

}
