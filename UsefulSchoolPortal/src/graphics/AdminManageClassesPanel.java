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

/**
 * The AdminManageClassesPanel class contains the graphics necessary to manage
 * classes in a school system. This feature is only accessible to
 * administrators. In this panel, administrators can add/remove students and
 * teachers from a class based on a drop-down list.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class AdminManageClassesPanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;
	/** JComboBox with list of classes */
	private JComboBox<String> viewClasses;
	/** JComboBox with list of teachers outside the class */
	private JComboBox<String> viewNonClassTeachers;
	/** JComboBox with list of teachers in the class */
	private JComboBox<String> viewClassTeachers;
	/** JComboBox with list of students outside the class */
	private JComboBox<String> viewNonClassStudents;
	/** JComboBox with list of students in the class */
	private JComboBox<String> viewClassStudents;
	/** ArrayList with list of classes */
	private ArrayList<SchoolClass> schoolClasses;
	/** ArrayList with list of teachers outside the class */
	private ArrayList<Teacher> nonClassTeachers;
	/** ArrayList with list of teachers in the class */
	private ArrayList<Teacher> classTeachers;
	/** ArrayList with list of students outside the class */
	private ArrayList<Student> nonClassStudents;
	/** ArrayList with list of students in the class */
	private ArrayList<Student> classStudents;
	/** Current class ID */
	private int classID;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public AdminManageClassesPanel() {
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_MANAGE_CLASSES.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes setting up the labels with instructions, preparing the
	 * buttons for data transfer/editing, and editing the lists.
	 */
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

			/**
			 * The actionPerformed method for this button loads the data
			 * of the class based on the selected class list index from the
			 * user.
			 * 
			 * @param e 	The ActionEvent
			 */
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

			/**
			 * The actionPerformed method for this button takes the selected
			 * values from the lists and determines which teachers/students to
			 * add or delete.
			 * 
			 * @param e 	The ActionEvent
			 */
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
		sl.putConstraint(SpringLayout.WEST, viewClasses, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, viewClasses, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, update, 0, SpringLayout.WEST, viewClasses);
		sl.putConstraint(SpringLayout.NORTH, update, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, addTeacher, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, addTeacher, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, viewNonClassTeachers, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, viewNonClassTeachers, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, removeTeacher, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, removeTeacher, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, viewClassTeachers, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, viewClassTeachers, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, addStudent, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, addStudent, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, viewNonClassStudents, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, viewNonClassStudents, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, removeStudent, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, removeStudent, 600, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, viewClassStudents, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, viewClassStudents, 600, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, confirm, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, confirm, 700, SpringLayout.NORTH, centerPanel);

		add(centerPanel, BorderLayout.CENTER);
	}


	/**
	 * The addChangePageButtons method adds the buttons from this panel that
	 * swap between pages of the program.
	 * 
	 * @param goHome 		Button that returns to the user homepage
	 */
	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}


	/**
	 * The refreshComboBox method refreshes the JComboBoxes as blank.
	 */
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


	/**
	 * The refreshOtherBoxes method refreshes the JComboBoxes and adds updated
	 * values after a change in data.
	 */
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


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "AdminManageClassesPanel [viewClasses=" + viewClasses + ", viewNonClassTeachers=" + viewNonClassTeachers
				+ ", viewClassTeachers=" + viewClassTeachers + ", viewNonClassStudents=" + viewNonClassStudents
				+ ", viewClassStudents=" + viewClassStudents + ", schoolClasses=" + schoolClasses
				+ ", nonClassTeachers=" + nonClassTeachers + ", classTeachers=" + classTeachers + ", nonClassStudents="
				+ nonClassStudents + ", classStudents=" + classStudents + ", classID=" + classID + "]";
	}


}
