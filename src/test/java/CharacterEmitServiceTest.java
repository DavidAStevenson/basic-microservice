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
		char output = ces.OnCharEvent(input);
		assertEquals('2', output);
	}
	
	@Test
	void testAemitsB() {
		char input = 'A';
		char output = ces.OnCharEvent(input);
		assertEquals('B', output);
	}

	@Test
	void testYemitsZ() {
		char input = 'Y';
		char output = ces.OnCharEvent(input);
		assertTrue('Z' == output);	
	}

	@Test
	void testZemitsA() {
		char input = 'Z';
		char output = ces.OnCharEvent(input);
		assertEquals('A', output);	
	}
}
