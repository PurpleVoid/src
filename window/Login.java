package window;

import tools.Captcha;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import factory.DAOFactory;
import table.Account;

public class Login extends JFrame {

	
	private JTextField accountNameText,captchaText;
	private JPasswordField pwdField;
	private JButton loginButton,cancelButton;
	private JLabel accountNameLabel,registerLabel,pwdLabel,pwdForgetLabel,captchaLabel,captchaIconLabel;
	private String captchaString;
	private Captcha captcha;
	private JPanel accountNamePanel,pwdPanel,captchaPanel,buttonPanel;
	Account account;
	
	public Login(){
		
		this.setTitle("用户登录");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(330,320);
		this.setLocationRelativeTo(null); 
		this.setLayout(new FlowLayout());
		this.getContentPane().add(new JLabel(new ImageIcon("water3.png")));
		
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
		accountNamePanel.add(accountNameText);
		registerLabel = new JLabel("注册");
		registerLabel.setForeground(Color.BLUE);
		registerLabel.setFont(new Font("幼圆",Font.PLAIN,15));
		registerLabel.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				registerLabel.setForeground(Color.MAGENTA);
			}
			public void mouseExited(MouseEvent e) {
				registerLabel.setForeground(Color.BLUE);
			}
			public void mouseClicked(MouseEvent e) {
				new Register();
	        } 
		});
		accountNamePanel.add(registerLabel);
		this.getContentPane().add(accountNamePanel);
		
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
		pwdForgetLabel = new JLabel("忘记密码？");
		pwdForgetLabel.setForeground(Color.BLUE);
		pwdForgetLabel.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				pwdForgetLabel.setForeground(Color.MAGENTA);
			}
			public void mouseExited(MouseEvent e) {
				pwdForgetLabel.setForeground(Color.BLUE);
			}
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(Login.this, "找管理员吧^^","提醒",JOptionPane.INFORMATION_MESSAGE);
	        } 
		});
		pwdPanel.add(pwdForgetLabel);
		this.getContentPane().add(pwdPanel);
		
		captchaPanel = new JPanel();
		captchaPanel.setLayout(new GridLayout(1,3));
		
		captchaLabel = new JLabel("验证码",JLabel.RIGHT);
		captchaLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				captchaText.setText("");
	        }   
		});
		captchaPanel.add(captchaLabel);
		captchaText = new JTextField(10);
		captchaPanel.add(captchaText);
		
		captcha = new Captcha();	
		captchaIconLabel = new JLabel(captcha.getInstance(60,25),JLabel.LEFT);
		captchaString = captcha.getString();
		captchaPanel.add(captchaIconLabel);
		captchaIconLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				captcha = new Captcha();
				captchaIconLabel.setIcon(captcha.getInstance(60,25));
				captchaString = captcha.getString();
				System.out.println(captchaString);
				Login.this.repaint();
	        }   
		});
		
		
		this.getContentPane().add(captchaPanel);
		
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		cancelButton = new JButton("取消");		
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Login.this.dispose();
			}
		});
		loginButton = new JButton("登录");		
		loginButton.addActionListener(new loginListener());
		
		buttonPanel.add(cancelButton);
		buttonPanel.add(loginButton);
		this.getContentPane().add(buttonPanel);
		
		this.setVisible(true);
	}
	
	

	public class loginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (accountNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(Login.this, "用户名不能为空","错误",JOptionPane.ERROR_MESSAGE);
			}
			else if (pwdField.getPassword().length == 0) {
				JOptionPane.showMessageDialog(Login.this, "密码不能为空","错误",JOptionPane.ERROR_MESSAGE);
			}
			else if (captchaText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(Login.this, "验证码不能为空","错误",JOptionPane.ERROR_MESSAGE);
			}
			else {
				account = null;
				try {
					account = DAOFactory.createAccount().findByAccountName(accountNameText.getText().trim());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
				if (!captchaString.equals(captchaText.getText().trim())) {
					JOptionPane.showMessageDialog(Login.this, "验证码错误", "错误",JOptionPane.ERROR_MESSAGE);
				}
				else if (account == null) {
					JOptionPane.showMessageDialog(Login.this, "用户名不存在", "错误",JOptionPane.ERROR_MESSAGE);
				}
				else if (!account.getAccountPassword().equals(String.valueOf(pwdField.getPassword()))) {
					JOptionPane.showMessageDialog(Login.this, "密码错误", "错误",JOptionPane.ERROR_MESSAGE);
				}
				else {
					System.out.println("success");
					new MainFrame(account.getAccountType());
					Login.this.dispose();
				}
			}
		}

	}

	public static void main(String[] args) {
		new Login();
	}	
	
}