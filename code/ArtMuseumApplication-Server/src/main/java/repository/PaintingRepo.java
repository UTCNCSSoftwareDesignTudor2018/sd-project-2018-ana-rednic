package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Painting;
import interpreter.Expression;
import interpreter.OrExpression;
import interpreter.TerminalExpression;

public class PaintingRepo {
	public void insertPainting(Painting a) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.persist(a);
        eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
	}
	public void updatePainting(Painting a) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.merge(a);
        eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
	}
	/*public void deletePainting(Painting a) {
		//Painting a3=readPainting(a.id);
		Painting a2=new Painting();
		//a2.id=a3.id;
		
		//////////// completez
		
		this.updatePainting(a2);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
		eniEntityManager.remove(eniEntityManager.contains(a2) ? a2 : eniEntityManager.merge(a2));
		eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
	}*/
	
	public Painting readPainting(int id) {
		Painting a=new Painting();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		eniEntityManager.getTransaction().begin();
		a = eniEntityManager.find(Painting.class, id);
		eniEntityManager.getTransaction().commit();
		eniEntityManager.close();
		entityManagerFactory.close();
		return a;
	}
	public List<Painting> viewAllPaintings(){
		List<Painting> listOfPaintings = new ArrayList<Painting>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		eniEntityManager.getTransaction().begin();
		listOfPaintings = eniEntityManager.createQuery("SELECT a FROM Painting a", Painting.class).getResultList();
		eniEntityManager.getTransaction().commit();
		eniEntityManager.close();
		entityManagerFactory.close();
		return listOfPaintings;
	}
	/*public List<Painting> allPaintingsOfACollection(int id){
		List<Painting> allPaintings = this.viewAllPaintings();
		List<Painting> PaintingsCollection=new ArrayList<Painting>();
		for (int i=0;i<allPaintings.size();i++) {
			if (allPaintings.get(i).getCollection().getId()==id) {
				PaintingsCollection.add(allPaintings.get(i));
			}
		}
		return PaintingsCollection;
	}*/
	public List<Object> search(String toSearch) {
		List<Object> returnList = new ArrayList<Object>();
		List<Painting> foundPaintings=new ArrayList<Painting>();
		List<Painting> listPaintings=this.viewAllPaintings();
		String[] words=toSearch.split(" ");
		for (Painting painting:listPaintings) {
			for (String string:words) {
				if (painting.getTitle().toLowerCase().contains(string.toLowerCase())) {
					foundPaintings.add(painting);
					break;
				}
			}
		}
		returnList.add(foundPaintings);
		Expression isBaimarean = baimareanExpression();
		if (isBaimarean.interpret(toSearch)) {
			String interpretation=new String("Did you know that during 117 years of painting school over 4.000 fine artists came to Baia Mare from the most diverse cultural spaces of Europe?");
			returnList.add(interpretation);
		}
		return returnList;
	}
	// Rule: "baimarean/baimareana"
	public Expression baimareanExpression() {
		Expression baimarean = new TerminalExpression("baimarean");
		Expression baimareana = new TerminalExpression("baimareana");
		return new OrExpression(baimarean, baimareana);
		
	}
}
