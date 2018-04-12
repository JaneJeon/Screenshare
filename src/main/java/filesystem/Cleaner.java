package filesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

// performs initial filesystem sweep
public class Cleaner {
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void initialize(String directory) {
		new File(directory).mkdirs();
	}
	
	public static List<String> sweep(String directory) throws IOException {
		if (!Files.isDirectory(new File(directory).toPath()))
			throw new RuntimeException("Input is not a directory!");
		
		try (var paths = Files.walk(Paths.get(directory))) {
			return paths.filter(Files::isRegularFile)
				.map(Path::toString)
				.collect(Collectors.toList());
		}
	}
}