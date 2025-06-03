package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import files.DataManagement;

/**
 * The AdminEditProfilePanel class contains the graphics necessary to edit their
 * own profile in the school system. In this panel, administrators have text
 * boxes they can use to edit information about themselves.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class AdminEditProfilePanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;
	/** Determines whether the password should be shown on the JPanel */
	private boolean isPasswordShown;

	
	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public AdminEditProfilePanel() {
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_EDIT_PROFILE.png")));
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
		
		JButton confirm = new JButton("Confirm");
		GraphicsHelpers.modifyButton(confirm, 250, 45);
		confirm.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button takes in the data from
			 * the text boxes. If the confirm button is pressed, the user
			 * information is passed to data for creation. Invalid input (i.e.,
			 * having commas in any information) is handled, ending the task.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
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
				
				DataManagement.refactorYourself(username, firstName, lastName, password);
				
				givenUsername.setText("");
				givenFirstName.setText("");
				givenLastName.setText("");
				givenPassword.setText("");
			}
			
		});
		
		centerPanel.add(enterUsername);
		centerPanel.add(givenUsername);
		centerPanel.add(enterFirstName);
		centerPanel.add(givenFirstName);
		centerPanel.add(enterLastName);
		centerPanel.add(givenLastName);
		centerPanel.add(enterPassword);
		centerPanel.add(givenPassword);
		centerPanel.add(showHidePassword);
		centerPanel.add(visiblePassword);
		centerPanel.add(confirm);

		sl.putConstraint(SpringLayout.WEST, enterUsername, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterUsername, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenUsername, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenUsername, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterFirstName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterFirstName, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenFirstName, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenFirstName, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterLastName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterLastName, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenLastName, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenLastName, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterPassword, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterPassword, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenPassword, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenPassword, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, showHidePassword, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, showHidePassword, 500, SpringLayout.NORTH,centerPanel);
		sl.putConstraint(SpringLayout.WEST, visiblePassword, 0, SpringLayout.WEST, givenPassword);
		sl.putConstraint(SpringLayout.NORTH, visiblePassword, 600, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, confirm, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, confirm, 700, SpringLayout.NORTH, centerPanel);

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
		return "AdminEditProfilePanel [isPasswordShown=" + isPasswordShown + "]";
	}
}
