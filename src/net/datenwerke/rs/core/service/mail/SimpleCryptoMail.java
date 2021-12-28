package net.datenwerke.rs.core.service.mail;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoGeneratorBuilder;
import org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.mail.smime.SMIMEEnvelopedGenerator;
import org.bouncycastle.mail.smime.SMIMEException;
import org.bouncycastle.mail.smime.SMIMESignedGenerator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import net.datenwerke.rs.core.service.mail.annotations.MailModuleDefaultFrom;
import net.datenwerke.rs.core.service.mail.annotations.MailModuleDefaultFromName;
import net.datenwerke.rs.core.service.mail.interfaces.NeedsPostprocessing;
import net.datenwerke.rs.utils.misc.Nullable;
import net.datenwerke.security.service.crypto.CryptoCredentials;
import net.datenwerke.security.service.crypto.CryptoService;

public class SimpleCryptoMail extends SimpleMail implements NeedsPostprocessing {

   private static final String BC = BouncyCastleProvider.PROVIDER_NAME;

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   protected final MimeBodyPart rootBodyPart;

   private CryptoService cryptoService;

   private boolean encrypted = false;
   private boolean signed = false;

   public boolean isEncrypted() {
      return encrypted;
   }

   public boolean isSigned() {
      return signed;
   }

   @Inject
   public SimpleCryptoMail(@Assisted Session session, CryptoService cryptoService, @MailModuleDefaultFrom String from,
         @Nullable @MailModuleDefaultFromName String fromName) {
      super(session, from, fromName);

      this.cryptoService = cryptoService;
      this.rootBodyPart = new MimeBodyPart();
   }

   @Override
   public void setText(String text) {
      try {
         rootBodyPart.setText(text, CHARSET_UTF8, MIME_SUBTYPE_PLAIN);
      } catch (MessagingException e) {
         logger.warn(e.getMessage(), e);
      }
   }

   @Override
   public void setHtml(String html) {
      try {
         rootBodyPart.setText(html, CHARSET_UTF8, MIME_SUBTYPE_HTML);
      } catch (MessagingException e) {
         logger.warn(e.getMessage(), e);
      }
   }

   public void setHtml(String html, SimpleAttachment... attachements) {
      /* create multipart */
      MimeMultipart multipart = new MimeMultipart();

      try {
         /* create text */
         MimeBodyPart textMBP = new MimeBodyPart();
         textMBP.setText(html, CHARSET_UTF8, MIME_SUBTYPE_HTML);
         multipart.addBodyPart(textMBP);

         /* create attachements */
         for (SimpleAttachment att : attachements) {
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(att.getAttachment(), att.getMimeType());
            mbp.setFileName(att.getFileName());
            multipart.addBodyPart(mbp);
         }

         /* set contents */
         rootBodyPart.setContent(multipart);
      } catch (MessagingException e) {
         logger.warn(e.getMessage(), e);
      }
   }

   @Override
   public void setContents(String text) {
      setText(text);
   }

   @Override
   public void setContent(String text, SimpleAttachment... attachements) {
      /* create multipart */
      MimeMultipart multipart = new MimeMultipart();

      try {
         /* create text */
         MimeBodyPart textMBP = new MimeBodyPart();
         textMBP.setText(text, CHARSET_UTF8, MIME_SUBTYPE_PLAIN);
         multipart.addBodyPart(textMBP);

         /* create attachements */
         for (SimpleAttachment att : attachements) {
            multipart.addBodyPart(createAttachmentPart(att));
         }

         /* set contents */
         rootBodyPart.setContent(multipart);
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
      }
   }

   public void doCrypto() throws CertificateEncodingException, OperatorCreationException, SMIMEException,
         MessagingException, IllegalArgumentException, CMSException, IOException {
      sign();
      encrypt();

      try {
         getContent();
      } catch (IOException e) {
         MimeMultipart mmp = new MimeMultipart();
         mmp.addBodyPart(rootBodyPart);
         setContent(mmp);
      }
   }

