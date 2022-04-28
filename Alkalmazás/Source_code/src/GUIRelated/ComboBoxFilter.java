package GUIRelated;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import Data.Content;

public class ComboBoxFilter {
	private Content content;
	
	public ComboBoxFilter(Content content) {
		this.content = content;
	}
	
	public DefaultComboBoxModel<String> initializeEveryItem() {
		
		DefaultComboBoxModel<String> returnList = new DefaultComboBoxModel<String>();
		ArrayList<String> contentItems = new ArrayList<String>();
		
		for(int i = 0; i < content.getNoteListModel().getSize(); i++) {
			contentItems.add(content.getNoteListModel().get(i).getNoteName());
			
			for(int j = 0; j < content.getNoteListModel().get(i).getNotesPages().size(); j++) {
				contentItems.add(content.getNoteListModel().get(i).getNotesPages().get(j).getPageName());
			}
			
		}
		String[] arr = new String[contentItems.size()];
		arr = contentItems.toArray(arr);
		
		for(int i = 0; i < contentItems.size(); i++) {
			returnList.addElement(arr[i]);
		}
		
		return returnList;
	}
	
	public DefaultComboBoxModel<String> filterElements(String text) {
		
		DefaultComboBoxModel<String> returnList = new DefaultComboBoxModel<String>();
		ArrayList<String> contentItems = new ArrayList<String>();
		
		for(int i = 0; i < content.getNoteListModel().getSize(); i++) {
			if(content.getNoteListModel().get(i).getNoteName().toLowerCase().contains(text.toLowerCase())) {
				contentItems.add(content.getNoteListModel().get(i).getNoteName());
			}
			
			for(int j = 0; j < content.getNoteListModel().get(i).getNotesPages().size(); j++) {
				if(	content.getNoteListModel().get(i).getNotesPages().get(j).getPageName().toLowerCase().contains(text.toLowerCase()) ||
					content.getNoteListModel().get(i).getNotesPages().get(j).getPageText().toLowerCase().contains(text.toLowerCase())) {
					contentItems.add(content.getNoteListModel().get(i).getNotesPages().get(j).getPageName());
				}
			}
			
		}
		String[] arr = new String[contentItems.size()];
		arr = contentItems.toArray(arr);
		
		for(int i = 0; i < contentItems.size(); i++) {
			returnList.addElement(arr[i]);
		}
		
	
		return returnList;
		
		
	}
	
}
