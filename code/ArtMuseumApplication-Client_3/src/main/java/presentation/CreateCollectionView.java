package presentation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateCollectionView {

	private JFrame frmCreateCollection;
	private JTextField textField;

	public CreateCollectionView() {
		initialize();
	}

	private void initialize() {
		frmCreateCollection = new JFrame();
		frmCreateCollection.setTitle("Create Collection");
		frmCreateCollection.setBounds(100, 100, 275, 300);
		frmCreateCollection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCreateCollection.getContentPane().setLayout(null);
		
		JLabel lblCollectionName = new JLabel("Collection name:");
		lblCollectionName.setBounds(21, 23, 108, 14);
		frmCreateCollection.getContentPane().add(lblCollectionName);
		
		textField = new JTextField();
		textField.setBounds(134, 20, 115, 20);
		frmCreateCollection.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnCreateCollection = new JButton("Create collection");
		btnCreateCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateCollection.setBounds(65, 227, 123, 23);
		frmCreateCollection.getContentPane().add(btnCreateCollection);
	}

}
