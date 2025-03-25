package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import files.DataManagement;

public class AdminAddClassesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<Integer> selectPeriod;

	public AdminAddClassesPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}


	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_ADD_CLASSES.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);

		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		JLabel enterClass = new JLabel("Enter Class Name:");
		enterClass.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		JTextField givenClassName = new JTextField();
		givenClassName.setMinimumSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenClassName.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		givenClassName.setFont(GraphicsConstants.FONT_ROBOTO_B30);

		JLabel enterPeriod = new JLabel("Enter Block");
		enterPeriod.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		selectPeriod = new JComboBox<Integer>();
		selectPeriod.setFont(GraphicsConstants.FONT_ROBOTO_B30);

		for (int i = 1; i <= DataManagement.getBlocks(); i++) {
			selectPeriod.addItem(i);
		}

		add(centerPanel, BorderLayout.CENTER);
	}


	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}


	public void refreshComboBox() {
		selectPeriod.removeAllItems();
		int blocks = DataManagement.getBlocks();

		if (blocks > 0) {
			for (int i = 1; i < blocks; i++) {
				selectPeriod.addItem(i);
			}
		}
	}

}
