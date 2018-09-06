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
		
		if (input == inputLetter) {
			pubFlag = true;
		} else {
			return input;
		}

		char retval = 0;
		if (isLastLetter(input)) {
			retval = firstCharacter();
		} else {
			input+=1;
			retval = input;
		}

		if (pubFlag) {
			if (publisher != null) {
				String output = Character.toString(retval);
				publisher.publish(subject + output, output);
			}
		}

		return retval;
	}

	public void setPublisher(NatsPublisher pubby, String subby) {
		publisher = pubby;
		subject = subby;
	}

	public void setInputCharacter(String string) {
		inputLetter = string.toCharArray()[0];
	}

	public void setInputCharacter(char character) {
		inputLetter = character;
	}

}
