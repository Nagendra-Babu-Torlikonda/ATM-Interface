package atminterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class Deposit extends JFrame
{
	private final JLabel lblNewLabel = new JLabel("Account No. :" + ATMInterface.acc_number);
	private JTextField dep_amount;
//	public static void main(String[] args)
//	{
//		new Deposit();
//	}

	/**
	 * Create the application.
	 */
	public Deposit()
	{
		setTitle("MyATM - Deposit");
		setSize(600,400);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(230, 230, 250));
		getContentPane().setLayout(null);
		lblNewLabel.setBounds(132, 34, 322, 36);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 100, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		JLabel lblNewLabel_1 = new JLabel("Enter amount to be deposited");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(135, 92, 314, 36);
		getContentPane().add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(58, 68, 469, 2);
		getContentPane().add(separator);
		
		dep_amount = new JTextField();
		dep_amount.setHorizontalAlignment(SwingConstants.CENTER);
		dep_amount.setFont(new Font("Times New Roman", Font.BOLD, 25));
		dep_amount.setColumns(12);
		dep_amount.setBounds(161, 138, 263, 36);
		getContentPane().add(dep_amount);
		
		JButton deposit_button = new JButton("Deposit");
		deposit_button.setForeground(new Color(0, 0, 128));
		deposit_button.setFont(new Font("Times New Roman", Font.BOLD, 30));
		deposit_button.setFocusable(false);
		deposit_button.setBackground(new Color(255, 160, 122));
		deposit_button.setBounds(214, 197, 158, 36);
		getContentPane().add(deposit_button);
		
		JButton back_button = new JButton(" Back");
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ATMInterface(ATMInterface.userName,ATMInterface.acc_number);
			}
		});
		back_button.setForeground(Color.DARK_GRAY);
		back_button.setFont(new Font("Times New Roman", Font.BOLD, 25));
		back_button.setFocusable(false);
		back_button.setBackground(Color.LIGHT_GRAY);
		back_button.setBounds(229, 295, 127, 36);
		getContentPane().add(back_button);
		
		dep_amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
//				System.out.println("Key pressed");
				if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9'  || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					dep_amount.setEditable(true);
				}
				else
				{
					dep_amount.setEditable(false);
				}
			}
		});
		
		deposit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!dep_amount.getText().equals(""))
				{
					int amount = Integer.parseInt(dep_amount.getText());
					boolean is_deposited = depositMoney(amount);
					if(is_deposited)
					{
						Connection con;
						try
						{
							con = new DBConnect().getCon();
							PreparedStatement st = con.prepareStatement("insert into transaction_table values(null, ?,CURRENT_TIMESTAMP, ?, ?)");
							st.setInt(1, ATMInterface.acc_number);
							st.setString(2, "Deposit");
							st.setInt(3, amount);
							st.execute();
						} catch (Exception e1)
						{
							JOptionPane.showMessageDialog(null,e1);
						}
						
						dep_amount.setText("");
						JOptionPane.showMessageDialog(null,"Deposited Successfully");
					}
					else
						JOptionPane.showMessageDialog(null,"Deposit failed");
				}
				else
					JOptionPane.showMessageDialog(null, "Please Enter Some Amount..");
			}
				
		});
		
		setResizable(false);
		setVisible(true);
		
	}
	
	private boolean depositMoney(int amount)
	{
		try
		{
			int old_balance = CheckBalance.checkBalance();
			Connection con = new DBConnect().getCon();
			PreparedStatement st = con.prepareStatement("update user_profile set balance = ? + ? where acc_number = ?");
			st.setInt(1, old_balance);
			st.setInt(2, amount);
			st.setInt(3,ATMInterface.acc_number);
			st.execute();
			return true;
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
		
	}
}
