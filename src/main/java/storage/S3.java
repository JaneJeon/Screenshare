package storage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import resource.LoginToken;
import resource.Settings;

import java.io.File;

public class S3 implements Provider {
	private String region;
	private String bucket;
	private AmazonS3 client;
	
	@Override
	public void configure(Settings settings) {
		region = settings.region;
		bucket = settings.bucket;
	}
	
	@Override
	public void login(LoginToken token) {
		client = AmazonS3ClientBuilder.standard()
			.withCredentials(
				new AWSStaticCredentialsProvider(
					new BasicAWSCredentials(token.id, token.password)
				)
			)
			.withRegion(region)
			.build();
	}
	
	@Override
	public String upload(String filepath, String key) {
		client.putObject(
			new PutObjectRequest(bucket, key, new File(filepath))
				.withCannedAcl(CannedAccessControlList.PublicRead)
		);
		return client.getUrl(bucket, key).toString();
	}
	
	@Override
	public boolean isAvailable() {
		return client.listBuckets().size() > 0;
	}
	
	@Override
	public boolean isReady() {
		return client != null;
	}
}