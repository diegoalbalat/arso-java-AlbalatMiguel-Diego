package controller;

import javax.xml.ws.Endpoint;

import clientesoap.DbplWebServiceSOAPImpl;

public class PublishService {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/dblpservice", new DbplWebServiceSOAPImpl());
	}
}
