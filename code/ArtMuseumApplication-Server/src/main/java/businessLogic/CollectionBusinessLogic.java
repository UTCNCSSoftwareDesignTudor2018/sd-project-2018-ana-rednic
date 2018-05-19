package businessLogic;

import java.util.List;

import entity.Collection;
import repository.CollectionRepo;

public class CollectionBusinessLogic {
	public CollectionRepo arepo=new CollectionRepo();
	public void insertCollection(Collection c) {
		arepo.insertCollection(c);
	}
	public void updateCollection(Collection a) {
		arepo.updateCollection(a);
	}
	/*public void deleteCollection(Collection a) {
		arepo.deleteCollection(a);
	}*/
	public List<Collection> viewAllCollections() {
		return arepo.viewAllCollections();
	}
	public List<Collection> allCollectionsOfAVisitor(int id){
		return arepo.allCollectionsOfAVisitor(id);
	}
	public Collection getCollectionByName(String name) {
		return arepo.getCollectionByName(name);
	}
}
