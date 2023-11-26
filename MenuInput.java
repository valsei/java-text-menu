public class MenuInput {

    private int x, y;
    private boolean select;

    public MenuInput() {
        update(0, 0, false);
    }
    public MenuInput(int x, int y, boolean select) {
        update(x, y, select);
    }

    public MenuInput update(int x, int y, boolean select) {
        this.x = x;
        this.y = y;
        this.select = select;
        return this;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public boolean getSelect() {
        return this.select;
    }
}