package com.sopadeletras.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sopadeletras.model.Juego;

public class JuegoDAO implements GenericDAO<Juego, Integer>{
	

	private EntityManager manager;
	
	public JuegoDAO() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sopadeletras");
		manager = emf.createEntityManager();
		}

	@Override
	public void insertar(Juego t){
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
	}

	@Override
	public void modificar(Juego t){
		manager.getTransaction().begin();
		manager.merge(t);
		manager.getTransaction().commit();
	}

	@Override
	public void eliminar(Juego t){
		manager.getTransaction().begin();
		manager.remove(t);
		manager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Juego> obtenerTodos() {
		return (List<Juego>) manager.createQuery("FROM Juego").getResultList();
	}

	@Override
	public Juego obtener(Integer id){
		return manager.find(Juego.class, id);
	}

}
