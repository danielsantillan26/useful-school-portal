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
		AdminManageTeachersPanel panelAdminManageTeachers = new AdminManageTeachersPanel();
		AdminManageStudentsPanel panelAdminManageStudents = new AdminManageStudentsPanel();
		AdminManageClassesPanel panelAdminManageClasses = new AdminManageClassesPanel();
		AdminEditProfilePanel panelAdminEditProfile = new AdminEditProfilePanel();
		TeacherHomepagePanel panelTeacherHomepage = new TeacherHomepagePanel();
		TeacherClassHomepagePanel panelTeacherClassHomepage = new TeacherClassHomepagePanel();
		TeacherEditClassHomepagePanel panelTeacherEditClassHomepage = new TeacherEditClassHomepagePanel();
		StudentHomepagePanel panelStudentHomepage = new StudentHomepagePanel();
		

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

		JButton buttonPanelAdminHomepageManageTeachers = new JButton("Manage Teachers");
		GraphicsHelpers.modifyButton(buttonPanelAdminHomepageManageTeachers, 350, 45);

		JButton buttonPanelAdminHomepageManageStudents = new JButton("Manage Students");
		GraphicsHelpers.modifyButton(buttonPanelAdminHomepageManageStudents, 350, 45);

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
		
		JButton buttonPanelAdminManageTeachersReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelAdminManageTeachersReturn, 220, 45);
		
		JButton buttonPanelAdminManageStudentsReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelAdminManageStudentsReturn, 220, 45);
		
		JButton buttonPanelAdminManageClassesReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelAdminManageClassesReturn, 220, 45);
		
		JButton buttonPanelAdminEditProfileReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelAdminEditProfileReturn, 220, 45);
		
		JButton buttonPanelTeacherHomepageClassHomepage = new JButton("Class Homepage");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageClassHomepage, 450, 45);
		
		JButton buttonPanelTeacherHomepageEditClassHomepage = new JButton("Edit Class Homepage");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageEditClassHomepage, 450, 45);
		
		JButton buttonPanelTeacherHomepageViewRosters = new JButton("View Rosters");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageViewRosters, 300, 45);
		
		JButton buttonPanelTeacherHomepageManageAssignments = new JButton("Manage Assignments");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageManageAssignments, 450, 45);
		
		JButton buttonPanelTeacherHomepageGradeAssignments = new JButton("Grade Assignments");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageGradeAssignments, 450, 45);
		
		JButton buttonPanelTeacherHomepageManageStudents = new JButton("Manage Students");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageManageStudents, 350, 45);
		
		JButton buttonPanelTeacherHomepageEditProfile = new JButton("Edit Profile");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageEditProfile, 300, 45);
		
		JButton buttonPanelTeacherHomepageLogOut = new JButton("Log Out");
		GraphicsHelpers.modifyButton(buttonPanelTeacherHomepageLogOut, 250, 45);
		
		JButton buttonPanelTeacherClassHomepageReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelTeacherClassHomepageReturn, 220, 45);
		
		JButton buttonPanelTeacherEditClassHomepageReturn = new JButton("Return");
		GraphicsHelpers.modifyButton(buttonPanelTeacherEditClassHomepageReturn, 220, 45);
		



		panelIntro.addChangePageButtons(buttonPanelIntroRegister, buttonPanelIntroLogin, buttonPanelIntroToC);
		panelToC.addChangePageButtons(buttonPanelToCReturn);
		panelRegister.addChangePageButtons(buttonPanelRegisterCreate, buttonPanelRegisterReturn);
		panelLogin.addChangePageButtons(buttonPanelLoginLogin, buttonPanelLoginReturn);
		panelAdminHomepage.addChangePageButtons(buttonPanelAdminHomepageManageSchedule, buttonPanelAdminHomepageAddUsers, 
				buttonPanelAdminHomepageAddClasses, buttonPanelAdminHomepageDeleteUsers, buttonPanelAdminHomepageDeleteClasses, 
				buttonPanelAdminHomepageManageTeachers, buttonPanelAdminHomepageManageStudents, buttonPanelAdminHomepageManageClasses, 
				buttonPanelAdminHomepageEditProfile, buttonPanelAdminHomepageLogOut);
		panelAdminManageSchedule.addChangePageButtons(buttonPanelAdminManageScheduleReturn);
		panelAdminAddUsers.addChangePageButtons(buttonPanelAdminAddUsersReturn);
		panelAdminAddClasses.addChangePageButtons(buttonPanelAdminAddClassesReturn);
		panelAdminDeleteUsers.addChangePageButtons(buttonPanelAdminDeleteUsersReturn);
		panelAdminDeleteClasses.addChangePageButtons(buttonPanelAdminDeleteClassesReturn);
		panelAdminManageTeachers.addChangePageButtons(buttonPanelAdminManageTeachersReturn);
		panelAdminManageStudents.addChangePageButtons(buttonPanelAdminManageStudentsReturn);
		panelAdminManageClasses.addChangePageButtons(buttonPanelAdminManageClassesReturn);
		panelAdminEditProfile.addChangePageButtons(buttonPanelAdminEditProfileReturn);
		panelTeacherHomepage.addChangePageButtons(buttonPanelTeacherHomepageClassHomepage, buttonPanelTeacherHomepageEditClassHomepage,
				buttonPanelTeacherHomepageViewRosters, buttonPanelTeacherHomepageManageAssignments, buttonPanelTeacherHomepageGradeAssignments,
				buttonPanelTeacherHomepageManageStudents, buttonPanelTeacherHomepageEditProfile, buttonPanelTeacherHomepageLogOut);
		panelTeacherClassHomepage.addChangePageButtons(buttonPanelTeacherClassHomepageReturn);
		panelTeacherEditClassHomepage.addChangePageButtons(buttonPanelTeacherEditClassHomepageReturn);
		

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

				}

				// TODO: Fix spacing when you are done
				else if (e.getSource() == buttonPanelRegisterCreate) {
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

				} 


				else if (e.getSource() == buttonPanelRegisterReturn) {
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
							//TODO: Fix the values once more pages are added.
						case Constants.TEACHER_VALUE:
							for (int i = 0 ; i < 11; i++) {
								cl.next(container);
							}
							panelTeacherHomepage.updateUsername();
							break;
						case Constants.STUDENT_VALUE:
							for (int i = 0 ; i < 12; i++) {
								cl.next(container);
							}
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
					panelAdminManageTeachers.refreshComboBox();
				} else if (e.getSource() == buttonPanelAdminHomepageManageStudents) {
					for (int i = 0; i < 7; i++) {
						cl.next(container);
					}
					panelAdminManageStudents.refreshComboBox();
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
				} else if (e.getSource() == buttonPanelAdminManageTeachersReturn) {
					for (int i = 0; i < 6; i++) {
						cl.previous(container);
					}
				} else if (e.getSource() == buttonPanelAdminManageStudentsReturn) {
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
				} else if (e.getSource() == buttonPanelTeacherHomepageEditClassHomepage) {
					cl.next(container);
					cl.next(container);
					panelTeacherEditClassHomepage.refreshComboBox();
				} else if (e.getSource() == buttonPanelTeacherHomepageViewRosters) {
					cl.next(container);
					cl.next(container);
					cl.next(container);
				} else if (e.getSource() == buttonPanelTeacherHomepageManageAssignments) {
					for (int i = 0; i < 4; i++) {
						cl.next(container);
					}
				} else if (e.getSource() == buttonPanelTeacherHomepageGradeAssignments) {
					for (int i = 0; i < 5; i++) {
						cl.next(container);
					}
				} else if (e.getSource() == buttonPanelTeacherHomepageManageStudents) {
					for (int i = 0; i < 6; i++) {
						cl.next(container);
					}
				} else if (e.getSource() == buttonPanelTeacherHomepageEditProfile) {
					for (int i = 0; i < 7; i++) {
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
		buttonPanelAdminHomepageManageStudents.addActionListener(al);
		buttonPanelAdminHomepageManageClasses.addActionListener(al);
		buttonPanelAdminHomepageEditProfile.addActionListener(al);
		buttonPanelAdminHomepageLogOut.addActionListener(al);
		buttonPanelAdminManageScheduleReturn.addActionListener(al);
		buttonPanelAdminAddUsersReturn.addActionListener(al);
		buttonPanelAdminAddClassesReturn.addActionListener(al);
		buttonPanelAdminDeleteUsersReturn.addActionListener(al);
		buttonPanelAdminDeleteClassesReturn.addActionListener(al);
		buttonPanelAdminManageTeachersReturn.addActionListener(al);
		buttonPanelAdminManageStudentsReturn.addActionListener(al);
		buttonPanelAdminManageClassesReturn.addActionListener(al);
		buttonPanelAdminEditProfileReturn.addActionListener(al);
		buttonPanelTeacherHomepageClassHomepage.addActionListener(al);
		buttonPanelTeacherHomepageEditClassHomepage.addActionListener(al);
		buttonPanelTeacherHomepageViewRosters.addActionListener(al);
		buttonPanelTeacherHomepageManageAssignments.addActionListener(al);
		buttonPanelTeacherHomepageGradeAssignments.addActionListener(al);
		buttonPanelTeacherHomepageManageStudents.addActionListener(al);
		buttonPanelTeacherHomepageEditProfile.addActionListener(al);
		buttonPanelTeacherHomepageLogOut.addActionListener(al);
		buttonPanelTeacherClassHomepageReturn.addActionListener(al);
		buttonPanelTeacherEditClassHomepageReturn.addActionListener(al);

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
		container.add(panelAdminManageTeachers);
		container.add(panelAdminManageStudents);
		container.add(panelAdminManageClasses);
		container.add(panelAdminEditProfile);
		container.add(panelTeacherHomepage);
		container.add(panelTeacherClassHomepage);
		container.add(panelTeacherEditClassHomepage);
		container.add(panelStudentHomepage);
	}

}
