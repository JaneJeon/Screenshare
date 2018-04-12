package storage;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.contentSources.B2ContentTypes;
import com.backblaze.b2.client.contentSources.B2FileContentSource;
import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.client.structures.B2Bucket;
import com.backblaze.b2.client.structures.B2UploadFileRequest;
import com.backblaze.b2.client.webApiHttpClient.B2StorageHttpClientBuilder;
import resource.LoginToken;
import resource.Settings;

import java.io.File;

// TODO: https://github.com/Backblaze/b2-sdk-java
public class B2 implements Provider {
	private B2StorageClient client;
	private B2Bucket bucket;
	
	@Override
	public void configure(Settings settings) {
		try {
			bucket = client.getBucketOrNullByName("screenshare-uploads");
		} catch (B2Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void login(LoginToken token) {
		client = B2StorageHttpClientBuilder.builder(token.id, token.password, "Screenshare-app").build();
	}
	
	@Override
	public String upload(String filepath, String key) {
		try {
			client.uploadSmallFile(
				B2UploadFileRequest.builder(
					"159dd5d25d0262496a27021f", filepath, B2ContentTypes.B2_AUTO, 
					B2FileContentSource.build(new File(filepath))
				).build()
			);
		} catch (B2Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean isAvailable() {
		return false;
	}
	
	@Override
	public boolean isReady() {
		return false;
	}
}