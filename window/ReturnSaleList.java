package window;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connection.DatabaseConnection;
import dao.ReturnSaleDAO;
import dao.CommodityDAO;
import table.ReturnSale;
import table.Commodity;

public class ReturnSaleList extends JInternalFrame {
	
	private JButton cancelBtn,addBtn,submitBtn,deleteBtn;
	private JTable table;
	private DefaultTableModel tablemodel;
	private JPanel contentPanel,buttonPanel;
	private JTextField saleIDText,commodityIDText,commodityNameText,returnNumberText,totalMoneyText,returnOperatorText;
	private JSpinner returnTimeSpinner;
	private ReturnSaleDAO rd;
	private Connection con;
	private ReturnSale returnSale;
	
	public ReturnSaleList() {
		this.setTitle("退货列表");
		this.setSize(800, 700);
		this.setLayout(new BorderLayout()); 
		con = DatabaseConnection.getConnection();
		rd = new ReturnSaleDAO(con);
		
		contentPanel = new JPanel();       
        String[] title={"销售单号","商品编号","商品名称","退货数量","总价","操作人员","退货日期"};
        tablemodel = new DefaultTableModel(title,0);
		table = new JTable(tablemodel);
        contentPanel.add(new JScrollPane(table));
        this.getContentPane().add(contentPanel,BorderLayout.NORTH);
        
        buttonPanel = new JPanel();
        cancelBtn = new JButton("取消");		
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ReturnSaleList.this.dispose();
			}
		});
		buttonPanel.add(cancelBtn);
        addBtn = new JButton("添加");
        addBtn.addActionListener(new addListener());
        buttonPanel.add(addBtn);      
        deleteBtn = new JButton("删除");     
        deleteBtn.addActionListener(new deleteListener());      
        buttonPanel.add(deleteBtn);
        submitBtn = new JButton("入库");
        submitBtn.addActionListener(new submitListener());
        buttonPanel.add(submitBtn);
        this.getContentPane().add(buttonPanel,BorderLayout.SOUTH);
        this.setVisible(true);
		print();
	}
	
	
	private class deleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int row = table.getSelectedRow();
			String saleID = tablemodel.getValueAt(row, 0).toString();
					
			try {
				if (rd.deleteBySaleID(saleID)) {
					JOptionPane.showMessageDialog(null,"数据已删除");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			print();
		}

	}
	
	private class submitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}

	}
	private class addListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JPanel f = new JPanel();
			f.setLayout(new GridLayout(7,2));
			
			f.add(new JLabel("销售单号"));
			saleIDText = new JTextField(10);
			f.add(saleIDText);
			
			f.add(new JLabel("商品编号"));
			commodityIDText = new JTextField(10);
			f.add(commodityIDText);
			
			f.add(new JLabel("商品名称"));
			commodityNameText = new JTextField(10);
			f.add(commodityNameText);
			
			f.add(new JLabel("退货数量"));
			returnNumberText = new JTextField(10);
			f.add(returnNumberText);
			
			f.add(new JLabel("总价"));
			totalMoneyText = new JTextField(10);
			f.add(totalMoneyText);
			
			f.add(new JLabel("操作人员"));
			returnOperatorText = new JTextField(10);
			f.add(returnOperatorText);
			
			f.add(new JLabel("退货日期"));			
			SpinnerDateModel model = new SpinnerDateModel();
		    returnTimeSpinner = new JSpinner(model);
			JSpinner.DateEditor edit = new JSpinner.DateEditor(returnTimeSpinner,"yyyy-MM-dd"); 
			returnTimeSpinner.setEditor(edit);
			f.add(returnTimeSpinner);
			
			int response = JOptionPane.showConfirmDialog(null,f,"添加窗口",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			
			if (response == JOptionPane.OK_OPTION) {
				returnSale = new ReturnSale();
				returnSale.setSaleID(Integer.parseInt(saleIDText.getText().trim()));
				returnSale.setCommodityID(Integer.parseInt(commodityIDText.getText().trim()));			
				returnSale.setCommodityName(commodityNameText.getText().trim());
				returnSale.setReturnNumber(Integer.parseInt(returnNumberText.getText().trim()));
				returnSale.setTotalMoney(Double.parseDouble(totalMoneyText.getText().trim()));
				returnSale.setReturnOperator(returnOperatorText.getText().trim());
				returnSale.setReturnTime((Calendar) returnTimeSpinner.getValue());
				try {
					if (rd.doCreate(returnSale)) {
						JOptionPane.showMessageDialog(null,"提交成功");
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
			List<ReturnSale> returnSaleList = rd.findAll();
            Iterator<ReturnSale> returnSaleIt = returnSaleList.iterator();
            while(returnSaleIt.hasNext()){
            	returnSale = returnSaleIt.next();
            	String[] str = new String[7];
            	str[0] = returnSale.getSaleID().toString();
            	str[1] = returnSale.getCommodityID().toString();
            	str[2] = returnSale.getCommodityName();
            	str[3] = returnSale.getReturnNumber().toString();
            	str[4] = returnSale.getTotalMoney().toString();
            	str[5] = returnSale.getReturnOperator();
            	str[6] = new SimpleDateFormat("yyyy-MM-dd").format(returnSale.getReturnTime().getTime());
            	tablemodel.addRow(str);
            }
		}
		catch(Exception e1){
			e1.printStackTrace();
		}
		this.repaint();
	}
}
