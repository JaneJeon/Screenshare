package resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class Load {
	// reads in entire file and loads it into a String
	public static String string(String filename) {
		return new Scanner(Load.class.getClassLoader().getResourceAsStream(filename)).useDelimiter("\\Z").next();
	}
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static LoginToken token(String filename) {
		try {
			return mapper.readValue(Load.string(filename), LoginToken.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Settings settings(String filename) {
		try {
			return mapper.readValue(Load.string(filename), Settings.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}