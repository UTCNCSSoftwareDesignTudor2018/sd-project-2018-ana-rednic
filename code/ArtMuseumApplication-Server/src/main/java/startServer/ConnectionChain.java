package startServer;

import java.util.List;

public class ConnectionChain {
	private CaseChain c1;

	public ConnectionChain() {
		// initialize the chain
		this.c1 = new Login();
		CaseChain c2 = new Register();
		CaseChain c3 = new Search();
		CaseChain c4 = new AllPaintings();
		CaseChain c5 = new CreateCollection();
		CaseChain c6 = new AllCollections();

		// set the chain of responsibility
		c1.setNextChain(c2);
		c2.setNextChain(c3);
		c3.setNextChain(c4);
		c4.setNextChain(c5);
		c5.setNextChain(c6);
	}
	
	public List<Object> solve(List<Object> receive){
		ConnectionChain cc=new ConnectionChain();
		return cc.c1.caseSolve(receive);
	}

}
