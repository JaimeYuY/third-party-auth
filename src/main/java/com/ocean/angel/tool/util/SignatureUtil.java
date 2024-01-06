package com.ocean.angel.tool.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

/**
 * OpenAPI签名工具类
 */
public class SignatureUtil {

    /**
     * 生成签名
     * @param appSecret
     * @param random
     * @return
     */
    public static String generateSignature(String appSecret, String random) {
        if(StrUtil.isEmpty(appSecret) || StrUtil.isEmpty(random)) {
            return null;
        }
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, appSecret.getBytes());
        return aes.encryptHex(random);
    }

    /**
     * 签名校验
     * @param appSecret
     * @param random
     * @param signature
     * @return
     */
    public static boolean checkSignature(String appSecret, String random, String signature) {
        if(StrUtil.isEmpty(appSecret) || StrUtil.isEmpty(random) || StrUtil.isEmpty(signature)) {
            return false;
        }
        String data = getRandom(appSecret, signature);
        if(random.equals(data)) {
            return true;
        }
        return false;
    }

    /**
     * 根据签名和AppSecret解析出随机字符
     * @param appSecret
     * @param signature
     * @return
     */
    public static String getRandom(String appSecret, String signature) {
        if(StrUtil.isEmpty(appSecret) || StrUtil.isEmpty(signature)) {
            return null;
        }
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, appSecret.getBytes());
        String random = aes.decryptStr(signature);
        return random;
    }
}
