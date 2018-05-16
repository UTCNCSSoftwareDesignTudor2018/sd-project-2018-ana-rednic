package startServer;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import entity.Painting;

public class Main {

	private static JFrame frame;
	static BufferedImage img;

	public static void main(String[] args) {
		try {
			img=ImageIO.read(new File("src/main/resources/Paintings/image1.jpg"));
			frame = new JFrame();
			frame.setBounds(100, 100, 450, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JLabel lbl=new JLabel(new ImageIcon(img));
			frame.add(lbl);
			frame.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Main() {
		initialize();
		frame.setVisible(true);
	}


	private void initialize() {
		
	}

}
