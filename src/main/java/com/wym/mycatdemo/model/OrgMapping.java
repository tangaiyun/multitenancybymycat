package com.wym.mycatdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgMapping {
	private long id;
	private String code;
	private String schema;
	
}
