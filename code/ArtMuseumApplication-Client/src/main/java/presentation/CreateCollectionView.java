package presentation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import entity.Collection;
import entity.Painting;
import entity.Visitor;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
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
	Visitor sessionVisitor = new Visitor();
	List<String> selectedItems = new LinkedList<String>();

	public CreateCollectionView(Visitor sessionVisitor) {
		this.sessionVisitor = sessionVisitor;
		initialize();
		frmCreateCollection.setVisible(true);

	}

	private void initialize() {
		frmCreateCollection = new JFrame();
		frmCreateCollection.setTitle("Create Collection");
		frmCreateCollection.setBounds(100, 100, 266, 300);
		frmCreateCollection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCreateCollection.getContentPane().setLayout(null);

		JLabel lblCollectionName = new JLabel("Collection name:");
		lblCollectionName.setBounds(21, 23, 108, 14);
		frmCreateCollection.getContentPane().add(lblCollectionName);

		textField_name = new JTextField();
		textField_name.setBounds(131, 20, 103, 20);
		frmCreateCollection.getContentPane().add(textField_name);
		textField_name.setColumns(10);

		// -- jlist
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 48, 213, 168);
		frmCreateCollection.getContentPane().add(scrollPane);

		List<Object> listObjects = new ArrayList<Object>();
		listObjects.add("AllPaintings");
		System.out.println("All paintings should be visualized");
		final List<Painting> selectedPaintings = new LinkedList<Painting>();

		try {
			final DefaultListModel<String> listModel = new DefaultListModel<String>();
			List<Object> response = processInformation(listObjects);
			final List<Painting> listPaintings = (List<Painting>) response.get(0);
			for (int i = 0; i < listPaintings.size(); i++) {
				listModel.addElement(listPaintings.get(i).getTitle());
			}
			final JList<String> jlist = new JList<String>(listModel);
			scrollPane.setViewportView(jlist);

			jlist.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int[] indices = jlist.getSelectedIndices();
					Object[] values = jlist.getSelectedValues();
					for (int i = 0; i < indices.length; i++) {
						String selectedItem = (String) values[i];
						for (int j = 0; j < listPaintings.size(); j++) {
							if (listPaintings.get(j).getTitle() == selectedItem) {
								selectedPaintings.add(listPaintings.get(j));
								System.out.println(listPaintings.get(j).getTitle());
							}
						}
					}
				}
			});

		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Something went wrong");
		}
		// -- jlist

		JButton btnCreateCollection = new JButton("Create collection");
		btnCreateCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collection c = new Collection();
				c.setName(textField_name.getText());
				//c.setListOfPaintings(selectedPaintings);
				c.setVisitor(sessionVisitor);
				List<Object> list = new ArrayList<Object>();
				list.add("CreateCollection");
				list.add(c);
				System.out.println("A new collection has been created");
				try {
					processInformation(list);
					CollectionsView cview = new CollectionsView(sessionVisitor);
					frmCreateCollection.setVisible(false);
					addObserver((Observer) cview); 
					notifyObserver();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong");
				}
			}
		});
		btnCreateCollection.setBounds(42, 227, 167, 23);
		frmCreateCollection.getContentPane().add(btnCreateCollection);

		frmCreateCollection.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// localhost
	public List<Object> processInformation(List<Object> toSend) throws UnknownHostException, IOException {
		// Socket socket = new Socket("192.168.43.197", 9999);
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
		JOptionPane.showMessageDialog(null, "A new collection has been created");
	}
}
