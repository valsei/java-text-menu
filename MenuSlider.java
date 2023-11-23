/**
 * A type of menu element for choosing a value inside a range.
 */
public class MenuSlider implements MenuElement {

	private double value, min, max, scale;
	private boolean isHovered;
	private String name;

	/**
	 * creates a new slider with an internal name.
	 * @param name for indentifying this slider later, is otherwise hidden
	 * @param min the minimum value allowed
	 * @param max the maximum value allowed
	 * @param scale scales the units per character when displayed
	 * @param defaultValue the starting value, instead of using minimum
	 */
	public MenuSlider(String name, double min, double max, double scale, double defaultValue) {
		this.min = min;
		this.max = max;
		this.scale = scale;
		this.value = defaultValue;
	}
	/**
	 * creates a new slider with an internal name.
	 * @param name for indentifying this slider later, is otherwise hidden
	 * @param min the minimum value allowed
	 * @param max the maximum value allowed
	 * @param scale scales the units per character when displayed
	 */
	public MenuSlider(String name, double min, double max, double scale) {
		this(name, min, max, scale, min);
	}
	/**
	 * creates a new slider with an internal name.
	 * @param name for indentifying this slider later, is otherwise hidden
	 * @param min the minimum value allowed
	 * @param max the maximum value allowed
	 */
	public MenuSlider(String name, double min, double max) {
		this(name, min, max, 1.0, min);
	}

	// render the slider and hover into a string to display
	public String getAsString() {
		String asString = isHovered ? ">[" : " [";
		double sliderLength = (this.max - this.min) * this.scale;
		for (int i = 0; i < Math.round(sliderLength); i++) {
			asString += i < (this.value - this.min) * this.scale ? "/" : "-";
		}
		return asString + "] " + (Math.round(this.value * 10.0) / 10.0);
	}

	// for finding the correct slider to read from
	public boolean matchesName(String other) {
		return this.name.equals(other);
	}

	// for reading the current slider value
	public double getResult() {
		return this.value;
	}

	// MenuElement required methods
	public boolean canHover() { return true; }

	public void updateWithInput(MenuInput input) {
		this.isHovered = true;
		double nextValue = this.value + input.x / this.scale;
		this.value = clamp(nextValue, this.min, this.max);
	}

	public void clearHover() { this.isHovered = false; }

	// clamps value between a minimum and maximum value
	private static double clamp(double value, double min, double max) {
		return Math.max(min, Math.min(max, value));
	}
}
