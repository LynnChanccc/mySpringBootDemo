package com.woniu.springboot.test.encryptTest;

import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * @author cl
 * @Date 2020/3/18 14:01
 * 加密算法
 * 对称加密：对称加密指通过一段密钥可以将原文进行加密得到密文，也可用使用同样的一段密钥将密文解密为原文。
 * （DES、IDEA、RC2、RC4、SKIPJACK、RC5、AES）
 * 非对称加密：非对称加密的密钥分为公钥和私钥，通过公钥加密的密文可以通过私钥解密为原文，通过私钥加密的密文
 * 可以通过公钥解密为原文。
 * （RSA）
 * 摘要算法加密： 摘要算法加密的重要特性是不可逆，也就是通过摘要算法对原文进行加密后是无法逆向解密为原文的。
 * （MD5和SHA）
 */
public class EncryptDemo {

    //定义8字节长度的秘钥
    private static String keyTextDES = "34er2323";
    //定义8字节长度的偏移量
    private static String ivStrDES = "12bv4bvk";
    private static byte[] ivDES = ivStrDES.getBytes();

    //定义16字节长度秘钥
    private static String keyTextAES = "34er232312bv4bvk";

    //定义私钥
    private static RSAPrivateKey rsaPrivateKey = null;
    //定义公钥
    private static RSAPublicKey rsaPublicKey = null;

    //静态块中初始化
    static {
        //KeyPairGenerator类用于生成公钥和私钥，基于RSA算法生成对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
            //初始化秘钥生成器，秘钥大小为1024位
            kpg.initialize(1024);
            //生成一个秘钥对，保存在keyPair中
            KeyPair keyPair = kpg.generateKeyPair();
            //得到私钥
            rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            //得到公钥
            rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //DES加密
    public static String encryptDES(String string) throws Exception {
        //生成向量
        IvParameterSpec zeroIv = new IvParameterSpec(ivDES);
        //根据秘钥字符串生成秘钥对象
        SecretKeySpec key = new SecretKeySpec(keyTextDES.getBytes(), "DES");
        //实例化密码类 用于加密和解密 实例化时需传入加解密方式
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        //初始化密码类 设置秘钥 向量 选择模式（加密还是解密）
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        //加密得到密文
        byte[] encryptData = cipher.doFinal(string.getBytes());
        //使用Base64工具类对密文字节数据进行编码
        return Base64.getEncoder().encodeToString(encryptData);
    }

    //DES解密
    public static String decryptDES(String str) throws Exception {
        //使用Base64工具类对密文进行解码
        byte[] decode = Base64.getDecoder().decode(str);
        //实例化向量
        IvParameterSpec zeroIv = new IvParameterSpec(ivDES);
        //生成秘钥
        SecretKeySpec key = new SecretKeySpec(keyTextDES.getBytes(), "DES");
        //实例化密码类
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        //初始化密码类 设置秘钥 向量 选择解密方式
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        //使用密码类对密文进行解密
        byte[] decryptData = cipher.doFinal(decode);
        return new String(decryptData);
    }

    //AES加密
    public static String encryptAES(String encryptStr) throws Exception {
        //根据秘钥字符串生成秘钥对象
        SecretKeySpec key = new SecretKeySpec(keyTextAES.getBytes(), "AES");
        //实例化密码类 用于加密和解密 实例化时需传入加解密方式
        Cipher cipher = Cipher.getInstance("AES");
        //初始化密码类 设置秘钥 向量 选择加密方式
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //加密得到密文
        byte[] encryptData = cipher.doFinal(encryptStr.getBytes());
        return Base64.getEncoder().encodeToString(encryptData);
    }

    //AES解密
    public static String decryptAES(String decryptStr) throws Exception {
        byte[] decode = Base64.getDecoder().decode(decryptStr);
        SecretKeySpec key = new SecretKeySpec(keyTextAES.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptData = cipher.doFinal(decode);
        return new String(decryptData);
    }

    //摘要算法加密 MD5 不可逆
    public static String encryptMD5(String encryptStr) throws Exception {

        //根据MD5算法生成MessageDigest对象
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] srcBytes = encryptStr.getBytes();
        //使用srcBytes更新摘要
        md5.update(srcBytes);
        //完成哈希计算 得到结果
        byte[] resultData = md5.digest();
        return Base64.getEncoder().encodeToString(resultData);
    }

    //摘要算法加密 SHA 不可逆
    public static String encryptSHA(String encryptStr) throws Exception {
        //使用SHA算法生成MessageDigest对象
        MessageDigest sha = MessageDigest.getInstance("SHA");
        byte[] bytes = encryptStr.getBytes();
        sha.update(bytes);
        byte[] resultData = sha.digest();
        return Base64.getEncoder().encodeToString(resultData);
    }

    //RSA加密
    public static String encryptRSA(String encryptRSA) throws Exception {
        if (rsaPublicKey != null) {
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥对Cipher对象初始化
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            byte[] bytes = cipher.doFinal(encryptRSA.getBytes());
            return Base64.getEncoder().encodeToString(bytes);
        }
        return null;
    }

    //RSA解密
    public static String decryptRSA(String decryptRSA) throws Exception {
        if (rsaPrivateKey != null) {
            byte[] bytes = Base64.getDecoder().decode(decryptRSA);
            Cipher cipher = Cipher.getInstance("RSA");
            //根据私钥对Cipher对象进行初始化
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            byte[] doFinal = cipher.doFinal(bytes);
            return new String(doFinal);
        }
        return null;
    }


    @Test
    public void test() throws Exception {
        //DES加密
        String encryptDES = EncryptDemo.encryptDES("123456");
        System.out.println(encryptDES);
        //DES解密
        String decryptDES = EncryptDemo.decryptDES(encryptDES);
        System.out.println(decryptDES);

        //AES加密
        String encryptAES = EncryptDemo.encryptAES("123456");
        System.out.println(encryptAES);
        //AES解密
        String decryptAES = EncryptDemo.decryptAES(encryptAES);
        System.out.println(decryptAES);

        //MD5加密
        String encryptMD5 = EncryptDemo.encryptMD5("123456");
        //SHA加密
        String encryptSHA = EncryptDemo.encryptSHA("123456");
        System.out.println(encryptMD5);
        System.out.println(encryptSHA);

        //RSA加密
        String encryptRSA = EncryptDemo.encryptRSA("123456");
        //RSA解密
        String decryptRSA = EncryptDemo.decryptRSA(encryptRSA);
        System.out.println(encryptRSA);
        System.out.println(decryptRSA);


    }


}
