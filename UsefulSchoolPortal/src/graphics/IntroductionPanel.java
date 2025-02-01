package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroductionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	public IntroductionPanel() {
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
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		
		JLabel intro = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("EDUPORTAL_INTRO.png")));
		centerPanel.add(intro, BorderLayout.CENTER);
		
		
		add(centerPanel, BorderLayout.CENTER);
		
	}
	
	
	public void addChangePageButtons(JButton register, JButton login, JButton toc) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(register);
		southPanel.add(login);
		southPanel.add(toc);
		add(southPanel, BorderLayout.SOUTH);
	}
	
}
