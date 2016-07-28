package window;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connection.DatabaseConnection;
import dao.ProviderDAO;
import table.Provider;

public class ProviderList extends JInternalFrame {
	
	private JButton cancelBtn,addBtn,updateBtn,deleteBtn;
	private JTable table;
	private DefaultTableModel tablemodel;
	private JPanel contentPanel,buttonPanel;
	private JTextField providerNameText,providerIDText,providerPhoneText,providerAddressText;
	private ProviderDAO pd;
	private Connection con;
	private Provider provider;
	
	public ProviderList() {
		this.setTitle("供应商列表");
		this.setSize(800, 700);
		this.setLayout(new BorderLayout()); 
		con = DatabaseConnection.getConnection();
		pd = new ProviderDAO(con);
		
		contentPanel = new JPanel();       
        String[] title={"供应商编号","供应商名称","供应商地址","供应商电话"};
        tablemodel = new DefaultTableModel(title,0);
		table = new JTable(tablemodel);
        contentPanel.add(new JScrollPane(table));
        this.getContentPane().add(contentPanel,BorderLayout.NORTH);
        
        buttonPanel = new JPanel();
        cancelBtn = new JButton("取消");		
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ProviderList.this.dispose();
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
	
	
	private class deletelistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int row = table.getSelectedRow();
			String providerID = tablemodel.getValueAt(row, 1).toString();
					
			try {
				if (pd.deleteByProviderID(providerID)) {
					JOptionPane.showMessageDialog(null,"数据已删除");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			print();
		}

	}
	
	private class updatelistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JPanel f = new JPanel();
			f.setLayout(new GridLayout(4,2));
			
			int row = table.getSelectedRow();
			String providerID = tablemodel.getValueAt(row, 0).toString();
			String providerName = tablemodel.getValueAt(row, 1).toString();
			String providerAddress = tablemodel.getValueAt(row, 2).toString();
			String providerPhone = tablemodel.getValueAt(row, 3).toString();
			f.add(new JLabel("供应商编号"));
			providerIDText = new JTextField(providerID);
			f.add(providerIDText);
			
			f.add(new JLabel("供应商名称"));
			providerNameText = new JTextField(providerName);
			f.add(providerNameText);
			
			f.add(new JLabel("供应商地址"));
			providerAddressText = new JTextField(providerAddress);
			f.add(providerAddressText);
			
			f.add(new JLabel("供应商电话"));
			providerPhoneText = new JTextField(providerPhone);
			f.add(providerPhoneText);
			
			
			String but[]={"确认","取消"};
			int response = JOptionPane.showOptionDialog(null,f,"更改窗口",JOptionPane.YES_OPTION,JOptionPane.CANCEL_OPTION,null,but,but[0]);
			if (response==0) {
				provider = new Provider();
				provider.setProviderID(Integer.parseInt(providerIDText.getText().trim()));
				provider.setProviderName(providerNameText.getText().trim());				
				provider.setProviderAddress(providerAddressText.getText().trim());
				provider.setProviderPhone(providerPhoneText.getText().trim());
				try {
					if (pd.updateByProviderID(provider)) {
						JOptionPane.showMessageDialog(null, "提交成功", "成功", JOptionPane.OK_OPTION);
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
			f.setLayout(new GridLayout(4,2));
			
			f.add(new JLabel("供应商编号"));
			providerIDText = new JTextField(10);
			f.add(providerIDText);
			
			f.add(new JLabel("供应商名称"));
			providerNameText = new JTextField(10);
			f.add(providerNameText);
			
			f.add(new JLabel("供应商地址"));
			providerAddressText = new JTextField(10);
			f.add(providerAddressText);
			
			f.add(new JLabel("供应商电话"));
			providerPhoneText = new JTextField(10);
			f.add(providerPhoneText);
			
			
			String but[]={"确认","取消"};
			int response = JOptionPane.showOptionDialog(null,f,"添加窗口",JOptionPane.YES_OPTION,JOptionPane.CANCEL_OPTION,null,but,but[0]);
			if (response==0) {
				provider = new Provider();
				int providerID = Integer.parseInt(providerIDText.getText().trim());
				provider.setProviderID(providerID);
				provider.setProviderName(providerNameText.getText().trim());			
				provider.setProviderAddress(providerAddressText.getText().trim());
				provider.setProviderPhone(providerPhoneText.getText().trim());
				try {
					if (pd.doCreate(provider)) {
						JOptionPane.showMessageDialog(null, "提交成功", "成功", JOptionPane.OK_OPTION);
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
			List<Provider> providerList = pd.findAll();
            Iterator<Provider> providerIt = providerList.iterator();
            while(providerIt.hasNext()){
            	provider = providerIt.next();
            	String[] str = new String[4];
            	str[0] = provider.getProviderID().toString();
            	str[1] = provider.getProviderName().toString();
            	str[2] = provider.getProviderAddress().toString();
            	str[3] = provider.getProviderPhone().toString();
            	tablemodel.addRow(str);
            }
		}
		catch(Exception e1){
			e1.printStackTrace();
		}
		this.repaint();
	}
}
