package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class AdminHomepagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel centerPanel;
	private SpringLayout sl;

	public AdminHomepagePanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}


	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_ADMIN_HOME.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	private void prepareCenterPanel() {
		sl = new SpringLayout();
		centerPanel = new JPanel(sl);

		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		//TODO: I need to get the username
		JLabel welcome = new JLabel("Welcome, USER!");
		welcome.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		centerPanel.add(welcome);
		sl.putConstraint(SpringLayout.WEST, welcome, 650, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, welcome, 50, SpringLayout.NORTH, centerPanel);

		add(centerPanel, BorderLayout.CENTER);
	}


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

}
