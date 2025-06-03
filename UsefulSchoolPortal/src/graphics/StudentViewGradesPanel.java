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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import files.DataManagement;
import objects.Assignment;
import objects.SchoolClass;

/**
 * The StudentViewGradesPanel class contains the graphics necessary to view
 * grades for each class the student is in.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class StudentViewGradesPanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;

	/** Class ID of the class where grades are shown */
	private int classID;
	/** ArrayList of classes */
	private ArrayList<SchoolClass> classes;
	/** JComboBox with list of classes */
	private JComboBox<String> classList;
	/** JTable to hold info */
	private JTable table;
	/** JScrollPane to hold table */
	private JScrollPane spTable;
	/** TableModel for table */
	private DefaultTableModel tableModel;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public StudentViewGradesPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
		classID = -1;
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_VIEW_GRADES.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes the JTable and the list of classes where the grade can be
	 * viewed.
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


		String[] columnNames = {"Assignment", "Grade"};
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
			 * Makes sure that cells cannot be edited. 
			 * 
			 * @param row		The row
			 * @param column	The column
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		table.setFillsViewportHeight(false);
		spTable = new JScrollPane(table);
		spTable.setPreferredSize(new Dimension(1300, 400));
		tableModel = new DefaultTableModel(data, columnNames);
		table.setModel(tableModel);


		JButton loadData = new JButton("Load Data");
		GraphicsHelpers.modifyButton(loadData, 250, 45);
		loadData.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button clears the table and
			 * adds the new grades and assignments for the new class selected.
			 * The overall grade is added at the bottom of the table.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int r = 0; r < tableModel.getRowCount(); r++) {
					tableModel.setValueAt("", r, 0);
					tableModel.setValueAt("", r, 1);
				}

				int index = classList.getSelectedIndex();
				if (index != 0) {
					classID = classes.get(index - 1).getClassID();

					if (classID != -1) {
						ArrayList<Assignment> assignments = DataManagement.getClassAssignments(classID);
						ArrayList<Double> gradeValues = DataManagement.getIndividualStudentAssignmentGrades(classID, DataManagement.getLoggedInUser().getID());
						ArrayList<String> assignmentNames = new ArrayList<String>();

						for (int i = 0; i < assignments.size(); i++) {
							assignmentNames.add(assignments.get(i).getName());
						}

						assignmentNames.add("-- Overall --");
						gradeValues.add(DataManagement.getIndividualStudentAverage(classID, DataManagement.getLoggedInUser().getID()));

						for (int j = 0; j < assignmentNames.size(); j++) {
							tableModel.setValueAt(assignmentNames.get(j), j, 0);
							if (gradeValues.get(j) != -1) {
								tableModel.setValueAt(gradeValues.get(j).toString(), j, 1);
							}
						}

					}
				}
			}

		});

		centerPanel.add(enterClass);
		centerPanel.add(classList);
		centerPanel.add(loadData);
		centerPanel.add(spTable);

		sl.putConstraint(SpringLayout.WEST, enterClass, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterClass, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, classList, 100, SpringLayout.EAST, enterClass);
		sl.putConstraint(SpringLayout.NORTH, classList, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, loadData, 0, SpringLayout.WEST, classList);
		sl.putConstraint(SpringLayout.NORTH, loadData, 25, SpringLayout.SOUTH, classList);
		sl.putConstraint(SpringLayout.WEST, spTable, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, spTable, 300, SpringLayout.NORTH, centerPanel);


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
	 * The refreshComboBox method refreshes the class list and clears the table.
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

		classID = -1;
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "StudentViewGradesPanel [classID=" + classID + ", classes=" + classes + ", classList=" + classList
				+ ", table=" + table + ", spTable=" + spTable + ", tableModel=" + tableModel + "]";
	}	
}
