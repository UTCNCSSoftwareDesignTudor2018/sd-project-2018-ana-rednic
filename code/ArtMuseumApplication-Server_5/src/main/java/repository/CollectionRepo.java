package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Collection;

public class CollectionRepo {
	public void insertCollection(Collection a) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.persist(a);
        eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
	}
	public void updateCollection(Collection a) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.merge(a);
        eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
	}
	/*public void deleteCollection(Collection a) {
		//Collection a3=readCollection(a.id);
		Collection a2=new Collection();
		//a2.id=a3.id;
		
		//////////// completez
		
		this.updateCollection(a2);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
		eniEntityManager.remove(eniEntityManager.contains(a2) ? a2 : eniEntityManager.merge(a2));
		eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
	}*/
	
	public Collection readCollection(int id) {
		Collection a=new Collection();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		eniEntityManager.getTransaction().begin();
		a = eniEntityManager.find(Collection.class, id);
		eniEntityManager.getTransaction().commit();
		eniEntityManager.close();
		entityManagerFactory.close();
		return a;
	}
	public List<Collection> viewAllCollections(){
		List<Collection> listOfCollections = new ArrayList<Collection>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		eniEntityManager.getTransaction().begin();
		listOfCollections = eniEntityManager.createQuery("SELECT a FROM Collection a", Collection.class).getResultList();
		eniEntityManager.getTransaction().commit();
		eniEntityManager.close();
		entityManagerFactory.close();
		return listOfCollections;
	}
	public List<Collection> allCollectionsOfAVisitor(int id){
		List<Collection> allCollections = this.viewAllCollections();
		List<Collection> CollectionsVisitor=new ArrayList<Collection>();
		for (int i=0;i<allCollections.size();i++) {
			if (allCollections.get(i).getVisitor().getId()==id) {
				CollectionsVisitor.add(allCollections.get(i));
			}
		}
		return CollectionsVisitor;
	}
}
