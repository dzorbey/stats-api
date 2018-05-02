package nl.advidi.camel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
@Consumes("application/json")
@Produces("application/json")
public class PriceRequest {

	@JsonProperty("date")
	private @Valid String date = null;

	@ApiModelProperty(value = "")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}