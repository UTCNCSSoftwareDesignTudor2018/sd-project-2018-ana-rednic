package presentation;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entity.Painting;
import entity.Visitor;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

public class HomeView {

	private JFrame frmHome;
	private JTextField txtEnterSomethingTo;
	Visitor sessionVisitor=new Visitor();
	
	public HomeView(Visitor sessionVisitor) {
		this.sessionVisitor=sessionVisitor;
		initialize();
		frmHome.setVisible(true);
	}

	private void initialize() {
		frmHome = new JFrame();
		frmHome.setTitle("Home");
		frmHome.setBounds(100, 100, 402, 350);
		frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHome.getContentPane().setLayout(null);
		
		txtEnterSomethingTo = new JTextField();
		txtEnterSomethingTo.setForeground(Color.GRAY);
		txtEnterSomethingTo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtEnterSomethingTo.setText("Enter something to search...");
		txtEnterSomethingTo.setBounds(24, 23, 166, 20);
		frmHome.getContentPane().add(txtEnterSomethingTo);
		txtEnterSomethingTo.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Object> list = new ArrayList<Object>();
				list.add("Search");
				list.add(txtEnterSomethingTo.getText());
				System.out.println("Search for something");
				try {
					List<Object> response = processInformation(list);
					Painting sessionPainting=(Painting) response.get(0);
					if (sessionPainting!=null) new PaintingView(sessionPainting);
					else JOptionPane.showMessageDialog(null, "Couldn't find your search");				
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Something went wrong");					}
			}
		});
		btnSearch.setBounds(194, 23, 103, 20);
		frmHome.getContentPane().add(btnSearch);
		
		JButton btnCreateCollection = new JButton("Create collection");
		btnCreateCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateCollectionView(sessionVisitor);
			}
		});
		btnCreateCollection.setBounds(24, 54, 166, 20);
		frmHome.getContentPane().add(btnCreateCollection);
		
		JButton btnviewMycollections = new JButton("View my collections");
		btnviewMycollections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CollectionsView(sessionVisitor);
			}
		});
		btnviewMycollections.setBounds(194, 54, 166, 20);
		frmHome.getContentPane().add(btnviewMycollections);
		
		// show all paintings
		int line=83;
		int column=24;
		List<Object> list = new ArrayList<Object>();
		list.add("AllPaintings");
		System.out.println("Show all paintings");
		try {
			List<Object> response = processInformation(list);
			final List<Painting> listPaintings=(List<Painting>) response.get(0);
			//parse all paintings and make a button for each
			for (int i=0;i<listPaintings.size(); i++) {
				byte[] currentImage = listPaintings.get(i).getImage();
				final Painting currentPainting = listPaintings.get(i);
				JButton btnCurrentButton = new JButton("");
				btnCurrentButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new PaintingView(currentPainting);
					}
				});
				btnCurrentButton.setIcon(new ImageIcon(currentImage));
				btnCurrentButton.setBounds(column, line, 81, 61);
				line+=65;
				if (line>250) { line=83; column+=85; }
				btnCurrentButton.setBorder(BorderFactory.createEmptyBorder());
				btnCurrentButton.setContentAreaFilled(false);
				btnCurrentButton.setFocusable(false);
				frmHome.getContentPane().add(btnCurrentButton);
			
			}
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Something went wrong");				
				}
		/*
		//for image 1
		File file1 = new File("src/main/resources/Paintings/image1.jpg");
        byte[] bFile = new byte[(int) file1.length()];
        try {
	     FileInputStream fileInputStream = new FileInputStream(file1);
	     fileInputStream.read(bFile);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        //System.out.println("Info clicked");
		    }
		});
		btnNewButton.setIcon(new ImageIcon(bFile));
		btnNewButton.setBounds(24, 83, 81, 61);
		btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusable(false);
		frmHome.add(btnNewButton);
		*/
		frmHome.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
}
