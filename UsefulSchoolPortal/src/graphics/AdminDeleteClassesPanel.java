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
import javax.swing.SpringLayout;

import files.DataManagement;
import objects.SchoolClass;
import objects.User;

public class AdminDeleteClassesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> classList;
	private ArrayList<SchoolClass> classes;
	
	public AdminDeleteClassesPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}


	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_DELETE_CLASSES.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);

		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		JLabel selectClass = new JLabel("Select Class:");
		selectClass.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		classList = new JComboBox<String>();
		classList.setFont(GraphicsConstants.FONT_ROBOTO_B30);

		JButton deleteClass = new JButton("Delete Class");
		GraphicsHelpers.modifyButton(deleteClass, 250, 45);
		deleteClass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = classList.getSelectedIndex();
				DataManagement.deleteClass(classes.get(index).getClassID());
				refreshComboBox();
			}

		});

		centerPanel.add(selectClass);
		centerPanel.add(classList);
		centerPanel.add(deleteClass);

		sl.putConstraint(SpringLayout.WEST, selectClass, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, selectClass, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, classList, 100, SpringLayout.EAST, selectClass);
		sl.putConstraint(SpringLayout.NORTH, classList, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, deleteClass, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, deleteClass, 300, SpringLayout.NORTH, centerPanel);

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
		classes = DataManagement.getCurrentSchoolClasses();

		if (classes != null) {
			for (SchoolClass sc : classes) {
				classList.addItem(sc.getName() + " - Block " + sc.getBlock());
			}
		}
	}

	
}
