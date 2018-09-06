import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.stevenson.basicmicroservice.CharacterEmitService;

class CharacterEmitServiceTest {

	private CharacterEmitService ces;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		ces = new CharacterEmitService();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void test1emits2() {
		char input = '1';
		ces.setInputCharacter(input);
		char output = ces.OnCharEvent(input);
		assertEquals('2', output);
	}
	
	@Test
	void testAemitsB() {
		char input = 'A';
		ces.setInputCharacter(input);
		char output = ces.OnCharEvent(input);
		assertEquals('B', output);
	}

	@Test
	void testYemitsZ() {
		char input = 'Y';
		ces.setInputCharacter(input);
		char output = ces.OnCharEvent(input);
		assertTrue('Z' == output);	
	}

	@Test
	void testZemitsA() {
		char input = 'Z';
		ces.setInputCharacter(input);
		char output = ces.OnCharEvent(input);
		assertEquals('A', output);	
	}
	
	@Test
	void testAProcessorIgnoresB() {
		ces.setInputCharacter('A');
		char output = ces.OnCharEvent('B');
		assertEquals('B', output); 
	}
	
	@Test
	void testBProcessorReturnsC() {
		ces.setInputCharacter('B');
		char output = ces.OnCharEvent('B');
		assertEquals('C', output); 
	}
}
