package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TermsAndConditionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	public TermsAndConditionsPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}
	
	
	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));
		
		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_TOC.png")));
		northPanel.add(header);
		
		add(northPanel, BorderLayout.NORTH);
	}
	
	
	private void prepareCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		centerPanel.setLayout(new FlowLayout());
		
		JTextArea textArea = new JTextArea(50, 150);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setText("TERMS AND CONDITIONS\r\n"
				+ "-----------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "Eduportal (\"Product\") is operated and manufactured by DS Tech (\"Owner\", we, us, or our). We are from Alexandria, VA and are registered under company number 01302025 and have our registered office at 50 Virtual Avenue, Alexandria, VA 22314.\r\n"
				+ "\r\n"
				+ "1. Please read these terms and conditions (\"the Terms\") carefully. By accessing any page of the Product, you agree to abide by these terms and any other instructions provided by us.\r\n"
				+ "2. If you do not accept these Terms, please do not use our Product.\r\n"
				+ "3. The Terms are subject to change at any time. Please review the Terms frequently so you are made known of any changes. You are still legally bound to our Terms despite alterations.\r\n"
				+ "\r\n"
				+ "-----------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "\r\n"
				+ "USE OF OUR SERVICES\r\n"
				+ "1. You can only use our Product for lawful purposes only and in a way that does not infringe anybody's privacy or use of the Product.\r\n"
				+ "2. By accessing the Product, you agree to only use it for use through your institution. \r\n"
				+ "3. Please protect your username and password at all times. If you believe your data has been breached, please change your password immediately.\r\n"
				+ "4. If you are under 18, you must be a student in your institution. If you are under 18 and own an institution as an administrator, we can press charges for unlawful use.\r\n"
				+ "\r\n"
				+ "-----------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "\r\n"
				+ "INTELLECTUAL PROPERTY RIGHTS\r\n"
				+ "1. All data and widgets of the Product are protected by copyright.\r\n"
				+ "2. Except if permitted by us, you may not redistribute the Product or modify it whatsoever in accordance with the Copyright Law of the United States.\r\n"
				+ "\r\n"
				+ "-----------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "\r\n"
				+ "USER CONTRIBUTIONS\r\n"
				+ "1. If you have any issue with the Product or have suggestions for improvement, please contact us at contact@eduportalmanager.com. We would be delighted to hear your feedback.\r\n"
				+ "2. Any prank messages to the email will be deleted, and the IP address associated with the email account will be blocked from sending further emails.\r\n"
				+ "\r\n"
				+ "-----------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "\r\n"
				+ "LIMITATION OF LIABILITY\r\n"
				+ "1. We accept no liability in respect of potential data breaches from potential players with malicious intent. It is your duty to protect your password in the event of a data breach. Please contact us if you have any questions.\r\n"
				+ "2. We will not be responsible for any breach of the Terms.\r\n"
				+ "\r\n"
				+ "-----------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "\r\n"
				+ "VIRUSES AND HACKING\r\n"
				+ "1. You cannot misuse the Product by introducing, transmitting, or arranging viruses, corrupt files, cancelbots, Trojan horses, worm, time, or logic bombs, keystroke logger, spyware, and any other material that damages computer software, the Product itself, or the data stored in the Product. You may not attempt a denial-of-service or distributed denial-of-service attack. Other attacks are not permitted and can be subject to litigation by us.\r\n"
				+ "\r\n"
				+ "-----------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "\r\n"
				+ "ADVERTISING\r\n"
				+ "1. Please let us know if you would like to advertise our company. Failure to do so could result in legal action.\r\n"
				+ "\r\n"
				+ "-----------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "\r\n"
				+ "JURISDICTION AND DISPUTES\r\n"
				+ "\r\n"
				+ "1. The Terms are governed by and construed in accordance with the laws of the United States. Any disputes concerning the Product will be subject to Virginia State Courts.\r\n"
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
				+ "So you decided to read all the way down here? We salute you. Please email us with the words \"DS Tech supports a TikTok and DeepSeek ban\" to get a free sneak peek of the next products coming from us.");
		JScrollPane jsp = new JScrollPane(textArea);
		
		centerPanel.add(jsp);
		
		add(centerPanel, BorderLayout.CENTER);
		
	}
	
	
	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		goHome.setFont(GraphicsConstants.FONT_BUTTON);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}
	
}
