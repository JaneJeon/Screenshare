package run;

import storage.S3;
import resource.Load;

public class Main {
	public static void main(String[] args) {
		S3 s3 = new S3();
		s3.configure(Load.settings("settings.json"));
		s3.login(Load.token("S3.json"));
		// must try-catch when uploading
		// TODO: setup logger
		// when the other providers are not available, move it to the backup directory,
		// then when the other providers are available again, upload it using those
	}
}