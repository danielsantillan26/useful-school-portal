package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

public class TermsAndConditionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel northPanel;
	private JPanel centerPanel;
	
	
	public TermsAndConditionsPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}
	
	
	private void prepareNorthPanel() {
		northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));
		
		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_TOC.png")));
		northPanel.add(header);
		
		add(northPanel, BorderLayout.NORTH);
	}
	
	
	private void prepareCenterPanel() {
		centerPanel = new JPanel();
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		centerPanel.setLayout(new FlowLayout());
		
		JTextArea textArea = new JTextArea(50, 120);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setText("TERMS AND CONDITIONS\r\n"
				+ "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "The Useful School Portal (\"Product\") is made by DS Tech (\"Owner\"). The Product was made between January and May 2025 for various educators and students (\"User\") as a backbone to their educational system. All Users must follow the following terms outlined by the Owner to use this Product.\r\n"
				+ "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "PROPER USE:\r\n"
				+ "Each User must have their own separate account with the given role associating to their role in the school (e.g., Administrator, Student, Teacher, etc.). Administrators must ensure that Users are properly created with the correct roles. Failure to do so will result in your school's suspension from the Product.\r\n"
				+ "\r\n"
				+ "Users must use the Product properly and avoid any attempt at exploiting the Product or collecting the data embedded in the Product. Any attempts will result in immediate suspension from the Product and, if necessary, the involvement of law enforcement services depending on the severity of the exploitation or data breach. Data breaches are unlawful and illegal.\r\n"
				+ "\r\n"
				+ "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "LITIGATION:\r\n"
				+ "The Owner ensured that malware was not included as part of the Product. Any malware potentially caused by the Product means the Product was tampered by a third party source. The Owner is not liable for any destruction of computer hardware caused by the program. Any decisions to send the Owner to court over the Product will result in immediate suspension of the case. Judges have been instructed to decline any litigation concerning the Product.\r\n"
				+ "\r\n"
				+ "Any further reasons to send the Owner to court concerning the Product will be fairly handled by a court of law. The Owner will not participate in any corruption to ensure your failure in the case.\r\n"
				+ "\r\n"
				+ "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "INSTRUCTIONS [I REALLY NEED TO EDIT THIS STUFF]:\r\n"
				+ "Begin by creating a school user through the register button. Follow the prompts. The first account you create under this new school is an administrator account.\r\n"
				+ "\r\n"
				+ "Administrators have the ability to create other administrator accounts, teacher accounts, and student accounts. They can be given a username and password.\r\n"
				+ "\r\n"
				+ "Users can change their password at any time, but administrators will have access to change these passwords to default settings if needed. Administrators will never have the current password on file. \r\n"
				+ "\r\n"
				+ "Administrators also have the ability to create classes of various levels and assign both teachers and students to these classes. Classes can be changed at any time. Note that students and teachers alike cannot have overlap in their schedule, which can be created through the administrator page.\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "Teachers have the ability to create assignments within their class. They can also edit their class pages and view student information. Again, teachers do not have the ability to view student passwords.\r\n"
				+ "\r\n"
				+ "Teachers can grade assignments according to the proper systems given by the program. \r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "EASTER EGG:\r\n"
				+ "Holy [expletive]! You read the entire terms and conditions. As a reward, please say \"I support DS Tech\" and you will get a complimentary guide on how to go to heaven.\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "does jscroll work");
		JScrollPane jsp = new JScrollPane(textArea);
		
		centerPanel.add(jsp);
		
		add(centerPanel, BorderLayout.CENTER);
		
	}
	
}
