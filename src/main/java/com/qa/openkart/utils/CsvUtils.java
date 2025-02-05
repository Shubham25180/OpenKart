package com.qa.openkart.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CsvUtils {
	
	//comma separated values
	private static final String CSV_PATH = ".//src//test//resources//testdata//";

	public static Object[][] csvData (String fileName) {
		
		try {
			CSVReader reader = new CSVReader (new FileReader(CSV_PATH+fileName+".csv"));
			
			List<String[]> rows  = reader.readAll();
			
			reader.close();
			
			Object[][] data = new Object[rows.size()][];
			
			for(int i = 0 ; i< rows.size(); i++) {
				data[i] = rows.get(i);
			}
			
			return data;
			
			
			
		} catch (IOException | CsvException e) {
			e.printStackTrace();
			return null;
			}
		
		
		
	}
}
