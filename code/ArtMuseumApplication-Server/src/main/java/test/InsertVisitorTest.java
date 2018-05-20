package test;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

import businessLogic.VisitorBusinessLogic;
import entity.Visitor;
public class InsertVisitorTest {

	@Test
	public void test() {
		VisitorBusinessLogic vlogic=new VisitorBusinessLogic();
		List<Visitor> listV=vlogic.viewAllVisitors();
		int currentNumber=listV.size();
		Visitor v=new Visitor();
		v.setName("Test");
		v.setUsername("test");
		v.setPassword("test");
		vlogic.insertVisitor(v);
		List<Visitor> newlistV=vlogic.viewAllVisitors();
		int newNumber=newlistV.size();
		assertEquals(true,newNumber==currentNumber+1);
	}

}
