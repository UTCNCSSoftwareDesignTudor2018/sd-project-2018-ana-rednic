package startServer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import businessLogic.PaintingBusinessLogic;
import businessLogic.VisitorBusinessLogic;
import entity.Painting;
import entity.Visitor;

public class StartServer {
	// src/main/resources/Paintings/image1.jpg
	public static List<Socket> allClientsList= new ArrayList<Socket>();
	@SuppressWarnings("unchecked")
	public static void main(String[] argv) throws Exception {
		
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("Server started");
		/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();*/
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
				
				break;
				
			case "CreateCollection":
				
				break;
			
			case "AllCollections":
				
				break;
			
			/*case "Read":
				List<Object> toSend = new ArrayList<Object>();
				Article a = (Article) receive.get(1);

				repo.insertArticle(a);
				toSend.add(a);
				ObjectOutputStream objectWayToSend = new ObjectOutputStream(aNewClientSocket.getOutputStream());
				objectWayToSend.writeObject(toSend);

				objectWayToSend.flush();
				objectWayToSend.close();
				aNewClientSocket.close();
				toReceiveFromClient.close();
				break;
			case "Reader":

				List<Object> send = new ArrayList<Object>();
				List<Article> allArticle = new ArrayList<Article>();
				allArticle=repo.viewAllArticles();
				for (Article articl : allArticle) {
					send.add(articl);
				}
				ObjectOutputStream backToClient = new ObjectOutputStream(aNewClientSocket.getOutputStream());
				backToClient.writeObject(send);
				backToClient.close();				
				toReceiveFromClient.close();
				aNewClientSocket.close();
				break;
				
			case "Writter":
				List<Object> sendList = new ArrayList<Object>();
				List<Writer> allWriters = new ArrayList<Writer>();
				allWriters=repoWriter.viewAllWriters();
				for(Writer w: allWriters) {
					sendList.add(w);
				}
				System.out.println(allWriters.size());
				ObjectOutputStream response = new ObjectOutputStream(aNewClientSocket.getOutputStream());
				Writer sesionWriter= repoWriter.writerGivenUsername((String)receive.get(1));
				JOptionPane.showMessageDialog(null, sesionWriter.getName());
				//sendList.add(sesionWriter);		
				
				response.writeObject(sendList);

				break;
			case "Adauga":
				List<Object> toDo = new ArrayList<Object>();
				toDo.add("Da");
				
				ObjectOutputStream ras = new ObjectOutputStream(aNewClientSocket.getOutputStream());
				Article artRec=(Article) receive.get(1);
				repo.insertArticle(artRec);
				ras.writeObject(toDo);

				break;*/
			default:
				JOptionPane.showMessageDialog(null, "Invalid command");
				break;
			}

		}

	}
}