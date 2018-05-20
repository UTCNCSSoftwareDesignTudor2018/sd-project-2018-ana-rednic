package chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

import businessLogic.VisitorBusinessLogic;
import entity.Visitor;

public class Login implements CaseChain {

	private CaseChain nextChain;

	@Override
	public void setNextChain(CaseChain nextChain) {
		this.nextChain = nextChain;
	}

	@Override
	public List<Object> caseSolve(List<Object> receive) {
		if (receive.get(0).equals("Login")) {
			System.out.println("log-in");
			VisitorBusinessLogic vlogic = new VisitorBusinessLogic();
			List<Object> responseLogin = new ArrayList<Object>();
			String username = (String) receive.get(1);
			String password = (String) receive.get(2);
			Visitor v = vlogic.getVisitorByUsernameAndPassword(username, password);
			responseLogin.add(v);
			return responseLogin;
		} else {
			return nextChain.caseSolve(receive);
		}
	}

}
