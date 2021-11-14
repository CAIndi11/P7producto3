package com.sopadeletras.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sopadeletras.model.Juego;

public class JuegoDAO implements GenericDAO<Juego, Integer>{
	
	//El entity manager guarda internamente todas las entidades que gestiona
	//Utiliza una caché de los datos en la base de datos. 
	private EntityManager manager;
	
	public JuegoDAO() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sopadeletras");
		manager = emf.createEntityManager();
		}

	//Metodos
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
