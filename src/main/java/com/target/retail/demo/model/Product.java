package com.target.retail.demo.model;




import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table("retaildemo.products_by_id")
public class Product {

  @PrimaryKeyColumn(name = "product_id", type = PrimaryKeyType.PARTITIONED)
  private int productId;

  @Column("product_name")
  private String productName;
  
 @Column("currency_code")
  private String currencyCode;
  
  @Column("current_value")
  private String currentValue;

  public Product(){
	  
  }

public Product(int productId, String productName,String currencyCode, String currentValue) {
	this.productId = productId;
	this.productName = productName;
	this.currencyCode = currencyCode;
	this.currentValue = currentValue;
	
	
}

public int getProductId() {
	return productId;
}

public void setProductId(int productId) {
	this.productId = productId;
}

public String getProductName() {
	return productName;
}

public void setProductName(String productName) {
	this.productName = productName;
}

public String getCurrencyCode() {
	return currencyCode;
}

public void setCurrencyCode(String currencyCode) {
	this.currencyCode = currencyCode;
}

public String getCurrentValue() {
	return currentValue;
}

public void setCurrentValue(String currentValue) {
	this.currentValue = currentValue;
}
}