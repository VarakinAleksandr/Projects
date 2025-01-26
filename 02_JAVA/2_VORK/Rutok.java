


import ru.CryptoPro.Crypto.CryptoProvider;
import ru.CryptoPro.JCP.KeyStore.HDImage.HDImageStore;
import ru.CryptoPro.JCSP.JCSP;
import ru.CryptoPro.reprov.RevCheck;

import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class Rutok {
    public static void main(String[] args) throws KeyStoreException, NoSuchProviderException, CertificateException, IOException, NoSuchAlgorithmException {
        System.setProperty("file.encoding", "UTF-8");
        Security.addProvider(new JCSP());
        Security.addProvider(new RevCheck());
        Security.addProvider(new CryptoProvider());

             String pinCode = "12345678"; // PIN-код для токена
  //      String pinCode = "1234567890"; // PIN-код для токена

        KeyStore keyStore = KeyStore.getInstance(null, "JCSP");
      //  keyStore.getCertificate("362B 4476 0001 0000 0C40");
        keyStore.load(null,pinCode.toCharArray());
      //  keyStore.getCertificateAlias()
        Enumeration<String> aliases = keyStore.aliases();

        while(aliases.hasMoreElements()){
            Certificate cert = keyStore.getCertificate(aliases.nextElement());
            if (cert==null){
                continue;
            }
            if (!(cert instanceof X509Certificate)){
                continue;
            }
            X509Certificate x509Cert = (X509Certificate) cert;
            System.out.println(x509Cert.getSubjectDN());
        }


    }
}
