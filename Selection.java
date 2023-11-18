public class Selection<T> implements MenuElement {

    public int selectedIndex = -1;
    public int hoverIndex = -1;
    private T[] options;

    public Selection(T[] options) {
		if (options == null || options.length < 1) {
			throw new IllegalArgumentException("Selection must have at least one option");
		}
		this.options = options;
    }

	public Class getType() {
		return options.getClass();
	}

	// MenuElement interface required methods
	public boolean canHover() { return true; }
	
	public void hover(int change) {
		this.hoverIndex = clamp(this.hoverIndex + change, 0, this.options.length-1);
	}
	
	public void select() { this.selectedIndex = this.hoverIndex; }
	public void startHover() { this.hoverIndex = 0; }
	public void clearHover() { this.hoverIndex = -1; }
	public void clearSelect() { this.selectedIndex = -1; }

	public Enum getResult() {
		return (Enum)(selectedIndex != -1 ? options[selectedIndex] : null);
	}

	// render the selection and hover into a string to display
    private static final String[] borders = {"  x "," >x "," [x]",">[x]"};
    public String getAsString() {
        String asString = "";
        for (int i = 0; i < options.length; i++) {
            int borderValue = (i == hoverIndex ? 1 : 0) +
                              (i == selectedIndex ? 2 : 0);
            asString += borders[borderValue].replace("x", options[i].toString());
        }
        return asString;
    }

	// clamps value between a minimum and maximum value
	private static int clamp(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}
}
