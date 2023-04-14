package atminterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class ChangePIN extends JFrame
{
	private final JLabel lblChangePin = new JLabel("Change PIN");
	private JPasswordField old_password;
	private JPasswordField new_password;
	private JPasswordField conf_new_password;
//	public static void main(String[] args)
//	{
//		new ChangePIN();
//	}

	public ChangePIN()
	{
		setTitle("MyATM - Change PIN");
		setSize(600,400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		lblChangePin.setBounds(190, 10, 205, 36);
		getContentPane().add(lblChangePin);
		lblChangePin.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangePin.setForeground(new Color(0, 100, 0));
		lblChangePin.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(68, 44, 450, 2);
		getContentPane().add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Old Password  ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(49, 86, 197, 36);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New Password  ");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(49, 132, 197, 36);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Confirm New Password  ");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(32, 178, 214, 36);
		getContentPane().add(lblNewLabel_1_2);
		
		old_password = new JPasswordField();
		old_password.setHorizontalAlignment(SwingConstants.CENTER);
		old_password.setFont(new Font("Times New Roman", Font.BOLD, 25));
		old_password.setColumns(12);
		old_password.setBounds(256, 86, 263, 36);
		getContentPane().add(old_password);
		
		new_password = new JPasswordField();
		new_password.setHorizontalAlignment(SwingConstants.CENTER);
		new_password.setFont(new Font("Times New Roman", Font.BOLD, 25));
		new_password.setColumns(12);
		new_password.setBounds(256, 132, 263, 36);
		getContentPane().add(new_password);
		
		conf_new_password = new JPasswordField();
		conf_new_password.setHorizontalAlignment(SwingConstants.CENTER);
		conf_new_password.setFont(new Font("Times New Roman", Font.BOLD, 25));
		conf_new_password.setColumns(12);
		conf_new_password.setBounds(255, 178, 263, 36);
		getContentPane().add(conf_new_password);
		
		JButton back_button = new JButton(" Back");
		back_button.setForeground(Color.DARK_GRAY);
		back_button.setFont(new Font("Times New Roman", Font.BOLD, 25));
		back_button.setFocusable(false);
		back_button.setBackground(Color.LIGHT_GRAY);
		back_button.setBounds(68, 271, 127, 36);
		getContentPane().add(back_button);
		
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ATMInterface(ATMInterface.userName,ATMInterface.acc_number);
			}
		});
		
		JButton change_pin_button = new JButton("Change");
		change_pin_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(old_password.getText().equals("")))
				{
					if(!(new_password.getText().equals("")))
					{
						if(!(old_password.getText().equals("")))
						{
							changePIN();
							old_password.setText("");
							new_password.setText("");
							conf_new_password.setText("");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please Confirm New Password");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please Enter New Password");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Enter Current Password");
				}
			}
		});
		change_pin_button.setForeground(new Color(0, 0, 128));
		change_pin_button.setFont(new Font("Times New Roman", Font.BOLD, 30));
		change_pin_button.setFocusable(false);
		change_pin_button.setBackground(new Color(255, 160, 122));
		change_pin_button.setBounds(362, 271, 156, 36);
		getContentPane().add(change_pin_button);
		
		setResizable(false);
		setVisible(true);
	}
	
	private void changePIN()
	{
		try
		{
			Connection connect = new DBConnect().getCon();
			PreparedStatement pst = null;
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("select user_password from user_profile where acc_number = " + ATMInterface.acc_number);
			if(rs.next())
			{
				if(old_password.getText().equals(rs.getString(1)))
				{
					if(new_password.getText().equals(conf_new_password.getText()))
					{
						pst =connect.prepareStatement("update myatm.user_profile set user_password = ? where acc_number = ?");
						pst.setString(1, new_password.getText());
						pst.setInt(2, ATMInterface.acc_number);
						pst.execute();
						JOptionPane.showMessageDialog(null, "PIN change Successful");
						connect.close();
						st.close();
						pst.close();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "New password and Confirmation password are not matching");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Current Password");
				}
			}
			
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
}
