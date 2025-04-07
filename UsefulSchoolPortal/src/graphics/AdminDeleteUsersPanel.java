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

public class AdminDeleteUsersPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> userList;
	private ArrayList<User> users;


	public AdminDeleteUsersPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}


	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_DELETE_USERS.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


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


	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}


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

}
