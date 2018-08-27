package login;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {

	private String name = "null";
	private String loggedAs = "notLogged";

	public Response() {
	}

	public Response(String name, String loggedAs) {
		super();
		this.name = name;
		this.loggedAs = loggedAs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoggedAs() {
		return loggedAs;
	}

	public void setLoggedAs(String loggedAs) {
		this.loggedAs = loggedAs;
	}

	@Override
	public String toString() {
		return "Response [name=" + name + ", loggedAs=" + loggedAs + "]";
	}

}
