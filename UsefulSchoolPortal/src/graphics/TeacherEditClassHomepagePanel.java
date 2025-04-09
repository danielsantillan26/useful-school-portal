package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import files.DataManagement;
import objects.SchoolClass;

public class TeacherEditClassHomepagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> classList;
	private ArrayList<SchoolClass> classes;


	public TeacherEditClassHomepagePanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}


	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_EDIT_CLASS_HOMEPAGE.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		JLabel enterClass = new JLabel("Select Class");
		enterClass.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		classList = new JComboBox<String>();
		classList.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		classList.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		
		JLabel editHomepage = new JLabel("Edit Homepage");
		editHomepage.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		JTextArea homepageText = new JTextArea();
		homepageText.setPreferredSize(new Dimension(800, 500));
		homepageText.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		homepageText.setLineWrap(true);
		
		JButton confirm = new JButton("Confirm");
		GraphicsHelpers.modifyButton(confirm, 220, 45);
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

		centerPanel.add(enterClass);
		centerPanel.add(classList);
		centerPanel.add(editHomepage);
		centerPanel.add(homepageText);
		centerPanel.add(confirm);

		sl.putConstraint(SpringLayout.WEST, enterClass, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterClass, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, classList, 100, SpringLayout.EAST, enterClass);
		sl.putConstraint(SpringLayout.NORTH, classList, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, editHomepage, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, editHomepage, 150, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, homepageText, 100, SpringLayout.EAST, editHomepage);
		sl.putConstraint(SpringLayout.NORTH, homepageText, 150, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, confirm, 0, SpringLayout.WEST, homepageText);
		sl.putConstraint(SpringLayout.NORTH, confirm, 50, SpringLayout.SOUTH, homepageText);

		add(centerPanel, BorderLayout.CENTER);
	}


	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}


	public void refreshComboBox() {
		classList.removeAllItems();
		classes = DataManagement.getCurrentUserClasses();

		if (classes != null) {
			for (SchoolClass sc : classes) {
				classList.addItem(sc.getName() + " - Block " + sc.getBlock());
			}
		}
	}
}
