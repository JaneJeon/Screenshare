package run;

import org.slf4j.LoggerFactory;

public class Main {
	public static void main(String[] args) {
		final var logger = LoggerFactory.getLogger("org.dartanon.screenshare");
		// must try-catch when uploading
		// when the other providers are not available, move it to the backup directory,
		// then when the other providers are available again, upload it using those
		// TODO: logging for every level?
	}
}