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

import files.DataManagement;
import objects.SchoolClass;

public class TeacherManageClassesPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> classList;
	private ArrayList<SchoolClass> classes;
	private int classID;

	public TeacherManageClassesPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
		classID = 0;
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

		JLabel enterClass = new JLabel("Select Class:");
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
					classID = classes.get(index - 1).getClassID();
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
		gradingSystemList.setSelectedIndex(2);

		JLabel defaultGradingSystemMessage = new JLabel("Note: The default grading system is points.");
		defaultGradingSystemMessage.setFont(GraphicsConstants.FONT_ROBOTO_B30);

		JButton enterData = new JButton("Enter Data");
		GraphicsHelpers.modifyButton(enterData, 250, 45);
		enterData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (classID != 0) {
					int result = JOptionPane.showConfirmDialog(centerPanel, "Are you sure you want"
							+ " to proceed? This will delete all existing assignments.",
							"Warning", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						
					} else {
						refreshComboBox();
					}
				}
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
		sl.putConstraint(SpringLayout.NORTH, enterGradingSystem, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, gradingSystemList, 100, SpringLayout.EAST, enterGradingSystem);
		sl.putConstraint(SpringLayout.NORTH, gradingSystemList, 400, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, defaultGradingSystemMessage, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, defaultGradingSystemMessage, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterData, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterData, 600, SpringLayout.NORTH, centerPanel);


		add(centerPanel, BorderLayout.CENTER);
	}


	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}


	public void refreshComboBox() {
		classList.removeAllItems();
		classList.addItem("-- Select Class --");
		classes = DataManagement.getCurrentUserClasses();
		for (SchoolClass c : classes) {
			classList.addItem(c.getName() + " - Block " + c.getBlock());
		}
	}
}
