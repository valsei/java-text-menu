public class MenuSlider implements MenuElement {

	private double value, min, max, scale;
	private boolean isHovered;
	private String name;

	public MenuSlider(String name, double defaultValue, double min, double max, double scale) {
		this.value = defaultValue;
		this.min = min;
		this.max = max;
		this.scale = scale;
	}
	public MenuSlider(String name, double defaultValue, double min, double max) {
		this(name, defaultValue, min, max, 1.0);
	}
	public MenuSlider(String name, double min, double max) {
		this(name, min, min, max);
	}

	public String getAsString() {
		String asString = isHovered ? ">[" : " [";
		double sliderLength = (this.max - this.min) * this.scale;
		for (int i = 0; i < Math.round(sliderLength); i++) {
			asString += i < (this.value - this.min) * this.scale ? "/" : "-";
		}
		return asString + "] " + (Math.round(this.value * 10.0) / 10.0);
	}

	public boolean matchesName(String other) {
		return this.name.equals(other);
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
