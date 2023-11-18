import java.util.*;

public class TextMenu {

    private ArrayList<MenuElement> elements = new ArrayList<>();
    private ArrayList<MenuElement> hoverableElements = new ArrayList<>();
    private int hoverRow = 0;

    public TextMenu() {}
    public TextMenu(ArrayList<MenuElement> elements) {
        this.elements = elements;
    }

    public TextMenu add(MenuElement element) {
        this.elements.add(element);
        if (element.canHover()) {
            this.hoverableElements.add(element);
        }
        return this;
    }

	public void updateWithInput(MenuInput input) {
        if (input.y != 0) {
            elements.get(hoverRow).clearHover();
            this.hoverRow = clamp(this.hoverRow + input.y, 0, elements.size() - 1);


        }
		/*if (y != 0) {
			elements.get(hoverRow).clearHover();
			this.hoverRow = clamp(this.hoverRow + y, 0, elements.size()-1);
			elements.get(hoverRow).startHover();
		}
		if (x != 0) {
			elements.get(hoverRow).hover(x);
		}
		if (select) {
			elements.get(hoverRow).select();
		}*/
	}
	
    public ArrayList<String> toListOfStrings() {
		ArrayList<String> list = new ArrayList<>();
        for (MenuElement element : elements) {
        	list.add(element.getAsString());
        }
		return list;
    }

	// clamps value between a minimum and maximum value
	private static int clamp(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}
}