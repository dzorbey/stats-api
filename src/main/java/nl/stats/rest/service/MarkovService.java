package nl.stats.rest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import nl.stats.camel.model.ClosePriceData;
import nl.stats.camel.model.ClosePriceResponse;
import nl.stats.camel.model.PriceRequest;
import nl.stats.camel.utilities.UtilityStatic;

public class MarkovService {
	public static TreeMap<Date, Double> closePrices = CSVReaderStatic
			.loadData("C:\\Users\\Delikan\\Downloads\\GOLD_daily.csv");

	int k, m;
	int flag, flag2;
	Double result_temp;
	List<Double> firstVector = new ArrayList<>();
	List<List<Double>> doubleVector = new ArrayList<>();;
	List<List<Double>> tmpVector = new ArrayList<>();;

	public MarkovService(int size) {
		this.k = 0;
		this.m = 0;
		this.flag = 0;
		this.flag2 = 0;
		this.result_temp = 0.0;
		this.firstVector = new ArrayList<>();
		this.doubleVector = matrixInitilize(this.doubleVector, size);
		this.tmpVector = matrixInitilize(this.tmpVector, size);

	}

	public List<List<Double>> matrixInitilize(List<List<Double>> matrix, int size) {
		for (int i = 0; i < size; i++) {
			matrix.add(new ArrayList<Double>());
			for (int j = 0; j < size; j++) {
				matrix.get(i).add(0.0);
			}
		}
		return matrix;
	}

	/*public Object getAverageClosePriceOverPeriod(AvgPriceRequest request) {

		try {
			Date startDate = (Date) UtilityStatic.formatter.parse(request.getStartDate());
			Date endDate = (Date) UtilityStatic.formatter.parse(request.getEndDate());
			Iterator<Entry<Date, Double>> it = closePrices.subMap(startDate, endDate).entrySet().iterator();

			int dataSize = 0;
			BigDecimal accumulatedPrice = new BigDecimal(0);
			while (it.hasNext()) {
				Map.Entry<Date, BigDecimal> iterateOver = (Map.Entry<Date, BigDecimal>) it.next();
				accumulatedPrice = accumulatedPrice.add(iterateOver.getValue());
				dataSize++;
			}
			accumulatedPrice = accumulatedPrice.divide(new BigDecimal(dataSize), 4, RoundingMode.CEILING);

			AvgClosePriceResponse response = new AvgClosePriceResponse();
			response.setMessage("Average price over the period");
			response.setSuccess(true);
			response.setData(
					new AvgClosePriceData(accumulatedPrice.toString(), request.getStartDate(), request.getEndDate()));
			return response;
		} catch (Exception e) {
			return UtilityStatic.error(e.getMessage());
		}
	}*/

	public Object getClosePriceAt(PriceRequest request) {

		try {
			ClosePriceResponse response = new ClosePriceResponse();
			response.setData(new ClosePriceData(
					closePrices.get((Date) UtilityStatic.formatter.parse(request.getDate())).toString(),
					request.getDate()));
			response.setMessage("Close price at the given date");
			response.setSuccess(true);
			return response;
		} catch (Exception e) {
			return UtilityStatic.error(e.getMessage());
		}
	}

	public void checkSize() {
		System.out.println("closePrices.size() :" + closePrices.size());
	}

	
	
	
	
	
	
	
	
	public void conditions() {
		
		Iterator<Entry<Date, Double>> it = closePrices.entrySet().iterator();

		
		
		Double previous = 0.0;
		int incWhileInc = 0;
		int incWhileDec = 0;
		int decWhileInc = 0;
		int decWhileDec = 0;
		int noChangeWhileDec = 0;
		int noChangeWhileInc = 0;
		int noChangeWhileNoChange = 0;
		int incWhileNoChange = 0;
        int decWhileNoChange = 0;
		
		Double lowerThanPrevious = 0.0; 
		
		while (it.hasNext()) {
			Map.Entry<Date, Double> iterateOver = (Map.Entry<Date, Double>) it.next();
			Double checkCurrent = iterateOver.getValue();
			
			if(closePrices.lowerKey(iterateOver.getKey()) != null) {
				Date lowerKey = closePrices.lowerKey(iterateOver.getKey());
				previous = closePrices.get(lowerKey);
				
				if(closePrices.lowerKey(lowerKey) != null) {
					lowerThanPrevious = closePrices.get(closePrices.lowerKey(lowerKey));		
				}
			}
			
			if((checkCurrent > previous) && (previous > lowerThanPrevious)) {
			     incWhileInc++;	
			}
			if((checkCurrent > previous) && (previous < lowerThanPrevious)) {
			     incWhileDec++;	
			}
			if((checkCurrent < previous) && (previous > lowerThanPrevious)) {
			     decWhileInc++;	
			}
			if((checkCurrent < previous) && (previous < lowerThanPrevious)) {
			     decWhileDec++;	
			}
	
			if((checkCurrent > previous) && (previous.equals(lowerThanPrevious))) {
			     incWhileNoChange++;	
			}
			if((checkCurrent < previous) && (previous.equals(lowerThanPrevious))) {
			     decWhileNoChange++;	
			}
			
			if((checkCurrent.equals(previous)) && (previous < lowerThanPrevious)) {
			     noChangeWhileDec++;	
			}			
			if((checkCurrent.equals(previous)) && (previous > lowerThanPrevious)) {
			     noChangeWhileInc++;	
			}
			if((checkCurrent.equals(previous)) && (previous.equals(lowerThanPrevious))) {
			     noChangeWhileNoChange++;	
			}
			
		}
		
		System.out.println("incWhileInc : " + incWhileInc);
		System.out.println("incWhileDec : " + incWhileDec);
		System.out.println("decWhileInc : " + decWhileInc);
		System.out.println("decWhileDec : " + decWhileDec);
		System.out.println("noChangeWhileDec : " + noChangeWhileDec);
		System.out.println("noChangeWhileInc : " + noChangeWhileInc);
		System.out.println("noChangeWhileNoChange : " + noChangeWhileNoChange);
		System.out.println("incWhileNoChange : " + incWhileNoChange);
		System.out.println("decWhileNoChange : " + decWhileNoChange);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public void calculate(List<List<Double>> matrix, int size, int future) {

		if (flag2 == 0) {
			for (int i = 0; i < size; i++) {
				for (int y = 0; y < size; y++) {
					doubleVector.get(i).set(y, matrix.get(i).get(y));
				}
			}
			flag2 = 1;
		}

		for (int i = 0; i < size; i++) {
			for (int y = 0; y < size; y++) {
				if (flag == 0) {
					k = i;
					m = y;
					flag = 1;
				}
				for (int z = 0; z < size; z++) {
					result_temp = result_temp + (doubleVector.get(k).get(z) * doubleVector.get(z).get(m));
				}
				tmpVector.get(i).set(y, result_temp);
				result_temp = 0.0;
				flag = 0;

			}
		}

		for (int i = 0; i < size; i++) {
			for (int y = 0; y < size; y++) {
				doubleVector.get(i).set(y, tmpVector.get(i).get(y));
			}
		}

		
		for (int i = 0; i < size; i++) {
			for (int y = 0; y < size; y++) {
				System.out.print("tmpVector["+i+"]["+y+"] = " + tmpVector.get(i).get(y)+ ", ");
			}
			System.out.println("");
		}
		
		System.out.println("");
		System.out.println("");
		
		if (future == 0) {
			return;
		}
		calculate(matrix, size, future - 1);

	}

}