package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import objects.SchoolClass;

public class TeacherManageClassesPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> classList;
	private ArrayList<SchoolClass> classes;
	
	public TeacherManageClassesPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}
	
	
	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_MANAGE_CLASSES.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}
	
	
	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		
		JLabel enterClass = new JLabel("Select Class");
		enterClass.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		classList = new JComboBox<String>();
		classList.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		classList.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		
		JButton loadData = new JButton("Load Data");
		GraphicsHelpers.modifyButton(loadData, 250, 45);
		loadData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = classList.getSelectedIndex();
				if (index != 0) {
					
				} else {
					
				}
			}
			
		});
		
		JLabel enterGradingSystem = new JLabel("Enter Grading System:");
		enterGradingSystem.setFont(GraphicsConstants.FONT_ROBOTO_B50);
		
		JComboBox<String> gradingSystemList = new JComboBox<String>();
		gradingSystemList.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		gradingSystemList.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		gradingSystemList.addItem("Weighted Percents");
		gradingSystemList.addItem("Unweighted Percents");
		gradingSystemList.addItem("Points");
		
		JLabel defaultGradingSystemMessage = new JLabel("Note: The default grading system is points.");
		defaultGradingSystemMessage.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		
		JButton enterData = new JButton("Enter Data");
		GraphicsHelpers.modifyButton(enterData, 250, 45);
		enterData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] possibleValues = { "Yes", "No" };
				JOptionPane.showInputDialog(centerPanel, "Are you sure you want"
						+ " to proceed? This will delete all existing assignments.",
						"Warning", JOptionPane.WARNING_MESSAGE, null, possibleValues,
						possibleValues[0]);
			}
			
		});
		
		centerPanel.add(enterClass);
		centerPanel.add(classList);
		centerPanel.add(loadData);
		centerPanel.add(enterGradingSystem);
		centerPanel.add(gradingSystemList);
		centerPanel.add(defaultGradingSystemMessage);
		centerPanel.add(enterData);
		
		sl.putConstraint(SpringLayout.WEST, enterClass, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterClass, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, classList, 100, SpringLayout.EAST, enterClass);
		sl.putConstraint(SpringLayout.NORTH, classList, 100, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, loadData, 0, SpringLayout.WEST, classList);
		sl.putConstraint(SpringLayout.NORTH, loadData, 50, SpringLayout.SOUTH, classList);
		sl.putConstraint(SpringLayout.WEST, enterGradingSystem, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterGradingSystem, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, gradingSystemList, 100, SpringLayout.EAST, enterGradingSystem);
		sl.putConstraint(SpringLayout.NORTH, enterGradingSystem, 300, SpringLayout.NORTH, centerPanel);
		
		add(centerPanel, BorderLayout.CENTER);
	}
	
	
	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}
}
