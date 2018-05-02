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
public class AvgClosePriceData {
	
	@JsonProperty("averageClosePrice")
	private @Valid String averageClosePrice = null;

	@JsonProperty("startDate")
	private @Valid String startDate = null;

	@JsonProperty("endDate")
	private @Valid String endDate = null;
	
	public AvgClosePriceData(String avgPrice, String startDate, String endDate) {
		this.averageClosePrice = avgPrice;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	@ApiModelProperty(value = "")
	public String getAverageClosePrice() {
		return averageClosePrice;
	}

	public void setAverageClosePrice(String averagePrice) {
		this.averageClosePrice = averagePrice;
	}

	@ApiModelProperty(value = "")
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@ApiModelProperty(value = "")
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
