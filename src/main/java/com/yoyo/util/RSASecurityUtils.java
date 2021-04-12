package com.yoyo.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

//import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

/**
 * 非对称加密工具类
 * @author yangjq
 * 2019-04-16
 */
@Component
public class RSASecurityUtils {
    /** 指定加密算法为RSA */
    private static final String ALGORITHM = "RSA";
    /** 密钥长度，注：只加密关键信息即可（过长字符串RSA加解密性能较差） */
    private static final int KEYSIZE = 1024;//1024、2048、3072、4096...长度无限制，越长性能越差安全性越高

    /**
     * 生成密钥对
     * @throws Exception
     */
    public static void generateKeyPair() throws Exception {
        // /**可信任的随机数源 */
        // SecureRandom secureRandom = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        // keyPairGenerator.initialize(KEYSIZE, secureRandom);
        keyPairGenerator.initialize(KEYSIZE);//默认随机数源
        /** 生成密匙对 */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        /** 得到公钥 */
        Key publicKey = keyPair.getPublic();
        /** 得到私钥 */
        Key privateKey = keyPair.getPrivate();
        try {
            /** 密钥转字符串 */
            byte[] privateData=privateKey.getEncoded();
            String privateStr=Base64.encodeBase64String(privateData);
            byte[] publicData=publicKey.getEncoded();
            String publicStr=Base64.encodeBase64String(publicData);
            System.out.println("私钥："+privateStr);
            System.out.println("公钥："+publicStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公钥加密方法
     * @param source 源数据
     * @return
     * @throws Exception
     */
    public static String encrypt(String publicKey,String source) throws Exception {
        Key key=stringToPublicKey(publicKey);
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        return Base64.encodeBase64String(b1);
    }

    /**
     * 私钥加密方法
     * @param source 源数据
     * @return
     * @throws Exception
     */
    public static String encryptPriKey(String privateKey,String source) throws Exception {
        Key key=stringToPrivateKey(privateKey);
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        return Base64.encodeBase64String(b1);
    }

    /**
     * 私钥解密算法
     * @param cryptograph 密文
     * @return
     * @throws Exception
     */
    public static String decrypt(String privateKey,String cryptograph) throws Exception {
        Key key=stringToPrivateKey(privateKey);
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] b1 = Base64.decodeBase64(cryptograph);
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    /**
     * 公钥解密算法
     * @param cryptograph 密文
     * @return
     * @throws Exception
     */
    public static String decryptPubKey(String publicKey,String cryptograph) throws Exception {
        Key key=stringToPublicKey(publicKey);
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] b1 = Base64.decodeBase64(cryptograph);
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    /**
     * 密钥串转privateKey
     * @param keyStr
     * @return
     * @throws Exception
     */
    public static Key stringToPrivateKey(String keyStr) throws Exception {
        byte[] keyBytes=Base64.decodeBase64(keyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 密钥串转publicKey
     * @param keyStr
     * @return
     * @throws Exception
     */
    public static PublicKey stringToPublicKey(String keyStr) throws Exception {
        byte[] keyBytes=Base64.decodeBase64(keyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static void main(String[] args) {
        try {
            //RSASecurityUtils.generateKeyPair();
            String source = "刘友才";
            String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCO6ehZyHd3vd4BCOJIMKcmfJFRHEPblJXEsCvWfkVDi1E6lwELPbmuEPpgZ3Vh/lkV3zeufg36DZXjvwEBQDQy0Ap/DyiXfEf2EsCzPxWvgX1kUebRLRFS49maeibxQ+OGIL5TmD98O9xyKQd8X8Owe4tamB1r515sWLX/CAyvzwIDAQAB";
            String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAI7p6FnId3e93gEI4kgwpyZ8kVEcQ9uUlcSwK9Z+RUOLUTqXAQs9ua4Q+mBndWH+WRXfN65+DfoNleO/AQFANDLQCn8PKJd8R/YSwLM/Fa+BfWRR5tEtEVLj2Zp6JvFD44YgvlOYP3w73HIpB3xfw7B7i1qYHWvnXmxYtf8IDK/PAgMBAAECgYBIiZDVgVqh/EziWZAA9DDi5/caJC6NFS2vV3voss6VKfFgu6RcAEhugU+T/u8lfePZpiKs/m45rQuXPStVZzEWle0BuO6kQlnsVqsUWOFcRdZUXuoO4Rh+70u39p/kuCpELGLSwLnKI+c9N3SIy2NOWzi6+uX2lqygt1RkSZvEAQJBANaYPTgjsX4aVx1HNlVoLrsrW9xMUi0ClhlnK5X2ZuoqIzDDaqVZgTBYKNpAsDpon1GlsvH/T4uyXlP0v2otsu8CQQCqfQjIPXCDbOxIqFFqT6I2WrJ4UxgYgUD6omaABPxvhu3IpVZEMd875gw+BD2ODSuGr+t4DrUSoKtbKDlaJVEhAkEAuWWVnmbX9YZ0NMtNWcREe1geaNlXNaPCYfgMY+cZSr1U4dAy1t/ZCRdhVA4HMq8o1bU+QGy/IIXdkNMmfGk26QJBAIPM38I1xUrFJTHSdgZzA9tzaG+fBYzEN+DLNeSYdjMeI6uvLi7QQ10CLsqu6otr4Q9h5u7Mp+17qp1xTk1DdiECQQC3asuqrFSUVByxsMaG6QcG+yQm5GdCRe9vjOsXvBERHxRg6LPi0H3A+i6FSrFUmgKOaxzgRqEW/Q7yvPSjZmNp";
            String cryptograph = RSASecurityUtils.encrypt(publicKey,source);
            System.out.println("公钥加密："+cryptograph);
            System.out.println("私钥解密："+RSASecurityUtils.decrypt(privateKey ,cryptograph));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

