package util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class Screenshot {
	private static final List<String> IMAGE_EXTENSIONS = List.of("png", "jpg", "jpeg", "tiff", "tif");
	
	public static boolean isValid(String filename) {
		// check if the file starts with 'screenshot'
		return filename.replaceAll("\\P{L}+", "").toLowerCase().startsWith("screenshot") &&
			// check if the file has proper extension
			IMAGE_EXTENSIONS.contains(filename.substring(filename.lastIndexOf('.')+1).toLowerCase());
	}
	
	public static String getKey(String filepath) {
		try {
			// appended with the folder name for easier browsing
			return "screenshare/" + URLEncoder.encode(Path.filename(filepath).trim().toLowerCase(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}