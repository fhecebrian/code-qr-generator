package com.softtek.generator.qr;

import com.opencsv.bean.CsvBindByName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	@CsvBindByName(column = "alias")
	private String alias;
	@CsvBindByName(column = "name")
	private String name;
	
}
