public class MenuSlider implements MenuElement {

    public int value;
    private int min, max, scale;

    public MenuHeader(int defaultValue, int min, int max, int scale) {
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.scale = scale;
    }

    public String getAsString() {
        String asString = this.value + " [";
        for (int i = 0; i < (this.max-this.min))
        return asString;
        // 15 [/////-----------]
    }

    // MenuElement required methods
    public boolean canHover() { return true; }

    public void updateWithInput(MenuInput input) {

    }
    
    public void clearHover() {}

	// clamps value between a minimum and maximum value
	private static int clamp(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}
}
