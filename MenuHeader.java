public class MenuHeader implements MenuElement {
    public String text;
    public MenuHeader(String text) {
        this.text = text;
    }
    public String getAsString() {
        return this.text;
    }

    // MenuElement required methods
    public boolean canHover() { return false; }
    public void updateWithInput(MenuInput input) {}
    public void clearHover() {}
}
