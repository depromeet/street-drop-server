package com.depromeet.external.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class AwsS3Service {
	private final AmazonS3 amazonS3;
	private final AmazonS3Client amazonS3Client;
	private static final String FILE_EXTENSION_SEPARATOR = ".";

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	public String getS3(String bucket, String fileName) {
		return amazonS3.getUrl(bucket, fileName).toString();
	}

	public String getS3FilePaths() {
		return bucket;
	}

	public String updateFileToS3(MultipartFile multipartFile, String category) {
		String fileName = buildFileName(category, multipartFile.getOriginalFilename());

		var objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(multipartFile.getContentType());

		try (InputStream inputStream = multipartFile.getInputStream()) {
			amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
					.withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (IOException e) {
			throw new BusinessException(ErrorCode.INVALID_FILE_UPLOAD);
		}
		return amazonS3Client.getUrl(bucket, fileName).toString();
	}

	private String buildFileName(String category, String originalFileName) {
		var fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
		var fileExtension = originalFileName.substring(fileExtensionIndex);
		var fileName = originalFileName.substring(0, fileExtensionIndex);
		var now = String.valueOf(System.currentTimeMillis());

		return category + "/" + fileName + "-" + now + fileExtension;
	}
}
