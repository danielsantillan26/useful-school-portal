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
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import files.DataManagement;
import objects.Assignment;
import objects.SchoolClass;

public class TeacherManageAssignmentsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int classID;
	private int assignmentID;
	private ArrayList<SchoolClass> classes;
	private JComboBox<String> classList;
	private ArrayList<Assignment> assignments;
	private JComboBox<String> assignmentList;


	public TeacherManageAssignmentsPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
		classID = -1;
		assignmentID = -1;
	}


	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_MANAGE_ASSIGNMENTS.png")));
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

		JButton loadData = new JButton("Load Data");
		GraphicsHelpers.modifyButton(loadData, 250, 45);
		loadData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = classList.getSelectedIndex();
				if (index != 0) {
					classID = classes.get(index - 1).getClassID();
					refreshAssignmentList();
				}
			}

		});

		JLabel enterAssignment = new JLabel("Select Assignment");
		enterAssignment.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		assignmentList = new JComboBox<String>();
		assignmentList.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		assignmentList.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		
		JLabel enterName = new JLabel("Enter Name:");
		enterName.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		JTextField givenName = new JTextField();
		givenName.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenName.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		
		JLabel enterPoints = new JLabel("Enter Points Value:");
		enterPoints.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		JTextField givenPoints = new JTextField();
		givenPoints.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenPoints.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		
		JLabel enterWeights = new JLabel("Enter Weights");
		enterWeights.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		JButton loadAssignmentData = new JButton("Load Data");
		GraphicsHelpers.modifyButton(loadAssignmentData, 250, 45);
		loadData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = assignmentList.getSelectedIndex();
				if (index != 0) {
					
				}
			}

		});


		centerPanel.add(enterClass);
		centerPanel.add(classList);
		centerPanel.add(loadData);
		centerPanel.add(enterAssignment);
		centerPanel.add(assignmentList);
		centerPanel.add(loadAssignmentData);

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

	}

}
