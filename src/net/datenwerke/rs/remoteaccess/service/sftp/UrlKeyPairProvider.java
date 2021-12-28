package net.datenwerke.rs.remoteaccess.service.sftp;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

import org.apache.sshd.common.keyprovider.AbstractKeyPairProvider;
import org.apache.sshd.common.session.SessionContext;
import org.apache.sshd.common.util.security.SecurityUtils;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

public class UrlKeyPairProvider extends AbstractKeyPairProvider {

   private URL[] ulrs;

   public UrlKeyPairProvider(URL... ulrs) {
      this.ulrs = ulrs;

   }

   @Override
   public Iterable<KeyPair> loadKeys(SessionContext session) throws IOException, GeneralSecurityException {
      if (!SecurityUtils.isBouncyCastleRegistered()) {
         throw new IllegalStateException("BouncyCastle must be registered as a JCE provider");
      }
      List<KeyPair> keys = new ArrayList<KeyPair>();
      for (int i = 0; i < ulrs.length; i++) {
         try {
            PEMParser r = new PEMParser(new InputStreamReader(ulrs[i].openStream()));
            try {
               Object o = r.readObject();
               if (o instanceof PEMKeyPair) {
                  PEMKeyPair pemKeyPair = (PEMKeyPair) o;
                  KeyPair kp = new JcaPEMKeyConverter().getKeyPair(pemKeyPair);
                  keys.add(kp);
               } else if (o instanceof KeyPair) {
                  keys.add((KeyPair) o);
               }
            } finally {
               r.close();
            }
         } catch (Exception e) {
            log.info("Unable to read key {}: {}", ulrs[i], e);
         }
      }
      return keys;

   }

}
