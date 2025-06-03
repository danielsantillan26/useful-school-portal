package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * The LoginPanel class contains the graphics necessary to log a user into the
 * school system. In this panel, users implement text boxes to add their
 * username and password.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class LoginPanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;
	/** Text field with given username */
	private JTextField givenUsername;
	/** Text field with given password */
	private JPasswordField givenPassword;
	/** Determines whether the password should be shown on the JPanel */
	private boolean isPasswordShown;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public LoginPanel() {
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_LOGIN.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes the text fields for adding usernames and passwords along
	 * with instruction labels. The button to show and hide password is also
	 * set up.
	 */
	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);

		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		JLabel enterUsername = new JLabel("Enter Username:");
		enterUsername.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		givenUsername = new JTextField();
		givenUsername.setMinimumSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenUsername.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenUsername.setFont(GraphicsConstants.FONT_ROBOTO_B30);

		JLabel enterPassword = new JLabel("Enter Password:");
		enterPassword.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		givenPassword = new JPasswordField();
		givenPassword.setMinimumSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenPassword.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenPassword.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		JLabel visiblePassword = new JLabel();
		visiblePassword.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		isPasswordShown = false;

		JButton showHidePassword = new JButton("Show/Hide Password");
		GraphicsHelpers.modifyButton(showHidePassword, 500, 45);

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
				String textVisiblePassword = String.valueOf(givenPassword.getPassword());

				if (isPasswordShown) {
					visiblePassword.setText("");
					isPasswordShown = false;
				} else {
					visiblePassword.setText(textVisiblePassword);
					isPasswordShown = true;
				}

			}

		};

		showHidePassword.addActionListener(al);

		centerPanel.add(enterUsername);
		centerPanel.add(givenUsername);
		centerPanel.add(enterPassword);
		centerPanel.add(givenPassword);
		centerPanel.add(visiblePassword);
		centerPanel.add(showHidePassword);

		sl.putConstraint(SpringLayout.WEST, enterUsername, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterUsername, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenUsername, 600, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenUsername, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterPassword, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterPassword, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenPassword, 600, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenPassword, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, visiblePassword, 0, SpringLayout.WEST, givenPassword);
		sl.putConstraint(SpringLayout.NORTH, visiblePassword, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, showHidePassword, 600, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, showHidePassword, 500, SpringLayout.NORTH, centerPanel);

		add(centerPanel, BorderLayout.CENTER);

	}


	/**
	 * The addChangePageButtons method adds the buttons from this panel that
	 * swap between pages of the program.
	 * 
	 * @param login		Button that logs a user in
	 * @param goHome	Button that returns to the homepage
	 */
	public void addChangePageButtons(JButton login, JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		southPanel.add(login);
		southPanel.add(goHome);

		add(southPanel, BorderLayout.SOUTH);
	}


	/**
	 * The getUsername method gets the username from the text field.
	 * 
	 * @return		The username
	 */
	public String getUsername() {
		return givenUsername.getText();
	}


	/**
	 * The getPassword method gets the password from the text field.
	 * 
	 * @return		The password
	 */
	public String getPassword() {
		return String.valueOf(givenPassword.getPassword());
	}


	/**
	 * The clearText method clears all the text from the fields.
	 */
	public void clearText() {
		givenUsername.setText("");
		givenPassword.setText("");
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "LoginPanel [givenUsername=" + givenUsername + ", givenPassword=" + givenPassword + ", isPasswordShown="
				+ isPasswordShown + "]";
	}
}
