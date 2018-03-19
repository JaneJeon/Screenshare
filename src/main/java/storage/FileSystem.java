package storage;

import resource.LoginToken;
import resource.Settings;
import util.Path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// a fallback provider in case there is no internet connection
public class FileSystem implements Provider {
	private String directory;
	
	@Override
	public void configure(Settings settings) {
		directory = Path.absolute(settings.backup_directory);
	}
	
	@Override
	public void login(LoginToken token) {
		// do nothing
	}
	
	@Override
	public String upload(String filepath, String key) {
		try {
			// move file
			Files.move(Paths.get(filepath), Paths.get(directory, Path.filename(filepath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// since we move things to the filesystem, there is no "link" to speak of
		return null;
	}
	
	@Override
	public boolean isAvailable() {
		return true;
	}
	
	@Override
	public boolean isReady() {
		return directory != null;
	}
}