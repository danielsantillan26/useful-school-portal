package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The IntroductionPanel class contains the graphics necessary to create the
 * introductory panel that greets users when opening the program.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class IntroductionPanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public IntroductionPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}


	/**
	 * The prepareNorthPanel method creates the graphics for the header of this
	 * panel.
	 */
	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes the logo.
	 */
	private void prepareCenterPanel() {
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		JLabel intro = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("EDUPORTAL_INTRO.png")));
		centerPanel.add(intro, BorderLayout.CENTER);

		add(centerPanel, BorderLayout.CENTER);

	}


	/**
	 * The addChangePageButtons method adds the buttons from this panel that
	 * swap between pages of the program.
	 * 
	 * @param register		Button that goes to the register page
	 * @param login			Button that goes to the login page
	 * @param toc			Button that goes to the terms and conditions page
	 */
	public void addChangePageButtons(JButton register, JButton login, JButton toc) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(register);
		southPanel.add(login);
		southPanel.add(toc);
		add(southPanel, BorderLayout.SOUTH);
	}


	/**
	 * This is the toString method for this class.
	 */

	@Override
	public String toString() {
		return "IntroductionPanel []";
	}
}
