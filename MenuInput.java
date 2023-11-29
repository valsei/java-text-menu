import java.util.concurrent.TimeUnit;

/**
 * A class for bridging input methods to a text menu.
 * <p>
 * This is made with the intention of supporting controllers!
 * That means that when passing input, one should set the input
 * type to {@code MenuInputType.CONTROLLER} either in the constructor
 * or with the method {@code .setInputType(inputType)}. The user can
 * then pass raw input into the {@code .update(...)} method and it
 * will be accordingly processed.
 */
public class MenuInput {

    // determines the type of input processing
    // RAW is good for standalone console debugging via keyboard
    private MenuInputType inputType;
    public enum MenuInputType {
        CONTROLLER,
        RAW,
    }

    // a constant for stick deadzone
    public final double INPUT_DEADZONE = 0.05;

    // processed values
    private int x, y;
    private boolean select;
    
    // input timers to stop input spamming (in seconds)
    private double xTimer = 0.0, yTimer = 0.0;

    private final double stickTapCooldown = 0.5;
    private final double stickHoldCooldown = 0.2;
    // switches on when past tap cooldown, then using hold cooldown
    private boolean isHoldingX = false, isHoldingY = false;

    // so it only registers once per held press
    private boolean hasAlreadySelected = false;
    private double selectTimer = 0.0;
    
    // time passed between update calls to update timers with
    private double deltaTime = 0.0;
    private Long lastTime = null;

    /**
     * creates a new input processing object.
     * @param inputType determines the type of input processing
     * @param x starting x input value
     * @param y starting y input value
     * @param select starting select input value
     */
    public MenuInput(MenuInputType inputType, double x, double y, boolean select) {
        this.inputType = inputType;
        update(x, y, select);
    }
    /**
     * creates a new input processing object.
     * @param inputType determines the type of input processing
     */
    public MenuInput(MenuInputType inputType) {
        this(inputType, 0, 0, false);
    }
    /**
     * creates a new input processing object.
     * likely need to follow with a {@code .setInputType(inputType)}.
     */
    public MenuInput() {
        this(MenuInputType.RAW, 0, 0, false);
    }

    /**
     * changes the current input type.
     * @param inputType an input type mode
     */
    public void setInputType(MenuInputType inputType) {
        this.inputType = inputType;
    }

    /**
     * updates the input values. considers current input type.
     * @param x current raw x input
     * @param y current raw y input
     * @param select current raw select input
     * @return itself so you can use it immediately after updating it
     */
    public MenuInput update(double x, double y, boolean select) {
        updateDeltaTime();
        // reset input
        this.x = 0;
        this.y = 0;
        // get new x,y input; consider deadzone
        if (Math.hypot(x, y) > INPUT_DEADZONE) {
            // snap input vector to axis
            if (Math.abs(x) >= Math.abs(y)) {
                this.x = (int)Math.signum(x);
            } else {
                this.y = (int)Math.signum(y);
            }
        }

        // if the input type is a controller, consider input cooldowns
        if (this.inputType == MenuInputType.CONTROLLER) {
            

        }
        
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

    // updates the deltatime in seconds
    private void updateDeltaTime() {
        if (this.lastTime != null) {
            this.deltaTime = (double)(System.nanoTime() - this.lastTime) / 1_000_000_000.0;
        }
        this.lastTime = System.nanoTime();
    }    
}