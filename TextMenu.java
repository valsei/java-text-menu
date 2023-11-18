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
            hoverableElements.get(hoverRow).clearHover();
            this.hoverRow = clamp(this.hoverRow + input.y, 0, hoverableElements.size() - 1);
        }
        hoverableElements.get(hoverRow).updateWithInput(input);
	}
	
    public ArrayList<String> toListOfStrings() {
		ArrayList<String> list = new ArrayList<>();
        for (MenuElement element : elements) {
        	list.add(element.getAsString());
        }
		return list;
    }

    public <T extends Enum<T>> getSelection(Class<T> enumClass) {
        String enumName = "";
        for (MenuElement sel : hoverableElements) {
            if (sel instanceof MenuSelection &&
                ((MenuSelection)sel).matchesEnumClass(enumClass)) {
                enumName = ((MenuSelection)sel).getSelectionString();
            }
        }
        try {
            return Enum.valueOf(enumClass, enumName);
        } catch (Exception IllegalArgumentException) {
            return null;
        }
    }

	// clamps value between a minimum and maximum value
	private static int clamp(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}
}