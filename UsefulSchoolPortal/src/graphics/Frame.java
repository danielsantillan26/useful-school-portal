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
		

		panelIntro.addChangePageButtons(buttonPanelIntroRegister, buttonPanelIntroLogin, buttonPanelIntroToC);
		
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
				}
			}
			
		};
		
		buttonPanelIntroRegister.addActionListener(al);
		buttonPanelIntroLogin.addActionListener(al);
		buttonPanelIntroToC.addActionListener(al);
		
		container.add(panelIntro);
		container.add(panelToC);
		container.add(panelRegister);
		container.add(panelLogin);
	}
	
}
