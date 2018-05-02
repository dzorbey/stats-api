package nl.stats.rest.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import nl.stats.camel.model.AvgClosePriceData;
import nl.stats.camel.model.AvgClosePriceResponse;
import nl.stats.camel.model.AvgPriceRequest;
import nl.stats.camel.model.ClosePriceData;
import nl.stats.camel.model.ClosePriceResponse;
import nl.stats.camel.model.PriceRequest;
import nl.stats.camel.utilities.Utility;

public class StatsService {
	public static TreeMap<Date, BigDecimal> closePrices = CSVReader.loadData();

	public Object getAverageClosePriceOverPeriod(AvgPriceRequest request) {

		try {
			Date startDate = (Date) Utility.formatter.parse(request.getStartDate());
			Date endDate = (Date) Utility.formatter.parse(request.getEndDate());
			Iterator<Entry<Date, BigDecimal>> it = closePrices.subMap(startDate, endDate).entrySet().iterator();

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
			return Utility.error(e.getMessage());
		}
	}

	public Object getClosePriceAt(PriceRequest request) {

		try {
			ClosePriceResponse response = new ClosePriceResponse();
			response.setData(
					new ClosePriceData(closePrices.get((Date) Utility.formatter.parse(request.getDate())).toString(), request.getDate()));
			response.setMessage("Close price at the given date");
			response.setSuccess(true);
			return response;
		} catch (Exception e) {
			return Utility.error(e.getMessage());
		}
	}
}