public interface MenuElement {

	// for rendering each element in the whole menu
	public String getAsString();

	// if user can select from this element or not
	public boolean canHover();

	// for chosing from options
	public void hover(int change);
	public void startHover();
	public void clearHover();
	public void select();
	public void clearSelect();

	public Class getType();
	public Enum getResult();
}