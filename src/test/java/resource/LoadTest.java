package resource;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoadTest {
	@Test
	void string() {
		assertEquals("{ Hello, \nworld! ", Load.string("testfile"));
	}
	
	@Test
	void token() {
		LoginToken token = Load.token("S3-test.json");
		assertNotNull(token);
		assertEquals(token.id, "id");
		assertEquals(token.password, "password");
		
		assertNull(Load.token("settings-test.json"));
		assertNull(Load.token("This file clearly doesn't exist"));
	}
	
	@Test
	void settings() {
		Settings settings = Load.settings("settings-test.json");
		assertNotNull(settings);
		assertEquals(settings.provider, "S3");
		assertEquals(settings.backup_directory, "backup_directory");
		
		assertNull(Load.settings("S3-test.json"));
		assertNull(Load.settings("This file clearly doesn't exist"));
	}
}