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

/**
 * The AdminDeleteClassesPanel class contains the graphics necessary to delete
 * classes from the school system. This feature is only accessible to 
 * administrators. In this panel, administrators delete classes by selecting the
 * user from a drop-down list.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class AdminDeleteClassesPanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;
	/** JComboBox with list of classes */
	private JComboBox<String> classList;
	/** List of classes */
	private ArrayList<SchoolClass> classes;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public AdminDeleteClassesPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}


	/**
	 * The prepareNorthPanel method creates the graphics for the header of this
	 * panel, taking out a .png file from the src folder and using it for the
	 * header.
	 */
	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_DELETE_CLASSES.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes the buttons to delete classes and setup for the drop-down
	 * that contains the list of classes.
	 */
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

			/**
			 * The actionPerformed method for this button removes the class at
			 * the selected index of the drop-down list.
			 * 
			 * @param e 	The ActionEvent
			 */
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


	/**
	 * The addChangePageButtons method adds the buttons from this panel that
	 * swap between pages of the program.
	 * 
	 * @param goHome 		Button that returns to the user homepage
	 */
	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}


	/**
	 * The refreshComboBox method refreshes the class list and the drop-down
	 * menu.
	 */
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
