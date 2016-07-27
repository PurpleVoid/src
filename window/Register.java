package window;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import connection.DatabaseConnection;
import dao.AccountDAO;
import dao.UserDAO;
import table.Account;
import table.User;


public class Register extends JFrame {
	
	
	private JTextField accountNameText,userIDText;
	private JPasswordField pwdField,pwdEnsureField;
	private JButton cancelButton,submitButton;
	private JLabel accountNameLabel,accountInfoLabel,pwdLabel,pwdInfoLabel,pwdEnsureLabel,pwdEnsureInfoLabel,userIDLabel,userIDInfoLabel;
	private JPanel accountNamePanel,pwdPanel,pwdEnsurePanel,userIDPanel,buttonPanel;
	private Connection con;
	private Account account;
	private AccountDAO ad;
	
	public Register() {
		
		this.setTitle("注册用户");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(300,200);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		con = DatabaseConnection.getConnection();
		ad = new AccountDAO(con);
		//用户名
		accountNamePanel = new JPanel();
		accountNamePanel.setLayout(new GridLayout(1,3));
		
		accountNameLabel = new JLabel("用户名",JLabel.RIGHT);
		accountNameLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				accountNameText.setText("");
	        } 
		});
		accountNamePanel.add(accountNameLabel);
		accountNameText = new JTextField(10);
		accountNameText.addFocusListener(new myFocusAdapter());
		accountNamePanel.add(accountNameText);
		accountInfoLabel = new JLabel("",JLabel.LEFT);
		accountNamePanel.add(accountInfoLabel);
		this.getContentPane().add(accountNamePanel);
		
		//密码
		pwdPanel = new JPanel();
		pwdPanel.setLayout(new GridLayout(1,3));
		
		pwdLabel = new JLabel("密  码",JLabel.RIGHT);
		pwdLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				pwdField.setText("");
	        } 
		});
		pwdPanel.add(pwdLabel);
		pwdField = new JPasswordField(10);
		pwdField.setEchoChar('*');
		pwdPanel.add(pwdField);
		pwdInfoLabel = new JLabel("",JLabel.LEFT);
		pwdPanel.add(pwdInfoLabel);
		this.getContentPane().add(pwdPanel);
		
		//密码确认
		pwdEnsurePanel = new JPanel();
		pwdEnsurePanel.setLayout(new GridLayout(1,3));
		
		pwdEnsureLabel = new JLabel("确认密码",JLabel.RIGHT);
		pwdEnsureLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				pwdEnsureField.setText("");
	        } 
		});
		pwdEnsurePanel.add(pwdEnsureLabel);
		pwdEnsureField = new JPasswordField(10);
		pwdEnsureField.setEchoChar('*');
		pwdEnsureField.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent e) {
				if(!String.valueOf(pwdField.getPassword()).equals(String.valueOf(pwdEnsureField.getPassword()))) {
					JOptionPane.showMessageDialog(Register.this ,"两次输入密码不匹配", "错误",JOptionPane.ERROR_MESSAGE);
					pwdEnsureField.requestFocus();
					pwdEnsureField.setText("");
				}
				else {
					pwdInfoLabel.setIcon(new ImageIcon("iconpng.png"));
					pwdEnsureInfoLabel.setIcon(new ImageIcon("iconpng.png"));
				}
			}
		});
		pwdEnsurePanel.add(pwdEnsureField);
		pwdEnsureInfoLabel = new JLabel("",JLabel.LEFT);
		pwdEnsurePanel.add(pwdEnsureInfoLabel);
		this.getContentPane().add(pwdEnsurePanel);
		
		//工号
		userIDPanel = new JPanel();
		userIDPanel.setLayout(new GridLayout(1,3));
		
		userIDLabel = new JLabel("工号",JLabel.RIGHT);
		userIDLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				userIDText.setText("");
	        } 
		});
		userIDPanel.add(userIDLabel);
		userIDText = new JTextField(10);
		userIDPanel.add(userIDText);
		userIDInfoLabel = new JLabel("",JLabel.LEFT);
		userIDPanel.add(userIDInfoLabel);
		this.getContentPane().add(userIDPanel);
			
		
		//按钮
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		cancelButton = new JButton("取消");		
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Register.this.dispose();
			}
		});
		buttonPanel.add(cancelButton);
		
		submitButton = new JButton("提交");
		submitButton.addActionListener(new clickOnSubmit());		
		buttonPanel.add(submitButton);
		
		this.getContentPane().add(buttonPanel);
		
		this.setVisible(true);
		
	}
	
	
	public class myFocusAdapter extends FocusAdapter {

		@Override
		public void focusLost(FocusEvent e) {
			try {
				account = ad.findByAccountName(accountNameText.getText().trim());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (account == null) {
				accountInfoLabel.setIcon(new ImageIcon("iconpng.png"));			
			}
			else {
				JOptionPane.showMessageDialog(Register.this ,"用户名已存在", "错误",JOptionPane.ERROR_MESSAGE);
				accountNameText.requestFocus();
				accountNameText.setText("");
			}
		}

	}
	
	public class clickOnSubmit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Account account = new Account();
			account.setAccountName(accountNameText.getText().trim());
			account.setAccountPassword(String.valueOf(pwdField.getPassword()));
			account.setAccountType(1);
			account.setUserID(Integer.parseInt(userIDText.getText().trim()));
			try {
				if (ad.doCreate(account)) {
					JOptionPane.showMessageDialog(Register.this, "提交成功", "成功", JOptionPane.OK_OPTION);
				}
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
	

}
