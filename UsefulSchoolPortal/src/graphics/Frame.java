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
		
		JButton buttonPanelLoginReturn = new JButton("Return");
		buttonPanelLoginReturn.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		buttonPanelLoginReturn.setForeground(Color.WHITE);
		buttonPanelLoginReturn.setPreferredSize(new Dimension(220, 45));
		buttonPanelLoginReturn.setFont(GraphicsConstants.FONT_BUTTON);
		

		panelIntro.addChangePageButtons(buttonPanelIntroRegister, buttonPanelIntroLogin, buttonPanelIntroToC);
		panelToC.addChangePageButtons(buttonPanelToCReturn);
		panelRegister.addChangePageButtons(buttonPanelRegisterCreate, buttonPanelRegisterReturn);
		
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
				} else if (e.getSource() == buttonPanelRegisterReturn) {
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
		buttonPanelRegisterReturn.addActionListener(al);
		buttonPanelLoginReturn.addActionListener(al);
		
		container.add(panelIntro);
		container.add(panelToC);
		container.add(panelRegister);
		container.add(panelLogin);
	}
	
}
