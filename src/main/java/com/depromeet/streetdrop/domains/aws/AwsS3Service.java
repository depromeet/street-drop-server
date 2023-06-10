package com.depromeet.streetdrop.domains.aws;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.depromeet.streetdrop.domains.aws.dto.vo.S3ImageCategory;
import com.depromeet.streetdrop.global.error.dto.ErrorCode;
import com.depromeet.streetdrop.global.error.exception.common.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AwsS3Service {
	private final AmazonS3Client amazonS3Client;
	public static final String FILE_EXTENSION_SEPARATOR = ".";

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;

	public List<String> uploadFilesToS3(List<MultipartFile> multipartFiles, S3ImageCategory imageCategory) {
		List<String> urls = new ArrayList<>();

		for (MultipartFile multipartFile : multipartFiles) {
			String fileName = makeFileName(imageCategory, multipartFile.getOriginalFilename());
			var objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(multipartFile.getContentType());

			try (InputStream inputStream = multipartFile.getInputStream()) {
				amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
						.withCannedAcl(CannedAccessControlList.PublicRead));
			} catch (IOException e) {
				throw new BusinessException(ErrorCode.FILE_UPLOAD_INVALID);
			}
			urls.add(amazonS3Client.getUrl(bucketName, fileName).toString());
		}
		return urls;
	}

	private String makeFileName(S3ImageCategory imageCategory, String originalFilename) {
		var fileExtensionIndex = originalFilename.lastIndexOf(FILE_EXTENSION_SEPARATOR);
		var fileExtension = originalFilename.substring(fileExtensionIndex);
		var fileName = originalFilename.substring(0, fileExtensionIndex);
		var now = String.valueOf(System.currentTimeMillis());

		return imageCategory + "/" + fileName + "_" + now + fileExtension;
	}
}
