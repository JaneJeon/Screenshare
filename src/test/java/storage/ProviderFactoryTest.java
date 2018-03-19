package storage;

import org.junit.jupiter.api.Test;
import resource.Load;
import resource.LoginToken;
import resource.Settings;

import static org.junit.jupiter.api.Assertions.*;

class ProviderFactoryTest {
	// TODO: mock behaviours of Settings, LoginToken, S3 and FileSystem
	@Test
	void get() {
		Settings settings = Load.settings("settings.json");
		LoginToken token = Load.token("S3.json");
		assertNotNull(settings);
		assertNotNull(token);
		
		settings.provider = "";
		Provider provider1 = ProviderFactory.get(settings, token);
		assertTrue(provider1 instanceof FileSystem);
		assertTrue(provider1.isReady());
		
		settings.provider = "S3";
		Provider provider2 = ProviderFactory.get(settings, token);
		assertTrue(provider2 instanceof S3);
		assertTrue(provider2.isReady());
		
		settings.provider = "invalid";
		assertThrows(RuntimeException.class, () -> ProviderFactory.get(settings, token), 
			"Invalid 'provider' in settings file!");
	}
}