package presentation;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entity.Painting;
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
import java.awt.Font;
import java.awt.Color;

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
		btnSearch.setBounds(202, 22, 73, 23);
		frmHome.getContentPane().add(btnSearch);
		
		JButton btnCreateCollection = new JButton("<html>Create<br />collection</html>");
		btnCreateCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateCollectionView();
			}
		});
		btnCreateCollection.setBounds(285, 22, 73, 37);
		frmHome.getContentPane().add(btnCreateCollection);
		
		JButton btnviewMycollections = new JButton("<html>View my<br />collections</html>");
		btnviewMycollections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CollectionsView();
			}
		});
		btnviewMycollections.setBounds(364, 22, 81, 37);
		frmHome.getContentPane().add(btnviewMycollections);
		
		frmHome.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public List<Object> processInformation(List<Object> toSend) throws UnknownHostException, IOException {
        Socket socket = new Socket("192.168.1.197", 9999);
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
