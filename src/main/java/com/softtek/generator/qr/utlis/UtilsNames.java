package com.softtek.generator.qr.utlis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilsNames {
	
	private static String SOFTTEK = "SOFTTEK - ";
	

	public static String generateAcronym(String name) {
        StringBuilder acronym = new StringBuilder();

        Pattern pattern = Pattern.compile("\\b\\w");
        Matcher matcher = pattern.matcher(name);

        while (matcher.find()) {
        	acronym.append(Character.toUpperCase(matcher.group().charAt(0)));
        }
        return acronym.toString();	
   }
	
	public static String generateAcronymWithSofttek(String name) {
		
		return SOFTTEK.concat(generateAcronym(name).concat(" - ").concat(name));
	}
}
