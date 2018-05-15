package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Visitor;

public class VisitorRepo {
	public void insertVisitor(Visitor a) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.persist(a);
        eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
	}
	public void updateVisitor(Visitor a) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.merge(a);
        eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
	}
	/*public void deleteVisitor(Visitor a) {
		//Visitor a3=readVisitor(a.id);
		Visitor a2=new Visitor();
		//a2.id=a3.id;
		
		//////////// completez
		
		this.updateVisitor(a2);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
		eniEntityManager.remove(eniEntityManager.contains(a2) ? a2 : eniEntityManager.merge(a2));
		eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
	}*/
	
	public Visitor readVisitor(int id) {
		Visitor a=new Visitor();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		eniEntityManager.getTransaction().begin();
		a = eniEntityManager.find(Visitor.class, id);
		eniEntityManager.getTransaction().commit();
		eniEntityManager.close();
		entityManagerFactory.close();
		return a;
	}
	public List<Visitor> viewAllVisitors(){
		List<Visitor> listOfVisitors = new ArrayList<Visitor>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		eniEntityManager.getTransaction().begin();
		listOfVisitors = eniEntityManager.createQuery("SELECT a FROM Visitor a", Visitor.class).getResultList();
		eniEntityManager.getTransaction().commit();
		eniEntityManager.close();
		entityManagerFactory.close();
		return listOfVisitors;
	}
}
