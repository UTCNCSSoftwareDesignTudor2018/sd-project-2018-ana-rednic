package chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

import businessLogic.VisitorBusinessLogic;
import entity.Visitor;

public class Register implements CaseChain {

	private CaseChain nextChain;
	@Override
	public void setNextChain(CaseChain nextChain) {
		this.nextChain=nextChain;
	}

	@Override
	public List<Object> caseSolve(List<Object> receive) {
		if (receive.get(0).equals("Register")) {
			VisitorBusinessLogic vlogic=new VisitorBusinessLogic();
			List<Object> responseRegister = new ArrayList<Object>();
			Visitor v1=(Visitor) receive.get(1);
			vlogic.insertVisitor(v1);
			Visitor v2=vlogic.getVisitorByName(v1.getName());
			responseRegister.add(v2);
			return responseRegister;
		}
		else return nextChain.caseSolve(receive);
	}

}
