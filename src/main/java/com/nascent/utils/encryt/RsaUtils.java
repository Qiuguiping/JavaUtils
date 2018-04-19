package com.nascent.utils.encryt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;

/**
 * @author guiping.Qiu
 * @version V1.0
 * @Package com.nascent.utils.encryt
 * @Description: TODO
 * @date 2018/4/19 16:24
 */
public class RsaUtils {
    /**
     * 根据publicKey 对data进行加密.
     *
     * @param publicKey
     * @param data
     * @throws Exception
     * @author Peter.Qiu
     */
    public static byte[] encryptMode(PublicKey publicKey, byte[] data)
            throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA",
                    new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);// ENCRYPT_MODE 加密
            int blockSize = cipher.getBlockSize();
            int outputSize = cipher.getOutputSize(data.length);
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1
                    : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize) {
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i
                            * outputSize);
                } else {
                    cipher.doFinal(data, i * blockSize, data.length - i
                            * blockSize, raw, i * outputSize);
                }
                i++;
            }
            return raw;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 根据privateKey 对data进行解密.
     *
     * @param privateKey
     * @param data
     * @throws Exception
     * @author Peter.Qiu
     */
    public static byte[] decryptMode(PrivateKey privateKey, byte[] data)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);// DECRYPT_MODE 解密
        int blockSize = cipher.getBlockSize();
        ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
        int j = 0;

        while (data.length - j * blockSize > 0) {
            bout.write(cipher.doFinal(data, j * blockSize, blockSize));
            j++;
        }
        return bout.toByteArray();
    }

    /**
     * 获取密钥.
     *
     * @param rsaKeyStore
     * @return
     * @throws Exception
     * @author Peter.Qiu
     */
    public static KeyPair getKeyPair(String rsaKeyStore) throws Exception {
        FileInputStream fis;
        fis = new FileInputStream(rsaKeyStore);
        ObjectInputStream oos = new ObjectInputStream(fis);
        KeyPair kp = (KeyPair) oos.readObject();
        oos.close();
        fis.close();
        return kp;
    }

    /**
     * 将密钥写入文件.
     *
     * @param kp
     * @param path
     * @throws Exception
     * @author Peter.Qiu
     */
    public static void saveKeyPair(KeyPair kp, String path) throws Exception {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        // 生成密钥
        oos.writeObject(kp);
        oos.close();
        fos.close();
    }

    /**
     * 用于生成公匙或私匙.
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @author Peter.Qiu
     */
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {

        SecureRandom sr = new SecureRandom();
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA",
                new BouncyCastleProvider());
        // 注意密钥大小最好为1024,否则解密会有乱码情况.
        kg.initialize(1024, sr);
        KeyPair genKeyPair = kg.genKeyPair();
        return genKeyPair;

    }

    /**
     * 将公密或私密写入文件.
     *
     * @param obj
     * @param path
     * @throws Exception
     * @author Peter.Qiu
     */
    public static void saveFile(Object obj, String path) throws Exception {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        // 生成密钥
        oos.writeObject(obj);
        oos.close();
        fos.close();
    }

    /**
     * 获取公密.
     *
     * @param publicKeyPath
     * @return
     * @throws Exception
     * @author Peter.Qiu
     */
    public static PublicKey getPublicKey(String publicKeyPath) throws Exception {
        FileInputStream fis = new FileInputStream(publicKeyPath);
        ObjectInputStream oos = new ObjectInputStream(fis);
        PublicKey kp = (PublicKey) oos.readObject();
        oos.close();
        fis.close();
        return kp;
    }

    /**
     * 获取私密.
     *
     * @param privateKeyPath
     * @return
     * @throws Exception
     * @author Peter.Qiu
     */
    public static PrivateKey getPrivateKey(String privateKeyPath)
            throws Exception {
        FileInputStream fis = new FileInputStream(privateKeyPath);
        ObjectInputStream oos = new ObjectInputStream(fis);
        PrivateKey kp = (PrivateKey) oos.readObject();
        oos.close();
        fis.close();
        return kp;
    }

    public static void main(String[] args) throws Exception {
        File dir = new File("./key/");
        if (!dir.exists()) {
            dir.mkdir();
        }
        // 获取公匙及私匙
        KeyPair generateKeyPair = getKeyPair("./key/key");

        // 生成公钥及私钥
        // generateKeyPair = generateKeyPair();

        // 存储KeyPair到本地用于后期解密 注意修改前台RSAKeyPair
        // saveKeyPair(generateKeyPair,"./key/key");

        System.out.println("generateKeyPair : " + generateKeyPair);
        // 公匙 用于前台加密
        PublicKey publicKey = null;// generateKeyPair.getPublic();
        publicKey = getPublicKey("./key/publicKey.key");
        System.out.println("publicKey:" + publicKey);
        // saveFile(publicKey,"./key/publicKey.key");
        // 私匙 存储在后台用于解密
        PrivateKey privateKey = null;// generateKeyPair.getPrivate(); //
        privateKey = getPrivateKey("./key/privateKey.key");//
        System.out.println("privateKey:" + privateKey);
        // saveFile(privateKey,"./key/privateKey.key");

        // 测试加密解密
        String test = "saaaa";
        test = "Peter.Qiu丘丘丘邱";
        System.out.println("加密前字符：" + test);
        byte[] en_test = encryptMode(publicKey, test.getBytes("UTF-8"));
        System.out.println("加密后字符:" + new String(en_test));

        byte[] de_test = decryptMode(privateKey, en_test);
        System.out.println("解密后字符:" + new String(de_test, "UTF-8"));

    }
}
