package Data;

import javax.swing.text.StyledDocument;

public class Page{
	/**
	 * A page's name.
	 */
	private String pageName;
	
	/**
	 * A page's text content.
	 */
	private String pageText;
	
	/**
	 * The note's id which contains this page.
	 */
	private int noteId;
	
	/**
	 * A static counter. Default value 0.
	 */
	private static int counter = 0;
	
	/**
	 * An integer type id.
	 */
	private int Id;
	private StyledDocument pageTextStyle;
	
	/**
	 * A page object's constructor. The static counter gets increased
	 * every time a note object is created. A page object's id is set to
	 * the current value of the counter integer.
	 * 
	 * @param 	pageName	sets the page's name.
	 * @param 	text		the page's content.
	 * @param	noteid		the id of the note that contains this page.
	
	 */
	public Page(String pageName, String text, int noteid) {
		this.pageName = pageName + " " + counter;
		this.pageText = text;
		this.noteId = noteid;
		setId(counter);
		counter++;
	}
	
	/**
	 * @return	the page's name.
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * @param 	pageName	set the page's name to this string value.
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
	/**
	 * @return	the page's text content.	
	 */
	public String getPageText() {
		return pageText;
	}
	
	/**
	 * @param 	text	set the page's text content to the given string value.
	 */
	public void setPageText(String text) {
		this.pageText = text;
	}
	
	/**
	 * @return	the note's id which contains the page.
	 */
	public int getNoteId() {
		return noteId;
	}

	/**
	 * @return	the page's id.
	 */
	public int getId() {
		return Id;
	}
	
	/**
	 * @param 	id	set the page's id to the given integer value.
	 */
	public void setId(int id) {
		this.Id = id;
	}

	public StyledDocument getPageTextStyle() {
		return pageTextStyle;
	}

	public void setPageTextStyle(StyledDocument pageTextStyle) {
		this.pageTextStyle = pageTextStyle;
	}
	
	
}
