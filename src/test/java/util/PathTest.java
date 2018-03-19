package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PathTest {
	@Test
	void filename() {
		assertEquals("settings-test.json", Path.filename("src/test/resources/settings-test.json"));
	}
	
	@Test
	void getHomePath() {
		assertEquals("/Desktop", Path.absolute("/Desktop"));
		assertEquals("/Users/SungilAhn/Desktop", Path.absolute("~/Desktop"));
	}
}