import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A highly advanced, very cool and professionally coded
 * text-based menu predominantly aimed at
 * choosing between enums via a controller.
 * <p>
 * By valsei!! [https://github.com/valsei/java-text-menu]
 * <p>
 * Intended usage is to remove the clutter of 8+ different
 * autonomous programs to choose between. Now just choose one!
 * <p>
 * How to use: Create an autonomous framework method that makes decisions
 * based on enums. Then, give those enums to this text menu
 * and use it at the beginning of a single autonomous class.
 * Once the menu is complete, run the framework method but replace
 * any enum calls with menu.getSelectionResult(<enumClass>) calls.
 */
public class TextMenu {

    // separate lists so that switching between hoverable is easier to deal with
    private ArrayList<MenuElement> elements = new ArrayList<>();
    private ArrayList<MenuElement> hoverableElements = new ArrayList<>();
    private int hoverRow = 0;

    /**
     * creates an empty TextMenu. use TextMenu.add(...) to add elements.
     */
    public TextMenu() {}
    public TextMenu(ArrayList<MenuElement> elements) {
        this.elements = elements;
    }

    /**
     * adds a MenuElement to the end of the menu.
     * @param element any MenuElement implementing object (ex. MenuHeader)
     * @return returns itself so you can chain .add() methods
     */
    public TextMenu add(MenuElement element) {
        this.elements.add(element);
        if (element.canHover()) {
            this.hoverableElements.add(element);
        }
        this.updateWithInput(new MenuInput());
        return this;
    }
    /**
     * adds an enum selector section to the end of the menu.
     * @param <E> requires that the class type is of an enum
     * @param enumClass the class of the enum (do myEnum.class)
     * @return returns itself so you can chain .add() methods
     */
    public <E extends Enum<E>> TextMenu add(Class<E> enumClass) {
        return this.add(new MenuSelection(enumClass));
    }
    /**
     * adds a text section to the end of the menu. does not wrap.
     * @param text any string of text
     * @return returns itself so you can chain .add() methods
     */
    public TextMenu add(String text) {
        return this.add(new MenuHeader(text));
    }
    /**
     * adds an empty line for spacing to the end of the menu.
     * @return returns itself so you can chain .add() methods
     */
    public TextMenu add() {
        return this.add("");
    }

	/**
     * passes input into the menu for navigation and selecting.
	 * @param input uses a MenuInput object as an inbetween
	 */
	public void updateWithInput(MenuInput input) {
        if (!this.hoverableElements.isEmpty()) {
            // update hover row from y input
            if (input.y != 0) {
                this.hoverableElements.get(this.hoverRow).clearHover();
                this.hoverRow = clamp(this.hoverRow - input.y, 0, this.hoverableElements.size() - 1);
            }
            // pass input into the hovered element
            this.hoverableElements.get(this.hoverRow).updateWithInput(input);
        }
	}
	
    /**
     * renders the menu in its current state into a list of strings.
     * should then be printed/logged using external methods.
     * @return list of strings representing the menu elements
     */
    public ArrayList<String> toListOfStrings() {
		ArrayList<String> list = new ArrayList<>();
        for (MenuElement element : this.elements) {
        	list.add(element.getAsString());
        }
		return list;
    }

    /**
     * gets the enum chosen from a selection element in the menu.
     * @param <E> requires that the class type is of an enum
     * @param enumClass the class of the enum (do myEnum.class)
     * @return the resulting enum that was chosen in its selection element
     * @throws NoSuchElementException when there is no selection element of that enum or chosen option
     */
    public <E extends Enum<E>> E getSelectionResult(Class<E> enumClass) {
        for (MenuElement sel : this.hoverableElements) {
            // check if the element is the target selection element
            if (sel instanceof MenuSelection &&
                    ((MenuSelection)sel).matchesEnumClass(enumClass)) {
                // get result from selection object
                E selectedOption = ((MenuSelection)sel).getResult(enumClass);
                if (selectedOption == null) {
                    throw new NoSuchElementException("SelectionElement "+enumClass.toString()+" does not have a chosen option");
                }
                return selectedOption;
            }
        }
        throw new NoSuchElementException("Could not find a selection element with enum "+enumClass.toString());
    }
    //public double getSliderResult()

    /**
     * checks if all the applicable menu elements have been filled out.
     * @return boolean of if the menu is completed
     */
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