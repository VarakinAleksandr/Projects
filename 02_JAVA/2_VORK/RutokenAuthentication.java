import ru.CryptoPro.CAdES.CAdESSignature;
import ru.CryptoPro.Crypto.CryptoProvider;
import ru.CryptoPro.JCP.JCP;
import ru.CryptoPro.JCP.KeyStore.JCPKeyStore;
import ru.CryptoPro.JCP.KeyStore.JCPPrivateKeyEntry;
import ru.CryptoPro.JCSP.JCSP;
import ru.CryptoPro.reprov.RevCheck;

import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Enumeration;

public class RutokenAuthentication {

    public static void main(String[] args) throws Exception {
        // 1. Инициализация провайдера JCP

        Security.addProvider(new JCSP());
        Security.addProvider(new RevCheck());
        Security.addProvider(new CryptoProvider());

        // 2. Загрузка KeyStore с Рутокена
        String keyStoreType = JCSP.MY_STORE_NAME;
 //       String keyStoreType = JCP.HARD_KEYSTORE_NAME; // Используем аппаратный ключевой контейнер
        String pinCode = "12345678"; // PIN-код для токена
  //      String pinCode = "1234567890"; // PIN-код для токена

        KeyStore keyStore = KeyStore.getInstance(keyStoreType);

        keyStore.load(null, pinCode.toCharArray());

        // 3. Выбор ключа и сертификата
        Enumeration<String> aliases = keyStore.aliases();
//        while(aliases.hasMoreElements()){
//            Certificate cert = keyStore.getCertificate(aliases.nextElement());
//            if (cert==null){
//                continue;
//            }
//            if (!(cert instanceof X509Certificate)){
//                continue;
//            }
//            X509Certificate x509Cert = (X509Certificate) cert;
//            System.out.println(x509Cert.getSubjectDN());
//        }
        String alias = keyStore.aliases().nextElement();
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, pinCode.toCharArray());
        Certificate certificate = keyStore.getCertificate(alias);



   //     JCPPrivateKeyEntry entry = (JCPPrivateKeyEntry) keyStore.getEntry(alias,null);
  //      JCPPrivateKeyEntry entry = (JCPPrivateKeyEntry) keyStore.getEntry(alias,null);
   //     PrivateKey privateKey = entry.getPrivateKey();
   //     Certificate cert = entry.getCertificate();
//        String alias = keyStore.aliases().nextElement(); // Предполагаем, что на токене один ключ
//        JCPPrivateKeyEntry entry = (JCPPrivateKeyEntry) keyStore.getEntry(alias, null);
 //       PrivateKey privateKey = entry.getPrivateKey();
//        Certificate cert = entry.getCertificate();

        // 4. Данные для подписи (challenge от сервера)
        String challenge = "random_challenge_from_server";
        byte[] dataToSign = challenge.getBytes("UTF-8");

        // 5. Подписание данных
        CAdESSignature cadesSignature = new CAdESSignature();
//        byte[] signature = cadesSignature.addSigner(dataToSign,privateKey,cert,);
//        byte[] signature = cadesSignature.sign(dataToSign, privateKey, cert, true);
  //      byte[] signature = cadesSignature.sign(dataToSign, privateKey, cert, true);

        // 6. Базовый64-кодирование подписи для отправки на сервер
  //      String signatureBase64 = Base64.getEncoder().encodeToString(signature);
 //       System.out.println("ЭЦП: " + signatureBase64);

        // 7. Отправка данных на сервер (например, через HTTP)
        // TODO: реализуйте HTTP-запрос, отправляющий challenge и signatureBase64
    }
}