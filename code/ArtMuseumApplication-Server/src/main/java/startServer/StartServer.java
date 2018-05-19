package startServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import businessLogic.CollectionBusinessLogic;
import businessLogic.PaintingBusinessLogic;
import businessLogic.VisitorBusinessLogic;
import entity.Collection;
import entity.Painting;
import entity.Visitor;
import repository.PaintingRepo;

public class StartServer {
	// src/main/resources/Paintings/image1.jpg
	public static List<Socket> allClientsList= new ArrayList<Socket>();
	@SuppressWarnings("unchecked")
	public static void main(String[] argv) throws Exception {
		
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("Server started");
		/*
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        */
		/*
		File file1 = new File("src/main/resources/Paintings/image13.jpg");
        byte[] bFile = new byte[(int) file1.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file1);
	     fileInputStream.read(bFile);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
		PaintingRepo prepo=new PaintingRepo();
		Painting painting1 = new Painting();
		painting1.setImage(bFile);
		painting1.setTitle("Peisaj de vara");
		painting1.setAuthor("Bortsok Samuel");
		painting1.setDescription("??");
		prepo.insertPainting(painting1);
		*/
		
		while (true) {

			List<Object> receive = new ArrayList<Object>();
			// Wait for client to connect
			Socket aNewClientSocket = serverSocket.accept();
			allClientsList.add(aNewClientSocket);
			
			System.out.println("Server-Client connection established");

			ObjectInputStream toReceiveFromClient = new ObjectInputStream(aNewClientSocket.getInputStream());
			receive = (List<Object>) toReceiveFromClient.readObject();

			System.out.println(aNewClientSocket.getLocalPort());
			System.out.println(aNewClientSocket.getInetAddress());
			
			// CREATE REPOS
			VisitorBusinessLogic vlogic=new VisitorBusinessLogic();
			PaintingBusinessLogic plogic=new PaintingBusinessLogic();
			CollectionBusinessLogic clogic=new CollectionBusinessLogic();
			
			switch ((String) receive.get(0)) {
			
			case "Login":
				List<Object> responseLogin = new ArrayList<Object>();
				String username = (String) receive.get(1);
				String password = (String) receive.get(2);
				Visitor v=vlogic.getVisitorByUsernameAndPassword(username, password);
				responseLogin.add(v);
				ObjectOutputStream outputLogin = new ObjectOutputStream(aNewClientSocket.getOutputStream());
				outputLogin.writeObject(responseLogin);
				break;
				
			case "Register":
				List<Object> responseRegister = new ArrayList<Object>();
				Visitor v1=(Visitor) receive.get(1);
				vlogic.insertVisitor(v1);
				Visitor v2=vlogic.getVisitorByName(v1.getName());
				responseRegister.add(v2);
				ObjectOutputStream outputRegister = new ObjectOutputStream(aNewClientSocket.getOutputStream());
				outputRegister.writeObject(responseRegister);
				break;
			
			case "Search":
				List<Object> responseSearch = new ArrayList<Object>();
				String toSearch = (String) receive.get(1);
				Painting paintingFound=plogic.search(toSearch);
				responseSearch.add(paintingFound);
				ObjectOutputStream outputSearch = new ObjectOutputStream(aNewClientSocket.getOutputStream());
				outputSearch.writeObject(responseSearch);
				break;
			
			case "AllPaintings":
				List<Object> responseAllPaintings = new ArrayList<Object>();
				List<Painting> listPaintings = plogic.viewAllPaintings();
				responseAllPaintings.add(listPaintings);
				ObjectOutputStream outputAllPaintings = new ObjectOutputStream(aNewClientSocket.getOutputStream());
				outputAllPaintings.writeObject(responseAllPaintings);
				break;
				
			case "CreateCollection":
				List<Object> responseCreateCollection = new ArrayList<Object>();
				Collection c=(Collection) receive.get(1);
				clogic.insertCollection(c);
				System.out.println("inserted");
				Collection c2=clogic.getCollectionByName(c.getName());
				/*for (int i=0; i<c2.getListOfPaintings().size(); i++) {
					Painting p=c2.getListOfPaintings().get(i);
					p.getListOfCollections().add(c2);
					plogic.updatePainting(p);
				}*/
				responseCreateCollection.add(c2);
				ObjectOutputStream outputCreateCollection = new ObjectOutputStream(aNewClientSocket.getOutputStream());
				outputCreateCollection.writeObject(responseCreateCollection);
				break;
			
			case "AllCollections":
				List<Object> responseAllCollections = new ArrayList<Object>();
				Visitor visi= (Visitor) receive.get(1);
				List<Collection> listCollections=clogic.allCollectionsOfAVisitor(visi.getId());
				responseAllCollections.add(listCollections);
				ObjectOutputStream outputAllCollections = new ObjectOutputStream(aNewClientSocket.getOutputStream());
				outputAllCollections.writeObject(responseAllCollections);
				break;
			
			default:
				JOptionPane.showMessageDialog(null, "Invalid command");
				break;
			}

		}

	}
}