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
import javax.swing.table.TableModel;

import files.Constants;
import files.DataManagement;
import objects.Assignment;
import objects.SchoolClass;
import objects.Student;

public class TeacherGradeAssignmentsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int classID;
	private int assignmentID;
	private ArrayList<SchoolClass> classes;
	private JComboBox<String> classList;
	private ArrayList<Assignment> assignments;
	private JComboBox<String> assignmentList;
	private ArrayList<Student> studentList;
	private ArrayList<String> studentNames;
	private ArrayList<Integer> studentIDs;
	private JTable table;
	private JScrollPane spTable;
	private DefaultTableModel tableModel;
	private JLabel gradingInfo;


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


	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_GRADE_ASSIGNMENTS.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


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

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = assignmentList.getSelectedIndex();
				if (index != 0) {
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

						if (assignmentGrades != null) {
							for (int i = 0; i < assignmentGrades.size(); i++) {
								if (assignmentGrades.get(i) != null) {
									tableModel.setValueAt(assignmentGrades.get(i), i, 1);
								}
							}
						}
					}
				} else {
					assignmentID = -1;
					gradingInfo.setText("");
				}
				repaint();
			}

		});

		JButton confirm = new JButton("Confirm");
		GraphicsHelpers.modifyButton(confirm, 220, 45);
		confirm.addActionListener(new ActionListener() {

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


	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}


	public void refreshComboBox() {
		classList.removeAllItems();
		classes = DataManagement.getCurrentUserClasses();

		classList.addItem("-- Select Class --");
		if (classes != null) {
			for (SchoolClass sc : classes) {
				classList.addItem(sc.getName() + " - Block " + sc.getBlock());
			}
		}

		assignmentList.removeAllItems();
		assignmentList.addItem("-- Select Assignment --");
		classID = -1;
		assignmentID = -1;
		gradingInfo.setText("");
		repaint();
	}


	private void refreshAssignmentList() {
		assignmentList.removeAllItems();
		assignments = DataManagement.getClassAssignments(classID);

		assignmentList.addItem("-- New Assignment --");
		if (classID != -1) {
			if (assignments != null) {
				for (Assignment a : assignments) {
					assignmentList.addItem(a.getName());
				}
			}
		}

		gradingInfo.setText("");
		repaint();
	}

}
