public class MenuSlider implements MenuElement {

    private double value min, max, scale;

    public MenuHeader(double defaultValue, double min, double max, double scale) {
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.scale = scale;
    }

    public String getAsString() {
        String asString = this.value + " [";
        double segmentSize = (this.max-this.min)/this.scale;//needs to be fixed
        double valueSegment = this.value/(this.max-this.min);
        for (int i = 0; i < this.scale; i++) {
            asString += i <= valueSegment ? "/" : "-";
        }
        return asString + "]";
    }

    // MenuElement required methods
    public boolean canHover() { return true; }

    public void updateWithInput(MenuInput input) {
        double segmentSize = (this.max-this.min)/this.scale;
        double nextValue = value + segmentSize * input.x;
        this.value = clamp(nextValue, this.min, this.max)
    }
    
    public void clearHover() {}

	// clamps value between a minimum and maximum value
	private static double clamp(double value, double min, double max) {
		return Math.max(min, Math.min(max, value));
	}
}
