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
import table.User;

public class UserList extends JInternalFrame {
	
	private JButton cancelBtn,addBtn,pwdClearBtn,deleteBtn;
	private JTable table;
	private DefaultTableModel tablemodel;
	private JPanel contentPanel,buttonPanel;
	private JTextField accountNameText,userIDText,userNameText,mobilePhoneText,fixedPhoneText,userEmailText;
	private AccountDAO ad;
	private UserDAO ud;
	private Connection con;
	private Account account;
	private User user;
	
	public UserList() {
		this.setTitle("用户列表");
		this.setSize(800, 700);
		this.setLayout(new BorderLayout()); 
		con = DatabaseConnection.getConnection();
		ad = new AccountDAO(con);
		ud = new UserDAO(con);
		
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
        pwdClearBtn = new JButton("密码重置");
        pwdClearBtn.addActionListener(new pwdClearlistener());
        buttonPanel.add(pwdClearBtn);
        deleteBtn = new JButton("删除");     
        deleteBtn.addActionListener(new deletelistener());      
        buttonPanel.add(deleteBtn);
        this.getContentPane().add(buttonPanel,BorderLayout.SOUTH);
        this.setVisible(true);
		print();
	}
	
	
	private class deletelistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int row = table.getSelectedRow();
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
	
	private class pwdClearlistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String but[]={"确认","取消"};
			int response = JOptionPane.showOptionDialog(null,"你确认要将此用户的密码重置吗？","密码重置",JOptionPane.YES_OPTION,JOptionPane.CANCEL_OPTION,null,but,but[0]);			
			
			
			if (response==0) {
				int row = table.getSelectedRow();
				String accountName = tablemodel.getValueAt(row, 0).toString();
				int userID = Integer.parseInt(tablemodel.getValueAt(row, 1).toString());
				account = new Account();
				account.setAccountName(accountName);
				account.setAccountPassword("");
				account.setAccountType(1);
				account.setUserID(userID);
				try {
					if (ad.updateByAccountName(account)) {
						JOptionPane.showMessageDialog(UserList.this, "提交成功", "成功", JOptionPane.OK_OPTION);
					}
					else {
						System.out.println("pwdClear failed");
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
			
			String but[]={"确认","取消"};
			int response = JOptionPane.showOptionDialog(null,f,"添加窗口",JOptionPane.YES_OPTION,JOptionPane.CANCEL_OPTION,null,but,but[0]);
			if (response==0) {
				account = new Account();
				int userID = Integer.parseInt(userIDText.getText().trim());
				account.setAccountName(accountNameText.getText().trim());
				account.setAccountPassword("");
				account.setAccountType(1);
				account.setUserID(userID);
				user.setUserID(userID);
				user.setUserName(userNameText.getText().trim());
				user.setMobilePhone(mobilePhoneText.getText().trim());
				user.setFixedPhone(fixedPhoneText.getText().trim());
				user.setUserEmail(userEmailText.getText().trim());
				try {
					con.setAutoCommit(false);		    
					ad.doCreate(account);
					ud.doCreate(user);			
					con.commit();
					con.setAutoCommit(true);
					JOptionPane.showMessageDialog(UserList.this, "提交成功", "成功", JOptionPane.OK_OPTION);				
				} catch (Exception e1) {
					try {
						con.rollback();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
			print();
		}

	}
	
	private void print(){
		tablemodel.setRowCount(0);
		try{
			List<Account> accountList = ad.findAll();
			List<User> userList = ud.findAll();
            Iterator<Account> accountIt = accountList.iterator();
            Iterator<User> userIt = userList.iterator();
            while(accountIt.hasNext() && userIt.hasNext()){
            	account = accountIt.next();
            	user = userIt.next();
            	String[] str = new String[6];
            	str[0] = account.getAccountName();
            	str[1] = account.getUserID().toString();
            	str[2] = user.getUserName().toString();
            	str[3] = user.getMobilePhone().toString();
            	str[4] = user.getFixedPhone().toString();
            	str[5] = user.getUserEmail().toString();
            	tablemodel.addRow(str);
            }
		}
		catch(Exception e1){
			e1.printStackTrace();
		}
		this.repaint();
	}
}
