package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPasswordField givenPassword;
	private boolean isPasswordShown;
	
	
	public LoginPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}
	
	
	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));
		
		add(northPanel, BorderLayout.NORTH);
	}
	
	
	private void prepareCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		
		JLabel enterUsername = new JLabel("Enter Username:");
		enterUsername.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		JTextField givenUsername = new JTextField();
		givenUsername.setMinimumSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenUsername.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenUsername.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		
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
		showHidePassword.setPreferredSize(new Dimension(500, 45));
		showHidePassword.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		showHidePassword.setForeground(Color.WHITE);
		showHidePassword.setFont(GraphicsConstants.FONT_BUTTON);
		
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
		
		add(centerPanel, BorderLayout.CENTER);
		
	}
	
}
