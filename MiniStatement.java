package atminterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.sql.Statement;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MiniStatement extends JFrame
{
	private JTable table;
	DefaultTableModel model;

//	public static void main(String[] args)
//	{
//		new MiniStatement();
//	}
	public MiniStatement()
	{
		setTitle("MyATM - Mini Statement");
		setSize(700,500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account No. : " + ATMInterface.acc_number);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 100, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(168, 10, 350, 36);
		getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(69, 44, 560, 2);
		getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(69, 56, 560, 320);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setForeground(new Color(0, 0, 139));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        model = (DefaultTableModel) table.getModel();
		scrollPane.setViewportView(table);
		
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
		back_button.setBounds(279, 403, 127, 36);
		getContentPane().add(back_button);
		
		viewMiniStatement();
		
		setResizable(false);
		setVisible(true);
	}
	private void viewMiniStatement()
	{
		Connection connect;
		try
		{
			connect = new DBConnect().getCon();
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("select  TID, date, type, amount from transaction_table where acc_number = " + ATMInterface.acc_number);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			String[] col_names = {"TID", "Date", "Type of Transaction", "Amount"};
			model.setColumnIdentifiers(col_names);
			table.getColumnModel().getColumn(0).setPreferredWidth(5);
			//table.getColumnModel().getColumn(1).setPreferredWidth(20);
			table.getColumnModel().getColumn(2).setPreferredWidth(20);
			table.getColumnModel().getColumn(3).setPreferredWidth(7);
			table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20));
			
			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		        for (int columnIndex = 0; columnIndex < 4; columnIndex++)
		        {
		            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
		        }
			
			table.setRowHeight(25);
			while(rs.next())
			{
				
//				System.out.println(String.valueOf(rs.getInt("TID")) + rs.getTimestamp("date") +rs.getString("type") + rs.getInt("amount"));
				Object[] row = {rs.getInt("TID") , rs.getTimestamp("date") , rs.getString("type") , rs.getInt("amount")} ;
				model.addRow(row);
			}
				
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, e);
		}
		
	}
}
