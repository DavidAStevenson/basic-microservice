package com.stevenson.basicmicroservice;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import io.nats.client.Connection;
import io.nats.client.Nats;

public class NatsPublisher {

	private Connection nc;
	private boolean connected = false;

	public boolean connect(String server) {
		try {
			nc = Nats.connect(server);
			if (nc != null) {
				connected = true;
				System.out.println("Established a Nats connection...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return connected;
	}

	public void publish(String subject, String payload) {
		System.out.println("MessagePublisher publish called...");
		
		if (!connected) {
			System.out.println("Cannot publish - connection has not be made");
			return;
		}
		
		nc.publish(subject, payload.toString().getBytes(StandardCharsets.UTF_8));
		try {
			nc.flush(Duration.ZERO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void disconnect() {
		if (!connected) {
			return;
		}

		try {
			nc.close();
			connected = false;
			System.out.println("Closed the connection.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
