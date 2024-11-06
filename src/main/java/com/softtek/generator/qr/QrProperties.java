package com.softtek.generator.qr;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ConfigurationProperties(prefix = "definition.qr")
public class QrProperties {
	
	private final Integer width;
	private final Integer height;
	private final String type; 
}
