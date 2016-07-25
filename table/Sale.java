package table;

import java.util.Calendar;

public class Sale {

	private Integer saleID;
	private Integer commodityID;
	private String commodityName;
	private Integer sellNumber;
	private Double totalMoney;
	private String sellOperator;
	private Calendar sellTime;
	
	public Integer getSaleID() {
		return saleID;
	}
	public void setSaleID(Integer saleID) {
		this.saleID = saleID;
	}
	public Integer getCommodityID() {
		return commodityID;
	}
	public void setCommodityID(Integer commodityID) {
		this.commodityID = commodityID;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public Integer getSellNumber() {
		return sellNumber;
	}
	public void setSellNumber(Integer sellNumber) {
		this.sellNumber = sellNumber;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getSellOperator() {
		return sellOperator;
	}
	public void setSellOperator(String sellOperator) {
		this.sellOperator = sellOperator;
	}
	public Calendar getSellTime() {
		return sellTime;
	}
	public void setSellTime(Calendar sellTime) {
		this.sellTime = sellTime;
	}
	
	
}
