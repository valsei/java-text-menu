import java.util.ArrayList;
import java.util.NoSuchElementException;

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
        this.updateWithInput(new MenuInput());
        return this;
    }
    public <E extends Enum<E>> TextMenu add(Class<E> enumClass) {
        return this.add(new MenuSelection(enumClass));
    }
    public TextMenu add(String text) {
        return this.add(new MenuHeader(text));
    }
    public TextMenu add() {
        return this.add("");
    }

	public void updateWithInput(MenuInput input) {
        if (input.y != 0) {
            this.hoverableElements.get(this.hoverRow).clearHover();
            this.hoverRow = clamp(this.hoverRow + input.y, 0, this.hoverableElements.size() - 1);
        }
        if (!this.hoverableElements.isEmpty()) {
            this.hoverableElements.get(this.hoverRow).updateWithInput(input);
        }
	}
	
    public ArrayList<String> toListOfStrings() {
		ArrayList<String> list = new ArrayList<>();
        for (MenuElement element : this.elements) {
        	list.add(element.getAsString());
        }
		return list;
    }

    public <E extends Enum<E>> E getSelectionResult(Class<E> enumClass) {
        for (MenuElement sel : this.hoverableElements) {
            if (sel instanceof MenuSelection &&
                    ((MenuSelection)sel).matchesEnumClass(enumClass)) {
                E selectedOption = ((MenuSelection)sel).getResult(enumClass);
                if (selectedOption == null) {
                    throw new NoSuchElementException("SelectionElement "+enumClass.toString()+" does not have a chosen option");
                }
                return selectedOption;
            }
        }
        throw new NoSuchElementException("Could not find a selection element with enum "+enumClass.toString());
    }

    public boolean isCompleted() {
        for (MenuElement sel : this.hoverableElements) {
            if (sel instanceof MenuSelection && !((MenuSelection)sel).isCompleted()) {
                return false;
            }
        }
        return true;
    }

	// clamps value between a minimum and maximum value
	private static int clamp(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}
}