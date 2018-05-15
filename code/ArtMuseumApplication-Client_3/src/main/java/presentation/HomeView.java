package presentation;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class HomeView {

	private JFrame frmHome;
	private JTextField txtEnterSomethingTo;
	
	public HomeView() {
		initialize();
	}

	private void initialize() {
		frmHome = new JFrame();
		frmHome.setTitle("Home");
		frmHome.setBounds(100, 100, 488, 300);
		frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHome.getContentPane().setLayout(null);
		
		txtEnterSomethingTo = new JTextField();
		txtEnterSomethingTo.setForeground(Color.GRAY);
		txtEnterSomethingTo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtEnterSomethingTo.setText("Enter something to search...");
		txtEnterSomethingTo.setBounds(24, 23, 170, 20);
		frmHome.getContentPane().add(txtEnterSomethingTo);
		txtEnterSomethingTo.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(202, 22, 73, 23);
		frmHome.getContentPane().add(btnSearch);
		
		JButton btnCreateCollection = new JButton("<html>Create<br />collection</html>");
		btnCreateCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateCollection.setBounds(285, 22, 73, 37);
		frmHome.getContentPane().add(btnCreateCollection);
		
		JButton btnviewMycollections = new JButton("<html>View my<br />collections</html>");
		btnviewMycollections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnviewMycollections.setBounds(364, 22, 81, 37);
		frmHome.getContentPane().add(btnviewMycollections);
	}

}
