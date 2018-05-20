package chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

import businessLogic.CollectionBusinessLogic;
import entity.Collection;

public class CreateCollection implements CaseChain {

	private CaseChain nextChain;
	@Override
	public void setNextChain(CaseChain nextChain) {
		this.nextChain=nextChain;
	}

	@Override
	public List<Object> caseSolve(List<Object> receive) {
		if (receive.get(0).equals("CreateCollection")) {
			CollectionBusinessLogic clogic=new CollectionBusinessLogic();
			List<Object> responseCreateCollection = new ArrayList<Object>();
			Collection c=(Collection) receive.get(1);
			clogic.insertCollection(c);
			System.out.println("inserted");
			Collection c2=clogic.getCollectionByName(c.getName());
			/*for (int i=0; i<c2.getListOfPaintings().size(); i++) {
				Painting p=c2.getListOfPaintings().get(i);
				p.getListOfCollections().add(c2);
				plogic.updatePainting(p);
			}*/
			responseCreateCollection.add(c2);
			return responseCreateCollection;
		}
		else return nextChain.caseSolve(receive);
	}

}
