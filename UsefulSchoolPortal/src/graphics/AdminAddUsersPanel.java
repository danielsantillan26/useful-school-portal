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

/**
 * The AdminAddUsersPanel class contains the graphics necessary to add users to
 * a school system. This feature is only accessible to administrators. In this
 * panel, administrators add new users by entering crucial user information that
 * gets sent in for data creation.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class AdminAddUsersPanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;
	/** Determines whether the password should be shown on the JPanel */
	private boolean isPasswordShown;

	
	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public AdminAddUsersPanel() {
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_ADD_USERS.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes the JLabels asking for user information and the JTextFields
	 * that allow adminitrators to enter user information. It also includes the
	 * buttons to show/hide password and create user.
	 */
	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);

		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		JLabel enterRole = new JLabel("Enter Role:");
		enterRole.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		JComboBox<String> givenRole = new JComboBox<String>();
		givenRole.addItem("Administrator");
		givenRole.addItem("Teacher");
		givenRole.addItem("Student");
		givenRole.setFont(GraphicsConstants.FONT_ROBOTO_B30);

		JLabel enterUsername = new JLabel("Enter Username:");
		enterUsername.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		JTextField givenUsername = new JTextField();
		givenUsername.setMinimumSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenUsername.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenUsername.setFont(GraphicsConstants.FONT_ROBOTO_B30);

		JLabel enterFirstName = new JLabel("Enter First Name:");
		enterFirstName.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		JTextField givenFirstName = new JTextField();
		givenFirstName.setMinimumSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenFirstName.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenFirstName.setFont(GraphicsConstants.FONT_ROBOTO_B30);

		JLabel enterLastName = new JLabel("Enter Last Name:");
		enterLastName.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		JTextField givenLastName = new JTextField();
		givenLastName.setMinimumSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenLastName.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenLastName.setFont(GraphicsConstants.FONT_ROBOTO_B30);

		JLabel enterPassword = new JLabel("Enter Password:");
		enterPassword.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		JPasswordField givenPassword = new JPasswordField();
		givenPassword.setMinimumSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenPassword.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenPassword.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		JLabel visiblePassword = new JLabel();
		visiblePassword.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		isPasswordShown = false;

		JButton showHidePassword = new JButton("Show/Hide Password");
		GraphicsHelpers.modifyButton(showHidePassword, 500, 45);

		JButton createAccount = new JButton("Create Account");
		GraphicsHelpers.modifyButton(createAccount, 500, 45);

		ActionListener al = new ActionListener() {

			/**
			 * The actionPerformed method for this panel has the code run when
			 * a button in this panel is pressed. If the show/hide password 
			 * button is pressed, the password is either shown or hidden. If the
			 * confirm button is pressed, the user information is passed to data
			 * to be created.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == showHidePassword) {
					String textVisiblePassword = String.valueOf(givenPassword.getPassword());

					if (isPasswordShown) {
						visiblePassword.setText("");
						isPasswordShown = false;
					} else {
						visiblePassword.setText(textVisiblePassword);
						isPasswordShown = true;
					}

				} else if (e.getSource() == createAccount) {
					String username = givenUsername.getText();
					String firstName = givenFirstName.getText();
					String lastName = givenLastName.getText();
					String password = String.valueOf(givenPassword.getPassword());
					
					if (username.strip().equals("") || firstName.strip().equals("") 
							|| lastName.strip().equals("") || password.strip().equals("")) {
						JOptionPane.showMessageDialog(centerPanel, "You " +
								"have an empty field somewhere. Please fill " +
								"out all values.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
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

					
					if (!GraphicsHelpers.isPasswordValid(password)) {
						JOptionPane.showMessageDialog(centerPanel, "Password must\n"
								+ "- Be between 10 and 50 characters\n" +
								"- Have no spaces\n" +
								"- Have both an uppercase and lowercase letter\n" +
								"- Have a number", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					switch (givenRole.getSelectedIndex()) {
					case 0:
						DataManagement.addNewAdministrator(username, firstName, lastName, password);
						break;
					case 1:
						DataManagement.addNewTeacher(username, firstName, lastName, password);
						break;
					case 2:
						DataManagement.addNewStudent(username, firstName, lastName, password);
						break;
					}
					
					givenUsername.setText("");
					givenFirstName.setText("");
					givenLastName.setText("");
					givenPassword.setText("");
				}
			}

		};

		showHidePassword.addActionListener(al);
		createAccount.addActionListener(al);

		centerPanel.add(enterRole);
		centerPanel.add(givenRole);
		centerPanel.add(enterUsername);
		centerPanel.add(givenUsername);
		centerPanel.add(enterFirstName);
		centerPanel.add(givenFirstName);
		centerPanel.add(enterLastName);
		centerPanel.add(givenLastName);
		centerPanel.add(enterPassword);
		centerPanel.add(givenPassword);
		centerPanel.add(visiblePassword);
		centerPanel.add(showHidePassword);
		centerPanel.add(createAccount);

		sl.putConstraint(SpringLayout.WEST, enterRole, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterRole, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenRole, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenRole, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterUsername, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterUsername, 175, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenUsername, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenUsername, 175, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterFirstName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterFirstName, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenFirstName, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenFirstName, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterLastName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterLastName, 425, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenLastName, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenLastName, 425, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterPassword, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterPassword, 550, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenPassword, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenPassword, 550, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, visiblePassword, 0, SpringLayout.WEST, givenPassword);
		sl.putConstraint(SpringLayout.NORTH, visiblePassword, 725, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, showHidePassword, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, showHidePassword, 675, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, createAccount, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, createAccount, 775, SpringLayout.NORTH, centerPanel);

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
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "AdminAddUsersPanel [isPasswordShown=" + isPasswordShown + "]";
	}

}
