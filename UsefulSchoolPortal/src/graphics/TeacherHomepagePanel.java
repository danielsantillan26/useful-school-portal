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
 * The TeacherHomepagePanel class contains the graphics necessary to establish a
 * hub for teachers upon login. They can access all the pages they need to use
 * and log out if necessary.
 * 
 * @author Daniel Santllan
 * @version 1.0
 */
public class TeacherHomepagePanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;
	/** The layout of the panel */
	private SpringLayout sl;
	/** The center portion of the panel */
	private JPanel centerPanel;
	/** A welcome message that is personalized by user */
	private JLabel welcome;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public TeacherHomepagePanel() {
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_TEACHER_HOME.png")));
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
	 * @param classHomepage		Button that goes to the class homepage page
	 * @param editClassHomepage	Button that goes to the edit class homepage page
	 * @param manageClasses		Button that goes to the manage classes page
	 * @param viewRosters		Button that goes to the view rosters page
	 * @param manageAssignments	Button that goes to the manage assignments page
	 * @param gradeAssignments	Button that goes to the grade assignments page
	 * @param manageStudents	Button that goes to the infract students page
	 * @param editProfile		Button that goes to the edit profile page
	 * @param logOut			Button that logs out and returns to home
	 */
	public void addChangePageButtons(JButton classHomepage, JButton editClassHomepage, 
			JButton manageClasses, JButton viewRosters, JButton manageAssignments, 
			JButton gradeAssignments, JButton manageStudents, JButton editProfile, 
			JButton logOut) {
		centerPanel.add(classHomepage);
		centerPanel.add(editClassHomepage);
		centerPanel.add(manageClasses);
		centerPanel.add(viewRosters);
		centerPanel.add(manageAssignments);
		centerPanel.add(gradeAssignments);
		centerPanel.add(manageStudents);
		centerPanel.add(editProfile);

		sl.putConstraint(SpringLayout.WEST, classHomepage, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, classHomepage, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, editClassHomepage, 700, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, editClassHomepage, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, manageClasses, 1300, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, manageClasses, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, viewRosters, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, viewRosters, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, manageAssignments, 700, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, manageAssignments, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, gradeAssignments, 1300, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, gradeAssignments, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, manageStudents, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, manageStudents, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, editProfile, 700, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, editProfile, 400, SpringLayout.NORTH, centerPanel);

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
		return "TeacherHomepagePanel [sl=" + sl + ", centerPanel=" + centerPanel + ", welcome=" + welcome + "]";
	}
}
