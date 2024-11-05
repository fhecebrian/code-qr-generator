package com.softtek.generator.qr.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.softtek.generator.qr.utlis.UtilsNames;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GeneratorServiceQRImpl implements GeneratorServiceQR{

	@Override
	public BufferedImage generateQRCode(String text, int width, int height) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(UtilsNames.generateAcronymWithSofttek(text), BarcodeFormat.QR_CODE, width, height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}

	@Override
	public void generateFile(BufferedImage qrImage, String fileName, String type, String directory) {
        try {
			ImageIO.write(qrImage, type, new File(directory.concat(UtilsNames.generateAcronym(fileName).concat(".").concat(type))));
		} catch (IOException e) {
			LOGGER.error("not posible to generate qr for user {} and directory {} and error {}",fileName, directory, e.getCause());
		}
		
	}

	@Override
	public List<String> getPeopleFromFile(String text) {
		 ObjectMapper mapper = new ObjectMapper();
         TypeReference<List<String>> typeReference = new TypeReference<List<String>>(){};
         
         InputStream inputStream = TypeReference.class.getResourceAsStream(text);
         List<String> users =null;
         try {
             users = mapper.readValue(inputStream,typeReference);
         } catch (IOException e){
 			LOGGER.error("not posible to get users from directory {}, and error {}",text, e.getCause());
         }
         return users;
	}

}
