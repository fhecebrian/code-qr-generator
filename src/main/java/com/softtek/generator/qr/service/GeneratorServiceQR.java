package com.softtek.generator.qr.service;

import java.awt.image.BufferedImage;
import java.util.List;

public interface GeneratorServiceQR {

	public void generateFile(BufferedImage qrImage, String fileName, String type, String directory);
	
	public BufferedImage generateQRCode(String text, int width, int height) throws Exception;

	public List<String> getPeopleFromFile(String text);
	
}
