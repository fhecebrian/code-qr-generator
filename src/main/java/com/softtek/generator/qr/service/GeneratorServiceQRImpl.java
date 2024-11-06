package com.softtek.generator.qr.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.softtek.generator.qr.User;
import com.softtek.generator.qr.utlis.UtilsNames;
import com.softtek.generator.qr.utlis.ZipUtils;

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
			ImageIO.write(qrImage, type, new File(directory.concat(fileName.concat(".").concat(type))));
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

	@Override
	public List<User> getPeopleFromCsvFile(String text) {
		List<User> users = null;
		
		try {
			//URL fileUrl = CodeQrGeneratorApplication.class.getClassLoader().getResource(text);
			users = new CsvToBeanBuilder(new FileReader(text))
					.withSeparator(';')
			        .withType(User.class)
			        .build()
			        .parse();
		} catch (IllegalStateException | FileNotFoundException e) {
			LOGGER.error("not posible to get users from directory {}, and error {}",text, e.getCause());
		}		
                
        return users;
	}
	
	
    private File getResourceFile(final String fileName) 
    {
        URL url = this.getClass()
            .getClassLoader()
            .getResource(fileName);
        
        if(url == null) {
            throw new IllegalArgumentException(fileName + " is not found 1");
        }
        
        File file = new File(url.getFile());
        
        return file;
    }

	@Override
	public void CompressQr(String sourceFile, String zipFile) {
        FileOutputStream fos = null;
        ZipOutputStream zipOut = null;
		try {
			fos = new FileOutputStream(zipFile);
	        zipOut = new ZipOutputStream(fos);

	        File fileToZip = new File(sourceFile);
	        ZipUtils.zipFile(fileToZip, fileToZip.getName(), zipOut);
	        zipOut.close();
	        fos.close();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
