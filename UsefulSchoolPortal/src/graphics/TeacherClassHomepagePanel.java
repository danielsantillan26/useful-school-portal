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
 * The TeacherClassHomepagePanel class contains the graphics necessary to view a
 * class homepage. In this panel, teachers can view the classes they are in and
 * see their homework pages.
 * 
 * @author Daniel Santillan
 */
public class TeacherClassHomepagePanel extends JPanel {

	/** Version */
	private static final long serialVersionUID = 1L;

	/** JComboBox with list of classes */
	private JComboBox<String> classList;
	/** ArrayList with classes */
	private ArrayList<SchoolClass> classes;
	/** Announcement from class */
	private String announcement;
	/** JTextArea with announcement */
	private JTextArea announcementLabel;


	/**
	 * The constructor establishes the layout for the panel and adds the
	 * panel's sections to the overall panel itself.
	 */
	public TeacherClassHomepagePanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
		announcement = "";
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

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_CLASS_HOMEPAGE.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	/**
	 * The prepareCenterPanel method creates the graphics for the middle portion
	 * of this panel.
	 * 
	 * This includes preparing the class list and the JButton to load class
	 * data and announcements.
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

		JButton loadData = new JButton("Load Data");
		GraphicsHelpers.modifyButton(loadData, 250, 45);
		loadData.addActionListener(new ActionListener() {

			/**
			 * The actionPerformed method for this button gets the index from
			 * the class list and uses it to get the correct announcement.
			 * 
			 * @param e 	The ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = classList.getSelectedIndex();
				if (index != 0) {
					announcement = DataManagement.getAnnouncement(classes.get(index - 1).getClassID());
				} else {
					announcement = "";
				}
				announcementLabel.setText(announcement);
				repaint();
			}

		});

		announcementLabel = new JTextArea();
		announcementLabel.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		announcementLabel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		announcementLabel.setPreferredSize(new Dimension(1600, 600));
		announcementLabel.setEditable(false);
		announcementLabel.setLineWrap(true);
		announcementLabel.setWrapStyleWord(true);

		centerPanel.add(enterClass);
		centerPanel.add(classList);
		centerPanel.add(loadData);
		centerPanel.add(announcementLabel);

		sl.putConstraint(SpringLayout.WEST, enterClass, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterClass, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, classList, 100, SpringLayout.EAST, enterClass);
		sl.putConstraint(SpringLayout.NORTH, classList, 50, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, loadData, 0, SpringLayout.WEST, classList);
		sl.putConstraint(SpringLayout.NORTH, loadData, 25, SpringLayout.SOUTH, classList);
		sl.putConstraint(SpringLayout.WEST, announcementLabel, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, announcementLabel, 200, SpringLayout.NORTH, centerPanel);

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
	 * The refreshComboBox method refreshes the list of classes.
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

		announcementLabel.setText("");
	}


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "TeacherClassHomepagePanel [classList=" + classList + ", classes=" + classes + ", announcement="
				+ announcement + ", announcementLabel=" + announcementLabel + "]";
	}

}
