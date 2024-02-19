package com.depromeet.util;

import io.micrometer.common.util.StringUtils;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HmacUtil {
    private static final String SIGNATURE_ALGORITHM = "HmacSHA256";

    @Value("${hmac.secret-key}")
    private String key;

    public boolean verify(String signature, String message) {
        if (StringUtils.isBlank(signature) || StringUtils.isBlank(message)) {
            return false;
        }
        String madeSignature = makeSignature(message);
        return signature.trim().equals(madeSignature);
    }

    public String makeSignature(String message) {
        final SecretKey secretKey = new SecretKeySpec(key.getBytes(), SIGNATURE_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(SIGNATURE_ALGORITHM);  // MAC 알고리즘 구현하는 MAC 객체
            mac.init(secretKey);
            return Base64.encodeBase64String(mac.doFinal(message.getBytes()));
        } catch (Exception ignored) {
            return "";
        }
    }
}
