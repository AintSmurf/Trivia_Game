import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Collections;

public class StartTheGame implements ActionListener {

	private Trivia_Builder questions[];
	private String[] options;
	char guess;
	String answer;
	int index;
	int correct_guesses = 0;
	int total_questions = 0;
	int result;
	int seconds = 10;

	JFrame frame = new JFrame();
	JTextField textfield = new JTextField();
	JTextArea textarea = new JTextArea();
	JButton buttonA = new JButton();
	JButton buttonB = new JButton();
	JButton buttonC = new JButton();
	JButton buttonD = new JButton();
	JLabel answer_labelA = new JLabel();
	JLabel answer_labelB = new JLabel();
	JLabel answer_labelC = new JLabel();
	JLabel answer_labelD = new JLabel();
	JLabel time_label = new JLabel();
	JLabel seconds_left = new JLabel();
	JTextField number_right = new JTextField();
	JTextField percentage = new JTextField();

	Timer timer = new Timer(1000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			seconds--;
			seconds_left.setText(String.valueOf(seconds));
			if (seconds <= 0) {
				displayAnswer();
			}
		}
	});

	public StartTheGame(Trivia_Builder t[]) {
		questions = t;
		total_questions = questions.length;
		frame.setTitle("Trivia Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(1600, 650);
		frame.getContentPane().setBackground(Color.gray);

		textfield.setBounds(0, 0, 1600, 50);
		textfield.setBackground(new Color(25, 25, 25));
		textfield.setForeground(new Color(25, 255, 0));
		textfield.setFont(new Font("Ink Free", Font.BOLD, 30));
		textfield.setBorder(BorderFactory.createBevelBorder(1));
		textfield.setHorizontalAlignment(JTextField.CENTER);
		textfield.setEditable(false);

		textarea.setBounds(0, 50, 1600, 80);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setBackground(new Color(25, 25, 25));
		textarea.setForeground(new Color(25, 255, 0));
		textarea.setFont(new Font("MV Boli", Font.BOLD, 25));
		textarea.setBorder(BorderFactory.createBevelBorder(1));
		textarea.setEditable(false);

		buttonA.setBounds(0, 130, 100, 100);
		buttonA.setFont(new Font("MV Boli", Font.BOLD, 35));
		buttonA.setFocusable(false);
		buttonA.addActionListener(this);
		buttonA.setText("A");

		buttonB.setBounds(0, 230, 100, 100);
		buttonB.setFont(new Font("MV Boli", Font.BOLD, 35));
		buttonB.setFocusable(false);
		buttonB.addActionListener(this);
		buttonB.setText("B");

		buttonC.setBounds(0, 330, 100, 100);
		buttonC.setFont(new Font("MV Boli", Font.BOLD, 35));
		buttonC.setFocusable(false);
		buttonC.addActionListener(this);
		buttonC.setText("C");

		buttonD.setBounds(0, 430, 100, 100);
		buttonD.setFont(new Font("MV Boli", Font.BOLD, 35));
		buttonD.setFocusable(false);
		buttonD.addActionListener(this);
		buttonD.setText("D");

		answer_labelA.setBounds(125, 100, 500, 100);
		answer_labelA.setBackground(new Color(50, 50, 50));
		answer_labelA.setForeground(new Color(25, 255, 0));
		answer_labelA.setFont(new Font("MV Boli", Font.PLAIN, 35));

		answer_labelB.setBounds(125, 200, 500, 100);
		answer_labelB.setBackground(new Color(50, 50, 50));
		answer_labelB.setForeground(new Color(25, 255, 0));
		answer_labelB.setFont(new Font("MV Boli", Font.PLAIN, 35));

		answer_labelC.setBounds(125, 300, 500, 100);
		answer_labelC.setBackground(new Color(50, 50, 50));
		answer_labelC.setForeground(new Color(25, 255, 0));
		answer_labelC.setFont(new Font("MV Boli", Font.PLAIN, 35));

		answer_labelD.setBounds(125, 400, 500, 100);
		answer_labelD.setBackground(new Color(50, 50, 50));
		answer_labelD.setForeground(new Color(25, 255, 0));
		answer_labelD.setFont(new Font("MV Boli", Font.PLAIN, 35));

		seconds_left.setBounds(1484, 510, 100, 100);
		seconds_left.setBackground(new Color(25, 25, 25));
		seconds_left.setForeground(new Color(255, 0, 0));
		seconds_left.setFont(new Font("Ink Free", Font.BOLD, 60));
		seconds_left.setBorder(BorderFactory.createBevelBorder(1));
		seconds_left.setOpaque(true);
		seconds_left.setHorizontalAlignment(JTextField.CENTER);
		seconds_left.setText(String.valueOf(seconds));

		time_label.setBounds(1484, 475, 100, 25);
		time_label.setBackground(new Color(50, 50, 50));
		time_label.setForeground(new Color(255, 0, 0));
		time_label.setFont(new Font("MV Boli", Font.PLAIN, 16));
		time_label.setHorizontalAlignment(JTextField.CENTER);
		time_label.setText("GoodLuck");

		number_right.setBounds(225, 225, 200, 100);
		number_right.setBackground(new Color(25, 25, 25));
		number_right.setForeground(new Color(25, 255, 0));
		number_right.setFont(new Font("Ink Free", Font.BOLD, 50));
		number_right.setBorder(BorderFactory.createBevelBorder(1));
		number_right.setHorizontalAlignment(JTextField.CENTER);
		number_right.setEditable(false);

		percentage.setBounds(225, 325, 200, 100);
		percentage.setBackground(new Color(25, 25, 25));
		percentage.setForeground(new Color(25, 255, 0));
		percentage.setFont(new Font("Ink Free", Font.BOLD, 50));
		percentage.setBorder(BorderFactory.createBevelBorder(1));
		percentage.setHorizontalAlignment(JTextField.CENTER);
		percentage.setEditable(false);

		frame.add(time_label);
		frame.add(seconds_left);
		frame.add(answer_labelA);
		frame.add(answer_labelB);
		frame.add(answer_labelC);
		frame.add(answer_labelD);
		frame.add(buttonA);
		frame.add(buttonB);
		frame.add(buttonC);
		frame.add(buttonD);
		frame.add(textarea);
		frame.add(textfield);
		frame.setVisible(true);
		nextQuestion();
	}
// get the next question
	public void nextQuestion() {
		String s[] = new String[4];
		if (index >= total_questions) {
			results();
		} else {
			textfield.setText("Question " + (index + 1));
			textarea.setText(fixTheTypos(questions[index].getQuestion()));
			if (questions[index].getType().equals("multiple")) {
				options = questions[index].splitTheIncorrectAnsers();
				s = random(questions[index].getCorrect_answer(), options);
				answer_labelA.setText(fixTheTypos(s[0]));
				answer_labelB.setText(fixTheTypos(s[1]));
				answer_labelC.setText(fixTheTypos(s[2]));
				answer_labelD.setText(fixTheTypos(s[3]));
			} else {
				String temp = questions[index].getCorrect_answer();
				if (temp.equals("True")) {
					answer_labelB.setText("False");
				} else {
					answer_labelB.setText("True");
				}
				answer_labelA.setText(fixTheTypos(questions[index].getCorrect_answer()));
				buttonC.setEnabled(false);
				buttonD.setEnabled(false);
				answer_labelC.setText("");
				answer_labelD.setText("");
				buttonC.setVisible(false);
				buttonD.setVisible(false);
				
			}
			timer.start();
		}
	}

// shuffle with arraylist
	public String[] random(String s, String s1[]) {
		String arr[] = new String[4];
		ArrayList<String> list = new ArrayList<String>(4);
		list.add(s);
		list.add(s1[0]);
		list.add(s1[1]);
		list.add(s1[2]);
		Collections.shuffle(list);
		for (int i = list.size() - 1; i >= 0; i--) {
			String temp = fixTheTypos(list.remove(i));
			arr[i] = temp;
		}
		return arr;
	}

// fix the typos
	public String fixTheTypos(String question) {

		String secondword = "&#039;s";
		String word = "&amp;";
		String thirdWord = "&quot;";
		String str = question;
		while (str.contains(word)) {
			str = str.replaceAll(word, ":");
		}
		while (str.contains(secondword)) {
			str = str.replaceAll(secondword, "'");
		}
		while (str.contains(thirdWord)) {
			str = str.replaceAll(thirdWord, "'");
		}

		return str;
	}

	@Override

	public void actionPerformed(ActionEvent e) {
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		answer = questions[index].getCorrect_answer();

		if (this.questions[index].getType().equals("multiple")) {
			if (e.getSource() == buttonA) {
				if (answer == answer_labelA.getText()) {
					correct_guesses++;
				}
			}
			if (e.getSource() == buttonB) {
				if (answer == answer_labelB.getText()) {
					correct_guesses++;
				}
			}
			if (e.getSource() == buttonC) {
				if (answer == answer_labelC.getText()) {
					correct_guesses++;

				}
			}
			if (e.getSource() == buttonD) {
				if (answer == answer_labelD.getText()) {
					correct_guesses++;
				}
			}
		} else {
			if (e.getSource() == buttonA) {
				if (answer == answer_labelA.getText()) {
					correct_guesses++;
				}
			}
			if (e.getSource() == buttonB) {
				if (answer == answer_labelB.getText()) {
					correct_guesses++;
				}
			}
		}
		displayAnswer();

	}

	public void displayAnswer() {
		timer.stop();

		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);

		if (answer_labelA.getText() != answer)
			answer_labelA.setForeground(new Color(255, 0, 0));
		if (answer_labelB.getText() != answer)
			answer_labelB.setForeground(new Color(255, 0, 0));
		if (answer_labelC.getText() != answer)
			answer_labelC.setForeground(new Color(255, 0, 0));
		if (answer_labelD.getText() != answer)
			answer_labelD.setForeground(new Color(255, 0, 0));

		Timer pause = new Timer(2000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				answer_labelA.setForeground(new Color(25, 255, 0));
				answer_labelB.setForeground(new Color(25, 255, 0));
				answer_labelC.setForeground(new Color(25, 255, 0));
				answer_labelD.setForeground(new Color(25, 255, 0));

				answer = " ";
				seconds = 10;
				seconds_left.setText(String.valueOf(seconds));
				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				buttonD.setEnabled(true);
				buttonC.setVisible(true);
				buttonD.setVisible(true);
				index++;
				nextQuestion();
			}
		});
		pause.setRepeats(false);
		pause.start();
	}

	public void results() {
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);

		result = (int) ((correct_guesses / (double) total_questions) * 100);

		textfield.setText("RESULTS!");
		textarea.setText("");
		answer_labelA.setText("");
		answer_labelB.setText("");
		answer_labelC.setText("");
		answer_labelD.setText("");

		number_right.setText("(" + correct_guesses + "/" + total_questions + ")");
		percentage.setText(result + "%");

		frame.add(number_right);
		frame.add(percentage);
	}

}
