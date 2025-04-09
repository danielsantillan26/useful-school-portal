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
import javax.swing.SpringLayout;

import files.DataManagement;
import objects.SchoolClass;
import objects.Student;
import objects.Teacher;

public class AdminManageClassesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> viewClasses;
	private JComboBox<String> viewNonClassTeachers;
	private JComboBox<String> viewClassTeachers;
	private JComboBox<String> viewNonClassStudents;
	private JComboBox<String> viewClassStudents;
	private ArrayList<SchoolClass> schoolClasses;
	private ArrayList<Teacher> nonClassTeachers;
	private ArrayList<Teacher> classTeachers;
	private ArrayList<Student> nonClassStudents;
	private ArrayList<Student> classStudents;
	private int classID;
	
	public AdminManageClassesPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}
	
	
	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_MANAGE_CLASSES.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}
	
	
	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);
		
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		
		JLabel selectClass = new JLabel("Select Class:");
		selectClass.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		viewClasses = new JComboBox<String>();
		viewClasses.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		
		JButton update = new JButton("Load Data");
		GraphicsHelpers.modifyButton(update, 220, 45);
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (viewClasses.getSelectedIndex() != 0) {
					int selectedClassID = schoolClasses.get(viewClasses.getSelectedIndex() - 1).getClassID();
					classID = selectedClassID;
					
					refreshOtherBoxes();
				}
			}
		});
		
		JLabel addTeacher = new JLabel("Add Teacher:");
		addTeacher.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		viewNonClassTeachers = new JComboBox<String>();
		viewNonClassTeachers.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		
		JLabel removeTeacher = new JLabel("Remove Teacher:");
		removeTeacher.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		viewClassTeachers = new JComboBox<String>();
		viewClassTeachers.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		
		JLabel addStudent = new JLabel("Add Student:");
		addStudent.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		viewNonClassStudents = new JComboBox<String>();
		viewNonClassStudents.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		
		JLabel removeStudent = new JLabel("Remove Student:");
		removeStudent.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		viewClassStudents = new JComboBox<String>();
		viewClassStudents.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		
		JButton confirm = new JButton("Confirm");
		GraphicsHelpers.modifyButton(confirm, 250, 45);
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (viewClassTeachers.getSelectedIndex() != 0) {
					DataManagement.manageTeacherInClass(classID, classTeachers.get(viewClassTeachers.getSelectedIndex() - 1).getID(), true);
				}
				
				if (viewNonClassTeachers.getSelectedIndex() != 0) {
					DataManagement.manageTeacherInClass(classID, nonClassTeachers.get(viewNonClassTeachers.getSelectedIndex() - 1).getID(), false);
				}
				
				if (viewClassStudents.getSelectedIndex() != 0) {
					DataManagement.manageStudentInClass(classID, classStudents.get(viewClassStudents.getSelectedIndex() - 1).getID(), true);
				}
				
				if (viewNonClassStudents.getSelectedIndex() != 0) {
					DataManagement.manageStudentInClass(classID, nonClassStudents.get(viewNonClassStudents.getSelectedIndex() - 1).getID(), false);
				}
				
				refreshOtherBoxes();
			}
			
		});
		
		centerPanel.add(selectClass);
		centerPanel.add(viewClasses);
		centerPanel.add(update);
		centerPanel.add(addTeacher);
		centerPanel.add(viewNonClassTeachers);
		centerPanel.add(removeTeacher);
		centerPanel.add(viewClassTeachers);
		centerPanel.add(addStudent);
		centerPanel.add(viewNonClassStudents);
		centerPanel.add(removeStudent);
		centerPanel.add(viewClassStudents);
		centerPanel.add(confirm);
		
		sl.putConstraint(SpringLayout.WEST, selectClass, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, selectClass, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, viewClasses, 100, SpringLayout.EAST, selectClass);
		sl.putConstraint(SpringLayout.NORTH, viewClasses, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, update, 0, SpringLayout.WEST, viewClasses);
		sl.putConstraint(SpringLayout.NORTH, update, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, addTeacher, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, addTeacher, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, viewNonClassTeachers, 100, SpringLayout.EAST, addTeacher);
		sl.putConstraint(SpringLayout.NORTH, viewNonClassTeachers, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, removeTeacher, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, removeTeacher, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, viewClassTeachers, 100, SpringLayout.EAST, removeTeacher);
		sl.putConstraint(SpringLayout.NORTH, viewClassTeachers, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, addStudent, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, addStudent, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, viewNonClassStudents, 100, SpringLayout.EAST, addStudent);
		sl.putConstraint(SpringLayout.NORTH, viewNonClassStudents, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, removeStudent, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, removeStudent, 600, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, viewClassStudents, 100, SpringLayout.EAST, removeStudent);
		sl.putConstraint(SpringLayout.NORTH, viewClassStudents, 600, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, confirm, 700, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, confirm, 700, SpringLayout.NORTH, centerPanel);
		
		add(centerPanel, BorderLayout.CENTER);
	}
	
	
	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}
	
	
	public void refreshComboBox() {
		viewClasses.removeAllItems();
		viewNonClassTeachers.removeAllItems();
		viewClassTeachers.removeAllItems();
		viewNonClassStudents.removeAllItems();
		viewClassStudents.removeAllItems();
		
		schoolClasses = DataManagement.getCurrentSchoolClasses();
		viewClasses.addItem("-- Select Class -- ");
		for (SchoolClass sc : schoolClasses) {
			viewClasses.addItem(sc.getName() + " - Block " + sc.getBlock());
		}
	}
	
	
	private void refreshOtherBoxes() {
		viewNonClassTeachers.removeAllItems();
		viewClassTeachers.removeAllItems();
		viewNonClassStudents.removeAllItems();
		viewClassStudents.removeAllItems();
		
		classTeachers = DataManagement.getClassTeachers(classID);
		classStudents = DataManagement.getClassStudents(classID);
		nonClassTeachers = DataManagement.getNonClassTeachers(classID);
		nonClassStudents = DataManagement.getNonClassStudents(classID);
		
		viewClassTeachers.addItem("-- Select Teacher --");
		viewNonClassTeachers.addItem("-- Select Teacher --");
		viewClassStudents.addItem("-- Select Student --");
		viewNonClassStudents.addItem("-- Select Student --");
		
		for (Teacher t : classTeachers) {
			viewClassTeachers.addItem(t.getFirstName() + " " + t.getLastName());
		}
		
		for (Teacher t : nonClassTeachers) {
			viewNonClassTeachers.addItem(t.getFirstName() + " " + t.getLastName());
		}
		
		for (Student s : classStudents) {
			viewClassStudents.addItem(s.getFirstName() + " " + s.getLastName());
		}
		
		for (Student s : nonClassStudents) {
			viewNonClassStudents.addItem(s.getFirstName() + " " + s.getLastName());
		}
	}
	
}
