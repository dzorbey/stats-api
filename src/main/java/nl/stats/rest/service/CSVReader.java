package nl.stats.rest.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import nl.stats.camel.utilities.Utility;

public class CSVReader {
	public static String filePath = Utility.prop.getProperty("filePath");
	
    public static TreeMap<Date, BigDecimal> loadData() {
    	HashMap<Date, BigDecimal> prices = new HashMap<>();
    	
        String csvFile = filePath;
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] resource = line.split(cvsSplitBy);

                try {
                	prices.put((Date)Utility.formatter.parse(resource[0]), new BigDecimal((resource[4])));
				} catch (ParseException e) {
					continue;
				}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TreeMap<Date, BigDecimal>(prices);
    }
}
