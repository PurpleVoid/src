package table;

import java.util.Calendar;

public class ReturnSale {

	private Integer saleID;
	private Integer commodityID;
	private String commodityName;
	private Integer returnNumber;
	private Double totalMoney;
	private String returnOperator;
	private Calendar returnTime;
	
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
	public Integer getreturnNumber() {
		return returnNumber;
	}
	public void setreturnNumber(Integer returnNumber) {
		this.returnNumber = returnNumber;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getReturnOperator() {
		return returnOperator;
	}
	public void setReturnOperator(String returnOperator) {
		this.returnOperator = returnOperator;
	}
	public Calendar getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Calendar returnTime) {
		this.returnTime = returnTime;
	}
}
