package window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connection.DatabaseConnection;
import dao.AccountDAO;
import dao.UserDAO;
import table.Account;

public class UserList extends JInternalFrame {
	
	private JButton cancelBtn,addBtn,updateBtn,deleteBtn;
	private JTable table;
	private DefaultTableModel tablemodel;
	private JPanel contentPanel,buttonPanel;
	private JTextField accountNameText,userIDText,userNameText,mobilePhoneText,fixedPhoneText,userEmailText;
	private JPasswordField pwdField;
	private AccountDAO ad;
	private UserDAO ud;
	
	public UserList() {
		this.setTitle("用户列表");
		this.setSize(800, 600);
		this.setLayout(new FlowLayout());
		ad = new AccountDAO();
		ad.setConnection(DatabaseConnection.getConnection());
		ud = new UserDAO();
		ud.setConnection(DatabaseConnection.getConnection());
		contentPanel = new JPanel();       
        String[] title={"用户名","工号","姓名","手机号","固定电话","电子邮箱"};
        tablemodel = new DefaultTableModel(title,0);
		table = new JTable(tablemodel);
        contentPanel.add(new JScrollPane(table));
        this.getContentPane().add(contentPanel,BorderLayout.NORTH);
        
        buttonPanel = new JPanel();
        cancelBtn = new JButton("取消");		
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				UserList.this.dispose();
			}
		});
		buttonPanel.add(cancelBtn);
        addBtn = new JButton("添加");
        addBtn.addActionListener(new addlistener());
        buttonPanel.add(addBtn);
        updateBtn = new JButton("更改");
        updateBtn.addActionListener(new updatelistener());
        buttonPanel.add(updateBtn);
        deleteBtn = new JButton("删除");     
        deleteBtn.addActionListener(new deletelistener());      
        buttonPanel.add(deleteBtn);
        this.getContentPane().add(buttonPanel,BorderLayout.SOUTH);
        this.setVisible(true);
		print();
	}
	
	private JPanel createInterPanel() {
		JPanel f = new JPanel();
		f.setLayout(new GridLayout(6,2));
		
		f.add(new JLabel("用户名"));
		accountNameText = new JTextField(10);
		f.add(accountNameText);
		
		f.add(new JLabel("工号"));
		userIDText = new JTextField(10);
		f.add(userIDText);
		
		f.add(new JLabel("姓名"));
		userNameText = new JTextField(10);
		f.add(userNameText);
		
		f.add(new JLabel("手机号"));
		mobilePhoneText = new JTextField(10);
		f.add(mobilePhoneText);
		
		f.add(new JLabel("固定电话"));
		fixedPhoneText = new JTextField(10);
		f.add(fixedPhoneText);
		
		f.add(new JLabel("电子邮箱"));
		userEmailText = new JTextField(10);
		f.add(userEmailText);
		
		return f;
	}
	
	
	private class deletelistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int row = table.getSelectedRow();
			Connection con = DatabaseConnection.getConnection();
			String accountName = tablemodel.getValueAt(row, 0).toString();
			int userID = Integer.parseInt(tablemodel.getValueAt(row, 1).toString());
			
			try {
				con.setAutoCommit(false);		    
				ad.deleteByAccountName(accountName);
				ud.deleteByUserID(userID);			
				con.commit();
				con.setAutoCommit(true);
				JOptionPane.showMessageDialog(UserList.this,"数据已删除");
				print();
			} catch (Exception e1) {
				try {
					con.rollback();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			} 
		}

	}
	
	private class updatelistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JPanel f = createInterPanel();
			int row = table.getSelectedRow();
			accountNameText.setText(table.getValueAt(row, 0).toString());
			accountNameText.setEditable(false);
			pwdField.setText(table.getValueAt(row, 1).toString());
			userIDText.setText(table.getValueAt(row, 2).toString());
			userIDText.setEditable(false);
			
			String but[]={"确认","取消"};
			int response = JOptionPane.showOptionDialog(null,f,"更改窗口",JOptionPane.YES_OPTION,JOptionPane.CANCEL_OPTION,null,but,but[0]);
			
			
			if (response==0) {
				Account account = new Account();
				account.setAccountName(accountNameText.getText().trim());
				account.setAccountPassword(String.valueOf(pwdField.getPassword()));
				account.setAccountType(1);
				account.setUserID(Integer.parseInt(userIDText.getText().trim()));
				try {
					if (ad.updateByAccountName(account)) {
						JOptionPane.showMessageDialog(UserList.this, "提交成功", "成功", JOptionPane.OK_OPTION);
					}
					else {
						System.out.println("update failed");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			print();
		}

	}
	private class addlistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JPanel f = createInterPanel();
			
			String but[]={"确认","取消"};
			int response = JOptionPane.showOptionDialog(null,f,"添加窗口",JOptionPane.YES_OPTION,JOptionPane.CANCEL_OPTION,null,but,but[0]);
			if (response==0) {
				Account account = new Account();
				account.setAccountName(accountNameText.getText().trim());
				account.setAccountPassword(String.valueOf(pwdField.getPassword()));
				account.setAccountType(1);
				account.setUserID(Integer.parseInt(userIDText.getText().trim()));
				try {
					if (ad.doCreate(account)) {
						JOptionPane.showMessageDialog(UserList.this, "提交成功", "成功", JOptionPane.OK_OPTION);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			print();
		}

	}
	
	private void print(){
		tablemodel.setRowCount(0);
		try{
			List<Account> list = ad.findAll();
            Iterator<Account> it = list.iterator();
            while(it.hasNext()){
            	Account account = it.next();
            	String[] str = new String[3];
            	str[0] = account.getAccountName();
            	str[1] = account.getAccountPassword();
            	str[2] = account.getUserID().toString();
            	tablemodel.addRow(str);
            }
		}
		catch(Exception e1){
			e1.printStackTrace();
		}
		this.repaint();
	}
}
