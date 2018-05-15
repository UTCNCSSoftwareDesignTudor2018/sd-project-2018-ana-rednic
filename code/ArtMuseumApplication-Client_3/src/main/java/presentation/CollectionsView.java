package presentation;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CollectionsView {

	private JFrame frmMyCollections;

	public CollectionsView() {
		initialize();
	}

	private void initialize() {
		frmMyCollections = new JFrame();
		frmMyCollections.setTitle("My Collections");
		frmMyCollections.setBounds(100, 100, 450, 300);
		frmMyCollections.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMyCollections.getContentPane().setLayout(null);
		
		JButton btnCreateCollection = new JButton("Create collection");
		btnCreateCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateCollection.setBounds(42, 227, 129, 23);
		frmMyCollections.getContentPane().add(btnCreateCollection);
	}

}
