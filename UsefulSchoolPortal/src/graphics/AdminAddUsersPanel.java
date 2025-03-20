package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import files.UserActions;
import helpermethods.Helpers;

public class AdminAddUsersPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean isPasswordShown;

	public AdminAddUsersPanel() {
		setLayout(new BorderLayout());
		addNorthPanel();
		prepareCenterPanel();
	}

	private void addNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_ADD_USERS.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


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
		showHidePassword.setPreferredSize(new Dimension(500, 45));
		showHidePassword.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		showHidePassword.setForeground(Color.WHITE);
		showHidePassword.setFont(GraphicsConstants.FONT_BUTTON);

		JButton createAccount = new JButton("Create Account");
		createAccount.setPreferredSize(new Dimension(500, 45));
		createAccount.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		createAccount.setForeground(Color.WHITE);
		createAccount.setFont(GraphicsConstants.FONT_BUTTON);

		ActionListener al = new ActionListener() {

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
					
					
					boolean hasComma = false;
					for (int i = 0; i < username.length(); i++) {
						if (username.substring(i, i+1).equals(",")) {
							hasComma = true;
						}
					}


					for (int i = 0; i < firstName.length(); i++) {
						if (firstName.substring(i, i+1).equals(",")) {
							hasComma = true;
						}
					}


					for (int i = 0; i < lastName.length(); i++) {
						if (lastName.substring(i, i+1).equals(",")) {
							hasComma = true;
						}
					}

					for (int i = 0; i < password.length(); i++) {
						if (password.substring(i, i+1).equals(",")) {
							hasComma = true;
						}
					}

					if (hasComma) {
						JOptionPane.showMessageDialog(centerPanel, "You cannot"
								+ " have commas in any of your fields to ensure proper"
								+ " data storage.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					
					if (!Helpers.isPasswordValid(password)) {
						JOptionPane.showMessageDialog(centerPanel, "Password must\n"
								+ "- Be between 10 and 50 characters\n" +
								"- Have no spaces\n" +
								"- Have both an uppercase and lowercase letter\n" +
								"- Have a number", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					switch (givenRole.getSelectedIndex()) {
					case 0:
						UserActions.addAdministrator(username, firstName, lastName, password);
						break;
					case 1:
						UserActions.addTeacher(username, firstName, lastName, password);
						break;
					case 2:
						UserActions.addStudent(username, firstName, lastName, password);
						break;
					}
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
		sl.putConstraint(SpringLayout.NORTH, enterRole, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenRole, 150, SpringLayout.EAST, enterRole);
		sl.putConstraint(SpringLayout.NORTH, givenRole, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterUsername, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterUsername, 225, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenUsername, 150, SpringLayout.EAST, enterUsername);
		sl.putConstraint(SpringLayout.NORTH, givenUsername, 225, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterFirstName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterFirstName,350, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenFirstName, 150, SpringLayout.EAST, enterFirstName);
		sl.putConstraint(SpringLayout.NORTH, givenFirstName, 350, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterLastName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterLastName, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenLastName, 150, SpringLayout.EAST, enterLastName);
		sl.putConstraint(SpringLayout.NORTH, givenLastName, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterPassword, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterPassword, 625, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenPassword, 150, SpringLayout.EAST, enterPassword);
		sl.putConstraint(SpringLayout.NORTH, givenPassword, 625, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, visiblePassword, 0, SpringLayout.WEST, givenPassword);
		sl.putConstraint(SpringLayout.NORTH, visiblePassword, 700, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, showHidePassword, 610, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, showHidePassword, 750, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, createAccount, 610, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, createAccount, 850, SpringLayout.NORTH, centerPanel);

		add(centerPanel, BorderLayout.CENTER);
	}

}
