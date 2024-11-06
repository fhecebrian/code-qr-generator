package com.softtek.generator.qr.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.generator.qr.FilesProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InitServiceImpl implements InitService{

	@Autowired
	FilesProperties filesProperties;
	
	
	@Override
	public void createInfraestructure() {
		
		createDirectory(filesProperties.getPathDirectory());
		createDirectory(filesProperties.getPathDirectory().concat(filesProperties.getCsvDirectory()));
		createDirectory(filesProperties.getPathDirectory().concat(filesProperties.getZipDirectory()));
		createDirectory(filesProperties.getPathDirectory().concat(filesProperties.getQrDirectory()));
	}

	private void createDirectory(String fileDirectories) {
		  try {

			    Path path = Paths.get(fileDirectories);
			    Files.createDirectories(path);
			    Files.createDirectories(path);

			  } catch (IOException e) {

			    System.err.println("Failed to create directory!" + e.getMessage());

			  }
		
	}

}
