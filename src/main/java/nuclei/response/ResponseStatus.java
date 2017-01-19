package nuclei.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Karthikeyan
 */

@XmlRootElement
public class ResponseStatus {

	@XmlElement(name = "status")
	public int status = 0;

	@XmlElement(name = "message")
	public String msg = "";

	public ResponseStatus() {
		super();
	}

	public ResponseStatus(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
}
