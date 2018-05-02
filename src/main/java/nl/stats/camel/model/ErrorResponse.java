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
public class ErrorResponse extends AbstractResponse {

	@JsonProperty("data")
	private @Valid String data = null;

	@ApiModelProperty(required = true, value = "")
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}