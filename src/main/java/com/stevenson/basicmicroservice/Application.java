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
		// inject business logic
		CharacterEmitService ces = new CharacterEmitService();

		// inject subscription mechanism
		NatsSubscriber middleware = new NatsSubscriber();
		middleware.connect("nats://192.168.99.100:4222");
		middleware.consume("alphabet.*", 10, ces);
		middleware.disconnect();

		// inject publish mechanism
	}

}
