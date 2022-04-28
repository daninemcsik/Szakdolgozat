package Data;

import javax.swing.DefaultListModel;
import javax.swing.JList;
 
/**
  * 
  * This object contains all the important data used in the application.
  *
  */
public class Content{
	
	/**
	 * A Note type DefaultListModel. Used to initialize a note type list's items.
	 */
	DefaultListModel<Note> notelist = new DefaultListModel<>(); //Ebben lévõ note-okat lehet majd xml-re konvertálni az adattároláshoz.
	
	/**
	 * A Page type DefaultListModel. Used to initialize a page type list's items.
	 */
	DefaultListModel<Page> pagelist = new DefaultListModel<>(); //Ez csak egy megjelenítésre használt tároló elem, az page-eket maguk a note-ok tartalmazzák.
	
	/**
	 * @return 	a Note type DefaultListModel.
	 */
	public DefaultListModel<Note> getNoteListModel() {
		return notelist;
		
	}
	
	/**
	 * Adds a Note to a Note type DefaultListModel.
	 * @param 	note 	A Note type object
	 */
	public void addNoteTolistModel(Note note) {
		notelist.addElement(note);
	}
	
	/**
	 * @return 	a Page type DefaultListModel.
	 */
	public DefaultListModel<Page> getPageListModel() {
		return pagelist;
	}
	
	/**
	 * Adds a Page to a Page type DefaultListModel.
	 * @param 	page 	A Page type object
	 */
	public void addPageToListModel(Page page) {
		pagelist.addElement(page);
	
	}
	
	/**
	 * Gets the selected note list item's pages and adds them to a clear
	 * page list.
	 * 
	 * @param 	noteList	A JList containing Note type objects.
	 */
	public void refreshPageListModel(JList<Note> noteList) {
		pagelist.clear();
		for(int i = 0; i < noteList.getSelectedValue().getNotesPages().size(); i++) {
			pagelist.addElement(noteList.getSelectedValue().getNotesPages().get(i));
		}
	}
	
}
