import java.util.EnumSet;

public class MenuSelection implements MenuElement {

    public int selectedIndex = -1;
    public int hoverIndex = -1;
    private Object[] options;

    // a couple type warnings but it's fine :)))
    public <E extends Enum<E>> MenuSelection(Class<E> enumClass) {
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException("Parameter class must be of an enum");
        }
        Object[] options = EnumSet.allOf(enumClass).toArray();
		if (options == null || options.length < 1) {
			throw new IllegalArgumentException("Enum must have at least one option");
		}
		this.options = options;
    }

	// MenuElement interface required methods
	public boolean canHover() { return true; }
    public void clearHover() { this.hoverIndex = -1; }
	
	public void updateWithInput(MenuInput input) {
        this.hoverIndex = clamp(this.hoverIndex + input.x, 0, this.options.length-1);
        if (input.select) {
            this.selectedIndex = this.hoverIndex;
        }
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

    // for finding the right selection element in textmenu
    public <E extends Enum<E>> boolean matchesEnumClass(Class<E> other) {
        return this.options[0].getClass().equals(other);
    }

    // for reading the selected enum's name
    public <E extends Enum<E>> E getResult(Class<E> enumClass) {
        try {
            return Enum.valueOf(enumClass, options[selectedIndex].toString());
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean isCompleted() {
        return selectedIndex != -1;
    }

	// clamps value between a minimum and maximum value
	private static int clamp(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}
}
