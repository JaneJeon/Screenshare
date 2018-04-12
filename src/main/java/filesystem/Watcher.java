package filesystem;

import storage.FileSystem;
import storage.Provider;
import util.Clipboard;
import util.Screenshot;

import java.io.IOException;
import java.nio.file.*;

public class Watcher {
	private Provider provider, backupProvider;
	
	public Watcher(Provider provider, FileSystem backupProvider) {
		this.provider = provider;
		this.backupProvider = backupProvider;
	}
	
	// watch directory for any new files or changes in filename
	public void detectNewFiles(String directory) throws IOException, InterruptedException {
		var watchService = FileSystems.getDefault().newWatchService();
		Paths.get(directory).register(
			watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY
		);
		
		WatchKey key;
		while ((key = watchService.take()) != null) {
			key.pollEvents().forEach(event -> 
				handleNewFiles(Paths.get(directory, event.context().toString()).toString()));
			key.reset();
		}
	}
	
	private void handleNewFiles(String filepath) {
		if (!Screenshot.isValid(filepath)) return;
		try {
			if (provider.isAvailable())
				Clipboard.copy(provider.upload(filepath, Screenshot.getKey(filepath)));
			else
				backupProvider.upload(filepath, null);
		} catch (Exception e) {
			System.err.print("Error uploading "+filepath+": ");
			e.printStackTrace();
		}
	}
}