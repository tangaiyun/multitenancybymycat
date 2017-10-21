package com.wym.mycatdemo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	private long id;
	private String userId;
	private String itemName;
	private double itemPrice;
	private int itemNum;
	private Date createDatetime;
}
