
import ru.CryptoPro.CAdES.*;
import ru.CryptoPro.Crypto.CryptoProvider;
import ru.CryptoPro.JCSP.JCSP;
import ru.CryptoPro.reprov.RevCheck;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class RutokenAuthentication_TestNew {

    public static void main(String[] args) throws Exception {
        // 1. Инициализация провайдера JCP
        try {
        Security.addProvider(new JCSP());
        Security.addProvider(new RevCheck());
        Security.addProvider(new CryptoProvider());

        // 2. Загрузка KeyStore с Рутокена
        String keyStoreType = JCSP.MY_STORE_NAME;

 //       String keyStoreType = JCP.HARD_KEYSTORE_NAME; // Используем аппаратный ключевой контейнер
        String pinCode = "12345678"; // PIN-код для токена
   //     String pinCode = "1234567890"; // PIN-код для токена


        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        KeyStore keyStore1 = KeyStore.getInstance(JCSP.CA_STORE_NAME);

        keyStore.load(null, pinCode.toCharArray());
        keyStore1.load(null,null);
        List<X509Certificate> certs = new ArrayList<X509Certificate>();
        List<X509Certificate> chain = new ArrayList<X509Certificate>();
        List<String> listCerts = new ArrayList<String>();




        // 3. Выбор ключа и сертификата
       Enumeration<String> aliases = keyStore.aliases();
        while(aliases.hasMoreElements()){
            String alias = aliases.nextElement();
            Certificate cert = keyStore.getCertificate(aliases.nextElement());
            if (cert==null){
                continue;
            }
            if (!(cert instanceof X509Certificate)){
                continue;
            }
            listCerts.add(alias);
            certs.add((X509Certificate)cert);
        }
//            aliases = keyStore.aliases();
//            while(aliases.hasMoreElements()){
//                String alias = keyStore.aliases().nextElement();
//                if (alias==null){
//                    continue;
//                }
//                if (!(alias instanceof String)){
//                    continue;
//                }
//                listCerts.add(alias);
//            }

        // 3.1 сохранение в список сертификатов
        Enumeration<String> aliases1 = keyStore1.aliases();
        while(aliases1.hasMoreElements()){
            Certificate cert = keyStore1.getCertificate(aliases1.nextElement());

            if (cert==null){
                continue;
            }
            if (!(cert instanceof X509Certificate)){
                continue;
            }
            chain.add((X509Certificate)cert);


        }
   //     System.out.println(certs);
   //     System.out.println(chain);

           boolean privateKey11 = keyStore.isKeyEntry(listCerts.get(0));
           PrivateKey privateKey = (PrivateKey) keyStore.getKey(listCerts.get(1), pinCode.toCharArray());
           Certificate certificate = keyStore.getCertificate(listCerts.get(1));


  //      String alias = keyStore.aliases().nextElement();
//        boolean privateKey11 = keyStore.isKeyEntry(alias));
//        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, pinCode.toCharArray());
//        Certificate certificate = keyStore.getCertificate(alias);



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
            var cAdESSignature = new CAdESSignature(false,false);

            //                        CAdESSignature cAdESSignature = new CAdESSignature();
      //      byte[] signature = cadesSignature.getSignedContent().readAllBytes();
      //      cadesSignature.addSigner(challenge,privateKey,chain,null ,challenge,true);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



//        byte[] signature = cadesSignature.sign(dataToSign, privateKey, cert, true);
  //      byte[] signature = cadesSignature.sign(dataToSign, privateKey, cert, true);

        // 6. Базовый64-кодирование подписи для отправки на сервер
  //      String signatureBase64 = Base64.getEncoder().encodeToString(signature);
 //       System.out.println("ЭЦП: " + signatureBase64);

        // 7. Отправка данных на сервер (например, через HTTP)
        // TODO: реализуйте HTTP-запрос, отправляющий challenge и signatureBase64
    }
}