package GUIRelated;

import javax.swing.JTextPane;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;


public class CustomEditorKit{
	
    private StyleSheet styleSheet;
    
	public CustomEditorKit() {
		
	}
	
	public void init(JTextPane textPane) {
		CustomHTMLEditorKit editorKit = new CustomHTMLEditorKit();
		styleSheet = new StyleSheet();
        
       
		styleSheet.addRule("body {font-family:\"Dialog\"; color:#ffffff; font-size:12;}");
        editorKit.setDefaultStyleSheet(styleSheet);
        textPane.setEditorKit(editorKit);
        textPane.setDocument(editorKit.createDefaultDocument());
	}
	
	public StyleSheet getStyle() {
		return styleSheet;
	}	
	
	private class CustomHTMLEditorKit extends HTMLEditorKit {
	
	    /**
		 * Generated Serial Id
		 */
		private static final long serialVersionUID = -8631306629574593369L;
		
		private StyleSheet myStyle;
	
	    @Override
	    public StyleSheet getStyleSheet() {
	        return myStyle == null ? super.getStyleSheet() : myStyle;
	    }
	
	    @Override
	    public void setStyleSheet(StyleSheet s) {
	        this.myStyle = s;
	    }
	
	    public void setDefaultStyleSheet(StyleSheet s) {
	        super.setStyleSheet(s);
	    }
	}

	 
}
