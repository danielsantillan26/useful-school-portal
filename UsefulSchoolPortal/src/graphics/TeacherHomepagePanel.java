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

public class TeacherHomepagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private SpringLayout sl;
	private JPanel centerPanel;
	private JLabel welcome;
	
	public TeacherHomepagePanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}
	
	
	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_TEACHER_HOME.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


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
	
	
	public void updateUsername() {
		User u = DataManagement.getLoggedInUser();
		String welcomeText = "Welcome, " + u.getFirstName() + " " + u.getLastName() + "!";
		welcome.setText(welcomeText);
		repaint();
	}

}
