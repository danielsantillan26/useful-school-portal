package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import files.DataManagement;
import objects.User;

/**
 * The AdminHomepagePanel class contains the graphics necessary to establish a
 * hub for administrators upon login. They can access all the pages they need
 * to use and log out if necessary.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class AdminHomepagePanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;
	/** The center portion of the panel */
	private JPanel centerPanel;
	/** The layout of the panel */
	private SpringLayout sl;
	/** A welcome message that is personalized by user */
	private JLabel welcome;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public AdminHomepagePanel() {
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_ADMIN_HOME.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes setting up the welcome message.
	 */
	private void prepareCenterPanel() {
		sl = new SpringLayout();
		centerPanel = new JPanel(sl);

		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		welcome = new JLabel();
		welcome.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		centerPanel.add(welcome);
		sl.putConstraint(SpringLayout.WEST, welcome, 350, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, welcome, 50, SpringLayout.NORTH, centerPanel);

		add(centerPanel, BorderLayout.CENTER);
	}


	/**
	 * The addChangePageButtons method adds the buttons from this panel that
	 * swap between pages of the program.
	 * 
	 * @param manageSchedule	Button that goes to the manage schedule page
	 * @param addUsers			Button that goes to the add users page
	 * @param addClasses		Button that goes to the add classes page
	 * @param deleteUsers		Button that goes to the delete users page
	 * @param deleteClasses		Button that goes to the delete classes page
	 * @param manageTeachers	Button that goes to the manage teachers page
	 * @param manageStudents	Button that goes to the manage students page
	 * @param manageClasses		Button that goes to the manage classes page
	 * @param editProfile		Button that goes to the edit profile page
	 * @param logOut			Button that logs out user and returns to home
	 */
	public void addChangePageButtons(JButton manageSchedule, JButton addUsers,
			JButton addClasses, JButton deleteUsers, JButton deleteClasses,
			JButton manageTeachers, JButton manageStudents, JButton manageClasses,
			JButton editProfile, JButton logOut) {
		centerPanel.add(manageSchedule);
		centerPanel.add(addUsers);
		centerPanel.add(addClasses);
		centerPanel.add(deleteUsers);
		centerPanel.add(deleteClasses);
		centerPanel.add(manageTeachers);
		centerPanel.add(manageStudents);
		centerPanel.add(manageClasses);
		centerPanel.add(editProfile);

		sl.putConstraint(SpringLayout.WEST, manageSchedule, 200, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, manageSchedule, 150, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, addUsers, 650, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, addUsers, 150, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, addClasses, 1100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, addClasses, 150, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, deleteUsers, 200, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, deleteUsers, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, deleteClasses, 650, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, deleteClasses, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, manageTeachers, 1100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, manageTeachers, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, manageStudents, 200, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, manageStudents, 450, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, manageClasses, 650, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, manageClasses, 450, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, editProfile, 1100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, editProfile, 450, SpringLayout.NORTH, centerPanel);

		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(logOut);
		add(southPanel, BorderLayout.SOUTH);

	}


	/**
	 * The updateUsername method updates the welcome message for the logged in
	 * user.
	 */
	public void updateUsername() {
		User u = DataManagement.getLoggedInUser();
		String welcomeText = "Welcome, " + u.getFirstName() + " " + u.getLastName() + "!";
		welcome.setText(welcomeText);
		repaint();
	}

	
	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "AdminHomepagePanel [centerPanel=" + centerPanel + ", sl=" + sl + ", welcome=" + welcome + "]";
	}
}