   private void sign()
         throws CertificateEncodingException, OperatorCreationException, SMIMEException, MessagingException {
      CryptoCredentials cryptoCredentials = cryptoService.getCryptoCredentials(CryptoService.KEY_SIGN);

      X509Certificate signerCertificate = cryptoCredentials.getX509Certificate();
      PrivateKey privateKey = cryptoCredentials.getPrivateKey();

      // Get and store the necessary certificates
      List<Certificate> certList = new ArrayList<Certificate>();
      certList.addAll(cryptoCredentials.getCertificateChain());
      certList.add(signerCertificate);
      Store certs = new JcaCertStore(certList);

//		// create some smime capabilities in case someone wants to respond
//		ASN1EncodableVector         signedAttrs = new ASN1EncodableVector();
//		SMIMECapabilityVector       caps = new SMIMECapabilityVector();
//
//		caps.addCapability(SMIMECapability.dES_EDE3_CBC);
//		caps.addCapability(SMIMECapability.rC2_CBC, 128);
//		caps.addCapability(SMIMECapability.dES_CBC);
      //
      // signedAttrs.add(new SMIMECapabilitiesAttribute(caps));

      //
      // add an encryption key preference for encrypted responses -
      // normally this would be different from the signing certificate...
      //
      // IssuerAndSerialNumber issAndSer = new IssuerAndSerialNumber(new
      // X500Name(signDN), origCert.getSerialNumber());
      // signedAttrs.add(new SMIMEEncryptionKeyPreferenceAttribute(issAndSer));

      //
      // create the generator for creating an smime/signed message
      //
      SMIMESignedGenerator gen = new SMIMESignedGenerator();

      //
      // add a signer to the generator - this specifies we are using SHA1 and
      // adding the smime attributes above to the signed attributes that
      // will be generated as part of the signature. The encryption algorithm
      // used is taken from the key - in this RSA with PKCS1Padding
      //
      gen.addSignerInfoGenerator(new JcaSimpleSignerInfoGeneratorBuilder().setProvider(BC).build("SHA1withRSA",
            privateKey, signerCertificate));

      //
      // add our pool of certs and cerls (if any) to go with the signature
      //
      gen.addCertificates(certs);

      //
      // extract the multipart object from the SMIMESigned object.
      //

      MimeMultipart mm = gen.generate(rootBodyPart);

      super.setContent(mm);
      super.saveChanges();

      this.signed = true;
   }

   private void encrypt() throws CertificateEncodingException, IllegalArgumentException, MessagingException,
         SMIMEException, CMSException, IOException {
      /* Encrypt the signed message */
      SMIMEEnvelopedGenerator encrypter = new SMIMEEnvelopedGenerator();

      boolean someRecipientsSupportEncryption = false;
      boolean someRecipientsDontSupportEncryption = false;

      for (Address recipientAddress : getAllRecipients()) {
         CryptoCredentials recipientCredentials = cryptoService.getUserCryptoCredentials(recipientAddress.toString());

         if (null != recipientCredentials) {
            encrypter.addRecipientInfoGenerator(
                  new JceKeyTransRecipientInfoGenerator(recipientCredentials.getX509Certificate()).setProvider(BC));
            someRecipientsSupportEncryption = true;
         } else {
            someRecipientsDontSupportEncryption = true;
         }
      }

      if (someRecipientsSupportEncryption && someRecipientsDontSupportEncryption)
         throw new RuntimeException(
               "Failed to retrieve key-material for all recipients while sending an encrypted message. ");

      if (someRecipientsDontSupportEncryption && !someRecipientsSupportEncryption)
         return;

      /* mimemify */
      try {
         getContent();
      } catch (IOException e) {
         MimeMultipart mmp = new MimeMultipart();
         mmp.addBodyPart(rootBodyPart);
         setContent(mmp);
      }

      MimeBodyPart encryptedPart = encrypter.generate(this,
            new JceCMSContentEncryptorBuilder(CMSAlgorithm.AES256_CBC).setProvider(BC).build());

      super.setContent(encryptedPart.getContent(), encryptedPart.getContentType());
      super.saveChanges();

      this.encrypted = true;
   }

   @Override
   public void postprocess() {
      try {
         doCrypto();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public MimeBodyPart getRootBodyPart() {
      return rootBodyPart;
   }
}
