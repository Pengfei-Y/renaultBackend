package com.rebims.renault.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * @description：对称加密
 */
public class DesUtil {

    private static final String KEY = "ComeOnBaby";

    /**
     * 根据KEY生成DES
     */
    private static final DES DES = SecureUtil.des(SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue(), KEY.getBytes()).getEncoded());

    /**
     * 获取加密后信息
     *
     * @param plainText 明文
     * @return 加密后信息
     */
    public static String getEncryptData(String plainText) {
        return DES.encryptHex(plainText); // 加密为16进制
    }

    /**
     * 获取解密后信息
     *
     * @param cipherText 密文
     * @return 解密后信息
     */
    public static String getDecryptData(String cipherText) {
        return DES.decryptStr(cipherText);
    }

    /**
     * 生成密钥，并转为字符串，可以储存起来，解密时可直接使用
     *
     * @return 密钥
     */
    public static String getSecretKey() {
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded(); // 随机生成秘钥
        return Base64.encode(key);
    }
}
