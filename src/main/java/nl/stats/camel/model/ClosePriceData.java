package nl.stats.camel.model;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
@Produces("application/json")
public class ClosePriceData {

	@JsonProperty("closePrice")
	private @Valid String closePrice = null;

	@JsonProperty("date")
	private @Valid String date = null;

	public ClosePriceData(String closePrice, String date) {
		this.closePrice = closePrice;
		this.date = date;
	}

	@ApiModelProperty(value = "")
	public String getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(String closePrice) {
		this.closePrice = closePrice;
	}

	@ApiModelProperty(value = "")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
