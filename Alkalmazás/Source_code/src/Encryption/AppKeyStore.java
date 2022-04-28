package Encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.google.firebase.cloud.StorageClient;

import Database.DatabaseFunctions;
import GUIRelated.PopupWindow;

public class AppKeyStore {
	private char[] passwordArray = "4^e@Q>.nZg>,$TPRxGeA$Z=n+#V*a-EFD3BsW@q'@Y8WaCKE_*@Vabk5+M_9\"*?f:7MK<^*cdp8NPJ@;*~Fw=)Hex,&(2WHf\\-dbGk!szV.>8;G7f[#z8@yq[QLgRc\\gXr%TqdyN@Kw]yBg2vH'D{;,}b>?d]94Yctve&MhQFBsA5/}qq:NPhd']#5R$r~tS@}a3#FC^8q#MzKM%@tC:j$M(H[.yJHV@Uba[g$-F^qFD[J/VS~dG&UmkAT\"Dr]8S".toCharArray();
	private KeyStore.ProtectionParameter password = new KeyStore.PasswordProtection(passwordArray);
	private PopupWindow popupWindow = new PopupWindow();
	private DatabaseFunctions dbFunc = new DatabaseFunctions();
	
	public AppKeyStore(StorageClient storageClient) {
		boolean exists = dbFunc.isFileInStorage(storageClient, "appKeyStore.jks");
		if(!exists) {
			initKeyStore(storageClient);
		}
		
	}
	
	public Key loadKeyStore(StorageClient storageClient) {
		try {
			String dirPath = new File(AppKeyStore.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
			String keyStoreName = "appKeyStore.jks";
			String fullKeyStorePath = dirPath + "\\" + keyStoreName;
			
			File tempKsFile = new File(fullKeyStorePath);
			try (FileOutputStream outputStream = new FileOutputStream(tempKsFile)) {
			    outputStream.write(dbFunc.storageDownload(storageClient, keyStoreName));
			}
			
			KeyStore ks = KeyStore.getInstance("JCEKS");
			ks.load(new FileInputStream(fullKeyStorePath), passwordArray);
			Key secretKey = ks.getKey("Application Encryption Key", passwordArray);
			
			if(tempKsFile.exists()) {
				tempKsFile.delete();
        	}
			return secretKey;
		} catch(Exception e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 16",
        			"Exception while loading KeyStore file");
    		while(val == 1) {}
		}
		return null;
	}
	
	public void initKeyStore(StorageClient storageClient){
		try {
			String dirPath = new File(AppKeyStore.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
			String keyStoreName = "appKeyStore.jks";
			String fullKeyStorePath = dirPath + "\\" + keyStoreName;
			
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        	keyGen.init(256);
        	SecretKey secretKey = keyGen.generateKey();

        	KeyStore ks = KeyStore.getInstance("JCEKS");
        	ks.load(null, passwordArray);
        	
        	KeyStore.SecretKeyEntry secret = new KeyStore.SecretKeyEntry(secretKey);
        	        	       	
        	ks.setEntry("Application Encryption Key", secret, password);
        	
			FileOutputStream fos = new FileOutputStream(fullKeyStorePath);
        	ks.store(fos, passwordArray);
        	fos.close();
        	dbFunc.storageUpload(storageClient, fullKeyStorePath, keyStoreName);
        	
        	File tempKsFile = new File(fullKeyStorePath);
        	if(tempKsFile.exists()) {
        		tempKsFile.delete();
        	}
        	
        	
		}catch(IOException e) {
        	int val = popupWindow.createErrorHandlingWindow("Java keystore is missing or path is incorrect.",
        			"Check the README file or config.xml!");
    		while(val == 1) {}
        } catch(NoSuchAlgorithmException e) {
        	int val = popupWindow.createErrorHandlingWindow("Code error 7", "NoSuchAlgorithmException while initializing KeyStore");
    		while(val == 1) {}
        } catch(KeyStoreException e) {
        	int val = popupWindow.createErrorHandlingWindow("Code error 14", "KeyStoreException while initializing KeyStore");
    		while(val == 1) {}
        } catch(CertificateException e) {
        	int val = popupWindow.createErrorHandlingWindow("Code error 15", "CertificateException while initializing KeyStore");
    		while(val == 1) {}
        }catch(URISyntaxException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 12", "URISyntaxException while saving files");
			while(val == 1) {
				
			}
		}
	}

	
}
