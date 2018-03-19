package storage;

import resource.LoginToken;
import resource.Settings;

public interface Provider {
	void configure(Settings settings);
	void login(LoginToken token);
	String upload(String filepath, String key); // returns link to uploaded file
	boolean isAvailable();
	boolean isReady();
}