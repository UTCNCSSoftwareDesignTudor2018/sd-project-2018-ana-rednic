package startServer;

import java.util.ArrayList;
import java.util.List;

import businessLogic.PaintingBusinessLogic;
import entity.Painting;

public class Search implements CaseChain {

	private CaseChain nextChain;
	@Override
	public void setNextChain(CaseChain nextChain) {
		this.nextChain=nextChain;
	}

	@Override
	public List<Object> caseSolve(List<Object> receive) {
		if (receive.get(0).equals("Search")) {
			PaintingBusinessLogic plogic=new PaintingBusinessLogic();
			List<Object> responseSearch = new ArrayList<Object>();
			String toSearch = (String) receive.get(1);
			Painting paintingFound=plogic.search(toSearch);
			responseSearch.add(paintingFound);
			return responseSearch;
		}
		else return nextChain.caseSolve(receive);
	}

}
