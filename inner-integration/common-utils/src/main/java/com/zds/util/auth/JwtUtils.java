package com.zds.util.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * JwtUtils
 *
 * @author zhongdongsheng
 * @since 2022/4/13
 */
public class JwtUtils {

    private static final String SK = "1qaz!QAZ";

    public static String getToken(Map<String, Object> map) {
        return JWT.create().withPayload(map).sign(Algorithm.HMAC256(SK));
    }

    public static String verify(String token) {
        JWTVerifier build = JWT.require(Algorithm.HMAC256(SK))
            .build();
        DecodedJWT verify = build.verify(token);
        return verify.getPayload();
    }

//    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap();
//        map.put("id","1");
//        map.put("name", "zhongdongsheng");
//        System.out.println(getToken(map));
//    }

}
