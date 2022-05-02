package Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import com.google.firebase.cloud.StorageClient;

import Data.Content;
import Database.DatabaseFunctions;
import Encryption.EncryptionFunctions;
import GUI.LoadingScreen;
import GUI.MainScreenOutline;
import GUIRelated.PopupWindow;
import XML.ConfigManager;
import XML.XMLReader;

public class MainClass {
	
	private static StorageClient dbStorageClient;
	private static MainScreenOutline mainScreen;
	private static LoadingScreen loadingScreen;
	private static PopupWindow popupWindow = new PopupWindow();
	
	public static void main(String[] args) {

		loadingScreen = new LoadingScreen();
			
		checkInternetConnectionOnce();
		new ConfigManager();
		mainScreen = new MainScreenOutline(initApplicationContent(), dbStorageClient);
		loadingScreen.close();
		mainScreen.setVisible(true);
			
		checkInternetConnection();
			
	}
	
	public static void checkInternetConnection() {
		Process process;
		PopupWindow popupWindows = new PopupWindow();
		
		try {
			process = java.lang.Runtime.getRuntime().exec("ping www.google.com");
			int x = process.waitFor();
        
			if(x != 0) {
				popupWindows.createNoInternetPopupMenu("inform", "inform");	
				System.exit(0);
			}
			
			Thread.sleep(4000);
			checkInternetConnection();
			
		} catch(IOException e){
			int val = popupWindow.createErrorHandlingWindow("Code error 11", "IOException while checking connection");
			while(val == 1) {}
		} catch(InterruptedException e){
			int val = popupWindow.createErrorHandlingWindow("Code error 16", 
					"InterruptedExceptionwhile while checking connection");
			while(val == 1) {}
		}
	}
	
	
	public static void checkInternetConnectionOnce() {
		Process process;
		PopupWindow popupWindows = new PopupWindow();
		
		try {
			process = java.lang.Runtime.getRuntime().exec("ping www.google.com");
			int x = process.waitFor();
        
			if(x != 0) {
				popupWindows.createNoInternetPopupMenu("inform", "inform");
				System.exit(0);
			}
		} catch(IOException e){
			int val = popupWindow.createErrorHandlingWindow("Code error 11", "IOException while checking connection");
			while(val == 1) {}
		} catch(InterruptedException e){
			int val = popupWindow.createErrorHandlingWindow("Code error 16", 
					"InterruptedExceptionwhile while checking connection");
			while(val == 1) {}
		}
		
	}
			
	private static Content initApplicationContent() {
		String dirPath = null;
		try {
			dirPath = new File(MainClass.class.getProtectionDomain().getCodeSource()
					.getLocation().toURI()).getParent();
		} catch (URISyntaxException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 4", "Error while parsing XML file");
			while(val == 1) {
				
			}
		}
		String fileName = "applicationData.xml";
		XMLReader xmlReader = new XMLReader();
		Content notesAndPages = new Content();
		DatabaseFunctions dbFunc = new DatabaseFunctions();
		EncryptionFunctions encFunc;
		
		dbStorageClient = dbFunc.initDatabaseConnection();
		
		encFunc = new EncryptionFunctions(dbStorageClient);
		
		byte[] encryptedBytes = dbFunc.storageDownload(dbStorageClient, fileName);
		
		if(encryptedBytes != null) {		
			byte[] decryptedBytes = encFunc.decrypt(encryptedBytes, dbStorageClient);
			
			String tempFilePath = dirPath + "\\dbTempDownload.xml";
			File tempFile = new File(tempFilePath);
			
			OutputStream os;
			try {
				os = new FileOutputStream(tempFile);
			
	
				os.write(decryptedBytes);	
				os.close();
				
				xmlReader.parseFile(tempFilePath, notesAndPages);
				PrintWriter pw = new PrintWriter(tempFilePath);
				pw.close();
				tempFile.delete();
			} catch (IOException e) {
				int val = popupWindow.createErrorHandlingWindow("Code error 11", "IOException while initializin application's content");
				while(val == 1) {
					
				}
			}
						
			return notesAndPages;
		}else {
			return new Content();
		}
	}
	
}
