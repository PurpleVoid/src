package window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import factory.DAOFactory;
import table.Account;

public class UserList extends JInternalFrame {
	
	private JButton cancelBtn,addBtn,updateBtn,deleteBtn;
	private JTable table;
	private DefaultTableModel tablemodel;
	private JPanel contentPanel,buttonPanel;
	private JTextField accountNameText,userIDText;
	private JPasswordField pwdField;
	
	public UserList() {
		this.setTitle("用户列表");
		this.setSize(500, 600);
		this.setLayout(new FlowLayout());
		
		contentPanel = new JPanel();
        
        String[] title={"用户名","密码","工号"};
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
		f.setLayout(new GridLayout(3,1));
		
		f.add(new JLabel("用户名"));
		accountNameText = new JTextField(10);
		f.add(accountNameText);
		
		f.add(new JLabel("密码"));
		pwdField = new JPasswordField(10);
		f.add(pwdField);
		
		f.add(new JLabel("工号"));
		userIDText = new JTextField(10);
		f.add(userIDText);
		
		return f;
	}
	
	
	private class deletelistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int row = table.getSelectedRow();
		    try {
				if (DAOFactory.createAccount().deleteByAccountName(tablemodel.getValueAt(row, 0).toString()))
					JOptionPane.showMessageDialog(UserList.this,"数据已删除");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    print();
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
					if (DAOFactory.createAccount().updateByAccountName(account)) {
						JOptionPane.showMessageDialog(UserList.this, "提交成功", "成功", JOptionPane.OK_OPTION);
					}
					else {
						System.out.println("update failed");
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
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
					if (DAOFactory.createAccount().doCreate(account)) {
						JOptionPane.showMessageDialog(UserList.this, "提交成功", "成功", JOptionPane.OK_OPTION);
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			print();
		}

	}
	
	private void print(){
		tablemodel.setRowCount(0);
		try{
			ArrayList<Account> list = DAOFactory.createAccount().findAll();
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
