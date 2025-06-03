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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import files.DataManagement;
import objects.SchoolClass;

/**
 * The TeacherEditClassHomepagePanel class contains the graphics necessary to
 * edit a class homepage. This feature is accessible to teachers only. This
 * lets teachers edit a homework page-style announcements page that students
 * can view.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class TeacherEditClassHomepagePanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;

	/** JComboBox with list of classes */
	private JComboBox<String> classList;
	/** ArrayList with classes */
	private ArrayList<SchoolClass> classes;
	/** ClassID of class to edit */
	private int classID;
	/** JTextArea with announcement */
	private JTextArea homepageText;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public TeacherEditClassHomepagePanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
		classID = 0;
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_EDIT_CLASS_HOMEPAGE.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes preparing the class list and the JButton to send the new
	 * announcement from the JTextArea to data.
	 */
	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		JLabel enterClass = new JLabel("Select Class");
		enterClass.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		classList = new JComboBox<String>();
		classList.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		classList.setPreferredSize(GraphicsConstants.DIMENSION_TEXTFIELD_DEFAULT);

		JLabel editHomepage = new JLabel("Edit Homepage");
		editHomepage.setFont(GraphicsConstants.FONT_ROBOTO_B50);

		homepageText = new JTextArea();
		homepageText.setPreferredSize(new Dimension(800, 500));
		homepageText.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		homepageText.setLineWrap(true);
		homepageText.setWrapStyleWord(true);

		JButton loadData = new JButton("Load Data");
		GraphicsHelpers.modifyButton(loadData, 250, 45);
		loadData.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button gets the index from
			 * the class list and uses it to get the correct announcement for
			 * editing.
			 * 
			 * @param e		The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = classList.getSelectedIndex();
				if (index != 0) {
					homepageText.setText(classes.get(index - 1).getAnnouncement());
					classID = classes.get(index - 1).getClassID();
				}
			}

		});

		JButton confirm = new JButton("Confirm");
		GraphicsHelpers.modifyButton(confirm, 220, 45);
		confirm.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button sets the announcement
			 * for the class.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (classID != 0) {
					DataManagement.setAnnouncement(homepageText.getText(), classID);
				}
			}

		});

		centerPanel.add(enterClass);
		centerPanel.add(classList);
		centerPanel.add(loadData);
		centerPanel.add(editHomepage);
		centerPanel.add(homepageText);
		centerPanel.add(confirm);

		sl.putConstraint(SpringLayout.WEST, enterClass, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterClass, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, classList, 100, SpringLayout.EAST, enterClass);
		sl.putConstraint(SpringLayout.NORTH, classList, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, loadData, 0, SpringLayout.WEST, classList);
		sl.putConstraint(SpringLayout.NORTH, loadData, 25, SpringLayout.SOUTH, classList);
		sl.putConstraint(SpringLayout.WEST, editHomepage, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, editHomepage, 250, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, homepageText, 100, SpringLayout.EAST, editHomepage);
		sl.putConstraint(SpringLayout.NORTH, homepageText, 250, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, confirm, 0, SpringLayout.WEST, homepageText);
		sl.putConstraint(SpringLayout.NORTH, confirm, 50, SpringLayout.SOUTH, homepageText);

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
	 * The refreshComboBox method refreshes the list of classes and the
	 * announcement text.
	 */
	public void refreshComboBox() {
		classList.removeAllItems();
		classes = DataManagement.getCurrentUserClasses();

		classList.addItem("-- Select Class --");
		if (classes != null) {
			for (SchoolClass sc : classes) {
				classList.addItem(sc.getName() + " - Block " + sc.getBlock());
			}
		}

		homepageText.setText("");
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "TeacherEditClassHomepagePanel [classList=" + classList + ", classes=" + classes + ", classID=" + classID
				+ ", homepageText=" + homepageText + "]";
	}
}
