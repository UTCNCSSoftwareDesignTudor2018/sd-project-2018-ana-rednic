package presentation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import entity.Painting;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class SearchView {

	private JFrame frmSearchResults;
	List<Object> response = new ArrayList<Object>();
	Painting selectedPainting = new Painting();
	List<Painting> sessionPaintings = new ArrayList<Painting>();

	public SearchView(List<Object> response) {
		this.response = response;
		if (response.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Couldn't find your search");
		} else {
			this.sessionPaintings = (List<Painting>) response.get(0);
			initialize();
		}
		frmSearchResults.setVisible(true);
	}

	private void initialize() {
		frmSearchResults = new JFrame();
		frmSearchResults.setTitle("Search results");
		frmSearchResults.setBounds(100, 100, 236, 265);
		frmSearchResults.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSearchResults.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 220, 2);
		frmSearchResults.getContentPane().add(scrollPane);
		final DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < sessionPaintings.size(); i++) {
			listModel.addElement(sessionPaintings.get(i).getTitle());
		}
		final JList<String> jlist = new JList<String>(listModel);
		jlist.setBounds(10, 38, 200, 76);
		frmSearchResults.getContentPane().add(jlist);

		JLabel lblThisIsThe = new JLabel("This is the result of your search: ");
		lblThisIsThe.setBounds(10, 13, 200, 14);
		frmSearchResults.getContentPane().add(lblThisIsThe);
		if (response.size() > 1) {
			String interpretation = (String) response.get(1);
			if (!interpretation.equals(null)) {
				JTextPane textPane_interpretation = new JTextPane();
				textPane_interpretation.setText(interpretation);
				textPane_interpretation.setBounds(10, 125, 200, 90);
				frmSearchResults.getContentPane().add(textPane_interpretation);
			}
		}
		jlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selectedItem = jlist.getSelectedValue();
				for (int j = 0; j < sessionPaintings.size(); j++) {
					if (sessionPaintings.get(j).getTitle() == selectedItem) {
						selectedPainting = sessionPaintings.get(j);
						new PaintingView(selectedPainting);
					}
				}
			}
		});

		frmSearchResults.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
