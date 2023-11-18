public class MenuInput {
    public int x, y;
    public boolean select;
    public MenuInput() {
        update(0, 0, false);
    }
    public MenuInput(int x, int y, boolean select) {
        update(x, y, select);
    }
    public void update(int x, int y, boolean select) {
        this.x = x;
        this.y = y;
        this.select = select;
    }
}
