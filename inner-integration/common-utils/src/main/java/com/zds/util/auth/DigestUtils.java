package com.zds.util.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * DigestUtils
 *
 * @author zhongdongsheng
 * @since 2022/4/15
 */
public class DigestUtils {

    private static final Logger DEBUG_LOGGER = LoggerFactory.getLogger("debugLogger");

    private static final String sha256 = "AHGKLNVMOJCTUIPZ";

    public static String sha256(String text) {
        String digestResult = "";
        MessageDigest instance;
        try {
            instance = MessageDigest.getInstance("SHA-256");
            byte[] digest = instance.digest((text + sha256).getBytes(StandardCharsets.UTF_8));
            digestResult = Hex.encodeHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            DEBUG_LOGGER.error("Failed to invoke sha256, exception is: {}", e.getMessage());
        }

        return digestResult;
    }

    public static void main(String[] args) {
        System.out.println(sha256("zhongdongsheng"));
    }
}
