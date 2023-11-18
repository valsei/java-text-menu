import java.util.*;

public class TextMenu {

    private ArrayList<MenuElement> elements = new ArrayList<>();
    private int hoverRow = 0;

    public TextMenu() {}
    public TextMenu(ArrayList<MenuElement> elements) {
        this.elements = elements;
    }

    public TextMenu add(MenuElement element) {
        this.elements.add(element);
        return this;
    }

	public void updateWithInput(int x, int y, boolean select) {
		if (y != 0) {
			elements.get(hoverRow).clearHover();
			this.hoverRow = clamp(this.hoverRow + y, 0, elements.size()-1);
			elements.get(hoverRow).startHover();
		}
		if (x != 0) {
			elements.get(hoverRow).hover(x);
		}
		if (select) {
			elements.get(hoverRow).select();
		}
	}
	
    public ArrayList<String> toListOfStrings() {
		ArrayList<String> list = new ArrayList<>();
        for (MenuElement element : elements) {
        	list.add(element.getAsString());
        }
		return list;
    }

	public Enum getResult(Class enumType) {
		for (MenuElement element : elements) {
			if (element.getType() == enumType) {
				return element.getResult();
			}
		}
		return null;
	}

	// clamps value between a minimum and maximum value
	private static int clamp(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}
}