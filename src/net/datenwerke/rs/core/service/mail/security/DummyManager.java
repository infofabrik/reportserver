package net.datenwerke.rs.core.service.mail.security;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 
 *
 */
public class DummyManager implements X509TrustManager {

   public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
      // allow all

   }

   public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
      // allow all

   }

   public X509Certificate[] getAcceptedIssuers() {
      return new X509Certificate[0];
   }

}
