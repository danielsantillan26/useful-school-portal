package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;

import files.DataManagement;

/**
 * The TeacherEditProfilePanel class contains the graphics necessary to edit
 * their own profile in the school system. In this panel, teachers have text
 * boxes they can use to edit information about themselves.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class TeacherEditProfilePanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;
	/** Determines whether the password should be shown on the JPanel */
	private boolean isPasswordShown;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public TeacherEditProfilePanel() {
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
				String password = String.valueOf(givenPassword.getPassword());

				if (GraphicsHelpers.isPasswordValid(password)) {
					DataManagement.changePassword(password);
					givenPassword.setText("");
				} else {
					JOptionPane.showMessageDialog(centerPanel, "Password must\n"
							+ "- Be between 10 and 50 characters\n" +
							"- Have no spaces\n" +
							"- Have both an uppercase and lowercase letter\n" +
							"- Have a number", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		centerPanel.add(enterPassword);
		centerPanel.add(givenPassword);
		centerPanel.add(showHidePassword);
		centerPanel.add(visiblePassword);
		centerPanel.add(confirm);

		sl.putConstraint(SpringLayout.WEST, enterPassword, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterPassword, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenPassword, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenPassword, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, showHidePassword, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, showHidePassword, 200, SpringLayout.NORTH,centerPanel);
		sl.putConstraint(SpringLayout.WEST, visiblePassword, 0, SpringLayout.WEST, givenPassword);
		sl.putConstraint(SpringLayout.NORTH, visiblePassword, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, confirm, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, confirm, 400, SpringLayout.NORTH, centerPanel);

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
		return "TeacherEditProfilePanel [isPasswordShown=" + isPasswordShown + "]";
	}

}
