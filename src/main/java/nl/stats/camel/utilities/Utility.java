package nl.stats.camel.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import com.fasterxml.jackson.databind.ObjectMapper;

import nl.stats.camel.model.ErrorResponse;

public class Utility {
	public static ObjectMapper objectMapper = new ObjectMapper();
	public static DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
	public static Properties prop = ConfigProperties.getProperties();
	
	public static Object error(String message) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(message);
		errorResponse.setSuccess(false);
		return errorResponse;
	}
}
