package com.kh;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesRun {

	public static void main(String[] args) {
		Properties prop = new  Properties();
		prop.setProperty("A","B");
		try {
			//prop.store(new FileOutputStream("resources/driver.properties"), "setting for DBMS");
			prop.storeToXML(new FileOutputStream("member.mapper.xml"), "Member");
		} catch (FileNotFoundException e) {
			//프로젝트 누르고 새로고침
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

	}

}
