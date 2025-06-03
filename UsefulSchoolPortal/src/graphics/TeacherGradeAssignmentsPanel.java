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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import files.Constants;
import files.DataManagement;
import objects.Assignment;
import objects.SchoolClass;
import objects.Student;

/**
 * The TeacherGradeAssignmentsPanel class contains the graphics necessary to
 * properly grade assignments for each class a teacher is in. This feature is
 * only accessible to teachers. In this panel, teachers can select classes
 * and assignments to enter grades for each student.
 */
public class TeacherGradeAssignmentsPanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;

	/** The class ID of the class the teacher is currently grading */
	private int classID;
	/** The assignment ID of the assignment the teacher is currently grading */
	private int assignmentID;
	/** ArrayList with classes */
	private ArrayList<SchoolClass> classes;
	/** JComboBox with list of classes */
	private JComboBox<String> classList;
	/** ArrayList with assignments */
	private ArrayList<Assignment> assignments;
	/** JComboBox with list of assignments */
	private JComboBox<String> assignmentList;
	/** ArrayList with students */
	private ArrayList<Student> studentList;
	/** ArrayList with student names */
	private ArrayList<String> studentNames;
	/** ArrayList with student IDs */
	private ArrayList<Integer> studentIDs;
	/** JTable to hold data */
	private JTable table;
	/** JScrollPane to hold JTable */
	private JScrollPane spTable;
	/** TableModel for table */
	private DefaultTableModel tableModel;
	/** JLabel with grading information specific to each assignment */
	private JLabel gradingInfo;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself. Values for IDs and lists
	 * are reset.
	 */
	public TeacherGradeAssignmentsPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
		classID = -1;
		assignmentID = -1;
		studentList = new ArrayList<Student>();
		studentNames = new ArrayList<String>();
		studentIDs = new ArrayList<Integer>();
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_GRADE_ASSIGNMENTS.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes the JTable to hold grades, labels with instructions, and
	 * buttons to send information to data.
	 */
	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		JLabel enterClass = new JLabel("Select Class");
		enterClass.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		classList = new JComboBox<String>();
		classList.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		classList.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);

		JLabel enterAssignment = new JLabel("Select Assignment");
		enterAssignment.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		assignmentList = new JComboBox<String>();
		assignmentList.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		assignmentList.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);

		String[] columnNames = {"Student Name", "Grade"};
		Object[][] data = {{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}};
		table = new JTable(data, columnNames) {

			private static final long serialVersionUID = 1L;

			/**
			 * Ensures that cells in column 0 cannot be editable since it has
			 * the class roster.
			 * 
			 * @param row		Row
			 * @param column	Column
			 * @return			Whether cell is editable
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				} else {
					return true;
				}
			}

		};
		table.setFillsViewportHeight(false);
		spTable = new JScrollPane(table);
		spTable.setPreferredSize(new Dimension(1300, 300));
		tableModel = new DefaultTableModel(data, columnNames);
		table.setModel(tableModel);

		gradingInfo = new JLabel();
		gradingInfo.setFont(GraphicsConstants.FONT_ROBOTO_B30);


		JButton loadData = new JButton("Load Data");
		GraphicsHelpers.modifyButton(loadData, 250, 45);
		loadData.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button loads the list of
			 * assignments based on the class selected. The class roster is
			 * loaded onto the table.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = classList.getSelectedIndex();
				assignmentID = -1;
				if (index != 0) {
					classID = classes.get(index - 1).getClassID();
					refreshAssignmentList();

					if (classID != -1) {
						studentList = DataManagement.getClassStudents(classID);
						studentNames = new ArrayList<String>();
						for (Student s : studentList) {
							studentNames.add(s.getLastName() + ", " + s.getFirstName());
							studentIDs.add(s.getID());
						}

						for (int i = 0; i < tableModel.getRowCount(); i++) {
							tableModel.setValueAt("", i, 0);
							tableModel.setValueAt("", i, 1);
						}

						for (int i = 0; i < studentNames.size(); i++) {
							tableModel.setValueAt(studentNames.get(i), i, 0);
						}

					}
				} else {
					classID = -1;
					for (int i = 0; i < tableModel.getRowCount(); i++) {
						tableModel.setValueAt("", i, 0);
					}
				}

			}

		});

		JButton loadAssignmentData = new JButton("Load Assignment Data");
		GraphicsHelpers.modifyButton(loadAssignmentData, 400, 45);
		loadAssignmentData.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button loads the
			 * saved grades for the selected assignment for modification or
			 * reference. Average grades are also an option for assignments
			 * and can be loaded in as well.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = assignmentList.getSelectedIndex();
				if (index != 0 && index != assignmentList.getItemCount() - 1) {
					assignmentID = assignments.get(index - 1).getAssignmentID();

					if (classID != -1) {
						if (DataManagement.getGradingMethod(classID) == Constants.GRADE_POINTS) {
							int points = DataManagement.getIndividualAssignmentPoints(classID, assignmentID);
							gradingInfo.setText("This assignment is worth " + String.valueOf(points) + " points.");
						} else if (DataManagement.getGradingMethod(classID) == Constants.GRADE_WEIGHTS) {
							String weight = DataManagement.getIndividualAssignmentWeightCategory(classID, assignmentID);
							int percent = DataManagement.getWeightPercentByCategory(classID, weight);
							gradingInfo.setText("This assignment is weighted as " + weight + ", worth " + String.valueOf(percent) + "%.");
						}

						ArrayList<Double> assignmentGrades = DataManagement.getGrades(classID, assignmentID);

						for (int j = 0; j < tableModel.getRowCount(); j++) {
							tableModel.setValueAt("", j, 1);
						}

						if (assignmentGrades != null) {
							for (int i = 0; i < assignmentGrades.size(); i++) {
								if (assignmentGrades.get(i) != null) {
									tableModel.setValueAt(assignmentGrades.get(i), i, 1);
								} else {
									tableModel.setValueAt("", i, 1);
								}
							}
						}
					}
				} else  {
					if (classID != -1) { 
						if (index == assignmentList.getItemCount() - 1) {
							ArrayList<Double> allAverages = DataManagement.getAllAverages(classID);

							if (allAverages != null) {
								for (int i = 0; i < allAverages.size(); i++) {
									tableModel.setValueAt(allAverages.get(i).toString(), i, 1);
								}
							}


						} else {
							assignmentID = -1;
							gradingInfo.setText("");
						}
					}
					gradingInfo.setText("");
				}
				repaint();
			}

		});

		JButton confirm = new JButton("Confirm");
		GraphicsHelpers.modifyButton(confirm, 220, 45);
		confirm.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button saves the newly-input
			 * grades into the data.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Double> grades = new ArrayList<Double>();
				for (int i = 0; i < table.getRowCount(); i++) {
					if (tableModel.getValueAt(i, 1).toString().strip() == "") {
						grades.add(null);
					} else {
						try {
							double individualGrade = Double.parseDouble(table.getValueAt(i,  1).toString());
							grades.add(individualGrade);
						} catch (Exception exc)  {
							JOptionPane.showMessageDialog(centerPanel, "Invalid Input\n"
									+ "- All grade cells should either have a number or be empty", 
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				DataManagement.setGrades(classID, assignmentID, grades);
			}


		});

		centerPanel.add(enterClass);
		centerPanel.add(classList);
		centerPanel.add(loadData);
		centerPanel.add(enterAssignment);
		centerPanel.add(assignmentList);
		centerPanel.add(loadAssignmentData);
		centerPanel.add(spTable);
		centerPanel.add(gradingInfo);
		centerPanel.add(confirm);

		sl.putConstraint(SpringLayout.WEST, enterClass, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterClass, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, classList, 100, SpringLayout.EAST, enterClass);
		sl.putConstraint(SpringLayout.NORTH, classList, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, loadData, 0, SpringLayout.WEST, classList);
		sl.putConstraint(SpringLayout.NORTH, loadData, 25, SpringLayout.SOUTH, classList);
		sl.putConstraint(SpringLayout.WEST, enterAssignment, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterAssignment, 225, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, assignmentList, 100, SpringLayout.EAST, enterAssignment);
		sl.putConstraint(SpringLayout.NORTH, assignmentList, 225, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, loadAssignmentData, 0, SpringLayout.WEST, assignmentList);
		sl.putConstraint(SpringLayout.NORTH, loadAssignmentData, 25, SpringLayout.SOUTH, assignmentList);
		sl.putConstraint(SpringLayout.WEST, spTable, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, spTable, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, confirm, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, confirm, 25, SpringLayout.SOUTH, spTable);
		sl.putConstraint(SpringLayout.WEST, gradingInfo, 50, SpringLayout.EAST, confirm);
		sl.putConstraint(SpringLayout.NORTH, gradingInfo, 0, SpringLayout.NORTH, confirm);

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
	 * The refreshComboBox method refreshes the class list and assignment list,
	 * removing all items.
	 */
	public void refreshComboBox() {
		classList.removeAllItems();
		classes = DataManagement.getCurrentUserClasses();

		classList.addItem("-- Select Class --");
		if (classes != null) {
			for (SchoolClass sc : classes) {
				classList.addItem(sc.getName() + " - Block " + sc.getBlock());
			}
		}

		for (int i = 0; i < tableModel.getRowCount(); i++) {
			for (int j = 0; j < tableModel.getColumnCount(); j++) {
				tableModel.setValueAt("", i, j);
			}
		}

		assignmentList.removeAllItems();
		assignmentList.addItem("-- Select Assignment --");
		classID = -1;
		assignmentID = -1;
		gradingInfo.setText("");
		repaint();
	}


	/**
	 * The refreshAssignmentList method adds assignments from a newly-selected
	 * class to the list of assignments.
	 */
	private void refreshAssignmentList() {
		assignmentList.removeAllItems();
		assignments = DataManagement.getClassAssignments(classID);

		assignmentList.addItem("-- Select Assignment --");
		if (classID != -1) {
			if (assignments != null) {
				for (Assignment a : assignments) {
					assignmentList.addItem(a.getName());
				}
			}
		}
		assignmentList.addItem("-- Overall --");

		gradingInfo.setText("");
		repaint();
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "TeacherGradeAssignmentsPanel [classID=" + classID + ", assignmentID=" + assignmentID + ", classes="
				+ classes + ", classList=" + classList + ", assignments=" + assignments + ", assignmentList="
				+ assignmentList + ", studentList=" + studentList + ", studentNames=" + studentNames + ", studentIDs="
				+ studentIDs + ", table=" + table + ", spTable=" + spTable + ", tableModel=" + tableModel
				+ ", gradingInfo=" + gradingInfo + "]";
	}
}
