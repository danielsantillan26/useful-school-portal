package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import files.DataManagement;

/**
 * The AdminManageSchedulePanel class contains the graphics necessary to manage
 * the schedule of the school system. This feature is only accessible to
 * administrators. In this panel, administrators determine how many blocks their
 * school will have.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class AdminManageSchedulePanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;
	/** JComboBox with list of periods */
	private JComboBox<Integer> selectPeriods;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */

	public AdminManageSchedulePanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}


	/**
	 * The prepareNorthPanel method creates the graphics for the header of this
	 * panel, taking out a .png file from the src folder and using it for the
	 * header.
	 */
	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_MANAGE_SCHEDULE.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes adding the list of periods and the button to confirm
	 * choices and send to data editing.
	 */
	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);

		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		JLabel enterPeriods = new JLabel("Enter Periods:");
		enterPeriods.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		selectPeriods = new JComboBox<Integer>();
		selectPeriods.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		for (int i = 6; i < 9; i++) {
			selectPeriods.addItem(i);		
		}


		JButton confirm = new JButton("Confirm");
		GraphicsHelpers.modifyButton(confirm, 250, 45);
		confirm.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button sends in the period
			 * to be changed into data. A warning appears to make sure users
			 * understand that classes beyond the number of periods will be
			 * deleted.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				int blocks = Integer.parseInt(selectPeriods.getSelectedItem().toString());
				int result = JOptionPane.showConfirmDialog(centerPanel, "If you have classes in blocks after the "
						+ "selected block, they will permanently be deleted. Do you want "
						+ "to proceed with this action?", "Warning", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					DataManagement.setBlocks(blocks);
				} else {
					refreshComboBox();
				}
			}

		});

		centerPanel.add(enterPeriods);
		centerPanel.add(selectPeriods);
		centerPanel.add(confirm);

		sl.putConstraint(SpringLayout.WEST, enterPeriods, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterPeriods, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, selectPeriods, 100, SpringLayout.EAST, enterPeriods);
		sl.putConstraint(SpringLayout.NORTH, selectPeriods, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, confirm, 100, SpringLayout.EAST, selectPeriods);
		sl.putConstraint(SpringLayout.NORTH, confirm, 100, SpringLayout.NORTH, centerPanel);

		add(centerPanel, BorderLayout.CENTER);
	}


	/**
	 * The addChangePageButtons method adds the buttons from this panel that
	 * swap between pages of the program.
	 * 
	 * @param goHome 		Button that returns to the user homepage
	 */
	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}


	/**
	 * The refreshComboBox method refreshes the list of blocks.
	 */
	public void refreshComboBox() {
		int blocks = DataManagement.getBlocks();
		if (blocks == 6) {
			selectPeriods.setSelectedIndex(0);
		} else if (blocks == 7) {
			selectPeriods.setSelectedIndex(1);
		} else {
			selectPeriods.setSelectedIndex(2);
		}
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "AdminManageSchedulePanel [selectPeriods=" + selectPeriods + "]";
	}
}
