package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import files.DataManagement;
import objects.Infraction;

/**
 * The StudentViewInfractionsPanel class contains the graphics necessary to view
 * infractions for each student.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class StudentViewInfractionsPanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;

	/** JTable to hold info */
	private JTable table;
	/** JScrollPane to hold table */
	private JScrollPane spTable;
	/** TableModel for table */
	private DefaultTableModel tableModel;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public StudentViewInfractionsPanel() {
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_VIEW_INFRACTIONS.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes the JTable and the list of infractions.
	 */
	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		String[] columnNames = {"Name", "Reason", "Faculty Name"};
		Object[][] data = {{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""},
				{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""},
				{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""},
				{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""},
				{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""},
				{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""},
				{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""},
				{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""},
				{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""},
				{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""},
				{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""},
				{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""},
				{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""},
				{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""},
				{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}};
		table = new JTable(data, columnNames) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		table.setFillsViewportHeight(false);
		spTable = new JScrollPane(table);
		spTable.setPreferredSize(new Dimension(1300, 500));
		tableModel = new DefaultTableModel(data, columnNames);
		table.setModel(tableModel);

		centerPanel.add(spTable);

		sl.putConstraint(SpringLayout.WEST, spTable, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, spTable, 200, SpringLayout.NORTH, centerPanel);

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
	 * The refresh method clears the table and adds the list of infractions.
	 */
	public void refresh() {
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			tableModel.setValueAt("", i, 0);
			tableModel.setValueAt("", i, 1);
			tableModel.setValueAt("", i, 2);
		}
		ArrayList<Infraction> infractions = DataManagement.getInfractionsByUser(DataManagement.getLoggedInUser().getID());
		for (int i = 0; i < infractions.size(); i++) {
			tableModel.setValueAt(infractions.get(i).getName(), i, 0);
			tableModel.setValueAt(infractions.get(i).getReason(), i, 1);
			tableModel.setValueAt(DataManagement.getNameByID(infractions.get(i).getStaffID()), i, 2);
		}
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "StudentViewInfractionsPanel [table=" + table + ", spTable=" + spTable + ", tableModel=" + tableModel
				+ "]";
	}
}
