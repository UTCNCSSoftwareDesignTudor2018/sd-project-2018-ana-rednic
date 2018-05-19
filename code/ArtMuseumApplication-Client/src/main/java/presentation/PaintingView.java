package presentation;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextPane;

import entity.Painting;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PaintingView {

	private JFrame frmPainting;
	Painting sessionPainting=new Painting();

	public PaintingView(Painting sessionPainting) {
		this.sessionPainting=sessionPainting;
		initialize();
		frmPainting.setVisible(true);
	}

	private void initialize() {
		frmPainting = new JFrame();
		frmPainting.setTitle("Painting");
		frmPainting.setBounds(100, 100, 269, 365);
		frmPainting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPainting.getContentPane().setLayout(null);
		
		JTextPane textPane_description = new JTextPane();
		textPane_description.setBounds(10, 282, 233, 33);
		frmPainting.getContentPane().add(textPane_description);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(10, 257, 78, 14);
		frmPainting.getContentPane().add(lblDescription);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setBounds(10, 235, 57, 14);
		frmPainting.getContentPane().add(lblAuthor);
		
		JLabel label_author = new JLabel("");
		label_author.setBounds(80, 235, 166, 14);
		frmPainting.getContentPane().add(label_author);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(10, 210, 46, 14);
		frmPainting.getContentPane().add(lblTitle);
		
		JLabel label_title = new JLabel("");
		label_title.setBounds(80, 210, 166, 14);
		frmPainting.getContentPane().add(label_title);
		
		//panel_painting.add(sessionPainting.getImage());
		label_title.setText(sessionPainting.getTitle());
		label_author.setText(sessionPainting.getAuthor());
		textPane_description.setText(sessionPainting.getDescription());
		
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(10, 11, 233, 195);
		byte[] byteImage=sessionPainting.getImage();
		ImageIcon iconImage=new ImageIcon(byteImage);
		Image imageImage = iconImage.getImage();
		Image scaledImage=imageImage.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
		lbl_image.setIcon(new ImageIcon(scaledImage));
		frmPainting.getContentPane().add(lbl_image);
		
		frmPainting.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
