package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import files.DataManagement;
import objects.Student;

public class AdminStudentInfractionsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> studentList;
	private ArrayList<Student> students;
	
	public AdminStudentInfractionsPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}


	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_MANAGE_STUDENTS.png")));
		// TODO: Must fix the label here too.
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);
		
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		
		JLabel selectStudent = new JLabel("Select Student");
		selectStudent.setFont(GraphicsConstants.FONT_ROBOTO_B40);
		
		studentList = new JComboBox<String>();
		studentList.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		
		JButton loadInfractionData = new JButton("Load Infraction Data");
		GraphicsHelpers.modifyButton(loadInfractionData, 420, 45);
		loadInfractionData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		JLabel enterInfraction = new JLabel("Enter/Edit Infraction:");
		enterInfraction.setFont(GraphicsConstants.FONT_ROBOTO_B40);
		
		JComboBox<String> selectInfraction = new JComboBox<String>();
		selectInfraction.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		selectInfraction.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		
		JLabel enterReason = new JLabel("Enter Reason");
		enterReason.setFont(GraphicsConstants.FONT_ROBOTO_B40);
		
		JTextArea givenReason = new JTextArea();
		givenReason.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenReason.setPreferredSize(new Dimension((int)(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT.getWidth()), 300));
		givenReason.setLineWrap(true);
		givenReason.setWrapStyleWord(true);
		
		JButton updateInfraction = new JButton("Update Infraction");
		GraphicsHelpers.modifyButton(updateInfraction, 350, 45);
		updateInfraction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		centerPanel.add(selectStudent);
		centerPanel.add(studentList);
		centerPanel.add(loadInfractionData);
		centerPanel.add(enterInfraction);
		centerPanel.add(selectInfraction);
		centerPanel.add(enterReason);
		centerPanel.add(givenReason);
		centerPanel.add(updateInfraction);
		
		
		sl.putConstraint(SpringLayout.WEST, selectStudent, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, selectStudent, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, studentList, 100, SpringLayout.EAST, selectStudent);
		sl.putConstraint(SpringLayout.NORTH, studentList, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, loadInfractionData, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, loadInfractionData, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterInfraction, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterInfraction, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, selectInfraction, 100, SpringLayout.EAST, enterInfraction);
		sl.putConstraint(SpringLayout.NORTH, selectInfraction, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterReason, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterReason, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenReason, 100, SpringLayout.EAST, enterReason);
		sl.putConstraint(SpringLayout.NORTH, givenReason, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, updateInfraction, 400, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, updateInfraction, 800, SpringLayout.NORTH, centerPanel);
		
		
		
		add(centerPanel, BorderLayout.CENTER);
	}
	
	
	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}
	
	
	public void refreshComboBox() {
		studentList.removeAllItems();
		students = DataManagement.getCurrentSchoolStudents();

		if (students != null) {
			for (Student s : students) {
				studentList.addItem(s.getFirstName() + " " + s.getLastName());
			}
		}
	}

}
