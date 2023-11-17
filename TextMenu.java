import java.util.*;

public class TextMenu {

    private ArrayList<Selection> selections = new ArrayList<>();
    private int hoverRow = 0, hoverCol = 0;

    public TextMenu() {}
    public TextMenu(ArrayList<Selection> selections) {
        this.selections = selections;
    }

    public TextMenu add(Selection selection) {
        this.selections.add(selection);
        return this;
    }
    
    public void moveHoverTemp(String input) {
        selections.get(hoverRow).hoverIndex = -1;
        if (input.matches("[ws]")) {
            hoverRow =
                clamp(hoverRow + (input.equals("s") ? 1 : -1),
                0, selections.size() - 1);
            hoverCol = 0;

        } else if (input.matches("[ad]")) {
            hoverCol =
                clamp(hoverCol + (input.equals("d") ? 1 : -1),
                0, selections.get(hoverRow).optionCount() - 1);
        } else if (input.matches("c")) {
            selections.get(hoverRow).selectedIndex = hoverCol;
        }
        selections.get(hoverRow).hoverIndex = hoverCol;
    }

    // temporary print method that uses console instead
    public void printToTelemetry() {
        for (Selection selection : selections) {
            System.out.println(selection.toString());
        }
    }
    /*
    public void printToTelemetry(Telemetry telemetry) {
        for (Selection selection : selections) {
            telemetry.addLine(selection.toString());
        }
        telemetry.update();
    }
    */

    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}