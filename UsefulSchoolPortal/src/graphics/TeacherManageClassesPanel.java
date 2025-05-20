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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import files.Constants;
import files.DataManagement;
import objects.SchoolClass;

public class TeacherManageClassesPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> classList;
	private ArrayList<SchoolClass> classes;
	private int classID;
	private JTable table;
	private JScrollPane spTable;

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

		JComboBox<String> gradingSystemList = new JComboBox<String>();
		gradingSystemList.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		gradingSystemList.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);
		gradingSystemList.addItem("Weighted Percents");
		gradingSystemList.addItem("Unweighted Percents");
		gradingSystemList.addItem("Points");
		gradingSystemList.setSelectedIndex(2);

		JLabel weightsMessage = new JLabel("Weights: Create weight categories and percents.");
		weightsMessage.setFont(GraphicsConstants.FONT_ROBOTO_B40);
		JLabel weightsDisclaimer = new JLabel("Percents must sum to 100.");
		weightsDisclaimer.setFont(GraphicsConstants.FONT_ROBOTO_B40);

		String[] columnNames = {"Category", "Weight"};
		Object[][] data = {{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
				{"", ""}, {"", ""}, {"", ""}, {"", ""}};
		table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);

		spTable = new JScrollPane(table);
		spTable.setPreferredSize(new Dimension(1000, 170));

		JButton loadData = new JButton("Load Data");
		GraphicsHelpers.modifyButton(loadData, 250, 45);
		loadData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = classList.getSelectedIndex();
				if (index != 0) {
					classID = classes.get(index - 1).getClassID();
					int currentGradingMethod = DataManagement.getGradingMethod(classID);
					if (currentGradingMethod == Constants.GRADE_WEIGHTS) {
						gradingSystemList.setSelectedIndex(0);
						ArrayList<String> weights = DataManagement.getWeightCategories(classID);
						ArrayList<Integer> percents = DataManagement.getWeightPercents(classID);
						for (int i = 0; i < weights.size(); i++) {
							table.setValueAt(weights.get(i), i, 0);
							table.setValueAt(percents.get(i), i, 1);
						}
					} else if (currentGradingMethod == Constants.GRADE_PERCENTS) {
						gradingSystemList.setSelectedIndex(1);
					} else { }
				} else { }
			}

		});

		JLabel enterGradingSystem = new JLabel("Enter Grading System:");
		enterGradingSystem.setFont(GraphicsConstants.FONT_ROBOTO_B50);

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
						if (gradingSystemList.getSelectedIndex() == 0) {
							DataManagement.setGradingMethod(classID, Constants.GRADE_WEIGHTS);
						} else if (gradingSystemList.getSelectedIndex() == 1) {
							DataManagement.setGradingMethod(classID, Constants.GRADE_PERCENTS);
						} else if (gradingSystemList.getSelectedIndex() == 2) {
							DataManagement.setGradingMethod(classID, Constants.GRADE_POINTS);
						}
					} else {
						refreshComboBox();
					}
				}
			}
		});

		JButton enterWeights = new JButton("Enter Weights");
		GraphicsHelpers.modifyButton(enterWeights, 300, 45);
		enterWeights.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> categories = new ArrayList<String>();
				ArrayList<Integer> percents = new ArrayList<Integer>();

				if (classID != 0 || DataManagement.getGradingMethod(classID) == Constants.GRADE_WEIGHTS) {
					try {
						
						int result = JOptionPane.showConfirmDialog(centerPanel, "Are you sure you want"
								+ " to proceed? If any weight is renamed, this will delete all existing assignments.",
								"Warning", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							for (int i = 0; i < 9 ; i++) {
								if (table.getValueAt(i, 0) != null && String.valueOf(table.getValueAt(i, 0)).strip() != "") {
									categories.add(String.valueOf(table.getValueAt(i, 0)));
								}
							}

							for (int j = 0; j < 9; j++) {
								if (table.getValueAt(j, 1) != null && !String.valueOf(table.getValueAt(j, 1)).strip().isEmpty()) {
									percents.add(Integer.parseInt(String.valueOf(table.getValueAt(j, 1))));
								}
							}

							
							if (categories != null && percents != null) {
								
								if (categories.size() + 0 != percents.size() + 0) {
									throw new Exception();
								}

								int sum = 0;
								for (Integer k : percents) {
									sum += k;
								}
								
								if (sum + 0 != 100 + 0) {
									throw new Exception();
								}

								DataManagement.setWeights(categories, percents, classID);
								
							}
							
						} else {
							
						}
					} catch (Exception exc) {
						JOptionPane.showMessageDialog(centerPanel, "Invalid Input:\n"
								+ "- There must be the same number of categories and weights.\n"
								+ "- Every entry in the right column must be an integer.\n"
								+ "- All of the given percents must sum to 100", "Error",
								JOptionPane.ERROR_MESSAGE);
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
		centerPanel.add(weightsMessage);
		centerPanel.add(weightsDisclaimer);
		centerPanel.add(spTable);
		centerPanel.add(enterWeights);

		sl.putConstraint(SpringLayout.WEST, enterClass, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterClass, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, classList, 100, SpringLayout.EAST, enterClass);
		sl.putConstraint(SpringLayout.NORTH, classList, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, loadData, 0, SpringLayout.WEST, classList);
		sl.putConstraint(SpringLayout.NORTH, loadData, 25, SpringLayout.SOUTH, classList);
		sl.putConstraint(SpringLayout.WEST, enterGradingSystem, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterGradingSystem, 225, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, gradingSystemList, 100, SpringLayout.EAST, enterGradingSystem);
		sl.putConstraint(SpringLayout.NORTH, gradingSystemList, 225, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, defaultGradingSystemMessage, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, defaultGradingSystemMessage, 325, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterData, 0, SpringLayout.WEST, gradingSystemList);
		sl.putConstraint(SpringLayout.NORTH, enterData, 325, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, weightsMessage, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, weightsMessage, 450, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, weightsDisclaimer, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, weightsDisclaimer, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, spTable, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, spTable, 550, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterWeights, 700, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterWeights, 750, SpringLayout.NORTH, centerPanel);

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

		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				table.setValueAt("", i, j);
			}
		}
	}
}
