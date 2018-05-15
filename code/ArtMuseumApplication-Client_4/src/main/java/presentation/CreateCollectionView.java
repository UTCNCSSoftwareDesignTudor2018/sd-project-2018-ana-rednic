package presentation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import entity.Collection;
import entity.Visitor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CreateCollectionView {

	private JFrame frmCreateCollection;
	private JTextField textField_name;

	public CreateCollectionView() {
		initialize();
		frmCreateCollection.setVisible(true);
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
		
		textField_name = new JTextField();
		textField_name.setBounds(134, 20, 115, 20);
		frmCreateCollection.getContentPane().add(textField_name);
		textField_name.setColumns(10);
		
		JButton btnCreateCollection = new JButton("Create collection");
		btnCreateCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collection c=new Collection();
				c.setName(textField_name.getText());
				//c.setPaintings....
				List<Object> list = new ArrayList<Object>();
				list.add("Create");
				list.add(c);
				System.out.println("A new collection has been created");
				try {
					processInformation(list);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCreateCollection.setBounds(65, 227, 123, 23);
		frmCreateCollection.getContentPane().add(btnCreateCollection);
	}
	
	public List<Object> processInformation(List<Object> toSend) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 8080);
        ObjectOutputStream infoToGiveToServer = new ObjectOutputStream(socket.getOutputStream());
        infoToGiveToServer.writeObject(toSend);
        infoToGiveToServer.flush();

        // Here we read the details from server
        ObjectInputStream serverResponse = new ObjectInputStream(socket.getInputStream());	    
        List<Object> list = null;
		try {
			list = (List<Object>) serverResponse.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        
        serverResponse.close();
        infoToGiveToServer.close();
        socket.close();
        return list;
	}
}
