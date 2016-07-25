package window;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class UserManage extends JPanel {
	private JLabel accountNameLabel,userIDLabel,userNameLabel,mobilePhoneLabel,fixedPhoneLabel,userEmailLabel;
	private JTextField accountNameText,userIDText,userNameText,mobilePhoneText,fixedPhoneText,userEmailText;
	
	
	public UserManage(){
		this.setSize(500, 500);
		this.setLayout(new GridLayout(7,2));
		
		accountNameLabel = new JLabel("登录名",JLabel.CENTER);
		accountNameLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				accountNameText.setText("");
	        } 
		});
		accountNameText = new JTextField(12); 
		
		userIDLabel = new JLabel("用户编号",JLabel.RIGHT);
		userIDLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				accountNameText.setText("");
	        } 
		});
		userIDText = new JTextField(12);
		
		userNameLabel = new JLabel("用户姓名",JLabel.RIGHT);
		userNameLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				accountNameText.setText("");
	        } 
		});
		userNameText = new JTextField(12);
		
		mobilePhoneLabel = new JLabel("手机号码",JLabel.RIGHT);
		mobilePhoneLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				accountNameText.setText("");
	        } 
		});
		mobilePhoneText = new JTextField(12);
		
		fixedPhoneLabel = new JLabel("固定电话",JLabel.RIGHT);
		fixedPhoneLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				accountNameText.setText("");
	        } 
		});
		fixedPhoneText = new JTextField(12);
		
		userEmailLabel = new JLabel("电子邮件",JLabel.RIGHT);
		userEmailLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				accountNameText.setText("");
	        } 
		});
		userEmailText = new JTextField(12);
		
		
		this.add(accountNameLabel);
		this.add(accountNameText);
		this.add(userIDLabel);
		this.add(userIDText);
		this.add(userNameLabel);
		this.add(userNameText);
		this.add(mobilePhoneLabel);
		this.add(mobilePhoneText);
		this.add(fixedPhoneLabel);
		this.add(fixedPhoneText);
		this.add(userEmailLabel);
		this.add(userEmailText);
		
		this.setVisible(true);
		
	}

}
