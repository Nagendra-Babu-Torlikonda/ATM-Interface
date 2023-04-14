package atminterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame
{
	Connection connection;
	String uName;
	private JTextField input_acc_number;
	private JPasswordField input_password;
	
	public static void main(String[] args)
	{
		new Login();
	}


	public Login() 
	{
		setSize(670, 400);
		setLocationRelativeTo(null);
		setTitle("MyATM - Login");
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 45));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(217, 10, 205, 53);
		getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(75, 53, 488, 2);
		getContentPane().add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Account Number:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1.setBounds(65, 105, 236, 36);
		getContentPane().add(lblNewLabel_1);
		
		input_acc_number = new JTextField();
		input_acc_number.setHorizontalAlignment(SwingConstants.CENTER);
		input_acc_number.setFont(new Font("Times New Roman", Font.BOLD, 25));
		input_acc_number.setBounds(300, 105, 263, 36);
		getContentPane().add(input_acc_number);
		input_acc_number.setColumns(12);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password: ");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(65, 164, 236, 36);
		getContentPane().add(lblNewLabel_1_1);
		
		input_password = new JPasswordField();
		input_password.setHorizontalAlignment(SwingConstants.CENTER);
		input_password.setFont(new Font("Times New Roman", Font.BOLD, 25));
		input_password.setColumns(10);
		input_password.setBounds(300, 164, 263, 36);
		getContentPane().add(input_password);
		
		JButton login_button = new JButton("Login");
		login_button.setBackground(Color.LIGHT_GRAY);
		login_button.setForeground(Color.DARK_GRAY);
		login_button.setFont(new Font("Times New Roman", Font.BOLD, 30));
		login_button.setBounds(383, 272, 127, 36);
		login_button.setFocusable(false);
		getContentPane().add(login_button);
		
		JButton signup_button = new JButton("Sign Up");
		signup_button.setForeground(Color.DARK_GRAY);
		signup_button.setFont(new Font("Times New Roman", Font.BOLD, 30));
		signup_button.setBackground(Color.LIGHT_GRAY);
		signup_button.setBounds(140, 272, 150, 36);
		signup_button.setFocusable(false);
		getContentPane().add(signup_button);
		

		getContentPane().setLayout(null);
		setResizable(false);
		setVisible(true);

		input_acc_number.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
//				System.out.println("Key pressed");
				if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9'  || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					input_acc_number.setEditable(true);
				}
				else
				{
					input_acc_number.setEditable(false);
				}
			}
		});
		
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int acc = Integer.parseInt(input_acc_number.getText());
				String pass = String.valueOf(input_password.getPassword());
				if(login(acc , pass))
				{
					input_acc_number.setText("");
					input_password.setText("");
					dispose();
					new ATMInterface(uName, acc);
				}
				else
					//TODO joption for incorrect details
					System.out.println("Incorrect account Number or Password");
			}
		});
		
		signup_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SignUp();
			}
		});
		
	}
	
	boolean login(int acc, String pass)
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		try
		{
			connection = new DBConnect().getCon();
			st = connection.prepareStatement("select user_password, user_name from user_profile where acc_number = ?");
			st.setInt(1, acc);
			rs = st.executeQuery();
			if (rs.next())
			{
				if(pass.equals(rs.getString(1)))
				{	
					uName = rs.getString(2);
					return true;
				}
				else
					JOptionPane.showMessageDialog(null,"Incorrect Password");
			}
			else
				JOptionPane.showMessageDialog(null,"Incorrect Account Number");
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
