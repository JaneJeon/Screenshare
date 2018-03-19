package storage;

import resource.LoginToken;
import resource.Settings;

public class ProviderFactory {
	public static Provider get(Settings settings, LoginToken token) {
		// TODO: implement it using ServiceLoaders?
		Provider provider;
		switch (settings.provider) {
			case "": provider = new FileSystem();
				break;
			case "S3": provider = new S3();
				break;
			default: throw new RuntimeException("Invalid 'provider' in settings file!");
		}
		provider.configure(settings);
		provider.login(token);
		return provider;
	}
}