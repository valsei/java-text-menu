package net.valsei.java_text_menu;

/**
 * A type of menu element that displays text.
 */
public class MenuHeader implements MenuElement {

    public String text;

    public MenuHeader(String text) {
        this.text = text;
    }
    
    // MenuElement interface required methods

    public String getAsString() {
        return this.text;
    }
}