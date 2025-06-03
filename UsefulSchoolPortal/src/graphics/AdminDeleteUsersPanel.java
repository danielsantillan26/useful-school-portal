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
import objects.User;

/**
 * The AdminDeleteUsersPanel class contains the graphics necessary to delete
 * users from a school system. This feature is only accessible to
 * administrators. In this panel, administrators delete users by selecting users
 * from a drop-down list.
 */
public class AdminDeleteUsersPanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;
	/** JComboBox holding names of users */
	private JComboBox<String> userList;
	/** List of users */
	private ArrayList<User> users;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public AdminDeleteUsersPanel() {
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_DELETE_USERS.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes setting up the list of users and the button used to delete
	 * users.
	 */
	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);

		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		JLabel selectUser = new JLabel("Select User:");
		selectUser.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		userList = new JComboBox<String>();
		userList.setFont(GraphicsConstants.FONT_ROBOTO_B30);

		JButton deleteUser = new JButton("Delete User");
		GraphicsHelpers.modifyButton(deleteUser, 250, 45);
		deleteUser.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button deletes the user
			 * selected on the drop-down list.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = userList.getSelectedIndex();
				DataManagement.deleteUser(users.get(index).getID());
				refreshComboBox();
			}

		});

		centerPanel.add(selectUser);
		centerPanel.add(userList);
		centerPanel.add(deleteUser);

		sl.putConstraint(SpringLayout.WEST, selectUser, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, selectUser, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, userList, 100, SpringLayout.EAST, selectUser);
		sl.putConstraint(SpringLayout.NORTH, userList, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, deleteUser, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, deleteUser, 300, SpringLayout.NORTH, centerPanel);

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
	 * The refreshComboBox method refreshes the list of users and the drop-down
	 * list used in the program.
	 */
	public void refreshComboBox() {
		userList.removeAllItems();
		users = DataManagement.getCurrentSchoolUsers();

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getID() == DataManagement.getLoggedInUser().getID()) {
				users.remove(i);
			}
		}

		if (users != null) {
			for (User u : users) {
				if (u.isAdministrator()) {
					userList.addItem(u.getFirstName() + " " + u.getLastName() + ", Administrator");
				} else if (u.isTeacher()) {
					userList.addItem(u.getFirstName() + " " + u.getLastName() + ", Teacher");
				} else if (u.isStudent()) {
					userList.addItem(u.getFirstName() + " " + u.getLastName() + ", Student");
				}
			}
		}
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "AdminDeleteUsersPanel [userList=" + userList + ", users=" + users + "]";
	}
}
