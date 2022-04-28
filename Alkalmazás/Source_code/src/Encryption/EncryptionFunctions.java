package Encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.google.firebase.cloud.StorageClient;

import GUIRelated.PopupWindow;

public class EncryptionFunctions {
	private AppKeyStore keyStore;
	private PopupWindow popupWindow = new PopupWindow();
	
	public EncryptionFunctions(StorageClient storageClient) {
		this.keyStore = new AppKeyStore(storageClient);
	}
	
	public void encrypt(File inputFile, File outputFile, StorageClient storageClient)  {
		try {
			Key secretKey = keyStore.loadKeyStore(storageClient);
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE,  secretKey);
			
			FileInputStream inputStream = new FileInputStream(inputFile);
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);
			
			byte[] outputBytes = cipher.doFinal(inputBytes);
			
			FileOutputStream outputStream = new FileOutputStream(outputFile);
			outputStream.write(outputBytes);
			
			inputStream.close();
            outputStream.close();
		}catch (NoSuchAlgorithmException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 7",
    				"NoSuchAlgorithmException while encrypting");
			while(val == 1) {
				
			}
		} catch (NoSuchPaddingException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 8","NoSuchPaddingException while encrypting");
			while(val == 1) {
				
			}
		}catch(InvalidKeyException e) {
			int val = popupWindow.createErrorHandlingWindow("Incorrect encryption key.", "Check the README file or config.xml!");
			while(val == 1) {
				
			}
		} catch(IllegalBlockSizeException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 9","IllegalBlockSizeException while encrypting");
			while(val == 1) {
				
			}
		} catch(BadPaddingException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 10","BadPaddingException while encrypting");
			while(val == 1) {
				
			}
		} catch(IOException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 11","IOException while encrypting");
			while(val == 1) {
				
			}
		}
	}
	
	public byte[] decrypt(byte[] inputBytes, StorageClient storageClient) {
		byte[] outputBytes = null;
		try {
			Key secretKey = keyStore.loadKeyStore(storageClient);
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE,  secretKey);
			
			outputBytes = cipher.doFinal(inputBytes);
			
			return outputBytes;
		} catch (NoSuchAlgorithmException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 7",
    				"NoSuchAlgorithmException while decrypting");
			while(val == 1) {
				
			}
		} catch (NoSuchPaddingException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 8","NoSuchPaddingException while decrypting");
			while(val == 1) {
				
			}
		} catch(InvalidKeyException e) {
			int val = popupWindow.createErrorHandlingWindow("Incorrect encryption key.", "Check the README file or config.xml!");
			while(val == 1) {
				
			}
		} catch(IllegalBlockSizeException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 9","IllegalBlockSizeException while decrypting");
			while(val == 1) {
				
			}
		} catch(BadPaddingException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 10","BadPaddingException while decrypting");
			while(val == 1) {
				
			}
		}
		return outputBytes;
	}
}
