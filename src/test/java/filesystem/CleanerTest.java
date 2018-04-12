package filesystem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
class CleanerTest {
	private static final String testDirectory = "~/.screenshare/test", badDirectory = "/bad/directory";
	private static final List<String> fakeFiles = List.of("file1.png", "file2.jpeg");
	
	@Test
	void initialize() {
		Cleaner.initialize(testDirectory);
		assertTrue(Files.exists(Paths.get(testDirectory))); // fails
	}
	
	@Test
	void sweep() throws IOException {
		assertThrows(RuntimeException.class, () -> Cleaner.sweep("/a/file.png"), "Input is not a directory!");
		assertThrows(IOException.class, () -> Cleaner.sweep("/bad/directory2"));
		
		// put some files
		fakeFiles.forEach(f -> new File(testDirectory, f));
		new File(testDirectory, badDirectory);
		var result = Cleaner.sweep(testDirectory);
		result.forEach(file -> assertTrue(Files.isRegularFile(Paths.get(file))));
		
		// TODO: assert that the files have the same name? - do I really need to do this?
	}
	
	@SuppressWarnings({"ResultOfMethodCallIgnored", "unused"})
	@BeforeAll
	@AfterAll
	static void cleanup() {
		// Java can't delete folders with files in it
		if (Files.exists(Paths.get(testDirectory))) {
			final var folder = new File(testDirectory);
			// no need for recursion
			Arrays.stream(Objects.requireNonNull(folder.list()))
				.forEach(file -> new File(folder, file).delete());
			folder.delete();
		}
	}
}