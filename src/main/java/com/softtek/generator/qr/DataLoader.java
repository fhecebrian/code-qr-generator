package com.softtek.generator.qr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.softtek.generator.qr.service.GeneratorServiceQR;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@EnableConfigurationProperties(value = { QrProperties.class })
public class DataLoader implements CommandLineRunner {

	@Autowired
	private GeneratorServiceQR generatorServiceQR;

	@Autowired
	QrProperties qrProperties;

	@Override
	public void run(String... args) throws Exception {
		List<String> users = generatorServiceQR.getPeopleFromFile(qrProperties.getPeople());
		users.forEach(user -> {
			try {
				generatorServiceQR.generateFile(
						generatorServiceQR.generateQRCode(user, qrProperties.getWidth(), qrProperties.getWidth()), user,
						qrProperties.getType(), qrProperties.getRepository());
			} catch (Exception e) {
				LOGGER.error("not posible to generate qr for user {} and error {}", user, e.getCause());
			}
		});
	}

}
