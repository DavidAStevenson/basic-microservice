package com.stevenson.basicmicroservice;

public class CharacterEmitService {
	
	private NatsPublisher publisher;
	private String subject;
	private char inputLetter;
	
	private boolean isLastLetter(char letter) {
		if (letter == 'Z') {
			return true;
		} else {
			return false;
		}
	}
	
	private char firstCharacter() {
		return 'A';
	}

	public char OnCharEvent(char input) {
		
		boolean pubFlag = false;
		System.out.println("OnCharEvent:" + input + ", " + inputLetter);
		
		if (input == inputLetter) {
			pubFlag = true;
		}
		System.out.println("OnCharEvent:" + pubFlag);
		
		System.out.println("OnCharEvent:" + Character.toString(input));
		if (isLastLetter(input)) {
			String output = Character.toString(firstCharacter());
			if (pubFlag) {
				System.out.println("OnCharEvent: publish: " + subject + "," + output);
				publisher.publish(subject + output, output);
			}
			return firstCharacter();
		} else {
			input+=1;
			String output = Character.toString(input);
			System.out.println("OnCharEvent: pre-publish: " + subject + "," + output);
			if (pubFlag) {
				System.out.println("OnCharEvent: publish: " + subject + "," + output);
				publisher.publish(subject + output, output);				
			}
			return input;
		}
	}

	public void setPublisher(NatsPublisher pubby, String subby) {
		publisher = pubby;
		subject = subby;
	}

	public void setInputCharacter(String string) {
		inputLetter = string.toCharArray()[0];
	}

}
