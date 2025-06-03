package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import files.DataManagement;

/**
 * The AdminAddClassesPanel class contains the graphics necessary to add classes
 * in school system. This feature is only accessible to administrators. In this
 * panel, administrators select a new class and choose a name and block for the
 * class.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class AdminAddClassesPanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;
	/** A JComboBox with the available block numbers */
	private JComboBox<Integer> selectPeriod;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public AdminAddClassesPanel() {
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_ADD_CLASSES.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes the labels asking to enter class name, labels with notices,
	 * and buttons that send out the data to be written.
	 */
	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);

		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		JLabel enterClass = new JLabel("Enter Class Name:");
		enterClass.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		JTextField givenClassName = new JTextField();
		givenClassName.setMinimumSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenClassName.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenClassName.setFont(GraphicsConstants.FONT_ROBOTO_B30);

		JLabel enterPeriod = new JLabel("Enter Block:");
		enterPeriod.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		selectPeriod = new JComboBox<Integer>();
		selectPeriod.setFont(GraphicsConstants.FONT_ROBOTO_B30);

		for (int i = 1; i <= DataManagement.getBlocks(); i++) {
			selectPeriod.addItem(i);
		}

		JLabel notice = new JLabel("Notice: Classes are immediately set to points system grading.\n"
				+ "Use the Manage Classes panel to edit the system.");
		notice.setFont(GraphicsConstants.FONT_ROBOTO_B30);	

		JButton confirm = new JButton("Confirm");
		GraphicsHelpers.modifyButton(confirm, 250, 45);
		confirm.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button takes the selected
			 * block and given class name and sends it to DataManagement for
			 * class creation. Invalid input (i.e., input with commas) is
			 * handled.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = givenClassName.getText();
				int block = Integer.parseInt(selectPeriod.getSelectedItem().toString());

				for (int i = 0; i < className.length(); i++) {
					if (className.substring(i, i+1).equals(",")) {
						JOptionPane.showMessageDialog(centerPanel, "You cannot"
								+ " have commas in any of your fields to ensure proper"
								+ " data storage.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				DataManagement.addNewClass(className, block);
				givenClassName.setText("");
			}

		});

		centerPanel.add(enterClass);
		centerPanel.add(givenClassName);
		centerPanel.add(enterPeriod);
		centerPanel.add(selectPeriod);
		centerPanel.add(notice);
		centerPanel.add(confirm);

		sl.putConstraint(SpringLayout.WEST, enterClass, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterClass, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenClassName, 100, SpringLayout.EAST, enterClass);
		sl.putConstraint(SpringLayout.NORTH, givenClassName, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterPeriod, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterPeriod, 250, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, selectPeriod, 100, SpringLayout.EAST, enterPeriod);
		sl.putConstraint(SpringLayout.NORTH, selectPeriod, 250, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, notice, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, notice, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, confirm, 0, SpringLayout.WEST, givenClassName);
		sl.putConstraint(SpringLayout.NORTH, confirm, 550, SpringLayout.NORTH, centerPanel);

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
	 * The refreshComboBox method refreshes the JComboBox period list to conform
	 * with the different school preferences of the logged in user.
	 */
	public void refreshComboBox() {
		selectPeriod.removeAllItems();
		int blocks = DataManagement.getBlocks();

		if (blocks > 0) {
			for (int i = 1; i <= blocks; i++) {
				selectPeriod.addItem(i);
			}
		}
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "AdminAddClassesPanel [selectPeriod=" + selectPeriod + "]";
	}
}
