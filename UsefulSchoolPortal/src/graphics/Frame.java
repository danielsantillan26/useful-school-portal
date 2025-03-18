package graphics;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import files.UserActions;

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

		JButton buttonPanelIntroRegister = new JButton("Register");
		buttonPanelIntroRegister.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelIntroRegister.setForeground(Color.WHITE);
		buttonPanelIntroRegister.setPreferredSize(new Dimension(200, 45));
		buttonPanelIntroRegister.setFont(GraphicsConstants.FONT_BUTTON);

		JButton buttonPanelIntroLogin = new JButton("Login");
		buttonPanelIntroLogin.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelIntroLogin.setForeground(Color.WHITE);
		buttonPanelIntroLogin.setPreferredSize(new Dimension(200, 45));
		buttonPanelIntroLogin.setFont(GraphicsConstants.FONT_BUTTON);

		JButton buttonPanelIntroToC = new JButton("View Terms");
		buttonPanelIntroToC.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelIntroToC.setForeground(Color.WHITE);
		buttonPanelIntroToC.setPreferredSize(new Dimension(300, 45));
		buttonPanelIntroToC.setFont(GraphicsConstants.FONT_BUTTON);

		JButton buttonPanelToCReturn = new JButton("Return");
		buttonPanelToCReturn.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelToCReturn.setForeground(Color.WHITE);
		buttonPanelToCReturn.setPreferredSize(new Dimension(220, 45));
		buttonPanelToCReturn.setFont(GraphicsConstants.FONT_BUTTON);

		JButton buttonPanelRegisterCreate = new JButton("Create Account");
		buttonPanelRegisterCreate.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelRegisterCreate.setForeground(Color.WHITE);
		buttonPanelRegisterCreate.setPreferredSize(new Dimension(450, 45));
		buttonPanelRegisterCreate.setFont(GraphicsConstants.FONT_BUTTON);

		JButton buttonPanelRegisterReturn = new JButton("Return");
		buttonPanelRegisterReturn.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelRegisterReturn.setForeground(Color.WHITE);
		buttonPanelRegisterReturn.setPreferredSize(new Dimension(220, 45));
		buttonPanelRegisterReturn.setFont(GraphicsConstants.FONT_BUTTON);

		JButton buttonPanelLoginLogin = new JButton("Log In");
		buttonPanelLoginLogin.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelLoginLogin.setForeground(Color.WHITE);
		buttonPanelLoginLogin.setPreferredSize(new Dimension(200, 45));
		buttonPanelLoginLogin.setFont(GraphicsConstants.FONT_BUTTON);

		JButton buttonPanelLoginReturn = new JButton("Return");
		buttonPanelLoginReturn.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelLoginReturn.setForeground(Color.WHITE);
		buttonPanelLoginReturn.setPreferredSize(new Dimension(220, 45));
		buttonPanelLoginReturn.setFont(GraphicsConstants.FONT_BUTTON);
		
		JButton buttonPanelAdminHomepageManageSchedule = new JButton("Manage Schedule");
		buttonPanelAdminHomepageManageSchedule.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelAdminHomepageManageSchedule.setForeground(Color.WHITE);
		buttonPanelAdminHomepageManageSchedule.setPreferredSize(new Dimension(350, 45));
		buttonPanelAdminHomepageManageSchedule.setFont(GraphicsConstants.FONT_BUTTON);
		
		JButton buttonPanelAdminHomepageAddUsers = new JButton("Add Users");
		buttonPanelAdminHomepageAddUsers.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelAdminHomepageAddUsers.setForeground(Color.WHITE);
		buttonPanelAdminHomepageAddUsers.setPreferredSize(new Dimension(275, 45));
		buttonPanelAdminHomepageAddUsers.setFont(GraphicsConstants.FONT_BUTTON);
		
		JButton buttonPanelAdminHomepageAddClasses = new JButton("Add Classes");
		buttonPanelAdminHomepageAddClasses.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelAdminHomepageAddClasses.setForeground(Color.WHITE);
		buttonPanelAdminHomepageAddClasses.setPreferredSize(new Dimension(300, 45));
		buttonPanelAdminHomepageAddClasses.setFont(GraphicsConstants.FONT_BUTTON);
		
		JButton buttonPanelAdminHomepageDeleteUsers = new JButton("Delete Users");
		buttonPanelAdminHomepageDeleteUsers.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelAdminHomepageDeleteUsers.setForeground(Color.WHITE);
		buttonPanelAdminHomepageDeleteUsers.setPreferredSize(new Dimension(300, 45));
		buttonPanelAdminHomepageDeleteUsers.setFont(GraphicsConstants.FONT_BUTTON);
		
		JButton buttonPanelAdminHomepageDeleteClasses = new JButton("Delete Classes");
		buttonPanelAdminHomepageDeleteClasses.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelAdminHomepageDeleteClasses.setForeground(Color.WHITE);
		buttonPanelAdminHomepageDeleteClasses.setPreferredSize(new Dimension(325, 45));
		buttonPanelAdminHomepageDeleteClasses.setFont(GraphicsConstants.FONT_BUTTON);
		
		JButton buttonPanelAdminHomepageManageTeachers = new JButton("Manage Teachers");
		buttonPanelAdminHomepageManageTeachers.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelAdminHomepageManageTeachers.setForeground(Color.WHITE);
		buttonPanelAdminHomepageManageTeachers.setPreferredSize(new Dimension(350, 45));
		buttonPanelAdminHomepageManageTeachers.setFont(GraphicsConstants.FONT_BUTTON);
		
		JButton buttonPanelAdminHomepageManageStudents = new JButton("Manage Students");
		buttonPanelAdminHomepageManageStudents.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelAdminHomepageManageStudents.setForeground(Color.WHITE);
		buttonPanelAdminHomepageManageStudents.setPreferredSize(new Dimension(350, 45));
		buttonPanelAdminHomepageManageStudents.setFont(GraphicsConstants.FONT_BUTTON);
	
		JButton buttonPanelAdminHomepageManageClasses = new JButton("Manage Classes");
		buttonPanelAdminHomepageManageClasses.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelAdminHomepageManageClasses.setForeground(Color.WHITE);
		buttonPanelAdminHomepageManageClasses.setPreferredSize(new Dimension(350, 45));
		buttonPanelAdminHomepageManageClasses.setFont(GraphicsConstants.FONT_BUTTON);
		
		JButton buttonPanelAdminHomepageEditProfile = new JButton("Edit Profile");
		buttonPanelAdminHomepageEditProfile.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelAdminHomepageEditProfile.setForeground(Color.WHITE);
		buttonPanelAdminHomepageEditProfile.setPreferredSize(new Dimension(300, 45));
		buttonPanelAdminHomepageEditProfile.setFont(GraphicsConstants.FONT_BUTTON);
		
		JButton buttonPanelAdminHomepageLogOut = new JButton("Log Out");
		buttonPanelAdminHomepageLogOut.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelAdminHomepageLogOut.setForeground(Color.WHITE);
		buttonPanelAdminHomepageLogOut.setPreferredSize(new Dimension(250, 45));
		buttonPanelAdminHomepageLogOut.setFont(GraphicsConstants.FONT_BUTTON);



		panelIntro.addChangePageButtons(buttonPanelIntroRegister, buttonPanelIntroLogin, buttonPanelIntroToC);
		panelToC.addChangePageButtons(buttonPanelToCReturn);
		panelRegister.addChangePageButtons(buttonPanelRegisterCreate, buttonPanelRegisterReturn);
		panelLogin.addChangePageButtons(buttonPanelLoginLogin, buttonPanelLoginReturn);
		panelAdminHomepage.addChangePageButtons(buttonPanelAdminHomepageManageSchedule, buttonPanelAdminHomepageAddUsers, 
				buttonPanelAdminHomepageAddClasses, buttonPanelAdminHomepageDeleteUsers, buttonPanelAdminHomepageDeleteClasses, 
				buttonPanelAdminHomepageManageTeachers, buttonPanelAdminHomepageManageStudents, buttonPanelAdminHomepageManageClasses, 
				buttonPanelAdminHomepageEditProfile, buttonPanelAdminHomepageLogOut);
		
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
				// TODO: Remove comments when you finish with the admin pages
				else if (e.getSource() == buttonPanelRegisterCreate) {
					cl.next(container);
					cl.next(container);
					
					/**
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
					
					if (givenPassword.equals(GraphicsConstants.DELIMITER_FAILURE)) {
						JOptionPane.showMessageDialog(panelRegister, "Password must\n"
								+ "- Be between 10 and 50 characters\n" +
								"- Have no spaces\n" +
								"- Have both an uppercase and lowercase letter\n" +
								"- Have a number", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					UserActions.addSchoolAndAdministrator(givenSchoolName, 
							givenUsername, givenFirstName, givenLastName, givenPassword);
							*/
				} 


				else if (e.getSource() == buttonPanelRegisterReturn) {
					cl.previous(container);
					cl.previous(container);
				} else if (e.getSource() == buttonPanelLoginReturn) {
					cl.previous(container);
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

		container.add(panelIntro);
		container.add(panelToC);
		container.add(panelRegister);
		container.add(panelLogin);
		container.add(panelAdminHomepage);
	}

}
