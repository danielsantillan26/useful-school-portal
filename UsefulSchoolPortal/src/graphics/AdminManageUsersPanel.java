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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import files.DataManagement;
import objects.User;

/**
 * The AdminManageUsersPanel class contains the graphics necessary to edit the
 * profile of a separate user in the school system. This feature is only
 * accessible to administrators. In this panel, administrators have text boxes
 * they can use to edit information about the other users in their system.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class AdminManageUsersPanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;
	/** JComboBox with list of users */
	private JComboBox<String> userList;
	/** ArrayList with users */
	private ArrayList<User> users;
	/** Determines whether the password should be shown on the JPanel */
	private boolean isPasswordShown;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public AdminManageUsersPanel() {
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_MANAGE_USERS.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes the text boxes used to edit the user's information and a
	 * button to confirm the data will be sent to be edited.
	 */
	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);

		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		JLabel selectTeacher = new JLabel("Select User");
		selectTeacher.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		userList = new JComboBox<String>();
		userList.setFont(GraphicsConstants.FONT_ROBOTO_B30);

		JLabel editUsername = new JLabel("Edit Username");
		editUsername.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		JTextField givenUsername = new JTextField();
		givenUsername.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenUsername.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);

		JLabel editFirstName = new JLabel("Edit First Name");
		editFirstName.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		JTextField givenFirstName = new JTextField();
		givenFirstName.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenFirstName.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);

		JLabel editLastName = new JLabel("Edit Last Name");
		editLastName.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		JTextField givenLastName = new JTextField();
		givenLastName.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenLastName.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);

		JLabel editPassword = new JLabel("Edit Password");
		editPassword.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		JPasswordField givenPassword = new JPasswordField();
		givenPassword.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenPassword.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);

		JLabel visiblePassword = new JLabel();
		visiblePassword.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		isPasswordShown = false;

		JButton showHidePassword = new JButton("Show/Hide Password");
		GraphicsHelpers.modifyButton(showHidePassword, 500, 45);
		showHidePassword.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button hides or shows the
			 * password based on whether the password is already shown on the
			 * panel.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				String textVisiblePassword = String.valueOf(givenPassword.getPassword());

				if (isPasswordShown) {
					visiblePassword.setText("");
					isPasswordShown = false;
				} else {
					visiblePassword.setText(textVisiblePassword);
					isPasswordShown = true;
				}
			}

		});


		JButton enterEdits = new JButton("Enter Edits");
		GraphicsHelpers.modifyButton(enterEdits, 300, 45);
		enterEdits.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				User u = users.get(userList.getSelectedIndex());
				String username = givenUsername.getText();
				String firstName = givenFirstName.getText();
				String lastName = givenLastName.getText();
				String password = String.valueOf(givenPassword.getPassword());

				ArrayList<String> inputs = new ArrayList<String>();
				inputs.add(username);
				inputs.add(firstName);
				inputs.add(lastName);
				inputs.add(password);

				if (GraphicsHelpers.hasComma(inputs)) {
					JOptionPane.showMessageDialog(centerPanel, "You cannot"
							+ " have commas in any of your fields to ensure proper"
							+ " data storage.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				DataManagement.refactorUser(username, firstName, lastName, password, u.getID());
				refreshComboBox();
			}

		});

		centerPanel.add(selectTeacher);
		centerPanel.add(userList);
		centerPanel.add(editUsername);
		centerPanel.add(givenUsername);
		centerPanel.add(editFirstName);
		centerPanel.add(givenFirstName);
		centerPanel.add(editLastName);
		centerPanel.add(givenLastName);
		centerPanel.add(editPassword);
		centerPanel.add(givenPassword);
		centerPanel.add(showHidePassword);
		centerPanel.add(visiblePassword);
		centerPanel.add(enterEdits);

		sl.putConstraint(SpringLayout.WEST, selectTeacher, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, selectTeacher, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, userList, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, userList, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, editUsername, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, editUsername, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenUsername, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenUsername, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, editFirstName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, editFirstName, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenFirstName, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenFirstName, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, editLastName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, editLastName, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenLastName, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenLastName, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, editPassword, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, editPassword, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenPassword, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenPassword, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, showHidePassword, 0, SpringLayout.WEST, givenPassword);
		sl.putConstraint(SpringLayout.NORTH, showHidePassword, 600, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, visiblePassword, 0, SpringLayout.WEST, givenPassword);
		sl.putConstraint(SpringLayout.NORTH, visiblePassword, 700, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterEdits, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterEdits, 800, SpringLayout.NORTH, centerPanel);

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
	 * The refreshComboBox method refreshes the list of users.
	 */
	public void refreshComboBox() {
		userList.removeAllItems();
		users = DataManagement.getCurrentSchoolUsers();

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getID() == DataManagement.getLoggedInUser().getID()) {
				users.remove(i);
			}
		}

		if (users != null) {
			for (User u : users) {
				if (u.isAdministrator()) {
					userList.addItem(u.getFirstName() + " " + u.getLastName() + ", Administrator");
				} else if (u.isTeacher()) {
					userList.addItem(u.getFirstName() + " " + u.getLastName() + ", Teacher");
				} else if (u.isStudent()) {
					userList.addItem(u.getFirstName() + " " + u.getLastName() + ", Student");
				}
			}
		}
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "AdminManageUsersPanel [userList=" + userList + ", users=" + users + ", isPasswordShown="
				+ isPasswordShown + "]";
	}
}
