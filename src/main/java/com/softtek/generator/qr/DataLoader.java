package com.softtek.generator.qr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.softtek.generator.qr.service.GeneratorServiceQR;
import com.softtek.generator.qr.service.InitService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@EnableConfigurationProperties(value = { QrProperties.class, FilesProperties.class })
public class DataLoader implements CommandLineRunner {

	@Autowired
	private GeneratorServiceQR generatorServiceQR;

	@Autowired
	QrProperties qrProperties;
	
	@Autowired
	InitService initService;

	@Autowired
	FilesProperties filesProperties;
	
	@Override
	public void run(String... args) throws Exception {

		initService.createInfraestructure();
		List<User> users = generatorServiceQR.getPeopleFromCsvFile(filesProperties.getTotalCsvDirectory());
		users.forEach(user -> {
			try {
			generatorServiceQR.generateFile(
					generatorServiceQR.generateQRCode(user.getName(), qrProperties.getWidth(), qrProperties.getWidth()), 
					user.getAlias(), qrProperties.getType(), filesProperties.getTotalQrDirectory());
		} catch (Exception e) {
			LOGGER.error("not posible to generate qr for user {} and error {}", user, e.getCause());
		}
			
		});

		generatorServiceQR.CompressQr(filesProperties.getTotalQrDirectory(),filesProperties.getTotalZipDirectory());
	}
	

}
