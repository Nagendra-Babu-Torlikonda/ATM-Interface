package atminterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class ATMInterface extends JFrame
{
	private JTextField textField;
	static String userName;
	static int acc_number;
	
	public ATMInterface(String name, int ac_num)
	{
		userName = name;
		acc_number = ac_num;
		
		setTitle("MyATM - " + name);
		setSize(900,600);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(230, 230, 250));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to MyATM");
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(204, 22, 477, 58);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hi," + userName);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel_1.setBounds(305, 100, 275, 42);
		getContentPane().add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(52, 78, 782, 2);
		getContentPane().add(separator);
		
		JLabel lblNewLabel_1_1 = new JLabel("Select Your Operation");
		lblNewLabel_1_1.setForeground(new Color(210, 105, 30));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(226, 153, 433, 42);
		getContentPane().add(lblNewLabel_1_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(129, 243, 628, 269);
		panel.setLayout(new GridLayout(2, 0, 30, 30));
		
		JButton deposit_operation = new JButton("Deposit");
		deposit_operation.setBackground(new Color(255, 160, 122));
		deposit_operation.setFont(new Font("Times New Roman", Font.BOLD, 35));
		deposit_operation.setFocusable(false);
		panel.add(deposit_operation);
		
		JButton withdraw_operation = new JButton("Withdraw");
		withdraw_operation.setBackground(new Color(255, 160, 122));
		withdraw_operation.setFont(new Font("Times New Roman", Font.BOLD, 35));
		withdraw_operation.setFocusable(false);
		panel.add(withdraw_operation);
		
		JButton check_balance = new JButton("Check Balance");
		check_balance.setBackground(new Color(255, 160, 122));
		check_balance.setFont(new Font("Times New Roman", Font.BOLD, 35));
		check_balance.setFocusable(false);
		panel.add(check_balance);
		
		JButton mini_statement_operation = new JButton("Mini Statement");
		mini_statement_operation.setBackground(new Color(255, 160, 122));
		mini_statement_operation.setFont(new Font("Times New Roman", Font.BOLD, 30));
		mini_statement_operation.setFocusable(false);
		panel.add(mini_statement_operation);
		
		getContentPane().add(panel);
		
		JButton btnNewButton_4 = new JButton("Logout");
		btnNewButton_4.setFocusable(false);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login();
			}
		});
		btnNewButton_4.setForeground(new Color(255, 0, 0));
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnNewButton_4.setBackground(new Color(222, 184, 135));
		btnNewButton_4.setBounds(666, 95, 168, 58);
		getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_4_1 = new JButton("Change PIN");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ChangePIN();
			}
		});
		btnNewButton_4_1.setForeground(Color.RED);
		btnNewButton_4_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnNewButton_4_1.setFocusable(false);
		btnNewButton_4_1.setBackground(new Color(222, 184, 135));
		btnNewButton_4_1.setBounds(37, 95, 187, 47);
		getContentPane().add(btnNewButton_4_1);
		
		check_balance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CheckBalance();
			}
		});
		
		deposit_operation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Deposit();
			}
		});
		
		withdraw_operation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Withdraw();
			}
		});
		
		mini_statement_operation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MiniStatement();
			}
		});
		
		setResizable(false);
		setVisible(true);		
	}
	
}
