package startServer;

import java.util.List;

public interface CaseChain {
	void setNextChain(CaseChain nextChain);
	List<Object> caseSolve(List<Object> receive);
}
