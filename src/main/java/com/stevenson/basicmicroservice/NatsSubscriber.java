package com.stevenson.basicmicroservice;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;

public class NatsSubscriber {

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
	
	public void consume(String subject, int msgCount, CharacterEmitService ces) {

		if (!connected) {
			System.out.println("Cannot consume - connection has not be made");
			return;
		}
		
		CountDownLatch latch = new CountDownLatch(msgCount);
		if (0 == msgCount) {
			System.out.println("Running indefinitely...");
		}

		Dispatcher d = nc.createDispatcher((msg) -> {
		    String dataString = new String(msg.getData(), StandardCharsets.UTF_8);
			System.out.printf("Received message \"%s\" on subject \"%s\"\n", 
		    		dataString, msg.getSubject());
		    char[] chars = dataString.toCharArray();
		    System.out.printf("Service returns \"%s\"\n", ces.OnCharEvent(chars[0]));
		    latch.countDown();
		});
		d.subscribe(subject);
		
		try {
			System.out.printf("Running for \"%d\" messages...\n", msgCount);
			latch.await();
			nc.closeDispatcher(d);
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
