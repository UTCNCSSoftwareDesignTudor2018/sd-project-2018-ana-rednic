package chainOfResponsibility;

import java.util.List;

import businessLogic.PaintingBusinessLogic;

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
			String toSearch = (String) receive.get(1);
			return plogic.search(toSearch);
		}
		else return nextChain.caseSolve(receive);
	}

}
