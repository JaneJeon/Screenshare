package util;

import java.nio.file.Paths;

public class Path {
	public static String filename(String filepath) {
		return Paths.get(filepath).getFileName().toString();
	}
	
	public static String absolute(String relativePath) {
		if (relativePath.charAt(0) != '~') return relativePath;
		return Paths.get(System.getProperty("user.home"), relativePath.substring(1)).toString();
	}
}