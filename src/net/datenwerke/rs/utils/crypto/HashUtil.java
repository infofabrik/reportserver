package net.datenwerke.rs.utils.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;


/**
 * Provides convenience methods for creating cryptographic hash values
 * 
 *
 */
@Singleton
public class HashUtil {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	
	public String md5(String in){
		return hashBytes(in.getBytes(), "MD5"); //$NON-NLS-1$
	}
	
	public String md5(File in){
		return hashFile(in, "MD5"); //$NON-NLS-1$
	}
	
	public String sha1(String in){
		return hashBytes(in.getBytes(), "SHA1"); //$NON-NLS-1$
	}
	
	public String sha1(File in){
		return hashFile(in, "SHA1"); //$NON-NLS-1$
	}
	
	public String sha1ToString(byte[] in){
		return hashBytes(in, "SHA1");
	}
	
	public byte[] sha1Bytes(byte[] in){
		try{
			MessageDigest dig = MessageDigest.getInstance("SHA1");
			dig.reset();
			dig.update(in);
			return dig.digest();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public String hashBytes(byte[] in, String algorithm){
		try {
			MessageDigest dig = MessageDigest.getInstance(algorithm);
		    dig.reset();
	        dig.update(in);
	        byte[] result = dig.digest();

	        StringBuffer buf = new StringBuffer();
	        for (int i=0; i<result.length; i++) {
	        	String hex = Integer.toHexString(0xFF & result[i]);
	        	if(hex.length()==1) 
	        		buf.append('0');
            	buf.append(hex);
	        }

	        return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.warn( "hashing error", e);
		}
       
		return ""; //$NON-NLS-1$
	}
	
	private String hashFile(File in, String algorithm){
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			InputStream is = new FileInputStream(in);				
			byte[] buffer = new byte[8192];
			int read = 0;
			try {
				while( (read = is.read(buffer)) > 0) {
					digest.update(buffer, 0, read);
				}		
				byte[] hashsum = digest.digest();
				BigInteger bigInt = new BigInteger(1, hashsum);
				String output = bigInt.toString(16);
				return output;
			}
			catch(IOException e) {
				throw new RuntimeException("Unable to process file for " + algorithm, e); //$NON-NLS-1$
			}
			finally {
				try {
					is.close();
				}
				catch(IOException e) {
					throw new RuntimeException("Unable to close input stream for " + algorithm + " calculation", e); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		} catch (NoSuchAlgorithmException e) {
			logger.warn( e.getMessage(), e);
		} catch (FileNotFoundException e) {
			logger.warn( e.getMessage(), e);
		}
		
		return null;
	}

	public String hashDirMD5(File dir){
		return hashDir(dir, "MD5", 16); //$NON-NLS-1$
	}
	
	private String hashDir(File dir, String algorithm, int bytes){
		if(! dir.exists() )
			throw new IllegalArgumentException("Directory does not exist");
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] hashsum = new byte[bytes];
			for(File f : dir.listFiles()){
				if(f.getName().length() > 0 && f.getName().charAt(0) == '.')
					continue;
				
				InputStream is = new FileInputStream(f);				
				byte[] buffer = new byte[8192];
				int read = 0;
				
				try {
					while( (read = is.read(buffer)) > 0) {
						digest.update(buffer, 0, read);
					}		
					byte[] tmphashsum = digest.digest();
					try{
						for(int i = 0; i < hashsum.length; i++){
							hashsum[i] = (byte)(hashsum[i] ^ tmphashsum[i]);
						}
					} catch(IndexOutOfBoundsException e){}
				}
				catch(IOException e) {
					throw new RuntimeException("Unable to process file for " + algorithm, e); //$NON-NLS-1$
				}
				finally {
					try {
						is.close();
					}
					catch(IOException e) {
						throw new RuntimeException("Unable to close input stream for " + algorithm + " calculation", e); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
			}
			
			BigInteger bigInt = new BigInteger(1, hashsum);
			String output = bigInt.toString(16);
			return output;
		} catch (NoSuchAlgorithmException e) {
			logger.warn( e.getMessage(), e);
		} catch (FileNotFoundException e) {
			logger.warn( e.getMessage(), e);
		}
		
		return null;
	}
	
}
