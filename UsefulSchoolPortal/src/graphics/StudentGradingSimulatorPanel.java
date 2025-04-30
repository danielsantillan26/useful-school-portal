package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class StudentGradingSimulatorPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public StudentGradingSimulatorPanel() {
		setLayout(new BorderLayout());
		prepareNorthPanel();
		prepareCenterPanel();
	}


	private void prepareNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(GraphicsConstants.COLOR_BG_HEADER);
		northPanel.setPreferredSize(new Dimension(getWidth(), 75));

		JLabel header = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("HEADER_GRADING_SIMULATOR.png")));
		northPanel.add(header);

		add(northPanel, BorderLayout.NORTH);
	}


	private void prepareCenterPanel() {
		SpringLayout sl = new SpringLayout();
		JPanel centerPanel = new JPanel(sl);
		centerPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);

		JLabel enterFirstQuarter = new JLabel("Q1 Grade:");
		enterFirstQuarter.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		JLabel enterSecondQuarter = new JLabel("Q2 Grade:");
		enterSecondQuarter.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		JLabel enterFinalExamGrade = new JLabel("Exam Grade:");
		enterFinalExamGrade.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		JLabel enterFinalGrade = new JLabel("Final Grade");
		enterFinalGrade.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		JTextField givenFirstQuarter = new JTextField();
		givenFirstQuarter.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenFirstQuarter.setPreferredSize(new Dimension(100, 50));
		JTextField givenSecondQuarter = new JTextField();
		givenSecondQuarter.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenSecondQuarter.setPreferredSize(new Dimension(100, 50));
		JTextField givenFinalExamGrade = new JTextField();
		givenFinalExamGrade.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenFinalExamGrade.setPreferredSize(new Dimension(100, 50));
		JTextField givenFinalGrade = new JTextField();
		givenFinalGrade.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenFinalGrade.setPreferredSize(new Dimension(100, 50));
		JLabel enterFirstQuarterWeight = new JLabel("Q1 Weight");
		enterFirstQuarterWeight.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		JLabel enterSecondQuarterWeight = new JLabel("Q2 Weight");
		enterSecondQuarterWeight.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		JLabel enterFinalExamWeight = new JLabel("Exam Weight");
		enterFinalExamWeight.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		JTextField givenFirstQuarterWeight = new JTextField();
		givenFirstQuarterWeight.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenFirstQuarterWeight.setPreferredSize(new Dimension(100, 50));
		JTextField givenSecondQuarterWeight = new JTextField();
		givenSecondQuarterWeight.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenSecondQuarterWeight.setPreferredSize(new Dimension(100, 50));
		JTextField givenFinalExamWeight = new JTextField();
		givenFinalExamWeight.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		givenFinalExamWeight.setPreferredSize(new Dimension(100, 50));

		JLabel instructions = new JLabel("Enter your first quarter and second quarter grades and weights.");
		instructions.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		JLabel instructions2 = new JLabel("Enter EITHER your final exam grade or desired final grade.");
		instructions2.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		JLabel instructions3 = new JLabel("Enter your final exam weight as well.");
		instructions3.setFont(GraphicsConstants.FONT_ROBOTO_B30);
		JLabel instructions4 = new JLabel("All weights must be in percents and sum up to 100 (e.g., enter 30 for 30% weight).");
		instructions4.setFont(GraphicsConstants.FONT_ROBOTO_B30);

		JButton enter = new JButton("Enter");
		GraphicsHelpers.modifyButton(enter, 220, 45);
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double firstQuarterGrade = -1;
				double firstQuarterWeight = -1;
				double secondQuarterGrade = -1;
				double secondQuarterWeight = -1;
				double finalExamGrade = -1;
				double finalExamWeight = -1;
				double finalGrade = -1;

				try {
					firstQuarterGrade = Double.parseDouble(givenFirstQuarter.getText());
					firstQuarterWeight = Double.parseDouble(givenFirstQuarterWeight.getText());
					secondQuarterGrade = Double.parseDouble(givenSecondQuarter.getText());
					secondQuarterWeight = Double.parseDouble(givenSecondQuarterWeight.getText());
					finalExamWeight = Double.parseDouble(givenFinalExamWeight.getText());

					if (givenFinalExamGrade.getText().strip().equals("")) {
						finalGrade = Double.parseDouble(givenFinalGrade.getText());
						double result = (finalGrade - ((firstQuarterGrade * firstQuarterWeight)/100) -
								((secondQuarterGrade * secondQuarterWeight))/100)/(finalExamWeight);
						result *= 100;
						givenFinalExamGrade.setText(String.valueOf(result));
						
					} else {
						finalExamGrade = Double.parseDouble(givenFinalExamGrade.getText());
						double result = firstQuarterGrade * firstQuarterWeight
								+ secondQuarterGrade * secondQuarterWeight + finalExamGrade *
								finalExamWeight;
						result = Math.floor(result * 100) / 100;
						result /= 100;
						givenFinalGrade.setText(String.valueOf(result));
					}

					if (firstQuarterWeight + secondQuarterWeight + finalExamWeight != 100) {
						throw new Exception();
					}

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(centerPanel, "- Make sure all of your entries are digits." +
							"\n- Make sure you fill in all fields except the final exam grade OR the final grade." +
							"\n- Make sure the percentages given for the weights sum to 100.", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		centerPanel.add(instructions);
		centerPanel.add(instructions2);
		centerPanel.add(instructions3);
		centerPanel.add(instructions4);
		centerPanel.add(enterFirstQuarter);
		centerPanel.add(givenFirstQuarter);
		centerPanel.add(enterSecondQuarter);
		centerPanel.add(givenSecondQuarter);
		centerPanel.add(enterFinalExamGrade);
		centerPanel.add(givenFinalExamGrade);
		centerPanel.add(enterFinalGrade);
		centerPanel.add(givenFinalGrade);
		centerPanel.add(enterFirstQuarterWeight);
		centerPanel.add(givenFirstQuarterWeight);
		centerPanel.add(enterSecondQuarterWeight);
		centerPanel.add(givenSecondQuarterWeight);
		centerPanel.add(enterFinalExamWeight);
		centerPanel.add(givenFinalExamWeight);
		centerPanel.add(enter);

		sl.putConstraint(SpringLayout.WEST, instructions, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, instructions, 25, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, instructions2, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, instructions2, 75, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, instructions3, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, instructions3, 125, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, instructions4, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, instructions4, 175, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterFirstQuarter, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterFirstQuarter, 250, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenFirstQuarter, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenFirstQuarter, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterSecondQuarter, 450, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterSecondQuarter, 250, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenSecondQuarter, 450, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenSecondQuarter, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterFinalExamGrade, 800, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterFinalExamGrade, 250, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenFinalExamGrade, 800, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenFinalExamGrade, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterFinalGrade, 1150, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterFinalGrade, 250, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenFinalGrade, 1150, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenFinalGrade, 300, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterFirstQuarterWeight, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterFirstQuarterWeight, 450, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenFirstQuarterWeight, 100, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenFirstQuarterWeight, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterSecondQuarterWeight, 450, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterSecondQuarterWeight, 450, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenSecondQuarterWeight, 450, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenSecondQuarterWeight, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enterFinalExamWeight, 800, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enterFinalExamWeight, 450, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, givenFinalExamWeight, 800, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, givenFinalExamWeight, 500, SpringLayout.NORTH, centerPanel);
		sl.putConstraint(SpringLayout.WEST, enter, 700, SpringLayout.WEST, centerPanel);
		sl.putConstraint(SpringLayout.NORTH, enter, 600, SpringLayout.NORTH, centerPanel);

		add(centerPanel, BorderLayout.CENTER);
	}


	public void addChangePageButtons(JButton goHome) {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(GraphicsConstants.COLOR_BG_MAIN);
		southPanel.add(goHome);
		add(southPanel, BorderLayout.SOUTH);
	}

}
