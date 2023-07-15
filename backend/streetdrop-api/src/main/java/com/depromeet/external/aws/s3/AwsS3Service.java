package com.depromeet.external.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

	private final AmazonS3 amazonS3;

	public static final String DIR_NAME = "USER_PROFILE";

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	public String getS3(String bucket, String fileName) {
		return amazonS3.getUrl(bucket, fileName).toString();
	}

	public String getS3FilePaths() {
		return bucket;
	}

	public String getFileName() {
		return DIR_NAME;
	}
}
