public class Selection<T> {

    public int selectedIndex = -1;
    public int hoverIndex = -1;
    private T[] options;

    public Selection(T[] options) {
        this.options = options;
    }

    public int optionCount() {
        return options.length;
    }

    private static final String[] borders = {"  x "," >x "," [x]",">[x]"};
    public String toString() {
        String asString = "";
        for (int i = 0; i < options.length; i++) {
            int borderValue = (i == hoverIndex ? 1 : 0) +
                              (i == selectedIndex ? 2 : 0);
            asString += borders[borderValue].replace("x", options[i].toString());
        }
        return asString;
    }
}
