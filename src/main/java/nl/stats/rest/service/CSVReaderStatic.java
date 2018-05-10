package nl.stats.rest.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import nl.stats.camel.utilities.UtilityStatic;

public class CSVReaderStatic {
	//public static String filePath = Utility.prop.getProperty("filePath");

    public static TreeMap<Date, Double> loadData(String filePath) {
    	HashMap<Date, Double> prices = new HashMap<>();
    	
        String csvFile = filePath;
        
        System.out.println(csvFile);
        
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] resource = line.split(cvsSplitBy);

                try {
                	prices.put((Date)UtilityStatic.formatter.parse(resource[0]), new Double((resource[1])));
                	
                	/*Date x = (Date)UtilityStatic.formatter.parse(resource[0]);
                	BigDecimal y = new BigDecimal((resource[1]));
                	
                	System.out.println(x + " "+  y);
                	*/
				} catch (ParseException e) {
					continue;
				}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TreeMap<Date, Double>(prices);
    }


}
