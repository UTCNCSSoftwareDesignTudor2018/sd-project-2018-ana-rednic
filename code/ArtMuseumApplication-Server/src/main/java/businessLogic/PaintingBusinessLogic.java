package businessLogic;

import java.util.List;

import entity.Painting;
import repository.PaintingRepo;

public class PaintingBusinessLogic {
	public PaintingRepo arepo=new PaintingRepo();
	public void insertPainting(Painting a) {
		arepo.insertPainting(a);
	}
	public void updatePainting(Painting a) {
		arepo.updatePainting(a);
	}
	public void deletePainting(Painting a) {
		//arepo.deletePainting(a);
	}
	public List<Painting> viewAllPaintings() {
		return arepo.viewAllPaintings();
	}
	/*public List<Painting> allPaintingsOfACollection(int id){
		return arepo.allPaintingsOfACollection(id);
	}*/
	public Painting search(String toSearch) {
		return arepo.search(toSearch);
	}
}
