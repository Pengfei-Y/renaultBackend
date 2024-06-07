package com.rebims.renault.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

public class DesUtil {

    private static final String KEY = "ComeOnBaby";

    /**
     * generate DES according to Key
     */
    private static final DES DES = SecureUtil.des(SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue(), KEY.getBytes()).getEncoded());

    /**
     * Getting encrypted information
     *
     * @param plainText plain text
     * @return encrypted information
     */
    public static String getEncryptData(String plainText) {
        return DES.encryptHex(plainText);
    }

    /**
     * Get decrypted information
     *
     * @param cipherText ciphertext
     * @return decrypted information
     */
    public static String getDecryptData(String cipherText) {
        return DES.decryptStr(cipherText);
    }

    /**
     * generate srcret key, turn to string, can be saved
     *
     * @return secret key
     */
    public static String getSecretKey() {
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded(); // generate srcret key randomly
        return Base64.encode(key);
    }
}
