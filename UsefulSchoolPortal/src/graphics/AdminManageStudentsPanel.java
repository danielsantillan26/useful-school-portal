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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import files.DataManagement;
import objects.Student;

public class AdminManageStudentsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> studentList;
	private ArrayList<Student> students;
	private boolean isPasswordShown;
	
	public AdminManageStudentsPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}


	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_MANAGE_STUDENTS.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);
		
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		
		JLabel selectStudent = new JLabel("Select Student");
		selectStudent.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		studentList = new JComboBox<String>();
		studentList.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		
		JLabel editUsername = new JLabel("Edit Username");
		editUsername.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		JTextField givenUsername = new JTextField();
		givenUsername.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenUsername.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		
		JLabel editFirstName = new JLabel("Edit First Name");
		editFirstName.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		JTextField givenFirstName = new JTextField();
		givenFirstName.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenFirstName.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		
		JLabel editLastName = new JLabel("Edit Last Name");
		editLastName.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		JTextField givenLastName = new JTextField();
		givenLastName.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenLastName.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		
		JLabel editPassword = new JLabel("Edit Password");
		editPassword.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		JPasswordField givenPassword = new JPasswordField();
		givenPassword.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenPassword.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		
		JLabel visiblePassword = new JLabel();
		visiblePassword.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		isPasswordShown = false;
		
		JButton showHidePassword = new JButton("Show/Hide Password");
		GraphicsHelpers.modifyButton(showHidePassword, 500, 45);
		showHidePassword.addActionListener(new ActionListener() {

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
		
		
		JButton enterEdits = new JButton("Enter Edits");
		GraphicsHelpers.modifyButton(enterEdits, 300, 45);
		enterEdits.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Student s = students.get(studentList.getSelectedIndex());
				String username = givenUsername.getText();
				String firstName = givenFirstName.getText();
				String lastName = givenLastName.getText();
				String password = String.valueOf(givenPassword.getPassword());
				
				ArrayList<String> inputs = new ArrayList<String>();
				inputs.add(username);
				inputs.add(firstName);
				inputs.add(lastName);
				inputs.add(password);
				
				if (GraphicsHelpers.hasComma(inputs)) {
					JOptionPane.showMessageDialog(centerPanel, "You cannot"
							+ " have commas in any of your fields to ensure proper"
							+ " data storage.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				DataManagement.refactorUser(username, firstName, lastName, password, s.getID());
				refreshComboBox();
			}
			
		});
		
		centerPanel.add(selectStudent);
		centerPanel.add(studentList);
		centerPanel.add(editUsername);
		centerPanel.add(givenUsername);
		centerPanel.add(editFirstName);
		centerPanel.add(givenFirstName);
		centerPanel.add(editLastName);
		centerPanel.add(givenLastName);
		centerPanel.add(editPassword);
		centerPanel.add(givenPassword);
		centerPanel.add(showHidePassword);
		centerPanel.add(visiblePassword);
		centerPanel.add(enterEdits);
		
		sl.putConstraint(SpringLayout.WEST, selectStudent, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, selectStudent, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, studentList, 100, SpringLayout.EAST, selectStudent);
		sl.putConstraint(SpringLayout.NORTH, studentList, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, editUsername, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, editUsername, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenUsername, 100, SpringLayout.EAST, editUsername);
		sl.putConstraint(SpringLayout.NORTH, givenUsername, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, editFirstName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, editFirstName, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenFirstName, 100, SpringLayout.EAST, editFirstName);
		sl.putConstraint(SpringLayout.NORTH, givenFirstName, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, editLastName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, editLastName, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenLastName, 100, SpringLayout.EAST, editLastName);
		sl.putConstraint(SpringLayout.NORTH, givenLastName, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, editPassword, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, editPassword, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenPassword, 100, SpringLayout.EAST, editPassword);
		sl.putConstraint(SpringLayout.NORTH, givenPassword, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, showHidePassword, 0, SpringLayout.WEST, givenPassword);
		sl.putConstraint(SpringLayout.NORTH, showHidePassword, 600, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, visiblePassword, 0, SpringLayout.WEST, givenPassword);
		sl.putConstraint(SpringLayout.NORTH, visiblePassword, 700, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterEdits, 600, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterEdits, 800, SpringLayout.NORTH, centerPanel);
		
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
