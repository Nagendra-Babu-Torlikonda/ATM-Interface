package atminterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Withdraw extends JFrame
{
	private final JLabel lblNewLabel = new JLabel("Account No. :" + ATMInterface.acc_number);
	private JTextField withdraw_amount;
	int old_balance;

//	public static void main(String[] args)
//	{
//		new Withdraw();
//	}

	public Withdraw()
	{
		setTitle("MyATM - Withdraw");
		setSize(600,400);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(230, 230, 250));
		getContentPane().setLayout(null);
		lblNewLabel.setBounds(124, 34, 337, 36);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 100, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		JLabel lblNewLabel_1 = new JLabel("Enter amount to withdraw");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(135, 92, 314, 36);
		getContentPane().add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(58, 68, 469, 2);
		getContentPane().add(separator);
		
		withdraw_amount = new JTextField();
		withdraw_amount.setHorizontalAlignment(SwingConstants.CENTER);
		withdraw_amount.setFont(new Font("Times New Roman", Font.BOLD, 25));
		withdraw_amount.setColumns(12);
		withdraw_amount.setBounds(161, 138, 263, 36);
		getContentPane().add(withdraw_amount);
		
		JButton withdraw_button = new JButton("Withdraw");
		withdraw_button.setForeground(new Color(0, 0, 128));
		withdraw_button.setFont(new Font("Times New Roman", Font.BOLD, 30));
		withdraw_button.setFocusable(false);
		withdraw_button.setBackground(new Color(255, 160, 122));
		withdraw_button.setBounds(214, 197, 170, 36);
		getContentPane().add(withdraw_button);
		
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
		
		withdraw_amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
//				System.out.println("Key pressed");
				if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9'  || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					withdraw_amount.setEditable(true);
				}
				else
				{
					withdraw_amount.setEditable(false);
				}
			}
		});
		
		setResizable(false);
		setVisible(true);
		
		withdraw_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!withdraw_amount.getText().equals(""))
				{
					 old_balance = CheckBalance.checkBalance();
					if(old_balance >= Integer.parseInt(withdraw_amount.getText()))
					{
						int amount = Integer.parseInt(withdraw_amount.getText());
						boolean is_withdrawn = withdrawMoney(amount);
						if(is_withdrawn)
						{
							try
							{
								Connection con = new DBConnect().getCon();
								PreparedStatement st = con.prepareStatement("insert into transaction_table values(?,?,CURRENT_TIMESTAMP, ?, ?)");
								st.setInt(2, ATMInterface.acc_number);
								st.setString(3, "Withdraw");
								st.setInt(4, amount);
								st.setString(1, null);
								st.execute();
							} catch (Exception e1)
							{
								JOptionPane.showMessageDialog(null,e1);
							}
							withdraw_amount.setText("");
							JOptionPane.showMessageDialog(null,"Withdrawn Successfully");
						}
						else
							JOptionPane.showMessageDialog(null,"Withdraw failed");
					}
					else
						JOptionPane.showMessageDialog(null, "Insufficient Balance");
				}
				else
					JOptionPane.showMessageDialog(null, "Please Enter Some Amount");
			}
		});
	}
	
	private boolean withdrawMoney(int amount)
	{
		try
		{
		
				Connection con = new DBConnect().getCon();
				PreparedStatement st = con.prepareStatement("update user_profile set balance = ? - ? where acc_number = ?");
				st.setInt(1, old_balance);
				st.setInt(2, amount);
				st.setInt(3,ATMInterface.acc_number);
				st.execute();
				
				st.close();
				con.close();
				return true;
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}

}
