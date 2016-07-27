package window;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import connection.DatabaseConnection;
import dao.AccountDAO;
import dao.UserDAO;
import table.Account;
import table.User;

public class UserManage extends JInternalFrame {
	private JLabel accountNameLabel,userIDLabel,userNameLabel,mobilePhoneLabel,fixedPhoneLabel,userEmailLabel;
	private JTextField accountNameText,userIDText,userNameText,mobilePhoneText,fixedPhoneText,userEmailText;
	private JPanel contentPanel,buttonPanel;
	private JButton cancelBtn,pwdChangeBtn,editBtn,saveBtn;
	private User user;
	private AccountDAO ad;
	private UserDAO ud;
	
	public UserManage(Account account){
		this.setTitle("个人信息");
		this.setSize(300, 260);
		this.setLayout(new FlowLayout());
		ad = new AccountDAO();
		ad.setConnection(DatabaseConnection.getConnection());
		ud = new UserDAO();
		ud.setConnection(DatabaseConnection.getConnection());
		try {
			user = ud.findByUserID(account.getUserID());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(6,1));
		
		accountNameLabel = new JLabel("登录名",JLabel.RIGHT);
		accountNameLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				accountNameText.setText("");
	        } 
		});
		contentPanel.add(accountNameLabel);
		accountNameText = new JTextField(12);
		accountNameText.setText(account.getAccountName());
		accountNameText.setEditable(false);
		contentPanel.add(accountNameText);
		
		userIDLabel = new JLabel("工号",JLabel.RIGHT);
		userIDLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				accountNameText.setText("");
	        } 
		});
		contentPanel.add(userIDLabel);
		userIDText = new JTextField(12);
		userIDText.setText(account.getUserID().toString());
		userIDText.setEditable(false);
		contentPanel.add(userIDText);
		
		userNameLabel = new JLabel("真实姓名",JLabel.RIGHT);
		userNameLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				accountNameText.setText("");
	        } 
		});
		contentPanel.add(userNameLabel);
		userNameText = new JTextField(12);
		userNameText.setText(user.getUserName());
		userNameText.setEditable(false);
		contentPanel.add(userNameText);
		
		mobilePhoneLabel = new JLabel("手机号码",JLabel.RIGHT);
		mobilePhoneLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				accountNameText.setText("");
	        } 
		});
		contentPanel.add(mobilePhoneLabel);
		mobilePhoneText = new JTextField(12);
		mobilePhoneText.setText(user.getMobilePhone());
		mobilePhoneText.setEditable(false);
		contentPanel.add(mobilePhoneText);
		
		fixedPhoneLabel = new JLabel("固定电话",JLabel.RIGHT);
		fixedPhoneLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				accountNameText.setText("");
	        } 
		});
		contentPanel.add(fixedPhoneLabel);
		fixedPhoneText = new JTextField(12);
		fixedPhoneText.setText(user.getFixedPhone());
		fixedPhoneText.setEditable(false);
		contentPanel.add(fixedPhoneText);
		
		userEmailLabel = new JLabel("电子邮件",JLabel.RIGHT);
		userEmailLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				accountNameText.setText("");
	        } 
		});
		contentPanel.add(userEmailLabel);
		userEmailText = new JTextField(12);
		userEmailText.setText(user.getUserEmail());
		userEmailText.setEditable(false);
		contentPanel.add(userEmailText);
		
		this.getContentPane().add(contentPanel);
		
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		cancelBtn = new JButton("取消");		
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				UserManage.this.dispose();
			}
		});
		buttonPanel.add(cancelBtn);
		
		pwdChangeBtn = new JButton("修改密码");
		pwdChangeBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				//修改密码
			}
		});
		buttonPanel.add(pwdChangeBtn);
		editBtn = new JButton("编辑");
		editBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mobilePhoneText.setEditable(true);
				fixedPhoneText.setEditable(true);
				userEmailText.setEditable(true);
				editBtn.setVisible(false);
				saveBtn.setVisible(true);
			}
		});
		buttonPanel.add(editBtn);
		saveBtn = new JButton("保存");
		saveBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mobilePhoneText.setEditable(false);
				fixedPhoneText.setEditable(false);
				userEmailText.setEditable(false);
				saveBtn.setVisible(false);
				editBtn.setVisible(true);
				
				user.setMobilePhone(mobilePhoneText.getText().trim());
				user.setFixedPhone(fixedPhoneText.getText().trim());
				user.setUserEmail(userEmailText.getText().trim());
				try {
					if (ud.updateByUserID(user)) {
						JOptionPane.showMessageDialog(UserManage.this, "提交成功", "成功", JOptionPane.OK_OPTION);
					}
					else {
						System.out.println("update failed");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		saveBtn.setVisible(false);
		buttonPanel.add(saveBtn);
		
		this.getContentPane().add(buttonPanel);
		
		this.setVisible(true);
		
	}

}
