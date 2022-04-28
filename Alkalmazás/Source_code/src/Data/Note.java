package Data;

import java.util.ArrayList;


public class Note {
	/**
	 * Note's name string.
	 */
	private String noteName;
	
	/**
	 * A static counter. Default value 0.
	 */
	private static int counter = 0;
	
	/**
	 * A note object can have more than 1 pages. This arrayList is used
	 * to store those pages
	 */
	private ArrayList<Page> pageList = new ArrayList<Page>();
	
	/**
	 * An integer type id.
	 */
	private int Id;

	/**
	 * A note object's constructor. The static counter gets increased
	 * every time a note object is created. the note object's id is set 
	 * the current value of the counter integer.
	 * @param 	noteName	sets the note's name.
	
	 */
	public Note(String noteName) {
		this.noteName = noteName + " " + counter;
		setId(counter);
		counter++;
	}
	/**
	 * A note object's empty constructor.
	
	 */
	public Note() {
		counter++;
	}
	
	/**
	 * @return	a note's name.
	 */
	public String getNoteName() {
		return noteName;
	}
	
	/**
	 * Sets the note's name to the given parameter.
	 * @param 	noteName	name you want to set for the note.
	 */
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	
	/**
	 * Adds a page object to a note object.
	 * @param 	page	a page object.
	 */
	public void addPageToNote(Page page) {
		pageList.add(page);
	}

	/**
	 * Removes a page from a note object.
	 * @param 	page	the page you want to remove.
	 */
	public void removePageFromNote(Page page) {
		pageList.remove(page.getNoteId());
	}
	
	public void deleteEveryPage() {
		pageList.clear();
	}
	
	/**
	 * 
	 * @return 	ArrayList of the given note's pages.
	 */
	public ArrayList<Page> getNotesPages() {
		return pageList;
	}


	/**
	 * @return	note's id.
	 */
	public int getId() {
		return Id;
	}

	/**
	 * Sets a note's id.
	 * @param 	id	sets the id to the given value.
	 */
	public void setId(int id) {
		this.Id = id;
	}
	

	
}
