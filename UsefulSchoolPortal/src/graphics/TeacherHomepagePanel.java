package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class TeacherHomepagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private SpringLayout sl;
	private JPanel centerPanel;
	
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
	}
	

	public void addChangePageButtons(JButton classHomepage, JButton editClassHomepage, 
			JButton viewRosters, JButton manageAssignments, JButton gradeAssignments,
			JButton manageStudents, JButton editProfile, JButton logOut) {
		centerPanel.add(classHomepage);
		centerPanel.add(editClassHomepage);
		centerPanel.add(viewRosters);
		centerPanel.add(manageAssignments);
		centerPanel.add(gradeAssignments);
		centerPanel.add(manageStudents);
		centerPanel.add(editProfile);
		
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(logOut);
		add(southPanel, BorderLayout.SOUTH);
	}

}
