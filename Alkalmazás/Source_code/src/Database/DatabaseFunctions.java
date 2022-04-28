package Database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.gson.stream.MalformedJsonException;

import GUIRelated.PopupWindow;
import XML.ConfigManager;

public class DatabaseFunctions {
	
	private ConfigManager configManager;
	private PopupWindow popupWindow = new PopupWindow();
	
	public DatabaseFunctions() {
		this.configManager = new ConfigManager();	
	}
	
	public StorageClient initDatabaseConnection() {
		FileInputStream serviceAccount = null;
		StorageClient storageClient = null;
		try {
			serviceAccount = new FileInputStream(configManager.getDbJsonPath());
		} catch (FileNotFoundException e) {
    		int val = popupWindow.createErrorHandlingWindow("Database service key missing or path is incorrect.",
    				"Check the README file or config.xml!");
			while(val == 1) {
				
			}
		}
		
		try {
			@SuppressWarnings("deprecation")
			FirebaseOptions options = new FirebaseOptions.Builder()
			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			  .setStorageBucket(configManager.getDbStoragePath())
			  .build();
			FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
			
			storageClient = StorageClient.getInstance(firebaseApp);
		
		}catch(MalformedJsonException e){
			int val = popupWindow.createErrorHandlingWindow("The contents of the database service file are incorrect.",
    				"Check the README file or config.xml!");
			while(val == 1) {
				
			}
		}catch (IOException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 2",
    				"Database service account error.");
			while(val == 1) {
				
			}
		}
		
		return storageClient;
	}

	public void storageUpload(StorageClient storageClient, String filePath, String fileName) {
		InputStream testFile = null;
		try {
			testFile = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 1",
					"DatabaseUpload file path incorrect.");
			while(val == 1) {
				
			}
		}
		String blobString = fileName;
		storageClient.bucket().create(blobString, testFile);
	}

	public byte[] storageDownload(StorageClient storageClient, String fileName) {
		Blob blob = null;
		try {
			blob = storageClient.bucket().get(fileName);
		}catch(IllegalArgumentException e) {
			int val = popupWindow.createErrorHandlingWindow("Bucket doesn't exist.",
					"Check the README file or config.xml!");
			while(val == 1) {
				
			}
		}
		if (blob != null) {
			byte[] content = blob.getContent();
			return content;
		} else {
			return null;
		}
	}
	
	public boolean isFileInStorage(StorageClient storageClient, String fileName) {
		Blob blob = null;
		try {
			blob = storageClient.bucket().get(fileName);
		}catch(IllegalArgumentException e) {
			int val = popupWindow.createErrorHandlingWindow("Bucket doesn't exist.",
					"Check the README file or config.xml!");
			while(val == 1) {
				
			}
		}
		if(blob == null) {
			return false;
		}else {
			return true;
		}
	}
}
