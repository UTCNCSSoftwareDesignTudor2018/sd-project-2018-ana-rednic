package presentation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import entity.Collection;
import entity.Painting;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class CreateCollectionView extends Observable {

	private ArrayList<Observer> listObservers = new ArrayList<Observer>();
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
		
		// -- jlist
		/*JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 48, 228, 168);
		frmCreateCollection.getContentPane().add(scrollPane);
		
		List<Object> listObjects = new ArrayList<Object>();
		listObjects.add("AllPaintings");
		System.out.println("All paintings should be visualized");
		try {
			final DefaultListModel<String> listModel = new DefaultListModel<String>();
			List<Object> response = processInformation(listObjects);
			List<Painting> listPaintings=(List<Painting>) response.get(0);
			for (int i=0;i<listPaintings.size();i++) {
				listModel.addElement(listPaintings.get(i).getTitle());
			}
			final JList<String> jlist = new JList<String>(listModel);
			scrollPane.setViewportView(jlist);
			//listen(jlist);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Something went wrong");
		}*/
		// -- jlist
		
		JButton btnCreateCollection = new JButton("Create collection");
		btnCreateCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collection c=new Collection();
				c.setName(textField_name.getText());
				//c.setPaintings....
				List<Object> list = new ArrayList<Object>();
				list.add("CreateCollection");
				list.add(c);
				System.out.println("A new collection has been created");
				try {
					processInformation(list);
					CollectionsView cview=new CollectionsView();
					addObserver((Observer) cview);
					notifyObserver();
				} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Something went wrong");
					}
				}
		});
		btnCreateCollection.setBounds(65, 227, 123, 23);
		frmCreateCollection.getContentPane().add(btnCreateCollection);
		
		frmCreateCollection.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	//localhost
	public List<Object> processInformation(List<Object> toSend) throws UnknownHostException, IOException {
        //Socket socket = new Socket("192.168.43.197", 9999);
        Socket socket = new Socket("localhost", 9999);

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
	
	@Override
	public void addObserver(Observer o) {
		listObservers.add(o);
	}

	public void removeObserver(Observer o) {
		listObservers.remove(o);		
	}

	public void notifyObserver() {		
		for (Observer reader :listObservers) {
			reader.update(null, null);
		}
	}
}
