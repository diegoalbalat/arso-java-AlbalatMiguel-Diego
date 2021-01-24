package soap;

import javax.xml.ws.Endpoint;

public class PublishService {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/dblpservice", new DbplWebServiceSOAPImpl());
	}
}
