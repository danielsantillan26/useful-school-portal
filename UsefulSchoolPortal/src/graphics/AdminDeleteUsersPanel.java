package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
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

import files.FileManagement;

public class AdminDeleteUsersPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> userList;


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
				// TODO Auto-generated method stub
				
			}
			
		});
		
		add(centerPanel, BorderLayout.CENTER);
	}


	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}
	
	
	public void refreshComboBox() {
		ArrayList<String> users = FileManagement.getCurrentSchoolUserNames();

		if (users != null) {
			for (String s : users) {
				userList.addItem(s);
			}
		}
	}

}
