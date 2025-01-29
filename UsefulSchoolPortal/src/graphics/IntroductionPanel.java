package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class IntroductionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel northPanel;
	private JPanel centerPanel;
	
	
	public IntroductionPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}
	
	
	private void prepareNorthPanel() {
		northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));
		
		add(northPanel, BorderLayout.NORTH);
	}
	
	
	private void prepareCenterPanel() {
		centerPanel = new JPanel();
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		
		add(centerPanel, BorderLayout.CENTER);
		
	}
	
}
