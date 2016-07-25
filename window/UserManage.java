package window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	
	private JTabbedPane tabbedPane;
	private JPanel userTabPanel,commodityTabPanel,providerTabPanel,circulateTabPanel;
	private JPanel userBtnPanel,commodityBtnPanel,providerBtnPanel,circulateBtnPanel;
	private JPanel userContentPanel,commodityContentPanel,providerContentPanel,circulateContentPanel;
	private JButton userListBtn,userManageBtn,commodityListBtn,providerListBtn,circulateInBtn,circulateSaleBtn,circulateReturnBtn;
	
	public MainFrame(int accountType) {
		this.setTitle("小型超市管理系统");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screen.width, screen.height);		
		this.setResizable(false);
		
		tabbedPane = new JTabbedPane();
		//用户与权限管理
		userTabPanel = new JPanel();
		userTabPanel.setLayout(new BorderLayout());
		userBtnPanel = new JPanel();
		userBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		userContentPanel=new JPanel();
		CardLayout card=new CardLayout();
		userContentPanel.setLayout(card);
		userManageBtn = new JButton("个人信息");
		userManageBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				UserManage user  = new UserManage();
				userContentPanel.add(user,"user");
				card.show(user,"user");
				userContentPanel.repaint();
			}
		});
		userBtnPanel.add(userManageBtn);
		if (accountType == 0) {
			userListBtn = new JButton("用户列表");
			userListBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JPanel f = new JPanel();

					userContentPanel.add(f);
					userContentPanel.repaint();
				}
			});
			userBtnPanel.add(userListBtn);
		}
		userTabPanel.add(userBtnPanel,BorderLayout.NORTH);
		
		userContentPanel = new JPanel();
		userContentPanel.setBackground(Color.DARK_GRAY);
		userTabPanel.add(userContentPanel,BorderLayout.CENTER);
		
		tabbedPane.add("用户信息管理", userTabPanel);
		
		//商品管理
		commodityTabPanel = new JPanel();
		commodityTabPanel.setLayout(new BorderLayout());
		commodityBtnPanel = new JPanel();
		commodityBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		commodityListBtn = new JButton("商品列表");
		commodityListBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JPanel f = new JPanel();
				
				commodityContentPanel.add(f);
				commodityContentPanel.repaint();
			}
		});
		commodityBtnPanel.add(commodityListBtn);
		commodityTabPanel.add(commodityBtnPanel,BorderLayout.NORTH);
		
		commodityContentPanel = new JPanel();
		commodityContentPanel.setBackground(Color.DARK_GRAY);
		commodityTabPanel.add(commodityContentPanel,BorderLayout.CENTER);
		
		tabbedPane.add("商品管理", commodityTabPanel);

		//供应商管理
		providerTabPanel = new JPanel();
		providerTabPanel.setLayout(new BorderLayout());
		providerBtnPanel = new JPanel();
		providerBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		providerListBtn = new JButton("供应商列表");
		providerListBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JPanel f = new JPanel();
				
				providerContentPanel.add(f);
				providerContentPanel.repaint();
			}
		});
		providerBtnPanel.add(providerListBtn);
		providerTabPanel.add(providerBtnPanel,BorderLayout.NORTH);
		
		providerContentPanel = new JPanel();
		providerContentPanel.setBackground(Color.DARK_GRAY);
		providerTabPanel.add(providerContentPanel,BorderLayout.CENTER);
		
		tabbedPane.add("供应商管理", providerTabPanel);

		//货物流通管理
		circulateTabPanel = new JPanel();
		circulateTabPanel.setLayout(new BorderLayout());
		circulateBtnPanel = new JPanel();
		circulateBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		circulateInBtn = new JButton("进货记录");
		circulateInBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JPanel f = new JPanel();
				
				circulateContentPanel.add(f);
				circulateContentPanel.repaint();
			}
		});
		circulateBtnPanel.add(circulateInBtn);
		
		circulateSaleBtn = new JButton("销售记录");
		circulateSaleBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JPanel f = new JPanel();
				
				circulateContentPanel.add(f);
				circulateContentPanel.repaint();
			}
		});
		circulateBtnPanel.add(circulateSaleBtn);
		
		circulateReturnBtn = new JButton("退货记录");
		circulateReturnBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JPanel f = new JPanel();
				
				circulateContentPanel.add(f);
				circulateContentPanel.repaint();
			}
		});
		circulateBtnPanel.add(circulateReturnBtn);
		
		circulateTabPanel.add(circulateBtnPanel,BorderLayout.NORTH);
		
		circulateContentPanel = new JPanel();
		circulateContentPanel.setBackground(Color.DARK_GRAY);
		circulateTabPanel.add(circulateContentPanel,BorderLayout.CENTER);
		
		tabbedPane.add("货物流通管理", circulateTabPanel);
				
		this.add(tabbedPane);		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame(0);
	}	
		
}

