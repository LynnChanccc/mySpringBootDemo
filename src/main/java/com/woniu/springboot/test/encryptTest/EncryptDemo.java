package com.woniu.springboot.test.encryptTest;

import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * @author cl
 * @Date 2020/3/18 14:01
 * 加密算法
 */
public class EncryptDemo {

    //定义8字节长度的秘钥
    private static String keyTextDES = "34er2323";
    //定义8字节长度的偏移量
    private static String ivStrDES = "12bv4bvk";
    private static byte[] ivDES = ivStrDES.getBytes();

    //定义16字节长度秘钥
    private static String keyTextAES = "34er232312bv4bvk";

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


    }


}
