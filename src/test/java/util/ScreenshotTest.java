package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScreenshotTest {
	@Test
	void isValid() {
		assertTrue(Screenshot.isValid("Screen Shot 2017-03-26 at 03.23.25.png"));
		assertTrue(Screenshot.isValid("Screen-Shot-2017-03-26 at 03.23.25.jpeg"));
		assertFalse(Screenshot.isValid("Screen Shot 2017-03-26 at 03.23.25.mkv"));
		assertFalse(Screenshot.isValid("https___banner.dartmouth.edu_banner_.pdf"));
		assertFalse(Screenshot.isValid("Itinerary/ Vancouver.jpg"));
	}
	
	@Test
	void getKey() {
		assertEquals("screenshare/screen+shot+2017-03-26+at+03.23.25.png", 
			Screenshot.getKey("Screen Shot 2017-03-26 at 03.23.25.png"));
	}
}