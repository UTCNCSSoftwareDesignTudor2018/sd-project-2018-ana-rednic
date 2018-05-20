package startServer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import chainOfResponsibility.ConnectionChain;

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
			
			ConnectionChain cc=new ConnectionChain();
			List<Object> response=new ArrayList<Object>();
			response=cc.solve(receive);
			
			ObjectOutputStream outputLogin = new ObjectOutputStream(aNewClientSocket.getOutputStream());
			outputLogin.writeObject(response);	
		}
	}
}