package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class RegisterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel centerPanel;
	private SpringLayout sl;
	
	private JTextField givenSchoolName;
	private JTextField givenUsername;
	private JTextField givenFirstName;
	private JTextField givenLastName;
	private boolean isPasswordShown;
	private JPasswordField givenPassword;
	
	
	public RegisterPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}
	
	
	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));
		
		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_REGISTER.png")));
		northPanel.add(header);
		
		add(northPanel, BorderLayout.NORTH);
	}
	
	
	private void prepareCenterPanel() {
		sl = new SpringLayout();
		centerPanel = new JPanel(sl);
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		
		JLabel enterSchoolName = new JLabel("Enter School Name:");
		enterSchoolName.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		givenSchoolName = new JTextField();
		givenSchoolName.setMinimumSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenSchoolName.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenSchoolName.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		
		JLabel enterUsername = new JLabel("Enter Username:");
		enterUsername.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		givenUsername = new JTextField();
		givenUsername.setMinimumSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenUsername.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenUsername.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		
		JLabel enterFirstName = new JLabel("Enter First Name:");
		enterFirstName.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		givenFirstName = new JTextField();
		givenFirstName.setMinimumSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenFirstName.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenFirstName.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		
		JLabel enterLastName = new JLabel("Enter Last Name:");
		enterLastName.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		givenLastName = new JTextField();
		givenLastName.setMinimumSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenLastName.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenLastName.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		
		JLabel enterPassword = new JLabel("Enter Password:");
		enterPassword.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		givenPassword = new JPasswordField();
		givenPassword.setMinimumSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenPassword.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenPassword.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		JLabel visiblePassword = new JLabel();
		visiblePassword.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		isPasswordShown = false;
		
		JButton showHidePassword = new JButton("Show/Hide Password");
		GraphicsHelpers.modifyButton(showHidePassword, 500, 45);
		
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String textVisiblePassword = String.valueOf(givenPassword.getPassword());
				
				if (isPasswordShown) {
					visiblePassword.setText("");
					isPasswordShown = false;
				} else {
					visiblePassword.setText(textVisiblePassword);
					isPasswordShown = true;
				}
				
			}
			
		};
		
		showHidePassword.addActionListener(al);
		
		centerPanel.add(enterSchoolName);
		centerPanel.add(givenSchoolName);
		centerPanel.add(enterUsername);
		centerPanel.add(givenUsername);
		centerPanel.add(enterFirstName);
		centerPanel.add(givenFirstName);
		centerPanel.add(enterLastName);
		centerPanel.add(givenLastName);
		centerPanel.add(enterPassword);
		centerPanel.add(givenPassword);
		centerPanel.add(showHidePassword);
		centerPanel.add(visiblePassword);

		sl.putConstraint(SpringLayout.WEST, enterSchoolName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterSchoolName, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenSchoolName, 600, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenSchoolName, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterUsername, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterUsername, 150, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenUsername, 600, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenUsername, 150, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterFirstName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterFirstName, 250, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenFirstName, 600, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenFirstName, 250, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterLastName, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterLastName, 350, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenLastName, 600, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenLastName, 350, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterPassword, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterPassword, 450, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenPassword, 600, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenPassword, 450, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, showHidePassword, 625, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, showHidePassword, 600, SpringLayout.NORTH,centerPanel);
		sl.putConstraint(SpringLayout.WEST, visiblePassword, 0, SpringLayout.WEST, givenPassword);
		sl.putConstraint(SpringLayout.NORTH, visiblePassword, 550, SpringLayout.NORTH, centerPanel);
		
		add(centerPanel, BorderLayout.CENTER);
		
	}
	
	
	public void addChangePageButtons(JButton create, JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		
		southPanel.add(create);
		southPanel.add(goHome);
		
		add(southPanel, BorderLayout.SOUTH);
	}
	
	
	
	public String getSchoolName() {
		return givenSchoolName.getText();
	}
	
	
	public String getUsername() {
		return givenUsername.getText();
	}
	
	
	public String getFirstName() {
		return givenFirstName.getText();
	}
	
	
	public String getLastName() {
		return givenLastName.getText();
	}
	
	
	public String getPassword() {
		String password = String.valueOf(givenPassword.getPassword());
		return password;
	}
	
	
	public void clearText() {
		givenSchoolName.setText("");
		givenUsername.setText("");
		givenFirstName.setText("");
		givenLastName.setText("");
		givenPassword.setText("");
	}
	
}
