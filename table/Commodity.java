package table;

import java.util.Calendar;

public class Commodity {

	private Integer commodityID;
	private String commodityName;
	private Integer providerNumber;
	private Double inPrice;
	private Double salePrice;
	private Calendar produceDate;
	private Calendar inDate;
	private Calendar storageTime;
	private Integer downLimit;
	private String unitName;
	private Integer commodityNumber;
	
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
	public Integer getProviderNumber() {
		return providerNumber;
	}
	public void setProviderNumber(Integer providerNumber) {
		this.providerNumber = providerNumber;
	}
	public Double getInPrice() {
		return inPrice;
	}
	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public Calendar getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(Calendar produceDate) {
		this.produceDate = produceDate;
	}
	public Calendar getInDate() {
		return inDate;
	}
	public void setInDate(Calendar inDate) {
		this.inDate = inDate;
	}
	public Calendar getStorageTime() {
		return storageTime;
	}
	public void setStorageTime(Calendar storageTime) {
		this.storageTime = storageTime;
	}
	public Integer getDownLimit() {
		return downLimit;
	}
	public void setDownLimit(Integer downLimit) {
		this.downLimit = downLimit;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Integer getCommodityNumber(){
		return commodityNumber;
	}
	public void setCommodityNumber(Integer commodityNumber) {
		this.commodityNumber = commodityNumber;
	}
	
	
}

