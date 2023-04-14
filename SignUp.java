package atminterface;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.jdatepicker.impl.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame
{
	Connection con;
	private JTextField user_name_field;
	private JTextField acc_number_field;
	private JPasswordField user_password_field;
	private JPasswordField user_conf_password_field;
	private JRadioButton male_button;
	private JRadioButton female_button;
	private JRadioButton others_button;
	private JTextArea user_address_field;
	private JDatePickerImpl date_of_birth_field;
	
	
	public SignUp()
	{
		setTitle("MyATM - Sign Up");
		setSize(700,750);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setFont(new Font("Times New Roman", Font.BOLD, 45));
		lblSignUp.setBounds(262, 21, 205, 53);
		getContentPane().add(lblSignUp);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(60, 63, 565, 2);
		getContentPane().add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Your Name :  ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setBounds(65, 107, 220, 40);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Gender :  ");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(65, 157, 220, 40);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Date of birth :  ");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_2.setBounds(65, 204, 220, 40);
		getContentPane().add(lblNewLabel_1_2);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		date_of_birth_field = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		date_of_birth_field.getJFormattedTextField().setFont(new Font("Times New Roman", Font.PLAIN, 20));
		date_of_birth_field.setBounds(295, 204, 288, 59);
		 
		getContentPane().add(date_of_birth_field);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Address :  ");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_2_1.setBounds(65, 254, 220, 40);
		getContentPane().add(lblNewLabel_1_2_1);
		
		user_address_field = new JTextArea();
		user_address_field.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		user_address_field.setBounds(295, 273, 288, 136);
		getContentPane().add(user_address_field);
		
		user_name_field = new JTextField();
		user_name_field.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		user_name_field.setBounds(295, 107, 288, 40);
		getContentPane().add(user_name_field);
		user_name_field.setColumns(10);
		
		ButtonGroup gender = new ButtonGroup();
		
		male_button = new JRadioButton("Male");
		male_button.setFont(new Font("Times New Roman", Font.BOLD, 20));
		male_button.setBounds(309, 169, 70, 21);
		gender.add(male_button);
		getContentPane().add(male_button);
		
		female_button = new JRadioButton("Female");
		female_button.setFont(new Font("Times New Roman", Font.BOLD, 20));
		female_button.setBounds(397, 169, 87, 21);
		gender.add(female_button);
		getContentPane().add(female_button);
		
		others_button = new JRadioButton("Others");
		others_button.setFont(new Font("Times New Roman", Font.BOLD, 20));
		others_button.setBounds(496, 169, 87, 21);
		gender.add(others_button);
		getContentPane().add(others_button);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Account Number :   ");
		lblNewLabel_1_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_2_2.setBounds(65, 434, 220, 40);
		getContentPane().add(lblNewLabel_1_2_2);
		
		JLabel lblNewLabel_1_2_2_1 = new JLabel("Password :   ");
		lblNewLabel_1_2_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_2_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_2_2_1.setBounds(65, 484, 220, 40);
		getContentPane().add(lblNewLabel_1_2_2_1);
		
		JLabel lblNewLabel_1_2_2_2 = new JLabel("Confirm Password :   ");
		lblNewLabel_1_2_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_2_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_2_2_2.setBounds(41, 534, 244, 40);
		getContentPane().add(lblNewLabel_1_2_2_2);
		
		acc_number_field = new JTextField();
		acc_number_field.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		acc_number_field.setColumns(10);
		acc_number_field.setBounds(295, 434, 288, 40);
		getContentPane().add(acc_number_field);
		
		user_password_field = new JPasswordField();
		user_password_field.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		user_password_field.setColumns(10);
		user_password_field.setBounds(295, 484, 288, 40);
		getContentPane().add(user_password_field);
		
		user_conf_password_field = new JPasswordField();
		user_conf_password_field.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		user_conf_password_field.setColumns(10);
		user_conf_password_field.setBounds(295, 534, 288, 40);
		getContentPane().add(user_conf_password_field);
		
		JButton signup_button = new JButton("Sign Up");
		signup_button.setForeground(Color.DARK_GRAY);
		signup_button.setFont(new Font("Times New Roman", Font.BOLD, 30));
		signup_button.setFocusable(false);
		signup_button.setBackground(Color.LIGHT_GRAY);
		signup_button.setBounds(433, 638, 150, 36);
		getContentPane().add(signup_button);
		
		JButton back_button = new JButton(" Back");
		back_button.setForeground(Color.DARK_GRAY);
		back_button.setFont(new Font("Times New Roman", Font.BOLD, 25));
		back_button.setFocusable(false);
		back_button.setBackground(Color.LIGHT_GRAY);
		back_button.setBounds(111, 638, 127, 36);
		getContentPane().add(back_button);
		
		setResizable(false);
		setVisible(true);
		
		acc_number_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
//				System.out.println("Key pressed");
//				System.out.println(e.getKeyChar());
				if(e.getKeyChar() >= '0'  && e.getKeyChar() <= '9'  || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
//					System.out.println("true");
					acc_number_field.setEditable(true);
				}
				else 
				{
					acc_number_field.setEditable(false);
//					System.out.println("false");
				}
			}
		});
		
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login();
			}
		});
		
		signup_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(date_of_birth_field.getJFormattedTextField().getText());
				if(user_name_field.getText().equals("") || 
					user_address_field.getText().equals("") ||
					date_of_birth_field.getJFormattedTextField().getText().equals("") ||
					(!male_button.isSelected() && !female_button.isSelected()
							&& !others_button.isSelected()) ||
					acc_number_field.getText().equals("") ||
					user_password_field.getText().equals("") ||
					user_conf_password_field.getText().equals("")
						)
				{
					JOptionPane.showMessageDialog(null, "Please fill all the required Fields.");
				}
				else
				{
					userSignUp();
				}
			}
		});
			}
	
//	public static void main(String[] args)
//	{
//		new SignUp();
//	}
	
	void userSignUp()
	{
		PreparedStatement stmt = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			con = new DBConnect().getCon();
			st = con.createStatement();
			stmt = con.prepareStatement("insert into user_profile values(?,?,?,?,?,?,?,?)");
			stmt.setString(1,null);
			stmt.setInt(8, 0);
			stmt.setString(2, user_name_field.getText());
			if(male_button.isSelected())
				stmt.setString(3, "Male");
			else if(female_button.isSelected())
				stmt.setString(3, "Female");
			else
				stmt.setString(3, "Others");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			LocalDate date = LocalDate.parse(date_of_birth_field.getJFormattedTextField().getText(), formatter);
//			stmt.setDate(4, (Date) date_of_birth_field.getJFormattedTextField().getValue());
			stmt.setDate(4, Date.valueOf(date));
			rs = st.executeQuery("select * from user_profile where acc_number =" + acc_number_field.getText());
			if(rs.next()) 
			{
				//TODO joption for duplicate acc number
				acc_number_field.setText("");
				System.out.println("already exists");
			}
			else
			{
				stmt.setInt(5,Integer.parseInt(acc_number_field.getText()));
				if(user_password_field.getText().equals(user_conf_password_field.getText()))
				{	
					stmt.setString(6, user_password_field.getText());
					stmt.setString(7, user_address_field.getText());
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(this, "Registered Successfully !! ");
					dispose();
					new Login();
				}
				else	
				{
					user_password_field.setText("");
					user_conf_password_field.setText("");
					System.out.println("Not matching");
				}
			}
			
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
