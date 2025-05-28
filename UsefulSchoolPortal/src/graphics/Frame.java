package graphics;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import files.Constants;
import files.DataManagement;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private CardLayout cl;
	private Container container;


	public Frame() {
		setTitle("Useful School Portal");
		Dimension x = Toolkit.getDefaultToolkit().getScreenSize();
		int length = x.width;
		int width = x.height;
		setSize(length, width);
		setExtendedState(JFrame. MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);	
		setFocusable(true);

		cl = new CardLayout();
		container = getContentPane();
		container.setLayout(cl);
		prepareCardLayout();
	}


	private void prepareCardLayout() {
		IntroductionPanel panelIntro = new IntroductionPanel();
		TermsAndConditionsPanel panelToC = new TermsAndConditionsPanel();
		RegisterPanel panelRegister = new RegisterPanel();
		LoginPanel panelLogin = new LoginPanel();
		AdminHomepagePanel panelAdminHomepage = new AdminHomepagePanel();
		AdminManageSchedulePanel panelAdminManageSchedule = new AdminManageSchedulePanel();
		AdminAddUsersPanel panelAdminAddUsers = new AdminAddUsersPanel();
		AdminAddClassesPanel panelAdminAddClasses = new AdminAddClassesPanel();
		AdminDeleteUsersPanel panelAdminDeleteUsers = new AdminDeleteUsersPanel();
		AdminDeleteClassesPanel panelAdminDeleteClasses = new AdminDeleteClassesPanel();
		AdminManageUsersPanel panelAdminManageUsers = new AdminManageUsersPanel();
		AdminStudentInfractionsPanel panelAdminStudentInfractions = new AdminStudentInfractionsPanel();
		AdminManageClassesPanel panelAdminManageClasses = new AdminManageClassesPanel();
		AdminEditProfilePanel panelAdminEditProfile = new AdminEditProfilePanel();
		TeacherHomepagePanel panelTeacherHomepage = new TeacherHomepagePanel();
		TeacherClassHomepagePanel panelTeacherClassHomepage = new TeacherClassHomepagePanel();
		TeacherEditClassHomepagePanel panelTeacherEditClassHomepage = new TeacherEditClassHomepagePanel();
		TeacherManageClassesPanel panelTeacherManageClasses = new TeacherManageClassesPanel();
		TeacherViewRostersPanel panelTeacherViewRosters = new TeacherViewRostersPanel();
		TeacherManageAssignmentsPanel panelTeacherManageAssignments = new TeacherManageAssignmentsPanel();
		TeacherGradeAssignmentsPanel panelTeacherGradeAssignments = new TeacherGradeAssignmentsPanel();
		TeacherInfractStudentsPanel panelTeacherInfractStudents = new TeacherInfractStudentsPanel();
		TeacherEditProfilePanel panelTeacherEditProfile = new TeacherEditProfilePanel();
		StudentHomepagePanel panelStudentHomepage = new StudentHomepagePanel();
		StudentClassHomepagePanel panelStudentClassHomepage = new StudentClassHomepagePanel();
		StudentViewRostersPanel panelStudentViewRosters = new StudentViewRostersPanel();
		StudentViewGradesPanel panelStudentViewGrades = new StudentViewGradesPanel();
		StudentGradingSimulatorPanel panelStudentGradingSimulator = new StudentGradingSimulatorPanel();
		StudentEditProfilePanel panelStudentEditProfile = new StudentEditProfilePanel();
		

		JButton buttonPanelIntroRegister = new JButton("Register");
		GraphicsHelpers.modifyButton(buttonPanelIntroRegister, 200, 45);

		JButton buttonPanelIntroLogin = new JButton("Login");
		GraphicsHelpers.modifyButton(buttonPanelIntroLogin, 200, 45);

		JButton buttonPanelIntroToC = new JButton("View Terms");
		GraphicsHelpers.modifyButton(buttonPanelIntroToC, 300, 45);

		JButton buttonPanelToCReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelToCReturn, 220, 45);

		JButton buttonPanelRegisterCreate = new JButton("Create Account");
		GraphicsHelpers.modifyButton(buttonPanelRegisterCreate, 450, 45);

		JButton buttonPanelRegisterReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelRegisterReturn, 220, 45);

		JButton buttonPanelLoginLogin = new JButton("Log In");
		GraphicsHelpers.modifyButton(buttonPanelLoginLogin, 200, 45);

		JButton buttonPanelLoginReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelLoginReturn, 220, 45);

		JButton buttonPanelAdminHomepageManageSchedule = new JButton("Manage Schedule");
		GraphicsHelpers.modifyButton(buttonPanelAdminHomepageManageSchedule, 350, 45);

		JButton buttonPanelAdminHomepageAddUsers = new JButton("Add Users");
		GraphicsHelpers.modifyButton(buttonPanelAdminHomepageAddUsers, 275, 45);

		JButton buttonPanelAdminHomepageAddClasses = new JButton("Add Classes");
		GraphicsHelpers.modifyButton(buttonPanelAdminHomepageAddClasses, 300, 45);

		JButton buttonPanelAdminHomepageDeleteUsers = new JButton("Delete Users");
		GraphicsHelpers.modifyButton(buttonPanelAdminHomepageDeleteUsers, 300, 45);

		JButton buttonPanelAdminHomepageDeleteClasses = new JButton("Delete Classes");
		GraphicsHelpers.modifyButton(buttonPanelAdminHomepageDeleteClasses, 325, 45);

		JButton buttonPanelAdminHomepageManageTeachers = new JButton("Manage Users");
		GraphicsHelpers.modifyButton(buttonPanelAdminHomepageManageTeachers, 300, 45);

		JButton buttonPanelAdminHomepageStudentInfractions = new JButton("Student Infractions");
		GraphicsHelpers.modifyButton(buttonPanelAdminHomepageStudentInfractions, 350, 45);

		JButton buttonPanelAdminHomepageManageClasses = new JButton("Manage Classes");
		GraphicsHelpers.modifyButton(buttonPanelAdminHomepageManageClasses, 350, 45);

		JButton buttonPanelAdminHomepageEditProfile = new JButton("Edit Profile");
		GraphicsHelpers.modifyButton(buttonPanelAdminHomepageEditProfile, 300, 45);

		JButton buttonPanelAdminHomepageLogOut = new JButton("Log Out");
		GraphicsHelpers.modifyButton(buttonPanelAdminHomepageLogOut, 250, 45);
		
		JButton buttonPanelAdminManageScheduleReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelAdminManageScheduleReturn, 220, 45);
		
		JButton buttonPanelAdminAddUsersReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelAdminAddUsersReturn, 220, 45);
		
		JButton buttonPanelAdminAddClassesReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelAdminAddClassesReturn, 220, 45);
		
		JButton buttonPanelAdminDeleteUsersReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelAdminDeleteUsersReturn, 220, 45);
		
		JButton buttonPanelAdminDeleteClassesReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelAdminDeleteClassesReturn, 220, 45);
		
		JButton buttonPanelAdminManageUsersReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelAdminManageUsersReturn, 220, 45);
		
		JButton buttonPanelAdminStudentInfractionsReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelAdminStudentInfractionsReturn, 220, 45);
		
		JButton buttonPanelAdminManageClassesReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelAdminManageClassesReturn, 220, 45);
		
		JButton buttonPanelAdminEditProfileReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelAdminEditProfileReturn, 220, 45);
		
		JButton buttonPanelTeacherHomepageClassHomepage = new JButton("Class Homepage");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageClassHomepage, 450, 45);
		
		JButton buttonPanelTeacherHomepageEditClassHomepage = new JButton("Edit Class Homepage");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageEditClassHomepage, 450, 45);
		
		JButton buttonPanelTeacherHomepageManageClasses = new JButton("Manage Classes");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageManageClasses, 375, 45);
		
		JButton buttonPanelTeacherHomepageViewRosters = new JButton("View Rosters");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageViewRosters, 300, 45);
		
		JButton buttonPanelTeacherHomepageManageAssignments = new JButton("Manage Assignments");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageManageAssignments, 450, 45);
		
		JButton buttonPanelTeacherHomepageGradeAssignments = new JButton("Grade Assignments");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageGradeAssignments, 450, 45);
		
		JButton buttonPanelTeacherHomepageInfractStudents = new JButton("Infract Students");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageInfractStudents, 350, 45);
		
		JButton buttonPanelTeacherHomepageEditProfile = new JButton("Edit Profile");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageEditProfile, 300, 45);
		
		JButton buttonPanelTeacherHomepageLogOut = new JButton("Log Out");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageLogOut, 250, 45);
		
		JButton buttonPanelTeacherClassHomepageReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelTeacherClassHomepageReturn, 220, 45);
		
		JButton buttonPanelTeacherEditClassHomepageReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelTeacherEditClassHomepageReturn, 220, 45);
		
		JButton buttonPanelTeacherManageClassesReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelTeacherManageClassesReturn, 220, 45);
		
		JButton buttonPanelTeacherViewRostersReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelTeacherViewRostersReturn, 220, 45);
		
		JButton buttonPanelTeacherManageAssignmentsReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelTeacherManageAssignmentsReturn, 220, 45);
		
		JButton buttonPanelTeacherGradeAssignmentsReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelTeacherGradeAssignmentsReturn, 220, 45);
		
		JButton buttonPanelTeacherEditProfileReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelTeacherEditProfileReturn, 220, 45);
		
		JButton buttonPanelStudentHomepageClassHomepage = new JButton("Class Homepage");
		GraphicsHelpers.modifyButton(buttonPanelStudentHomepageClassHomepage, 450, 45);
		
		JButton buttonPanelStudentHomepageViewRosters = new JButton("View Rosters");
		GraphicsHelpers.modifyButton(buttonPanelStudentHomepageViewRosters, 300, 45);
		
		JButton buttonPanelStudentHomepageViewGrades = new JButton("View Grades");
		GraphicsHelpers.modifyButton(buttonPanelStudentHomepageViewGrades, 300, 45);
		
		JButton buttonPanelStudentHomepageGradingSimulator = new JButton("Grading Simulator");
		GraphicsHelpers.modifyButton(buttonPanelStudentHomepageGradingSimulator, 500, 45);
		
		JButton buttonPanelStudentHomepageEditProfile = new JButton("Edit Profile");
		GraphicsHelpers.modifyButton(buttonPanelStudentHomepageEditProfile, 300, 45);
		
		JButton buttonPanelStudentHomepageLogOut = new JButton("Log Out");
		GraphicsHelpers.modifyButton(buttonPanelStudentHomepageLogOut, 250, 45);
		
		JButton buttonPanelStudentClassHomepageReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelStudentClassHomepageReturn, 220, 45);
		
		JButton buttonPanelStudentViewRostersReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelStudentViewRostersReturn, 220, 45);
		
		JButton buttonPanelStudentGradingSimulatorReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelStudentGradingSimulatorReturn, 220, 45);
		
		JButton buttonPanelStudentEditProfileReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelStudentEditProfileReturn, 220, 45);
		



		panelIntro.addChangePageButtons(buttonPanelIntroRegister, buttonPanelIntroLogin, buttonPanelIntroToC);
		panelToC.addChangePageButtons(buttonPanelToCReturn);
		panelRegister.addChangePageButtons(buttonPanelRegisterCreate, buttonPanelRegisterReturn);
		panelLogin.addChangePageButtons(buttonPanelLoginLogin, buttonPanelLoginReturn);
		panelAdminHomepage.addChangePageButtons(buttonPanelAdminHomepageManageSchedule, buttonPanelAdminHomepageAddUsers, 
				buttonPanelAdminHomepageAddClasses, buttonPanelAdminHomepageDeleteUsers, buttonPanelAdminHomepageDeleteClasses, 
				buttonPanelAdminHomepageManageTeachers, buttonPanelAdminHomepageStudentInfractions, buttonPanelAdminHomepageManageClasses, 
				buttonPanelAdminHomepageEditProfile, buttonPanelAdminHomepageLogOut);
		panelAdminManageSchedule.addChangePageButtons(buttonPanelAdminManageScheduleReturn);
		panelAdminAddUsers.addChangePageButtons(buttonPanelAdminAddUsersReturn);
		panelAdminAddClasses.addChangePageButtons(buttonPanelAdminAddClassesReturn);
		panelAdminDeleteUsers.addChangePageButtons(buttonPanelAdminDeleteUsersReturn);
		panelAdminDeleteClasses.addChangePageButtons(buttonPanelAdminDeleteClassesReturn);
		panelAdminManageUsers.addChangePageButtons(buttonPanelAdminManageUsersReturn);
		panelAdminStudentInfractions.addChangePageButtons(buttonPanelAdminStudentInfractionsReturn);
		panelAdminManageClasses.addChangePageButtons(buttonPanelAdminManageClassesReturn);
		panelAdminEditProfile.addChangePageButtons(buttonPanelAdminEditProfileReturn);
		panelTeacherHomepage.addChangePageButtons(buttonPanelTeacherHomepageClassHomepage, buttonPanelTeacherHomepageEditClassHomepage,
				buttonPanelTeacherHomepageManageClasses, buttonPanelTeacherHomepageViewRosters, buttonPanelTeacherHomepageManageAssignments, 
				buttonPanelTeacherHomepageGradeAssignments, buttonPanelTeacherHomepageInfractStudents, buttonPanelTeacherHomepageEditProfile, 
				buttonPanelTeacherHomepageLogOut);
		panelTeacherClassHomepage.addChangePageButtons(buttonPanelTeacherClassHomepageReturn);
		panelTeacherEditClassHomepage.addChangePageButtons(buttonPanelTeacherEditClassHomepageReturn);
		panelTeacherManageClasses.addChangePageButtons(buttonPanelTeacherManageClassesReturn);
		panelTeacherViewRosters.addChangePageButtons(buttonPanelTeacherViewRostersReturn);
		panelTeacherManageAssignments.addChangePageButtons(buttonPanelTeacherManageAssignmentsReturn);
		panelTeacherGradeAssignments.addChangePageButtons(buttonPanelTeacherGradeAssignmentsReturn);
		panelTeacherEditProfile.addChangePageButtons(buttonPanelTeacherEditProfileReturn);
		panelStudentHomepage.addChangePageButtons(buttonPanelStudentHomepageClassHomepage,
				buttonPanelStudentHomepageViewRosters, buttonPanelStudentHomepageViewGrades,
				buttonPanelStudentHomepageGradingSimulator, buttonPanelStudentHomepageEditProfile,
				buttonPanelStudentHomepageLogOut);
		panelStudentClassHomepage.addChangePageButtons(buttonPanelStudentClassHomepageReturn);
		panelStudentViewRosters.addChangePageButtons(buttonPanelStudentViewRostersReturn);
		panelStudentGradingSimulator.addChangePageButtons(buttonPanelStudentGradingSimulatorReturn);
		panelStudentEditProfile.addChangePageButtons(buttonPanelStudentEditProfileReturn);
		
		

		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonPanelIntroToC) {
					cl.next(container);
				} else if (e.getSource() == buttonPanelIntroRegister) {
					cl.next(container);
					cl.next(container);
				} else if (e.getSource() == buttonPanelIntroLogin) {
					cl.next(container);
					cl.next(container);
					cl.next(container);
				} else if (e.getSource() == buttonPanelToCReturn) {
					cl.previous(container);

				} else if (e.getSource() == buttonPanelRegisterCreate) {
					String givenSchoolName = panelRegister.getSchoolName();
					String givenUsername = panelRegister.getUsername();
					String givenFirstName = panelRegister.getFirstName();
					String givenLastName = panelRegister.getLastName();
					String givenPassword = panelRegister.getPassword();

					if (givenSchoolName.trim().equals("") ||
							givenUsername.trim().equals("") ||
							givenFirstName.trim().equals("") ||
							givenLastName.trim().equals("") ||
							givenPassword.trim().equals("")) {
						JOptionPane.showMessageDialog(panelRegister, "You " +
								"have an empty field somewhere. Please fill " +
								"out all values.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					ArrayList<String> inputs = new ArrayList<String>();
					inputs.add(givenSchoolName);
					inputs.add(givenUsername);
					inputs.add(givenFirstName);
					inputs.add(givenLastName);
					inputs.add(givenPassword);

					if (GraphicsHelpers.hasComma(inputs)) {
						JOptionPane.showMessageDialog(panelRegister, "You cannot"
								+ " have commas in any of your fields to ensure proper"
								+ " data storage.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}


					if (!GraphicsHelpers.isPasswordValid(givenPassword)) {
						JOptionPane.showMessageDialog(panelRegister, "Password must\n"
								+ "- Be between 10 and 50 characters\n" +
								"- Have no spaces\n" +
								"- Have both an uppercase and lowercase letter\n" +
								"- Have a number", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					panelRegister.clearText();
					DataManagement.addSchoolAndAdministrator(givenSchoolName, 
							givenUsername, givenFirstName, givenLastName, givenPassword);

					cl.next(container);
					cl.next(container);

				} else if (e.getSource() == buttonPanelRegisterReturn) {
					cl.previous(container);
					cl.previous(container);
				} else if (e.getSource() == buttonPanelLoginLogin) { 
					String username = panelLogin.getUsername();
					String password = panelLogin.getPassword();

					if (DataManagement.login(username, password)) {
						panelLogin.clearText();
						switch (DataManagement.getLoggedInUserRole()) {
						case Constants.ADMINISTRATOR_VALUE:
							cl.next(container);
							panelAdminHomepage.updateUsername();
							break;
						case Constants.TEACHER_VALUE:
							for (int i = 0 ; i < 11; i++) {
								cl.next(container);
							}
							panelTeacherHomepage.updateUsername();
							break;
						case Constants.STUDENT_VALUE:
							for (int i = 0 ; i < 20; i++) {
								cl.next(container);
							}
							panelStudentHomepage.updateUsername();
							break;
						}
					} else {
						JOptionPane.showMessageDialog(panelRegister,
								"Incorrect Username or Password.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} else if (e.getSource() == buttonPanelLoginReturn) {
					cl.previous(container);
					cl.previous(container);
					cl.previous(container);
				} else if (e.getSource() == buttonPanelAdminHomepageManageSchedule) {
					panelAdminManageSchedule.refreshComboBox();
					cl.next(container);
				} else if (e.getSource() == buttonPanelAdminHomepageAddUsers) {
					cl.next(container);
					cl.next(container);
				} else if (e.getSource() == buttonPanelAdminHomepageAddClasses) {
					cl.next(container);
					cl.next(container);
					cl.next(container);
					panelAdminAddClasses.refreshComboBox();
				} else if (e.getSource() == buttonPanelAdminHomepageDeleteUsers) {
					for (int i = 0; i < 4; i++) {
						cl.next(container);
					}
					panelAdminDeleteUsers.refreshComboBox();
				} else if (e.getSource() == buttonPanelAdminHomepageDeleteClasses) {
					for (int i = 0; i < 5; i++) {
						cl.next(container);
					}
					panelAdminDeleteClasses.refreshComboBox();
				} else if (e.getSource() == buttonPanelAdminHomepageManageTeachers) {
					for (int i = 0; i < 6; i++) {
						cl.next(container);
					}
					panelAdminManageUsers.refreshComboBox();
				} else if (e.getSource() == buttonPanelAdminHomepageStudentInfractions) {
					for (int i = 0; i < 7; i++) {
						cl.next(container);
					}
					panelAdminStudentInfractions.refreshComboBox();
				} else if (e.getSource() == buttonPanelAdminHomepageManageClasses) {
					for (int i = 0; i < 8; i++) {
						cl.next(container);
					}
					panelAdminManageClasses.refreshComboBox();
				} else if (e.getSource() == buttonPanelAdminHomepageEditProfile) {
					for (int i = 0; i < 9; i++) {
						cl.next(container);
					}
				} else if (e.getSource() == buttonPanelAdminHomepageLogOut) {
					cl.first(container);
					DataManagement.logOutUser();
				} else if (e.getSource() == buttonPanelAdminManageScheduleReturn) {
					cl.previous(container);
				} if (e.getSource() == buttonPanelAdminAddUsersReturn) {
					cl.previous(container);
					cl.previous(container);
				} else if (e.getSource() == buttonPanelAdminAddClassesReturn) {
					cl.previous(container);
					cl.previous(container);
					cl.previous(container);
				} else if (e.getSource() == buttonPanelAdminDeleteUsersReturn) {
					for (int i = 0; i < 4; i++) {
						cl.previous(container);
					}
				} else if (e.getSource() == buttonPanelAdminDeleteClassesReturn) {
					for (int i = 0; i < 5; i++) {
						cl.previous(container);
					}
				} else if (e.getSource() == buttonPanelAdminManageUsersReturn) {
					for (int i = 0; i < 6; i++) {
						cl.previous(container);
					}
				} else if (e.getSource() == buttonPanelAdminStudentInfractionsReturn) {
					for (int i = 0; i < 7; i++) {
						cl.previous(container);
					}
				} else if (e.getSource() == buttonPanelAdminManageClassesReturn) {
					for (int i = 0; i < 8; i++) {
						cl.previous(container);
					}
				} else if (e.getSource() == buttonPanelAdminEditProfileReturn) {
					for (int i = 0; i < 9; i++) {
						cl.previous(container);
					}
					panelAdminHomepage.updateUsername();
				} else if (e.getSource() == buttonPanelTeacherHomepageClassHomepage) {
					cl.next(container);
					panelTeacherClassHomepage.refreshComboBox();
				} else if (e.getSource() == buttonPanelTeacherHomepageEditClassHomepage) {
					cl.next(container);
					cl.next(container);
					panelTeacherEditClassHomepage.refreshComboBox();
				} else if (e.getSource() == buttonPanelTeacherHomepageManageClasses) {
					cl.next(container);
					cl.next(container);
					cl.next(container);
					panelTeacherManageClasses.refreshComboBox();
				}else if (e.getSource() == buttonPanelTeacherHomepageViewRosters) {
					for (int i = 0; i < 4; i++) {
						cl.next(container);
					}
					panelTeacherViewRosters.refreshComboBox();
				} else if (e.getSource() == buttonPanelTeacherHomepageManageAssignments) {
					for (int i = 0; i < 5; i++) {
						cl.next(container);
					}
					panelTeacherManageAssignments.refreshComboBox();
				} else if (e.getSource() == buttonPanelTeacherHomepageGradeAssignments) {
					for (int i = 0; i < 6; i++) {
						cl.next(container);
					}
				} else if (e.getSource() == buttonPanelTeacherHomepageInfractStudents) {
					for (int i = 0; i < 7; i++) {
						cl.next(container);
					}
				} else if (e.getSource() == buttonPanelTeacherHomepageEditProfile) {
					for (int i = 0; i < 8; i++) {
						cl.next(container);
					}
				} else if (e.getSource() == buttonPanelTeacherHomepageLogOut) {
					cl.first(container);
					DataManagement.logOutUser();
				} else if (e.getSource() == buttonPanelTeacherClassHomepageReturn) {
					cl.previous(container);
				} else if (e.getSource() == buttonPanelTeacherEditClassHomepageReturn) {
					cl.previous(container);
					cl.previous(container);
				} else if (e.getSource() == buttonPanelTeacherManageClassesReturn) {
					cl.previous(container);
					cl.previous(container);
					cl.previous(container);
				} else if (e.getSource() == buttonPanelTeacherViewRostersReturn) {
					for (int i = 0; i < 4; i++) {
						cl.previous(container);
					}
				} else if (e.getSource() == buttonPanelTeacherManageAssignmentsReturn) {
					for (int i = 0; i < 5; i++) {
						cl.previous(container);
					}
				}  else if (e.getSource() == buttonPanelTeacherGradeAssignmentsReturn) { 
					for (int i = 0; i < 6; i++) {
						
					}
				} else if (e.getSource() == buttonPanelTeacherEditProfileReturn) {
					for (int i = 0; i < 8; i++) {
						cl.previous(container);
					}
				} else if (e.getSource() == buttonPanelStudentHomepageClassHomepage) {
					cl.next(container);
					panelStudentClassHomepage.refreshComboBox();
				} else if (e.getSource() == buttonPanelStudentHomepageViewRosters) {
					cl.next(container);
					cl.next(container);
					panelStudentViewRosters.refreshComboBox();
				} else if (e.getSource() == buttonPanelStudentHomepageViewGrades) {
					cl.next(container);
					cl.next(container);
					cl.next(container);
				} else if (e.getSource() == buttonPanelStudentHomepageGradingSimulator) {
					for (int i = 0; i < 4; i++) {
						cl.next(container);
					}
				} else if (e.getSource() == buttonPanelStudentHomepageEditProfile) {
					for (int i = 0; i < 5; i++) {
						cl.next(container);
					}
				} else if (e.getSource() == buttonPanelStudentHomepageLogOut) {
					cl.first(container);
					DataManagement.logOutUser(); 
				} else if (e.getSource() == buttonPanelStudentClassHomepageReturn){
					cl.previous(container);
				} else if (e.getSource() == buttonPanelStudentViewRostersReturn) {
					cl.previous(container);
					cl.previous(container);
				} else if (e.getSource() == buttonPanelStudentGradingSimulatorReturn) {
					for (int i = 0; i < 4; i++) {
						cl.previous(container);
					}
				} else if (e.getSource() == buttonPanelStudentEditProfileReturn) {
					for (int i = 0; i < 5; i++) {
						cl.previous(container);
					}
				}
			} 
		};

		buttonPanelIntroRegister.addActionListener(al);
		buttonPanelIntroLogin.addActionListener(al);
		buttonPanelIntroToC.addActionListener(al);
		buttonPanelToCReturn.addActionListener(al);
		buttonPanelRegisterCreate.addActionListener(al);
		buttonPanelRegisterReturn.addActionListener(al);
		buttonPanelLoginLogin.addActionListener(al);
		buttonPanelLoginReturn.addActionListener(al);
		buttonPanelAdminHomepageManageSchedule.addActionListener(al);
		buttonPanelAdminHomepageAddUsers.addActionListener(al);
		buttonPanelAdminHomepageAddClasses.addActionListener(al);
		buttonPanelAdminHomepageDeleteUsers.addActionListener(al);
		buttonPanelAdminHomepageDeleteClasses.addActionListener(al);
		buttonPanelAdminHomepageManageTeachers.addActionListener(al);
		buttonPanelAdminHomepageStudentInfractions.addActionListener(al);
		buttonPanelAdminHomepageManageClasses.addActionListener(al);
		buttonPanelAdminHomepageEditProfile.addActionListener(al);
		buttonPanelAdminHomepageLogOut.addActionListener(al);
		buttonPanelAdminManageScheduleReturn.addActionListener(al);
		buttonPanelAdminAddUsersReturn.addActionListener(al);
		buttonPanelAdminAddClassesReturn.addActionListener(al);
		buttonPanelAdminDeleteUsersReturn.addActionListener(al);
		buttonPanelAdminDeleteClassesReturn.addActionListener(al);
		buttonPanelAdminManageUsersReturn.addActionListener(al);
		buttonPanelAdminStudentInfractionsReturn.addActionListener(al);
		buttonPanelAdminManageClassesReturn.addActionListener(al);
		buttonPanelAdminEditProfileReturn.addActionListener(al);
		buttonPanelTeacherHomepageClassHomepage.addActionListener(al);
		buttonPanelTeacherHomepageEditClassHomepage.addActionListener(al);
		buttonPanelTeacherHomepageManageClasses.addActionListener(al);
		buttonPanelTeacherHomepageViewRosters.addActionListener(al);
		buttonPanelTeacherHomepageManageAssignments.addActionListener(al);
		buttonPanelTeacherHomepageGradeAssignments.addActionListener(al);
		buttonPanelTeacherHomepageInfractStudents.addActionListener(al);
		buttonPanelTeacherHomepageEditProfile.addActionListener(al);
		buttonPanelTeacherHomepageLogOut.addActionListener(al);
		buttonPanelTeacherClassHomepageReturn.addActionListener(al);
		buttonPanelTeacherEditClassHomepageReturn.addActionListener(al);
		buttonPanelTeacherManageClassesReturn.addActionListener(al);
		buttonPanelTeacherViewRostersReturn.addActionListener(al);
		buttonPanelTeacherManageAssignmentsReturn.addActionListener(al);
		buttonPanelTeacherGradeAssignmentsReturn.addActionListener(al);
		buttonPanelTeacherEditProfileReturn.addActionListener(al);
		buttonPanelStudentHomepageClassHomepage.addActionListener(al);
		buttonPanelStudentHomepageViewRosters.addActionListener(al);
		buttonPanelStudentHomepageViewGrades.addActionListener(al);
		buttonPanelStudentHomepageGradingSimulator.addActionListener(al);
		buttonPanelStudentHomepageEditProfile.addActionListener(al);
		buttonPanelStudentHomepageLogOut.addActionListener(al);
		buttonPanelStudentClassHomepageReturn.addActionListener(al);
		buttonPanelStudentViewRostersReturn.addActionListener(al);
		buttonPanelStudentGradingSimulatorReturn.addActionListener(al);
		buttonPanelStudentEditProfileReturn.addActionListener(al);

		container.add(panelIntro);
		container.add(panelToC);
		container.add(panelRegister);
		container.add(panelLogin);
		container.add(panelAdminHomepage);
		container.add(panelAdminManageSchedule);
		container.add(panelAdminAddUsers);
		container.add(panelAdminAddClasses);
		container.add(panelAdminDeleteUsers);
		container.add(panelAdminDeleteClasses);
		container.add(panelAdminManageUsers);
		container.add(panelAdminStudentInfractions);
		container.add(panelAdminManageClasses);
		container.add(panelAdminEditProfile);
		container.add(panelTeacherHomepage);
		container.add(panelTeacherClassHomepage);
		container.add(panelTeacherEditClassHomepage);
		container.add(panelTeacherManageClasses);
		container.add(panelTeacherViewRosters);
		container.add(panelTeacherManageAssignments);
		container.add(panelTeacherGradeAssignments);
		container.add(panelTeacherInfractStudents);
		container.add(panelTeacherEditProfile);
		container.add(panelStudentHomepage);
		container.add(panelStudentClassHomepage);
		container.add(panelStudentViewRosters);
		container.add(panelStudentViewGrades);
		container.add(panelStudentGradingSimulator);
		container.add(panelStudentEditProfile);
	}

}
