package com.stevenson.basicmicroservice;

public class CharacterEmitService {
	
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
		if (isLastLetter(input)) {
			return firstCharacter();
		} else {
			return input+=1;
		}
	}

}
