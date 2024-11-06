package com.softtek.generator.qr;

import java.io.File;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ConfigurationProperties(prefix = "infrastructure")
public class FilesProperties {
	
	private final String pathDirectory;
	private final String csvDirectory;
	private final String qrDirectory; 
	private final String zipDirectory; 
	private final String csvFile; 
	private final String zipFile; 
	
	
	public String getTotalCsvDirectory(){
		return getAbsoluteFolder(this.pathDirectory.concat(this.csvDirectory)).concat(this.csvFile);
	}

	public String getTotalQrDirectory(){
		return getAbsoluteFolder(this.pathDirectory.concat(this.qrDirectory));
	}

	public String getTotalZipDirectory(){
		return getAbsoluteFolder(this.pathDirectory.concat(this.zipDirectory)).concat(this.zipFile);
	}
	
	private String getAbsoluteFolder(String path) {
		return new File(path).getAbsolutePath().concat(System.getProperty("file.separator"));
	}
}
