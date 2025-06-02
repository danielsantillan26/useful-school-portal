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
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import files.DataManagement;
import objects.Infraction;
import objects.Student;

public class AdminStudentInfractionsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> studentList;
	private ArrayList<Student> students;
	private JComboBox<String> infractionList;
	private ArrayList<Infraction> infractions;
	
	public AdminStudentInfractionsPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}


	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_INFRACT_STUDENTS.png")));
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
		
		JLabel enterInfraction = new JLabel("Enter/Edit Infraction:");
		enterInfraction.setFont(GraphicsConstants.FONT_ROBOTO_B40);
		
		infractionList = new JComboBox<String>();
		infractionList.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		infractionList.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		
		JLabel enterInfractionName = new JLabel("Infraction Name:");
		enterInfractionName.setFont(GraphicsConstants.FONT_ROBOTO_B40);
		
		JTextField givenInfractionName = new JTextField();
		givenInfractionName.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenInfractionName.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		
		JLabel enterReason = new JLabel("Enter Reason");
		enterReason.setFont(GraphicsConstants.FONT_ROBOTO_B40);
		
		JTextArea givenReason = new JTextArea();
		givenReason.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenReason.setPreferredSize(new Dimension((int)(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT.getWidth()), 250));
		givenReason.setLineWrap(true);
		givenReason.setWrapStyleWord(true);
		
		JButton loadInfractionData = new JButton("Load Infraction Data");
		GraphicsHelpers.modifyButton(loadInfractionData, 420, 45);
		loadInfractionData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = infractionList.getSelectedIndex();
				
				givenInfractionName.setText("");
				givenReason.setText("");
				
				if (index != 0) {
					
				} else {
					
				}
			}
			
		});
		
		JButton updateInfraction = new JButton("Update Infraction");
		GraphicsHelpers.modifyButton(updateInfraction, 350, 45);
		updateInfraction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		JButton deleteInfraction = new JButton("Delete Infraction");
		GraphicsHelpers.modifyButton(deleteInfraction, 350, 45);
		deleteInfraction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		centerPanel.add(selectStudent);
		centerPanel.add(studentList);
		centerPanel.add(loadInfractionData);
		centerPanel.add(enterInfraction);
		centerPanel.add(infractionList);
		centerPanel.add(enterInfractionName);
		centerPanel.add(givenInfractionName);
		centerPanel.add(enterReason);
		centerPanel.add(givenReason);
		centerPanel.add(updateInfraction);
		centerPanel.add(deleteInfraction);
		
		sl.putConstraint(SpringLayout.WEST, selectStudent, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, selectStudent, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, studentList, 100, SpringLayout.EAST, selectStudent);
		sl.putConstraint(SpringLayout.NORTH, studentList, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, loadInfractionData, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, loadInfractionData, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterInfraction, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterInfraction, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, infractionList, 100, SpringLayout.EAST, enterInfraction);
		sl.putConstraint(SpringLayout.NORTH, infractionList, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterInfractionName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterInfractionName, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenInfractionName, 100, SpringLayout.EAST, enterInfractionName);
		sl.putConstraint(SpringLayout.NORTH, givenInfractionName, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterReason, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterReason, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenReason, 100, SpringLayout.EAST, enterReason);
		sl.putConstraint(SpringLayout.NORTH, givenReason, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, updateInfraction, 400, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, updateInfraction, 800, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, deleteInfraction, 50, SpringLayout.EAST, updateInfraction);
		sl.putConstraint(SpringLayout.NORTH, deleteInfraction, 800, SpringLayout.NORTH, centerPanel);
		
		
		
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
		
		infractionList.removeAllItems();
		infractions = DataManagement.getCurrentSchoolInfractions();
		infractionList.addItem("-- New Infraction --");
		
		if (infractions != null) {
			for (Infraction i : infractions) {
				
			}
		}
	}

}
