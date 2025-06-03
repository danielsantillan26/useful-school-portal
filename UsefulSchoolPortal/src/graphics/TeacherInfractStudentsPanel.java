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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import files.DataManagement;
import objects.Infraction;
import objects.Student;

/**
 * The TeacherInfractStudentsPanel class contains the graphics necessary to
 * add infractions on a student's record. In this panel, teachers select the
 * student to infract and provide a rationale before submitting it to the
 * system. Infractions done by the same teacher can be modified or deleted.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class TeacherInfractStudentsPanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;

	/** Index selected in the infraction list */
	private int infractionIndex;
	/** ID of the infraction selected from the infraction list */
	private int infractionID;
	/** JComboBox with list of students */
	private JComboBox<String> studentList;
	/** ArrayList of students */
	private ArrayList<Student> students;
	/** JComboBox with list of infractions */
	private JComboBox<String> infractionList;
	/** ArrayList of infractions */
	private ArrayList<Infraction> infractions;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself. ID and index values
	 * are reset.
	 */
	public TeacherInfractStudentsPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
		infractionID = -1;
		infractionIndex = 0;
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_INFRACT_STUDENTS.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes preparing the lists and labels necessary for the user to
	 * perform a successful infraction creation. Buttons are also made to
	 * assist the user in performing such tasks.
	 */
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

			/**
			 * The actionPerformed method for this button loads the data
			 * from the selected infraction on the JComboBox.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				givenInfractionName.setText("");
				givenReason.setText("");
				infractionIndex = infractionList.getSelectedIndex();

				if (infractionIndex != 0) {
					infractionID = infractions.get(infractionIndex - 1).getId();
					Infraction inf = infractions.get(infractionIndex - 1);
					givenInfractionName.setText(inf.getName());
					givenReason.setText(inf.getReason());

					int infractionStudentID = inf.getStudentID();
					for (int i = 0; i < students.size(); i++) {
						if (students.get(i).getID() == infractionStudentID) {
							studentList.setSelectedIndex(i);
						}
					}
				} 
			}

		});

		JButton updateInfraction = new JButton("Update Infraction");
		GraphicsHelpers.modifyButton(updateInfraction, 350, 45);
		updateInfraction.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button either adds
			 * or updates an infraction depending on the user's selection
			 * on the JComboBox with the infraction list. The infraction is
			 * sent to data to be worked on.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (givenInfractionName.getText().contains(",") ||
						givenReason.getText().contains(",")) {
					JOptionPane.showMessageDialog(centerPanel, "You cannot"
							+ " have commas in any of your fields to ensure proper"
							+ " data storage.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {

					if (infractionIndex != 0) {
						String infractionName = givenInfractionName.getText();
						String infractionReason = givenReason.getText();
						int infractionStudentID = students.get(studentList.getSelectedIndex()).getID();
						DataManagement.modifyInfraction(infractionName, infractionID, infractionStudentID, infractionReason);
					} else {
						String infractionName = givenInfractionName.getText();
						String infractionReason = givenReason.getText();
						int infractionStudentID = students.get(studentList.getSelectedIndex()).getID();
						DataManagement.addInfraction(infractionName, infractionStudentID, infractionReason);
					}
				}
				givenInfractionName.setText("");
				givenReason.setText("");
				refreshComboBox();
			}

		});

		JButton deleteInfraction = new JButton("Delete Infraction");
		GraphicsHelpers.modifyButton(deleteInfraction, 350, 45);
		deleteInfraction.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button deletes the infraction
			 * selected in the list.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (infractionIndex != 0) {
					DataManagement.deleteInfraction(infractionID);
					refreshComboBox();
				}
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

		sl.putConstraint(SpringLayout.WEST, enterInfraction, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterInfraction, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, infractionList, 100, SpringLayout.EAST, enterInfraction);
		sl.putConstraint(SpringLayout.NORTH, infractionList, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, loadInfractionData, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, loadInfractionData, 200, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, selectStudent, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, selectStudent, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, studentList, 100, SpringLayout.EAST, selectStudent);
		sl.putConstraint(SpringLayout.NORTH, studentList, 300, SpringLayout.NORTH, centerPanel);
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
	 * The refreshComboBox method refreshes the list of students and infractions
	 * based on the school system's data. Only infractions involving the logged
	 * in teacher are added.
	 */
	public void refreshComboBox() {
		infractionID = -1;

		studentList.removeAllItems();
		students = DataManagement.getCurrentSchoolStudents();

		if (students != null) {
			for (Student s : students) {
				studentList.addItem(s.getFirstName() + " " + s.getLastName());
			}
		}

		infractionList.removeAllItems();
		infractions = DataManagement.getInfractionsByUser(DataManagement.getLoggedInUser().getID());
		infractionList.addItem("-- New Infraction --");

		if (infractions != null) {
			for (Infraction i : infractions) {
				infractionList.addItem(i.getName());
			}
		}
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "TeacherInfractStudentsPanel [infractionIndex=" + infractionIndex + ", infractionID=" + infractionID
				+ ", studentList=" + studentList + ", students=" + students + ", infractionList=" + infractionList
				+ ", infractions=" + infractions + "]";
	}	
}
