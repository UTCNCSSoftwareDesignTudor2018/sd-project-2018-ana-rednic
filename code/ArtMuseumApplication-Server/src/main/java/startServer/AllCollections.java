package startServer;

import java.util.ArrayList;
import java.util.List;

import businessLogic.CollectionBusinessLogic;
import entity.Collection;
import entity.Visitor;

public class AllCollections implements CaseChain {

	private CaseChain nextChain;
	@Override
	public void setNextChain(CaseChain nextChain) {
		this.nextChain=nextChain;
	}

	@Override
	public List<Object> caseSolve(List<Object> receive) {
		if (receive.get(0).equals("AllCollections")) {
			CollectionBusinessLogic clogic=new CollectionBusinessLogic();
			List<Object> responseAllCollections = new ArrayList<Object>();
			Visitor visi= (Visitor) receive.get(1);
			List<Collection> listCollections=clogic.allCollectionsOfAVisitor(visi.getId());
			responseAllCollections.add(listCollections);
			return responseAllCollections;
		}
		else return nextChain.caseSolve(receive);
	}

}
