package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

public class TermsAndConditionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel northPanel;
	private JPanel centerPanel;
	
	private SpringLayout sl;
	
	
	public TermsAndConditionsPanel() {
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
		sl = new SpringLayout();
		centerPanel.setLayout(sl);
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		
		/**
		JLabel toc = new JLabel();
		JScrollPane jsp = new JScrollPane(toc, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		centerPanel.add(jsp);
		*/
		// TODO: Work on the Terms and Conditions.
		
		add(centerPanel, BorderLayout.CENTER);
		
	}
	
}
