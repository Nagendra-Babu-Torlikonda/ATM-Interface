package guessnumber;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

public class GuessTheNumber extends JFrame
{
	private JTextField textField;
	JLabel attemptsLeftLabel;
	JButton checkButton;
	JLabel hintLabel;
	JButton helpButton;
	JButton quitButton;
	int random;
	int max_chances = 5;
	int attempts = 0;
	
	public GuessTheNumber() {
		
		random = new Random().nextInt(1,100);
//		System.out.println(random);
		setSize(700,500);
		setResizable(false);
		setTitle("Guess The Number");
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Guess The Number");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(84, 10, 517, 72);
		getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLUE);
		separator.setBounds(97, 80, 492, 4);
		getContentPane().add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Guess a Number between 1 and 100");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(154, 92, 377, 48);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Calligraphy", Font.BOLD, 25));
		textField.setBounds(253, 174, 132, 39);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter your number:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(71, 177, 172, 38);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Attempts Left:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(445, 182, 132, 31);
		getContentPane().add(lblNewLabel_3);
		
		attemptsLeftLabel = new JLabel("");
		attemptsLeftLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
		attemptsLeftLabel.setBounds(587, 186, 40, 31);
		getContentPane().add(attemptsLeftLabel);
		
		checkButton = new JButton("Check");
		checkButton.setBackground(Color.LIGHT_GRAY);
		checkButton.setForeground(Color.BLUE);
		checkButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		checkButton.setBounds(276, 238, 132, 39);
		checkButton.setFocusable(false);
		getContentPane().add(checkButton);
		
		JLabel hintLabel = new JLabel("");
		hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hintLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		hintLabel.setBounds(97, 309, 492, 31);
		getContentPane().add(hintLabel);
		
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Guess a number between 1 and 100. "
						+ "You have maximum of 5 attempts to guess correctly. "
						+ "You will get suggetion for each attempt.");
			}
		});
		helpButton.setBackground(Color.LIGHT_GRAY);
		helpButton.setForeground(Color.MAGENTA);
		helpButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		helpButton.setBounds(97, 382, 85, 31);
		getContentPane().add(helpButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(null, "Are you sure to quit? ");
//				System.out.println(opt);
				if(opt == 0)
					dispose();
			}
		});
		quitButton.setForeground(Color.MAGENTA);
		quitButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		quitButton.setBackground(Color.LIGHT_GRAY);
		quitButton.setBounds(492, 382, 85, 31);
		getContentPane().add(quitButton);
		
		attemptsLeftLabel.setText("" + max_chances);
		
		
		setVisible(true);
		
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
//				System.out.println("Key pressed");
				if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE )
				{
					textField.setEditable(true);
				}
				else
				{
					textField.setEditable(false);
					hintLabel.setText("Enter only Number");
				}
			}
		});
		
		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setEditable(true);
				if(! textField.getText().equals( ""))
				{
//					System.out.println("Hello");
					int guess = Integer.parseInt(textField.getText());
					if(guess < 1 || guess >100)
					{
						textField.setText("");
						hintLabel.setText("Enter numbers only between 1 and 100");
					}
					else 
					{
						attempts++;
						attemptsLeftLabel.setText("" + (max_chances - attempts));
//						System.out.println(attempts);
						if(max_chances != attempts)
						{
							
							if(guess == random)
							{
								dispose();
								String[] options = {"Play Again", "Close"};
								int play =JOptionPane.showOptionDialog(null, "You guessed it.. Crrect.  Do you want to play again", 
														"Success",JOptionPane.DEFAULT_OPTION, 
														JOptionPane.INFORMATION_MESSAGE,
														null,options , options[0]);
								if(play == 0)
									new GuessTheNumber();
							}
							else if(guess > random)
							{
								textField.setText("");
								hintLabel.setText("The Number is less than " + guess);
								
							}
							else
							{
								textField.setText("");
								hintLabel.setText("The Number is greater than " + guess);
								
							}
						}
						else
						{
							dispose();
							String[] opts2 = {"Retry", "Quit"};
							int retry =JOptionPane.showOptionDialog(null, "You are out of ATTEMPTS", 
									"Game Over",JOptionPane.DEFAULT_OPTION, 
									JOptionPane.INFORMATION_MESSAGE,
									null,opts2 , opts2[0]);
							if(retry == 0 )
								new GuessTheNumber();
						}
					}
				}
				else
				{
					textField.setText("");
					hintLabel.setText("Enter any Number between 1 and 100");
					
				}
				
			}
		});
		
	}

	/*
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		new GuessTheNumber();
	}
}
