package net.datenwerke.security.service.crypto;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class CryptoCredentials {
	
	private X509Certificate x509Certificate;
	private PrivateKey privateKey;
	private List<X509Certificate> certificateChain = new ArrayList<X509Certificate>();

	public CryptoCredentials() {
	}
	
	public X509Certificate getX509Certificate() {
		return x509Certificate;
	}

	public void setX509Certificate(X509Certificate x509Certificate) {
		this.x509Certificate = x509Certificate;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	
	public PublicKey getPublicKey() {
		return x509Certificate.getPublicKey();
	}

	public void setCertificateChain(List<X509Certificate> certificateChain) {
		this.certificateChain = certificateChain;
	}
	
	public void addCertificateToChain(X509Certificate certificate) {
		certificateChain.add(certificate);
	}
	
	public List<X509Certificate> getCertificateChain() {
		return certificateChain;
	}
	
}
