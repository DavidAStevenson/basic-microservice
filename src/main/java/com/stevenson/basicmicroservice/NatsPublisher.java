package com.stevenson.basicmicroservice;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import io.nats.client.Connection;
import io.nats.client.Nats;

public class NatsPublisher {

	private Connection nc;

	public void connect(String server) {
		try {
			nc = Nats.connect(server);
			if (nc != null) {
				System.out.println("Established a Nats connection...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public void publish(String subject, String payload) {
		System.out.println("MessagePublisher publish called...");
		nc.publish(subject, payload.toString().getBytes(StandardCharsets.UTF_8));
		try {
			nc.flush(Duration.ZERO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void disconnect() {
		try {
			nc.close();
			System.out.println("Closed the connection.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
