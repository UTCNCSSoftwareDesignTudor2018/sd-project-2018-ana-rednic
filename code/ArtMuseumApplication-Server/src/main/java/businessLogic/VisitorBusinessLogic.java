package businessLogic;

import java.util.List;

import entity.Visitor;
import repository.VisitorRepo;

public class VisitorBusinessLogic {
	public VisitorRepo arepo=new VisitorRepo();
	public void insertVisitor(Visitor a) {
		arepo.insertVisitor(a);
	}
	public void updateVisitor(Visitor a) {
		arepo.updateVisitor(a);
	}
	public void deleteVisitor(Visitor a) {
		//arepo.deleteVisitor(a);
	}
	public List<Visitor> viewAllVisitors() {
		return arepo.viewAllVisitors();
	}
	public Visitor getVisitorByName(String name) {
		return arepo.getVisitorByName(name);
	}
	public Visitor getVisitorByUsernameAndPassword(String username, String password) {
		return arepo.getVisitorByUsernameAndPassword(username,password);
	}
}
