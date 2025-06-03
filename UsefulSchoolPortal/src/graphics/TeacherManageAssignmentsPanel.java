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
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import files.Constants;
import files.DataManagement;
import objects.Assignment;
import objects.SchoolClass;

/**
 * The TeacherManageAssignmentsPanel class contains the graphics necessary to
 * add, modify, or delete an assignment in any class a teacher is in. In this
 * panel, teachers select assignment types and determine the name and value of
 * each assignment before entering it into data.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class TeacherManageAssignmentsPanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;

	/** Class ID of the class being edited */
	private int classID;
	/** Assignment ID of the assignment being edited */
	private int assignmentID;
	/** ArrayList of classes */
	private ArrayList<SchoolClass> classes;
	/** JComboBox with list of classes */
	private JComboBox<String> classList;
	/** ArrayList with assignments */
	private ArrayList<Assignment> assignments;
	/** JComboBox with list of assignments */
	private JComboBox<String> assignmentList;
	/** JTextField for assignment name */
	private JTextField givenName;
	/** JTextField for given points */
	private JTextField givenPoints;
	/** JTextField for given weights */
	private JComboBox<String> givenWeights;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself. ID and index values
	 * are reset.
	 */
	public TeacherManageAssignmentsPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
		classID = -1;
		assignmentID = -1;
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_MANAGE_ASSIGNMENTS.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes preparing the lists and labels necessary for the user to
	 * manage assignments effectively along with the buttons to help the program
	 * pass the information to data.
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

		JLabel enterName = new JLabel("Enter Name:");
		enterName.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		givenName = new JTextField();
		givenName.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenName.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);

		JLabel enterPoints = new JLabel("Enter Points Value:");
		enterPoints.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		givenPoints = new JTextField();
		givenPoints.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenPoints.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);

		JLabel enterWeights = new JLabel("Enter Weights");
		enterWeights.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		givenWeights = new JComboBox<String>();
		givenWeights.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenWeights.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);

		JButton loadData = new JButton("Load Data");
		GraphicsHelpers.modifyButton(loadData, 250, 45);
		loadData.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button sets the class ID
			 * based on the value received from the class JComboBox and
			 * accordingly refreshes the list of assignments.
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
					givenName.setText("");
					givenPoints.setText("");
					givenWeights.removeAllItems();
					if (DataManagement.getGradingMethod(classID) == Constants.GRADE_WEIGHTS) {
						ArrayList<String> weights = DataManagement.getWeightCategories(classID);
						for (String s : weights) {
							givenWeights.addItem(s);
						}
					}
				}
			}

		});

		JButton loadAssignmentData = new JButton("Load Assignment Data");
		GraphicsHelpers.modifyButton(loadAssignmentData, 400, 45);
		loadAssignmentData.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button loads the data
			 * for an assignment if an existing assignment is selected in the
			 * assignment drop-down list.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = assignmentList.getSelectedIndex();
				if (index != 0) {
					givenName.setText(assignments.get(index - 1).getName());
					givenPoints.setText(String.valueOf(assignments.get(index - 1).getPoints()));
					assignmentID = assignments.get(index - 1).getAssignmentID();
					if (DataManagement.getGradingMethod(classID) == Constants.GRADE_WEIGHTS) {
						String weightCategory = DataManagement.getIndividualAssignmentWeightCategory(classID, assignmentID);
						for (int i = 0; i < givenWeights.getItemCount(); i++) {
							if (givenWeights.getItemAt(i).equals(weightCategory)) {
								givenWeights.setSelectedIndex(i);
							}
						}
					}
				} else {
					givenName.setText("");
					givenPoints.setText("");
					assignmentID = -1;
				}
			}

		});

		JButton enterData = new JButton("Confirm");
		GraphicsHelpers.modifyButton(enterData, 225, 45);
		enterData.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button sends the new
			 * or modified assignments to be placed in data. Error handling
			 * is caught for invalid input.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (classID != -1) {
						String name = givenName.getText();
						for (int i = 0; i < name.length(); i++) {
							if (name.substring(i, i+1).equals(",")) {
								throw new Exception();
							}
						}

						int points = -1;
						String weight = "";

						if (assignmentID == -1) {
							if (DataManagement.getGradingMethod(classID) == Constants.GRADE_POINTS) {
								points = Integer.parseInt(givenPoints.getText());
								DataManagement.addAssignment(classID, name, points);

							} else if (DataManagement.getGradingMethod(classID) == Constants.GRADE_WEIGHTS) {
								weight = String.valueOf(givenWeights.getSelectedItem());
								DataManagement.addAssignment(classID, name, weight);
							} else {
								DataManagement.addAssignment(classID, name);
							}
						} else {
							if (DataManagement.getGradingMethod(classID) == Constants.GRADE_POINTS) {
								points = Integer.parseInt(givenPoints.getText());
								System.out.println("Here");
								DataManagement.modifyAssignment(classID, assignmentID, name, points);
							} else if (DataManagement.getGradingMethod(classID) == Constants.GRADE_WEIGHTS) {
								weight = String.valueOf(givenWeights.getSelectedItem());
								DataManagement.modifyAssignment(classID, assignmentID, name, weight);
							} else {
								DataManagement.modifyAssignment(classID, assignmentID, name);
							}
						}
					}
				} catch (Exception exc) { 
					JOptionPane.showMessageDialog(centerPanel, "Invalid Input:\n"
							+ "- There must be a numerical value for points.\n"
							+ "- There must be no commas in the assignment name.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				refreshAssignmentList();
			}

		});

		JButton deleteAssignment = new JButton("Delete Assignment");
		GraphicsHelpers.modifyButton(deleteAssignment, 350, 45);
		deleteAssignment.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button deletes the
			 * assignment if it exists and is part of a class.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (classID != -1 && assignmentID != -1) {
					DataManagement.deleteAssignment(classID, assignmentID);
					refreshAssignmentList();
				}
			}

		});


		centerPanel.add(enterClass);
		centerPanel.add(classList);
		centerPanel.add(loadData);
		centerPanel.add(enterAssignment);
		centerPanel.add(assignmentList);
		centerPanel.add(loadAssignmentData);
		centerPanel.add(enterName);
		centerPanel.add(givenName);
		centerPanel.add(enterPoints);
		centerPanel.add(givenPoints);
		centerPanel.add(enterWeights);
		centerPanel.add(givenWeights);
		centerPanel.add(enterData);
		centerPanel.add(deleteAssignment);

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
		sl.putConstraint(SpringLayout.WEST, enterName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterName, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenName, 570, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenName, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterPoints, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterPoints, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenPoints, 570, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenPoints, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterWeights, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterWeights, 600, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenWeights, 570, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenWeights, 600, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterData, 570, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterData, 700, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, deleteAssignment, 450, SpringLayout.WEST, assignmentList);
		sl.putConstraint(SpringLayout.NORTH, deleteAssignment, 25, SpringLayout.SOUTH, assignmentList);

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
	 * The refreshComboBox method refreshes the text boxes and the lists.
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

		assignmentList.removeAllItems();
		assignmentList.addItem("-- Select Assignment --");
		classID = -1;
		assignmentID = -1;
		givenName.setText("");
		givenPoints.setText("");
	}


	/**
	 * The refreshAssignmentList refreshes the assignment list, either making
	 * it blank or adding new assignments to it based on the class selection.
	 */
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
		givenName.setText("");
		givenPoints.setText("");
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "TeacherManageAssignmentsPanel [classID=" + classID + ", assignmentID=" + assignmentID + ", classes="
				+ classes + ", classList=" + classList + ", assignments=" + assignments + ", assignmentList="
				+ assignmentList + ", givenName=" + givenName + ", givenPoints=" + givenPoints + ", givenWeights="
				+ givenWeights + "]";
	}
}
