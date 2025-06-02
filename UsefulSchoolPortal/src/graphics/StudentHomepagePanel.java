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

public class StudentHomepagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private SpringLayout sl;
	private JPanel centerPanel;
	private JLabel welcome;
	
	public StudentHomepagePanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}
	
	
	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_STUDENT_HOMEPAGE.png")));
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
	
	
	public void addChangePageButtons(JButton classHomepage, JButton viewRosters,
			JButton viewGrades, JButton gradingSimulator, JButton editProfile, JButton viewInfractions,
			JButton logOut) {
		centerPanel.add(classHomepage);
		centerPanel.add(viewRosters);
		centerPanel.add(viewGrades);
		centerPanel.add(gradingSimulator);
		centerPanel.add(editProfile);
		centerPanel.add(viewInfractions);
		
		sl.putConstraint(SpringLayout.WEST, classHomepage, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, classHomepage, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, viewRosters, 700, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, viewRosters, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, viewGrades, 1150, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, viewGrades, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, gradingSimulator, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, gradingSimulator, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, viewInfractions, 1150, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, viewInfractions, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, editProfile, 700, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, editProfile, 300, SpringLayout.NORTH, centerPanel);
		
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
