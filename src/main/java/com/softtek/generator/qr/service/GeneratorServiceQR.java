package com.softtek.generator.qr.service;

import java.awt.image.BufferedImage;
import java.util.List;

import com.softtek.generator.qr.User;

public interface GeneratorServiceQR {

	public void generateFile(BufferedImage qrImage, String fileName, String type, String directory);
	
	public BufferedImage generateQRCode(String text, int width, int height) throws Exception;

	public List<String> getPeopleFromFile(String text);

	public List<User> getPeopleFromCsvFile(String text);
	
	public void CompressQr(String sourceFile, String zipFile);
	
}
