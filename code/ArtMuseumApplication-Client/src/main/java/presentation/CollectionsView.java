package presentation;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

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
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

public class CollectionsView implements Observer {

	private JFrame frmMyCollections;
	
	Visitor sessionVisitor=new Visitor();
	Collection selectedCollection=new Collection();
	Painting selectedPainting = new Painting();
	
	public CollectionsView(Visitor sessionVisitor) {
		this.sessionVisitor=sessionVisitor;
		initialize();
		frmMyCollections.setVisible(true);
	}

	private void initialize() {
		frmMyCollections = new JFrame();
		frmMyCollections.setTitle("My Collections");
		frmMyCollections.setBounds(100, 100, 237, 300);
		frmMyCollections.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMyCollections.getContentPane().setLayout(null);
		
		JButton btnCreateCollection = new JButton("Create new collection");
		btnCreateCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateCollectionView(sessionVisitor);
				frmMyCollections.setVisible(false);
			}
		});
		btnCreateCollection.setBounds(20, 227, 174, 23);
		frmMyCollections.getContentPane().add(btnCreateCollection);
		
		JButton btnDeleteCollection = new JButton("Delete collection");
		btnDeleteCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// DELETE COLLECTION
			}
		});
		btnDeleteCollection.setBounds(242, 227, 131, 23);
		frmMyCollections.getContentPane().add(btnDeleteCollection);
		
		// jlist collections
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 198, 192);
		frmMyCollections.getContentPane().add(scrollPane);
		
		List<Object> listObjects = new ArrayList<Object>();
		listObjects.add("AllCollections");
		listObjects.add(sessionVisitor);
		System.out.println("All collections should be visualized");
		try {
			final DefaultListModel<String> listModel = new DefaultListModel<String>();
			List<Object> response = processInformation(listObjects);
			final List<Collection> listCollections=(List<Collection>) response.get(0);
			for (int i=0;i<listCollections.size();i++) {
				listModel.addElement(listCollections.get(i).getName());
				System.out.println(listCollections.get(i).getName());
			}
			final JList<String> jlist = new JList<String>(listModel);
			scrollPane.setViewportView(jlist);
			jlist.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String selectedItem=jlist.getSelectedValue();
						for (int j=0; j<listCollections.size(); j++) {
							if (listCollections.get(j).getName()==selectedItem) {
								selectedCollection=listCollections.get(j);
							}
						}
				}
			});
			// show selectedCollection
			JScrollPane scrollPane1 = new JScrollPane();
			scrollPane1.setBounds(232, 48, 192, 168);
			frmMyCollections.getContentPane().add(scrollPane1);
			final DefaultListModel<String> listModel1 = new DefaultListModel<String>();
			for (int i=0;i<selectedCollection.getListOfPaintings().size();i++) {
				listModel1.addElement(selectedCollection.getListOfPaintings().get(i).getTitle());
				System.out.println(selectedCollection.getListOfPaintings().get(i).getTitle());
			}
			final JList<String> jlist1 = new JList<String>(listModel1);
			scrollPane1.setViewportView(jlist1);
			jlist1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String selectedItem1=jlist1.getSelectedValue();
						for (int j=0; j<selectedCollection.getListOfPaintings().size(); j++) {
							if (selectedCollection.getListOfPaintings().get(j).getTitle()==selectedItem1) {
								selectedPainting=selectedCollection.getListOfPaintings().get(j);
								new PaintingView(selectedPainting);
							}
						}
				}
			});
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Something went wrong");
		}
		frmMyCollections.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

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
	public void update(Observable o, Object arg) {		
	}
}

