package atminterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckBalance extends JFrame
{
	
//	public static void main(String[] args)
//	{
//		new CheckBalance();
//	}

	/**
	 * Create the application.
	 */
	public CheckBalance()
	{
		setTitle("MyATM - CheckBalance");
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(230, 230, 250));
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account No. :" + ATMInterface.acc_number);
		lblNewLabel.setForeground(new Color(0, 100, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(130, 46, 326, 42);
		getContentPane().add(lblNewLabel);
		
		int balance = checkBalance();
		
		JLabel lblNewLabel_1 = new JLabel("Balance : " + balance);
		lblNewLabel_1.setForeground(new Color(0, 0, 205));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1.setBounds(172, 138, 241, 42);
		getContentPane().add(lblNewLabel_1);
		
		JButton back_button = new JButton(" Back");
		back_button.setForeground(Color.DARK_GRAY);
		back_button.setFont(new Font("Times New Roman", Font.BOLD, 25));
		back_button.setFocusable(false);
		back_button.setBackground(Color.LIGHT_GRAY);
		back_button.setBounds(233, 244, 127, 36);
		getContentPane().add(back_button);
		
		setResizable(false);
		setVisible(true);
		
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ATMInterface(ATMInterface.userName,ATMInterface.acc_number);
			}
		});
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		            public void windowClosing(java.awt.event.WindowEvent e) {
		        	    dispose();
					new ATMInterface(ATMInterface.userName,ATMInterface.acc_number);
		            }
		        });
		
	}

	static int checkBalance()
	{
		int bal = 0;
		try
		{
			Connection connection = new DBConnect().getCon();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select balance from user_profile where acc_number = " + ATMInterface.acc_number);
			if(rs.next())
				bal = rs.getInt("balance");
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		return bal;
	}
}
