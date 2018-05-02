package nl.advidi.camel.model;

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
public class AbstractResponse {

	@JsonProperty("success")
	private @Valid Boolean success = null;

	@JsonProperty("message")
	private @Valid String message = null;

	@ApiModelProperty(required = true, value = "")
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@ApiModelProperty(required = true, value = "")
	public String getMessage() {
		return message;
	}

	public Boolean getSuccess() {
		return success;
	}
}