package ru.rutoken.Samples.sunJCA;

import java.security.*;
import javax.crypto.*;
import java.security.cert.*;
import java.util.Enumeration;
import java.security.Provider;

public class RSA {

    static byte[] message = {
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };

    public static void main(String[] args) {
        try {

            String userPin = "12345678";
            int keySize = 512;

            System.out.println("Example of working with RSA algorithm using SunRsaSign Provider via JCA");

            String config = "pkcs11.cfg";
      //      Provider sunPKCS11 = new sun.security.pkcs11.SunPKCS11(config);
            String sunPKCS11 = "";
      //      int pos = Security.addProvider(sunPKCS11);

      //      System.out.println("Provider Level: " + pos);

            // Авторизация на токен
            char[] pin = userPin.toCharArray();
            KeyStore ks = KeyStore.getInstance("PKCS11", sunPKCS11);
            ks.load(null, pin);

            // Генерация ключевой пары
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", sunPKCS11);
            generator.initialize(keySize);
            KeyPair pair = generator.generateKeyPair();
            PrivateKey privKey = pair.getPrivate();
            PublicKey pubKey = pair.getPublic();

            // Поиск ключевой пары на токене (только если присутствует сертификат)
            Enumeration aliases = ks.aliases();
            String alias = null;
            while (aliases.hasMoreElements()) {
                alias = aliases.nextElement().toString();
            }
            if (alias != null) {
                X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
                System.out.println("Certificate:\n " + cert);
                pubKey = cert.getPublicKey();
                privKey = (PrivateKey) ks.getKey(alias, null);
            }

            System.out.println("Public key:\n " + pubKey);
            System.out.println("Private key:\n " + privKey);

            // Шифрование по алгоритму RSA
            Cipher rsaEncrypt = Cipher.getInstance("RSA/ECB/NoPadding", sunPKCS11);
            rsaEncrypt.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] encryptedMessage = rsaEncrypt.doFinal(message);

            System.out.println("Encrypted data:");
            for (int i = 0; i < encryptedMessage.length; i++) {
                System.out.printf(" %02X", encryptedMessage[i]);
                if ((i + 1) % 8 == 0)
                    System.out.printf("\n");
            }
            System.out.println("Data has been encrypted successfully.");

            // Расшифрование по алгоритму RSA
            Cipher rsaDecrypt = Cipher.getInstance("RSA/ECB/NoPadding", sunPKCS11);
            rsaDecrypt.init(Cipher.DECRYPT_MODE, privKey);
            byte[] decryptedMessage = rsaDecrypt.doFinal(encryptedMessage);

            System.out.println("Decrypted data:");
            for (int i = 0; i < decryptedMessage.length; i++) {
                System.out.printf(" %02X", decryptedMessage[i]);
                if ((i + 1) % 8 == 0)
                    System.out.printf("\n");
            }
            System.out.println("Data has been decrypted successfully.");

            // Подпись по алгоритму RSA
            String dataToSign = "Aktiv Co., Ltd.";
            System.out.println("Data for signature: " + dataToSign);

            Signature rsaSign = Signature.getInstance("SHA1withRSA", sunPKCS11);
            rsaSign.initSign(privKey);
            rsaSign.update(dataToSign.getBytes());
            byte[] signedData = rsaSign.sign();

            System.out.println("Signed data:");
            for (int i = 0; i < signedData.length; i++) {
                System.out.printf(" %02X", signedData[i]);
                if ((i + 1) % 8 == 0)
                    System.out.printf("\n");
            }
            System.out.println("Data has been signed successfully.");

            // Проверка подписи по алгоритму RSA
            Signature rsaVerify = Signature.getInstance("SHA1withRSA", sunPKCS11);
            rsaVerify.initVerify(pubKey);
            rsaVerify.update(dataToSign.getBytes());
            if (rsaVerify.verify(signedData)) {
                System.out.println("Signature has been verified successfully.");
            } else {
                System.out.println("Signature verification failed!");
            }
        } catch (Exception e) {
            System.out.printf("Some error occurred. Error code: " + e.getMessage());
        }
    }
}