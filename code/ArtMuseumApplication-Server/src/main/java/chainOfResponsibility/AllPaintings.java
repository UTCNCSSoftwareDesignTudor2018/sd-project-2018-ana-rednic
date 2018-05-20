package chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

import businessLogic.PaintingBusinessLogic;
import entity.Painting;

public class AllPaintings implements CaseChain {

	private CaseChain nextChain;
	@Override
	public void setNextChain(CaseChain nextChain) {
		this.nextChain=nextChain;
	}

	@Override
	public List<Object> caseSolve(List<Object> receive) {
		if (receive.get(0).equals("AllPaintings")) {
			PaintingBusinessLogic plogic=new PaintingBusinessLogic();
			List<Object> responseAllPaintings = new ArrayList<Object>();
			List<Painting> listPaintings = plogic.viewAllPaintings();
			responseAllPaintings.add(listPaintings);
			return responseAllPaintings;
		}
		else return nextChain.caseSolve(receive);
	}

}
