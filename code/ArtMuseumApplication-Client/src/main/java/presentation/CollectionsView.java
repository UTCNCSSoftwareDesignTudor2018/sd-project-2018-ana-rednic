package presentation;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

public class CollectionsView implements Observer {

	private JFrame frmMyCollections;
	
	public CollectionsView() {
		initialize();
		frmMyCollections.setVisible(true);
	}

	private void initialize() {
		frmMyCollections = new JFrame();
		frmMyCollections.setTitle("My Collections");
		frmMyCollections.setBounds(100, 100, 450, 300);
		frmMyCollections.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMyCollections.getContentPane().setLayout(null);
		
		JButton btnCreateCollection = new JButton("Create new collection");
		btnCreateCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateCollectionView();
			}
		});
		btnCreateCollection.setBounds(42, 227, 163, 23);
		frmMyCollections.getContentPane().add(btnCreateCollection);
		
		JButton btnDeleteCollection = new JButton("Delete collection");
		btnDeleteCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// DELETE COLLECTION
			}
		});
		btnDeleteCollection.setBounds(244, 227, 131, 23);
		frmMyCollections.getContentPane().add(btnDeleteCollection);
		
		frmMyCollections.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void update(Observable o, Object arg) {		
	}
}
