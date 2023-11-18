public interface MenuElement {
	// for rendering each element in the whole menu
	public String getAsString();

	// if user can select from this element or not
	public boolean canHover();

    // allow it to take input (if it can be hovered on)
    public void updateWithInput(MenuInput input);
    public void clearHover();
}