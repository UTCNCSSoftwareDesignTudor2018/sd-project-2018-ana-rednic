package presentation;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;

public class PaintingView {

	private JFrame frmPainting;

	public PaintingView() {
		initialize();
	}

	private void initialize() {
		frmPainting = new JFrame();
		frmPainting.setTitle("Painting");
		frmPainting.setBounds(100, 100, 269, 335);
		frmPainting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPainting.getContentPane().setLayout(null);
		
		JTextPane textPane_description = new JTextPane();
		textPane_description.setBounds(10, 176, 233, 109);
		frmPainting.getContentPane().add(textPane_description);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(10, 160, 78, 14);
		frmPainting.getContentPane().add(lblDescription);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setBounds(10, 138, 57, 14);
		frmPainting.getContentPane().add(lblAuthor);
		
		JLabel label_author = new JLabel("");
		label_author.setBounds(95, 138, 148, 14);
		frmPainting.getContentPane().add(label_author);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(10, 113, 46, 14);
		frmPainting.getContentPane().add(lblTitle);
		
		JLabel label_title = new JLabel("");
		label_title.setBounds(95, 113, 148, 14);
		frmPainting.getContentPane().add(label_title);
	}
}
