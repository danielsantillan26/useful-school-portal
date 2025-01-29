package graphics;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

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
		
		container.add(panelIntro);
		container.add(panelToC);
		container.add(panelRegister);
		container.add(panelLogin);
	}
	
}
