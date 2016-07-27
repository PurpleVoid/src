package window;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
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
	private JPasswordField pwdOldField,pwdNewField,pwdEnsureField;
	private JLabel pwdOldInfoLabel,pwdNewInfoLabel,pwdEnsureInfoLabel;
	private User user;
	private Connection con;
	private AccountDAO ad;
	private UserDAO ud;
	
	public UserManage(Account account){
		this.setTitle("个人信息");
		this.setSize(300, 260);
		this.setLayout(new FlowLayout());
		con = DatabaseConnection.getConnection();
		ad = new AccountDAO(con);
		ud = new UserDAO(con);
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
				JPanel f = new JPanel();
				f.setLayout(new GridLayout(3,3));
				
				f.add(new JLabel("旧密码",JLabel.RIGHT));
				pwdOldField = new JPasswordField(10);
				pwdOldField.setEchoChar('*');
				pwdOldField.addFocusListener(new FocusAdapter(){
					public void focusLost(FocusEvent e) {
						if(!account.getAccountPassword().equals(String.valueOf(pwdOldField.getPassword()))) {
							JOptionPane.showMessageDialog(null,"输入密码错误", "错误",JOptionPane.ERROR_MESSAGE);
							pwdOldField.requestFocus();
							pwdOldField.setText("");
						}
						else {
							pwdOldInfoLabel.setIcon(new ImageIcon("iconpng.png"));
						}
					}
				});
				f.add(pwdOldField);
				pwdOldInfoLabel = new JLabel("",JLabel.LEFT);
				f.add(pwdOldInfoLabel);
				
				f.add(new JLabel("新密码",JLabel.RIGHT));
				pwdNewField = new JPasswordField(10);
				pwdNewField.setEchoChar('*');
				f.add(pwdNewField);
				pwdNewInfoLabel = new JLabel("",JLabel.LEFT);
				f.add(pwdNewInfoLabel);
				
				f.add(new JLabel("确认密码",JLabel.RIGHT));
				pwdEnsureField = new JPasswordField(10);
				pwdEnsureField.setEchoChar('*');
				pwdEnsureField.addFocusListener(new FocusAdapter(){
					public void focusLost(FocusEvent e) {
						if(!String.valueOf(pwdNewField.getPassword()).equals(String.valueOf(pwdEnsureField.getPassword()))) {
							JOptionPane.showMessageDialog(null,"两次输入密码不匹配", "错误",JOptionPane.ERROR_MESSAGE);
							pwdEnsureField.requestFocus();
							pwdEnsureField.setText("");
						}
						else {
							pwdNewInfoLabel.setIcon(new ImageIcon("iconpng.png"));
							pwdEnsureInfoLabel.setIcon(new ImageIcon("iconpng.png"));
						}
					}
				});
				f.add(pwdEnsureField);
				pwdEnsureInfoLabel = new JLabel("",JLabel.LEFT);
				f.add(pwdEnsureInfoLabel);
				
				String but[]={"确认","取消"};
				int response = JOptionPane.showOptionDialog(null,f,"修改密码",JOptionPane.YES_OPTION,JOptionPane.CANCEL_OPTION,null,but,but[0]);					
				
				if (response==0) {
					account.setAccountPassword(String.valueOf(pwdEnsureField.getPassword()));
					try {
						if (ad.updateByAccountName(account)) {
							JOptionPane.showMessageDialog(null, "提交成功", "成功", JOptionPane.OK_OPTION);
						}
						else {
							System.out.println("pwdUpdate failed");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
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
