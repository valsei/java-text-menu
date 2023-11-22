public class MenuSlider implements MenuElement{

	private double value, min, max, scale;

	public MenuSlider(double defaultValue, double min, double max, double scale) {
		this.value = defaultValue;
		this.min = min;
		this.max = max;
		this.scale = scale;
	}

	public String getAsString() {
		String asString = "[/";
		double sliderLength = (this.max - this.min) * this.scale;
		for (int i = 0; i < Math.round(sliderLength); i++) {
			asString += i < (this.value - this.min) * this.scale ? "/" : "-";
		}
		return asString + "] " + (Math.round(this.value * 10.0) / 10.0);
	}

	// MenuElement required methods
	public boolean canHover() { return true; }

	public void updateWithInput(MenuInput input) {
		double nextValue = this.value + input.x / this.scale;
		this.value = clamp(nextValue, this.min, this.max);
	}

	public void clearHover() {}

	// clamps value between a minimum and maximum value
	private static double clamp(double value, double min, double max) {
		return Math.max(min, Math.min(max, value));
	}
}
