/**
 * 
 */
package com.stevenson.basicmicroservice;

/**
 * @author David
 *
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Run with args: (1) the nats topic, (2) the input character to process, e.g. 'A'");
			System.exit(1);
		}
		// "inject" business logic
		CharacterEmitService ces = new CharacterEmitService();

		NatsPublisher pubby = new NatsPublisher();
		if (!pubby.connect("nats://192.168.99.100:4222")) {
			System.out.println("Failed to open publisher connection, quitting...");
			System.exit(1);
		}
		// "inject" publish mechanism
		ces.setPublisher(pubby, "alphabet.");
		ces.setInputCharacter(args[1]);
		
		// "inject" subscription mechanism
		NatsSubscriber middleware = new NatsSubscriber();
		if (!middleware.connect("nats://192.168.99.100:4222")) {
			System.out.println("Failed to open subscriber connection, quitting...");
			connectionsDown(pubby, middleware);
			System.exit(1);
		}
	
		middleware.consume(args[0], 10, ces);
		
		connectionsDown(pubby, middleware);
	}
	
	private static void connectionsDown(NatsPublisher p, NatsSubscriber s) {
		if (p != null) {
			p.disconnect();
		}
		if (s != null) {
			s.disconnect();			
		}
	}

}
