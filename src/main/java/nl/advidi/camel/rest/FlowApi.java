package nl.advidi.camel.rest;

import javax.validation.Valid;
import nl.advidi.rest.client.StatsClient;

public class FlowApi {
	public static StatsClient statsClient = new StatsClient();

	public Object getAverageClosePriceOverPeriod(@Valid String body) {
		return statsClient.getAverageClosePriceOverPeriod(body);
	}

	public Object getClosePriceAt(@Valid String body) {
		return statsClient.getClosePriceAt(body);
	}
}
