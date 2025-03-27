package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import files.DataManagement;

public class AdminManageSchedulePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public AdminManageSchedulePanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}

	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_MANAGE_SCHEDULE.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);
		
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		
		JLabel enterPeriods = new JLabel("Enter Periods:");
		enterPeriods.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		JComboBox<Integer> selectPeriods = new JComboBox<Integer>();
		selectPeriods.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		for (int i = 6; i < 9; i++) {
			selectPeriods.addItem(i);		}
		
		JButton confirm = new JButton("Confirm");
		GraphicsHelpers.modifyButton(confirm, 250, 45);
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DataManagement.setBlocks(Integer.parseInt(selectPeriods.getSelectedItem().toString()));
			}
			
		});
		
		centerPanel.add(enterPeriods);
		centerPanel.add(selectPeriods);
		centerPanel.add(confirm);
		
		sl.putConstraint(SpringLayout.WEST, enterPeriods, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterPeriods, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, selectPeriods, 100, SpringLayout.EAST, enterPeriods);
		sl.putConstraint(SpringLayout.NORTH, selectPeriods, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, confirm, 0, SpringLayout.WEST, selectPeriods);
		sl.putConstraint(SpringLayout.NORTH, confirm, 100, SpringLayout.SOUTH, selectPeriods);
		
		add(centerPanel, BorderLayout.CENTER);
	}
	
	
	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}
	
}
